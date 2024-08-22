package cmp.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;



public class chatManager { // 클래스 이름 수정

    private DBConnectionMgr pool;

    public chatManager() {
        pool = DBConnectionMgr.getInstance();
    }

    public Vector<EmployeeBean> selectEmployee(String id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vector<EmployeeBean> vlist = new Vector<EmployeeBean>();
        String sql = null;
        
        try {
            con = pool.getConnection();
            // SQL 쿼리에 WHERE 절을 추가하여 id와 일치하지 않는 데이터만 선택합니다.
            sql = "SELECT em_name, em_position, em_department, em_id FROM employee WHERE em_id != ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id); 

            rs = pstmt.executeQuery();
            while (rs.next()) {
                EmployeeBean bean = new EmployeeBean();
                bean.setName(rs.getString("em_name"));
                bean.setPosition(rs.getString("em_position"));
                bean.setDepartment(rs.getString("em_department"));
                bean.setId(rs.getString("em_id"));
                
                vlist.addElement(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        
        return vlist;
    }
   
    public boolean chatCheck(String my_id, String your_id) {
        boolean flag = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        
        try {
            con = pool.getConnection();
            sql = "SELECT chat_list.chat_no " +
                  "FROM chat_list " +
                  "JOIN chat_user_list cul1 ON chat_list.chat_no = cul1.chat_no " +
                  "JOIN chat_user_list cul2 ON chat_list.chat_no = cul2.chat_no " +
                  "WHERE cul1.user_id = ? AND cul2.user_id = ? " +
                  "AND chat_list.chatroom_name IS NULL";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, my_id);
            pstmt.setString(2, your_id);

            rs = pstmt.executeQuery();
            
            // If the result set has at least one record, set flag to true
            if (rs.next()) {
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        
        return flag;
    }


    public boolean createPrivateChat(String userId1, String userId2) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean flag = false;
        long chatNo;

        try {
            con = pool.getConnection();
            con.setAutoCommit(false); // 트랜잭션 시작

            // Step 1: 새 채팅방 생성
            String insertChatSql = "INSERT INTO chat_list (chatroom_name) VALUES (NULL)";
            pstmt = con.prepareStatement(insertChatSql, PreparedStatement.RETURN_GENERATED_KEYS);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 1) {
                // 채팅방이 성공적으로 생성되었으면, 생성된 채팅방의 ID를 가져옵니다
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    chatNo = rs.getLong(1);
                } else {
                    throw new Exception("Failed to retrieve chat room ID.");
                }
            } else {
                throw new Exception("Failed to create chat room.");
            }

            // Step 2: 생성된 채팅방에 두 사용자 추가
            String insertUsersSql = "INSERT INTO chat_user_list (chat_no, user_id) VALUES (?, ?), (?, ?)";
            pstmt.close(); // 이전 PreparedStatement 닫기
            pstmt = con.prepareStatement(insertUsersSql);
            pstmt.setLong(1, chatNo);
            pstmt.setString(2, userId1);
            pstmt.setLong(3, chatNo);
            pstmt.setString(4, userId2);

            int userAffectedRows = pstmt.executeUpdate();
            if (userAffectedRows == 2) {
                con.commit(); // 트랜잭션 커밋
                flag = true;
            } else {
                con.rollback(); // 사용자 삽입 실패 시 롤백
                throw new Exception("Failed to insert users into chat room.");
            }
            
            System.out.println(chatNo+" : "+userId1+" : "+userId2);

        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback(); // 트랜잭션 롤백
                }
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace(); // 스택 트레이스 출력

        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
                if (con != null) con.setAutoCommit(true); // 트랜잭션 종료
                pool.freeConnection(con);
            } catch (Exception finalEx) {
                finalEx.printStackTrace();
            }
        }
        return flag;
    }


    public Vector<chatListBean> selectChat(String id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vector<chatListBean> vlist = new Vector<chatListBean>();
        String sql = null;

        try {
            con = pool.getConnection();
            // SQL 쿼리에 DISTINCT를 사용하여 중복된 채팅방을 제거합니다.
            sql = "SELECT DISTINCT chat_list.chat_no, chat_list.chatroom_name "
                    + "FROM chat_list chat_list "
                    + "JOIN chat_user_list cul ON chat_list.chat_no = cul.chat_no "
                    + "WHERE cul.user_id = ? "
                    + "AND chat_list.chatroom_name IS NOT NULL;";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                chatListBean bean = new chatListBean();
                bean.setChat_no(rs.getInt("chat_no"));
                bean.setChatroom_name(rs.getString("chatroom_name"));
                
                vlist.addElement(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }

        return vlist;
    }

    
    public boolean createChat(String my_id, String chatroom_name,Vector<String> vlist) {
    	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean flag = false;
        vlist.add(my_id);

        try {
            con = pool.getConnection();
            con.setAutoCommit(false); // 트랜잭션 시작

            // Step 1: 새 채팅방 생성
            String insertChatSql = "INSERT INTO chat_list (chatroom_name) VALUES (?)";
            pstmt = con.prepareStatement(insertChatSql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, chatroom_name);
            int affectedRows = pstmt.executeUpdate();
            

            if (affectedRows == 1) {
                // 채팅방이 성공적으로 생성되었으면, 생성된 채팅방의 ID를 가져옵니다
            	
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    long chatNo = rs.getLong(1);
                    for (int i = 0; i < vlist.size(); i++) {
						
                    	// Step 2: 생성된 채팅방에 두 사용자 추가
                        String insertUsersSql = "INSERT INTO chat_user_list (chat_no, user_id) VALUES (?, ?)";
                        pstmt = con.prepareStatement(insertUsersSql);
                        pstmt.setLong(1, chatNo);
                        pstmt.setString(2, vlist.get(i));
                       
                        int userAffectedRows = pstmt.executeUpdate();
                        if (userAffectedRows == 1) {
                            con.commit(); // 트랜잭션 커밋
                            flag = true;
                        } else {
                            con.rollback(); // 사용자 삽입 실패 시 롤백
                        }
                    	
					}
                    
                }
            } else {
                con.rollback(); // 채팅방 생성 실패 시 롤백
            }
        } catch (Exception e) {
            e.printStackTrace(); // 스택 트레이스 출력
            
        } finally {
        	 pool.freeConnection(con, pstmt, rs);

        
    }
        return flag;
   
    }  
    
    public Vector<chatListBean> selectPrivateChat(String my_id, String your_id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vector<chatListBean> vlist = new Vector<chatListBean>();
        String sql = null;
        
        try {
            con = pool.getConnection();
            
            sql = "SELECT chat_list.chat_no " +
                    "FROM chat_list " +
                    "JOIN chat_user_list cul1 ON chat_list.chat_no = cul1.chat_no " +
                    "JOIN chat_user_list cul2 ON chat_list.chat_no = cul2.chat_no " +
                    "WHERE cul1.user_id = ? AND cul2.user_id = ? " +
                    "AND chat_list.chatroom_name IS NULL";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, my_id);
            pstmt.setString(2, your_id);

             

            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	chatListBean bean = new chatListBean();
                bean.setChat_no(rs.getInt(1)); 
                              
                vlist.addElement(bean);
                System.out.println(bean.getChat_no());
            }
            
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        
        return vlist;
    }

    public Vector<EmployeeBean> myName(String id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vector<EmployeeBean> vlist = new Vector<EmployeeBean>();
        String sql = null;
        
        try {
            con = pool.getConnection();
            // SQL 쿼리에 WHERE 절을 추가하여 id와 일치하지 않는 데이터만 선택합니다.
            sql = "SELECT em_name, em_position, em_department, em_id FROM employee WHERE em_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id); 

            rs = pstmt.executeQuery();
            while (rs.next()) {
                EmployeeBean bean = new EmployeeBean();
                bean.setName(rs.getString("em_name"));
                bean.setPosition(rs.getString("em_position"));
                bean.setDepartment(rs.getString("em_department"));
                bean.setId(rs.getString("em_id"));
                
                vlist.addElement(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        
        return vlist;
    }
    	
    public boolean insertchatContents(chatContentsBean bean) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO chat_contents (chat_date, chat_contents, user_id, chat_no) VALUES (?, ?, ?, ?)";
        boolean flag = false;

        try {
            // 커넥션 획득
            con = pool.getConnection();
            con.setAutoCommit(false);  // 트랜잭션 시작

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bean.getChat_date());
            pstmt.setString(2, bean.getChat_contents());
            pstmt.setString(3, bean.getUser_id());
            pstmt.setInt(4, bean.getChat_no());

            int cnt = pstmt.executeUpdate();
            if (cnt == 1) {
                con.commit();  // 성공 시 커밋
                flag = true;
            } else {
                con.rollback();  // 실패 시 롤백
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();  // 예외 발생 시 롤백
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            // 자원 해제
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null) {
                    con.setAutoCommit(true);  // 트랜잭션 모드 복원
                    pool.freeConnection(con);  // 커넥션 반환
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return flag;
    }



    public Vector<chatContentsBean> selectChatContents(int chat_no) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vector<chatContentsBean> vlist = new Vector<chatContentsBean>();
        String sql = null;
        
        try {
            con = pool.getConnection();
            // SQL 쿼리에 WHERE 절을 추가하여 id와 일치하지 않는 데이터만 선택합니다.
            sql = "SELECT chat_contents, user_id FROM chat_contents WHERE chat_no = ? ORDER BY chat_date ASC;";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, chat_no); 

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	chatContentsBean bean = new chatContentsBean();
                bean.setChat_contents(rs.getString(1));
                bean.setUser_id(rs.getString(2));

                
                vlist.addElement(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        
        return vlist;
    }
    
}