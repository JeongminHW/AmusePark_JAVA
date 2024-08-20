package DB;

import java.sql.*;
import java.util.*;

import DB.DBConnectionMgr;

public class DBMgr {
	private DBConnectionMgr pool;

	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	public DBMgr() {
		try {
			pool = DBConnectionMgr.getInstance();
			System.out.println("연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 직원 로그인
	public boolean LoginCheckEmployee(String id, String pw) {
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select em_pw from employee where em_id = ? and em_pw = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (pw.equals(rs.getString("em_pw"))) {
					flag = true;
				} else {
					flag = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // con는 반납, pstmt/rs는 close
		}

		return flag;
	}

	// 직원 회원가입
	public void SignUpEmployee(EmployeeBean bean) {
		try {
			con = pool.getConnection();
			sql = "insert into employee(em_id, em_pw, em_name, em_birthday, em_phone, em_position, em_department) values (?,?,?,?,?,?,?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPw());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getBirthday());
			pstmt.setString(5, bean.getPhone());
			pstmt.setString(6, bean.getPosition());
			pstmt.setString(7, bean.getDepartment());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // con는 반납, pstmt/rs는 close
		}
	}

	// 직원 회원가입 아이디 중복 확인
	public boolean IdCheckEmployee(String id) {
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select em_id from employee where em_id = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("em_id").equals(id)) {
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // con는 반납, pstmt/rs는 close
		}
		return flag;
	}

	// 관리자 여부 확인
	public boolean CheckManagerEmployee(String id) {
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select em_manage from employee where em_id =?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("em_manage").equals("관리자")) {
					flag = true;
				} else {
					flag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // con는 반납, pstmt/rs는 close
		}
		return flag;
	}/*
		 * 
		 * // 관리자 부여 public boolean ManagerEmployee(String id) { boolean flag = false;
		 * try { con = pool.getConnection(); sql =
		 * "update employee set em_manage = '관리자' where em_id = ?"; pstmt =
		 * con.prepareStatement(sql); pstmt.setString(1, id); pstmt.executeUpdate(); if
		 * (pstmt.executeUpdate() == 1) flag = true; } catch (Exception e) {
		 * e.printStackTrace(); } finally { pool.freeConnection(con, pstmt, rs); // con는
		 * 반납, pstmt/rs는 close } return flag; }
		 * 
		 * // 관리자 해제 public boolean NonManagerEmployee(String id) { boolean flag =
		 * false; try { con = pool.getConnection(); sql =
		 * "update employee set em_manage = '' where em_id = ?;"; pstmt =
		 * con.prepareStatement(sql); pstmt.setString(1, id); pstmt.executeUpdate(); if
		 * (pstmt.executeUpdate() == 1) { flag = true; } } catch (Exception e) {
		 * e.printStackTrace(); } finally { pool.freeConnection(con, pstmt, rs); // con는
		 * 반납, pstmt/rs는 close } return flag; }
		 */

	// 직원 직급에 따른 휴가 일수 부여
	public boolean VacationEmployee(EmployeeBean bean) {
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update employee set em_manage = ?, usable_vacation = ? where em_position = ?;";
			pstmt = con.prepareStatement(sql);
			if (bean.getPosition().equals("사원")) {
				pstmt.setString(1, null);
				pstmt.setInt(2, 15);
				pstmt.setString(3, bean.getPosition());
			} else if (bean.getPosition().equals("대리")) {
				pstmt.setString(1, null);
				pstmt.setInt(2, 18);
				pstmt.setString(3, bean.getPosition());
			} else if (bean.getPosition().equals("과장")) {
				pstmt.setString(1, null);
				pstmt.setInt(2, 20);
				pstmt.setString(3, bean.getPosition());
			} else if (bean.getPosition().equals("차장")) {
				pstmt.setString(1, null);
				pstmt.setInt(2, 23);
				pstmt.setString(3, bean.getPosition());
			} else if (bean.getPosition().equals("부장")) {
				pstmt.setString(1, "관리자");
				pstmt.setInt(2, 25);
				pstmt.setString(3, bean.getPosition());
			}
			if (pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // con는 반납, pstmt/rs는 close
		}
		return flag;
	}

	// 알바 로그인
	public boolean LoginCheckAlba(String id, String pw) {
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select alba_pw from alba where alba_id = ? and alba_pw = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (pw.equals(rs.getString("alba_pw"))) {
					flag = true;
				} else {
					flag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // con는 반납, pstmt/rs는 close
		}
		return flag;
	}

	// 알바 회원가입
	public void SignUpAlba(AlbaBean bean) {
		try {
			con = pool.getConnection();
			sql = "insert into alba(alba_id, alba_pw, alba_name, alba_birthday, alba_phone, parttime) values (?,?,?,?,?,?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getid());
			pstmt.setString(2, bean.getpw());
			pstmt.setString(3, bean.getname());
			pstmt.setString(4, bean.getbirthday());
			pstmt.setString(5, bean.getphone());
			pstmt.setString(6, bean.getPart_time());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // con는 반납, pstmt/rs는 close
		}
	}

	// 알바 회원가입 아이디 중복 확인
	public boolean IdCheckAlba(String id) {
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select alba_id from alba where alba_id = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("alba_id").equals(id)) {
					flag = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // con는 반납, pstmt/rs는 close
		}
		return flag;
	}

	// 알바 문의사항 작성
	public boolean insertinquire(InquireBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;

		try {
			con = pool.getConnection();
			sql = "INSERT INTO alba_inquire (inquire_id, inquire_title, inquire_contents) VALUES (?, ?, ?)"; // 테이블 이름 및
																												// 컬럼 명시
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getInquire_id());
			pstmt.setString(2, bean.getInquire_title());
			pstmt.setString(3, bean.getInquire_contents());

			int cnt = pstmt.executeUpdate();
			if (cnt == 1)
				flag = true;

		} catch (Exception e) {
			e.printStackTrace(); // 스택 트레이스 출력
		} finally {
			pool.freeConnection(con, pstmt); // 자원 해제
		}

		return flag;
	}

	// 알바 문의사항 리스트 불러오기
	public Vector<InquireBean> selectinquire() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<InquireBean> vlist = new Vector<InquireBean>();
		try {
			con = pool.getConnection();
			sql = "select * from alba_inquire";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString(5) == null) {
					InquireBean bean = new InquireBean();
					bean.setInquire_num(rs.getInt(1));
					bean.setInquire_id(rs.getString(2));
					bean.setInquire_title(rs.getString(3));
					bean.setInquire_contents(rs.getString(4));
					vlist.addElement(bean);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}

	// 알바 문의사항 답글 DB 업로드
	public boolean reviewInquire(InquireBean bean) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "UPDATE alba_inquire SET reply_contents=? WHERE inquire_num = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getReply_contents());
			pstmt.setInt(2, bean.getInquire_num());

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected == 1) {
				flag = true;
			} else {
				System.out.println("No rows updated. Check inquire_num and reply_contents.");
			}

			if (pstmt.executeUpdate() == 1)
				flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	// 문의사항 이름 확인
		public String myName(String id) {
			String name = null;

			try {
				con = pool.getConnection();
				sql = "select alba_name from alba where alba_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					name = rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return name;
		}

		// 문의사항 삭제
		public boolean deleteInquire(int num) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "delete from alba_inquire where inquire_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				if (pstmt.executeUpdate() == 1)
					flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flag;
		}

	// 직원 남은 휴가 일수 체크
	public Vector<EmployeeBean> TotalVacation(String id) {
		Vector<EmployeeBean> vlist = new Vector<EmployeeBean>();
		try {
			con = pool.getConnection();
			sql = "select usable_vacation from employee where em_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmployeeBean bean = new EmployeeBean();
				bean.setUsable_vacation(rs.getInt(1));
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // con는 반납, pstmt/rs는 close
		}
		return vlist;
	}

	// 직원 휴가 사용 시 남은 휴가 일수 업데이트
	

	// 투두리스트 가져오기
	public Vector<TodoBean> selectTodo(String id) {
		Vector<TodoBean> vlist = new Vector<TodoBean>();
		try {
			con = pool.getConnection();
			sql = "select * from todo where writer_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TodoBean bean = new TodoBean();
				bean.setNum(rs.getInt(1));
				bean.setTodo_contents(rs.getString(3));
				bean.setWriter_id(rs.getString(2));
				vlist.addElement(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}

	// 투두 리스트 작성
	public boolean insertTodo(TodoBean bean) {
		boolean flag = false;

		try {
			con = pool.getConnection();
			sql = "INSERT INTO todo (writer_id, todo_contents) VALUES (?, ?)"; // 테이블 이름 및 컬럼 명시
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getWriter_id());
			pstmt.setString(2, bean.getTodo_contents());

			int cnt = pstmt.executeUpdate();
			if (cnt == 1)
				flag = true;

		} catch (Exception e) {
			e.printStackTrace(); // 스택 트레이스 출력
		} finally {
			pool.freeConnection(con, pstmt); // 자원 해제
		}

		return flag;
	}

	// 투두리스트 내용 가져오기
	public Vector<String> getTodo(String id) {
		Vector<String> vlist = new Vector<String>();
		try {
			con = pool.getConnection();
			sql = "select distinct todo_contents from todo";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				vlist.addElement(rs.getString(1));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}

	// 투두리스트 삭제
	public boolean deleteTodo(int num) {
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "delete from todo where todo_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			if (pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

	// 투두리스트 내용변경
	public boolean changeTodo(TodoBean bean1, TodoBean bean2) {
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update todo set todo_contents=? where todo_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean2.getTodo_contents());
			pstmt.setInt(2, bean1.getNum());

			if (pstmt.executeUpdate() == 1)
				flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	// 휴가 리스트 불러오기
		public Vector<VacationBean> listVacation() {
			Vector<VacationBean> vlist = new Vector<VacationBean>();
			try {
				con = pool.getConnection();
				sql = "select a.em_name, b.vacation_start, b.vacation_end, b.vacation_reason from employee a, vacation b where b.request_id = a.em_id;";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					VacationBean bean = new VacationBean();
					bean.setName(rs.getString(1));
					bean.setStart(rs.getString(2));
					bean.setEnd(rs.getString(3));
					bean.setReason(rs.getString(4));
					vlist.addElement(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return vlist;
		}

		// 휴가 신청
		public void savaVacationEmployee(VacationBean bean) {
			try {
				con = pool.getConnection();
				String sql = "INSERT INTO vacation (request_id, vacation_start, vacation_end, vacation_reason) VALUES (?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getId());
				pstmt.setString(2, bean.getStart());
				pstmt.setString(3, bean.getEnd());
				pstmt.setString(4, bean.getReason());
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
		}

	// 직원 마이페이지 정보 불러오기
	public EmployeeBean listEmployee(String id) {
		EmployeeBean bean = new EmployeeBean();
		try {
			con = pool.getConnection();
			sql = "select em_name, em_phone, em_position from employee where em_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setName(rs.getString(1));
				bean.setPhone(rs.getString(2));
				bean.setPosition(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // con는 반납, pstmt/rs는 close
		}
		return bean;
	}

	// 직원 마이페이지 수정
	public boolean updateEmployee(EmployeeBean bean) {
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update employee set em_pw = ?, em_name = ?, em_phone = ?, em_position = ? where em_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getPw());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPhone());
			pstmt.setString(4, bean.getPosition());
			pstmt.setString(5, bean.getId());
			pstmt.executeUpdate();
			if (pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // con는 반납, pstmt/rs는 close
		}
		return flag;
	}

	// 알바 마이페이지 정보 불러오기
	public AlbaBean listAlba(String id) {
		AlbaBean bean = new AlbaBean();
		try {
			con = pool.getConnection();
			sql = "select alba_name, alba_phone, parttime from alba where alba_id = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setname(rs.getString(1));
				bean.setphone(rs.getString(2));
				bean.setPart_time(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // con는 반납, pstmt/rs는 close
		}
		return bean;
	}

	// 알바 파트타임 시간 불러오기
	public ParttimeBean infoParttime(String id) {
		ParttimeBean bean = new ParttimeBean();
		try {
			con = pool.getConnection();
			sql = "select start_time, end_time from alba, parttime where alba_id = ? and parttime.part_time = alba.parttime";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setStart_time(rs.getString(1));
				bean.setEnd_time(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // con는 반납, pstmt/rs는 close
		}
		return bean;
	}

	// 알바 마이페이지 수정
	public boolean updateAlba(AlbaBean bean) {
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update alba set alba_pw = ?, alba_name = ?, alba_phone = ?, parttime = ? where alba_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getpw());
			pstmt.setString(2, bean.getname());
			pstmt.setString(3, bean.getphone());
			pstmt.setString(4, bean.getPart_time());
			pstmt.setString(5, bean.getid());
			pstmt.executeUpdate();
			if (pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // con는 반납, pstmt/rs는 close
		}
		return flag;
	}
}
