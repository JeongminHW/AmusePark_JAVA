package cmp.GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;

import com.teamdev.jxbrowser.dom.event.MouseEvent;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.Vector;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Dimension;
import javax.annotation.processing.RoundEnvironment;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;

import cmp.DB.*;
import cmp.GUI.*;

public class EmployeeMain extends JFrame implements ActionListener {
	private static EmployeeMain instance;
	private static String id;

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		EmployeeMain.id = id;
	}
	
	public static EmployeeMain getInstance() {
        if (instance == null) {
            instance = new EmployeeMain();
        }
        return instance;
    }
	DBMgr db = new DBMgr();
	Vector<TodoBean> vlist;

	private static final long serialVersionUID = 1L;
	private final JPanel MainTest;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private Vector<EmployeeBean> emvlist;
	private Vector<EmployeeBean> em2vlist;
	private Vector<chatListBean> cvlist;
	private Vector<chatListBean> c2vlist;
	private Vector<chatContentsBean> ccvlist;
	private chatManager cm = new chatManager();

	GridBagLayout gbl_mainTest = new GridBagLayout();
	CentralDropShadowPanel leftUpperPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 30);
	GridBagConstraints gbc_leftUpperPanel = new GridBagConstraints();
	JPanel calendarPanel = new CalendarPanel();
	CentralDropShadowPanel rightUpperPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 30);
	GridBagConstraints gbc_rightUpperPanel = new GridBagConstraints();
	JPanel titlePanel = new JPanel();
	FlowLayout flowLayout = (FlowLayout) titlePanel.getLayout();
	// 할일
	JLabel todoTitleLabel = new JLabel("오늘 할일");
	JPanel contentPanel = new JPanel();
	JPanel newPanel;
	JLabel newJLabel;

	ImageIcon vacation_icon = new ImageIcon("./cmp/IMG/vacation_img.png");
	ImageIcon date_icon = new ImageIcon("./cmp/IMG/schedule_img.png");
	ImageIcon mypage_icon = new ImageIcon("./cmp/IMG/user_img.png");
	ImageIcon todo_icon = new ImageIcon("./cmp/IMG/todo_img.png");
	ImageIcon ask_icon = new ImageIcon("./cmp/IMG/question_img.png");
	ImageIcon chat_icon = new ImageIcon("./cmp/IMG/chat_img.png");
	JButton vacationButton = new RoundedButton(vacation_icon, 20);
	JButton dateButton = new RoundedButton(date_icon, 20);
	JButton myPageButton = new RoundedButton(mypage_icon, 20);
	JButton QAButton = new RoundedButton(ask_icon, 20);
	JButton todoButton = new RoundedButton(todo_icon, 20);
	JLabel vacationLabel = new JLabel("휴가");
	JLabel dateLabel = new JLabel("일정");
	JLabel myPageLabel = new JLabel("마이페이지");
	JLabel QALabel = new JLabel("문의사항");
	JLabel todoLabel = new JLabel("할일");
	JButton logOut = new RoundedButton("로그아웃", 15);

	/**
	 * Create the frame.
	 */
	public EmployeeMain() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setVisible(true);
		setTitle("직원 - " + id);
		MainTest = new JPanel();
		MainTest.setBackground(Color.WHITE);
		MainTest.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(MainTest);
		gbl_mainTest.columnWidths = new int[] { 260, 240, 300 };
		gbl_mainTest.rowHeights = new int[] { 265, 320 };
		gbl_mainTest.columnWeights = new double[] { 1.0, 0.0, 0.0 };
		gbl_mainTest.rowWeights = new double[] { 0.0, 1.0 };
		MainTest.setLayout(gbl_mainTest);

		leftUpperPanel.setBackground(Color.WHITE);
		gbc_leftUpperPanel.fill = GridBagConstraints.BOTH;
		gbc_leftUpperPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_leftUpperPanel.insets = new Insets(0, 0, 5, 5);
		gbc_leftUpperPanel.gridx = 0;
		gbc_leftUpperPanel.gridy = 0;
		MainTest.add(leftUpperPanel, gbc_leftUpperPanel);
		leftUpperPanel.setLayout(new BorderLayout(0, 0));

		calendarPanel.setBorder(new EmptyBorder(15, 5, 15, 5));
		calendarPanel.setOpaque(false);
		leftUpperPanel.add(calendarPanel);

		rightUpperPanel.setBackground(Color.WHITE);
		gbc_rightUpperPanel.fill = GridBagConstraints.BOTH;
		gbc_rightUpperPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_rightUpperPanel.insets = new Insets(0, 0, 5, 5);
		gbc_rightUpperPanel.gridx = 1;
		gbc_rightUpperPanel.gridy = 0;
		MainTest.add(rightUpperPanel, gbc_rightUpperPanel);
		rightUpperPanel.setLayout(new BoxLayout(rightUpperPanel, BoxLayout.Y_AXIS));

		// 할 일
		titlePanel.setMaximumSize(new Dimension(32767, 40));
		titlePanel.setPreferredSize(new Dimension(10, 40));
		flowLayout.setAlignment(FlowLayout.LEFT);
		titlePanel.setOpaque(false);
		rightUpperPanel.add(titlePanel);

		todoTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		titlePanel.add(todoTitleLabel);

		contentPanel.setOpaque(false);
		rightUpperPanel.add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		//
		CentralDropShadowPanel DownerPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 30);
		DownerPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_DownerPanel = new GridBagConstraints();
		gbc_DownerPanel.fill = GridBagConstraints.BOTH;
		gbc_DownerPanel.gridwidth = 2;
		gbc_DownerPanel.insets = new Insets(0, 0, 0, 5);
		gbc_DownerPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_DownerPanel.gridx = 0;
		gbc_DownerPanel.gridy = 1;
		MainTest.add(DownerPanel, gbc_DownerPanel);
		DownerPanel.setLayout(new BorderLayout(0, 0));

		JPanel appContainer = new JPanel();
		appContainer.setOpaque(false);
		appContainer.setBorder(new EmptyBorder(20, 10, 10, 10));
		DownerPanel.add(appContainer);
		appContainer.setLayout(new GridLayout(0, 5, 5, 5));

		JPanel appPanel = new JPanel();
		appPanel.setOpaque(false);
		appContainer.add(appPanel);
		appPanel.setLayout(new BoxLayout(appPanel, BoxLayout.Y_AXIS));

		vacationButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		vacationButton.setMaximumSize(new Dimension(40, 40));
		vacationButton.setPreferredSize(new Dimension(40, 40));
		vacationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		vacationButton.setBackground(Color.white);
		appPanel.add(vacationButton);

		vacationLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		vacationLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		vacationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		vacationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		appPanel.add(vacationLabel);

		JPanel appPanel_1 = new JPanel();
		appPanel_1.setOpaque(false);
		appContainer.add(appPanel_1);
		appPanel_1.setLayout(new BoxLayout(appPanel_1, BoxLayout.Y_AXIS));

		dateButton.setBackground(Color.white);
		dateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateButton.setMaximumSize(new Dimension(40, 40));
		dateButton.setPreferredSize(new Dimension(40, 40));
		dateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		appPanel_1.add(dateButton);

		dateLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		dateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		appPanel_1.add(dateLabel);

		JPanel appPanel_2 = new JPanel();
		appPanel_2.setOpaque(false);
		appContainer.add(appPanel_2);
		appPanel_2.setLayout(new BoxLayout(appPanel_2, BoxLayout.Y_AXIS));

		myPageButton.setBackground(Color.white);
		myPageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		myPageButton.setMaximumSize(new Dimension(40, 40));
		myPageButton.setPreferredSize(new Dimension(40, 40));
		myPageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		appPanel_2.add(myPageButton);

		myPageLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		myPageLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		myPageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		appPanel_2.add(myPageLabel);

		JPanel appPanel_3 = new JPanel();
		appPanel_3.setOpaque(false);
		appContainer.add(appPanel_3);
		appPanel_3.setLayout(new BoxLayout(appPanel_3, BoxLayout.Y_AXIS));

		QAButton.setBackground(Color.white);
		QAButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		QAButton.setMaximumSize(new Dimension(40, 40));
		QAButton.setPreferredSize(new Dimension(40, 40));
		QAButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		appPanel_3.add(QAButton);

		QALabel.setBackground(new Color(102, 204, 0));
		QALabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		QALabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		QALabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		appPanel_3.add(QALabel);

		JPanel appPanel_4 = new JPanel();
		appPanel_4.setOpaque(false);
		appContainer.add(appPanel_4);
		appPanel_4.setLayout(new BoxLayout(appPanel_4, BoxLayout.Y_AXIS));

		todoButton.setBackground(Color.white);
		todoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		todoButton.setMaximumSize(new Dimension(40, 40));
		todoButton.setPreferredSize(new Dimension(40, 40));
		todoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		appPanel_4.add(todoButton);

		todoLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		todoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		todoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		appPanel_4.add(todoLabel);
		
		for(int i = 0; i<4; i++) {
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			appContainer.add(panel);
		}
		JPanel logoutPanel = new JPanel();
		logoutPanel.setBackground(Color.white);
		logOut.setMaximumSize(new Dimension(60, 23));
		logOut.setPreferredSize(new Dimension(60, 23));
		logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
		logOut.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		logOut.setBackground(Color.GRAY);
		logOut.setForeground(Color.WHITE);
		logoutPanel.add(logOut, BorderLayout.SOUTH);
		logoutPanel.add(Box.createVerticalStrut(100));
		appContainer.add(logoutPanel);

		CentralDropShadowPanel ChatingPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 30);
		ChatingPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_ChatingPanel = new GridBagConstraints();
		gbc_ChatingPanel.gridheight = 2;
		gbc_ChatingPanel.insets = new Insets(0, 0, 0, 0);
		gbc_ChatingPanel.fill = GridBagConstraints.BOTH;
		gbc_ChatingPanel.gridx = 2;
		gbc_ChatingPanel.gridy = 0;
		MainTest.add(ChatingPanel, gbc_ChatingPanel);
		ChatingPanel.setLayout(new BorderLayout(0, 0));

		JPanel chatTitlePanel = new JPanel();
		chatTitlePanel.setMaximumSize(new Dimension(32767, 40));
		chatTitlePanel.setOpaque(false);
		ChatingPanel.add(chatTitlePanel, BorderLayout.NORTH);
		chatTitlePanel.setLayout(new BorderLayout(0, 0));

		JButton chatNewButton = new RoundedButton("+", 20);
		chatNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		chatNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		chatNewButton.setBackground(Color.WHITE);
		chatNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							chatCreate frame = new chatCreate(id);
							frame.setVisible(true);
							frame.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosed(WindowEvent e) {
									// 창이 닫힐 때 업데이트할 작업 수행
									updateChatPanel();
								}
							});
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		chatTitlePanel.add(chatNewButton, BorderLayout.EAST);

		JPanel cardButtonPanel = new JPanel();
		cardButtonPanel.setOpaque(false);
		FlowLayout fl_cardButtonPanel = (FlowLayout) cardButtonPanel.getLayout();
		fl_cardButtonPanel.setAlignment(FlowLayout.LEFT);
		chatTitlePanel.add(cardButtonPanel, BorderLayout.CENTER);

		JButton friendButton = new RoundedButton("친구창", 20);
		friendButton.setForeground(Color.WHITE);
		friendButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		friendButton.setBackground(new Color(0, 204, 0));
		cardButtonPanel.add(friendButton);

		JButton chatButton = new RoundedButton("채팅창", 20);
		chatButton.setForeground(Color.WHITE);
		chatButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		chatButton.setBackground(new Color(0, 153, 255));
		cardButtonPanel.add(chatButton);

		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		ChatingPanel.add(cardPanel, BorderLayout.CENTER);

		// 친구 목록 패널 생성
		JScrollPane friendScrollPane = createScrollPane(createFriendPanel());
		cardPanel.add(friendScrollPane, "FriendPanel");

		// 채팅 목록 패널 생성 
		JScrollPane chatScrollPane =createScrollPane(createChatPanel());
		cardPanel.add(chatScrollPane, "ChatPanel");

		// 버튼 액션 리스너 설정
		friendButton.addActionListener(e -> cardLayout.show(cardPanel, "FriendPanel"));
		chatButton.addActionListener(e -> cardLayout.show(cardPanel, "ChatPanel"));

		// 초기에는 친구창을 보여줌
		cardLayout.show(cardPanel, "FriendPanel");

		vacationButton.addActionListener(this);
		dateButton.addActionListener(this);
		myPageButton.addActionListener(this);
		QAButton.addActionListener(this);
		todoButton.addActionListener(this);
		logOut.addActionListener(this);

		updateTodoPanel();

		if (getTitle().equals("직원 - null")) {
			dispose();
		}
	}

	// 사용자 정의 ScrollBar UI 클래스
	private static class CustomScrollBarUI extends javax.swing.plaf.basic.BasicScrollBarUI {
		@Override
		protected void configureScrollBarColors() {
			this.thumbColor = new Color(0, 0, 0, 0); // 투명한 thumb 설정
			this.trackColor = new Color(0, 0, 0, 0); // 투명한 track 설정
		}
	}

	private JPanel createFriendPanel() {
		JPanel friendContentPanel = new JPanel();
		friendContentPanel.setOpaque(false);
		friendContentPanel.setLayout(new BoxLayout(friendContentPanel, BoxLayout.Y_AXIS));

		emvlist = cm.selectEmployee(id);
		Vector<String> svlist = new Vector<String>();
		// 친구 목록을 테스트로 추가
		for (int i = 0; i < emvlist.size(); i++) {
			if (emvlist.get(i) != null) {
				String str = emvlist.get(i).getName() + " " + emvlist.get(i).getPosition() + " ("
						+ emvlist.get(i).getDepartment() + ")";
				svlist.add(str);

				boolean flag = cm.chatCheck(id, emvlist.get(i).getId());

				if (flag) {
					// 기존 채팅방이 존재할 때
					System.out.println("true");

				} else {
					// 새로운 채팅방을 생성해야 할 때
					System.out.println("false");
					System.out.println(id + " : " + emvlist.get(i).getId());

					if (cm.createPrivateChat(id, emvlist.get(i).getId())) {
						System.out.println("성공");

						// 채팅방을 생성한 후 다시 c2vlist를 업데이트

					}
				}

			}
		}
		for (int i = 0; i < emvlist.size(); i++) {
			JPanel friendPanel = new JPanel();
			friendPanel.setBackground(Color.LIGHT_GRAY);
			friendPanel.setMaximumSize(new Dimension(32767, 40));
			friendPanel.setPreferredSize(new Dimension(10, 40));
			friendPanel.setLayout(new GridLayout(0, 1, 0, 0));
			friendContentPanel.add(friendPanel);

			String str = svlist.get(i);
			int chat_no = cm.selectPrivateChat(id, emvlist.get(i).getId()).get(0).getChat_no();
			JLabel friendLabel = new JLabel(str);
			friendLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
			friendLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			friendLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {

					if (e.getClickCount() == 2) {

						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									chatRoom frame = new chatRoom(chat_no, str, id);
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				}
			});
			friendPanel.add(friendLabel);
		}
		return friendContentPanel;
	}

	// 채팅 목록 패널 생성
	private JPanel createChatPanel() {
		JPanel chatContentPanel = new JPanel();
		chatContentPanel.setOpaque(false);
		chatContentPanel.setLayout(new BoxLayout(chatContentPanel, BoxLayout.Y_AXIS));

		// 채팅 목록을 테스트로 추가
		cvlist = cm.selectChat(id);

		for (int i = 0; i < cvlist.size(); i++) {
			JPanel chatRoomPanel = new JPanel();
			chatRoomPanel.setBackground(Color.LIGHT_GRAY);
			chatRoomPanel.setMaximumSize(new Dimension(32767, 40));
			chatRoomPanel.setPreferredSize(new Dimension(10, 40));
			chatRoomPanel.setLayout(new GridLayout(0, 1, 0, 0));
			chatContentPanel.add(chatRoomPanel);

			String str = cvlist.get(i).getChatroom_name();
			int chat_no = cvlist.get(i).getChat_no();
			JLabel whoLabel = new JLabel(str);
			whoLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
			whoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));

			whoLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getClickCount() == 2) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									chatRoom frame = new chatRoom(chat_no, str, id);
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				}
			});
			chatRoomPanel.add(whoLabel);
		}
		return chatContentPanel;
	}

	// JScrollPane 생성
	private JScrollPane createScrollPane(JPanel contentPanel) {
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(0, 0, 15, 0));
		scrollPane.setOpaque(false);

		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
		verticalScrollBar.setPreferredSize(new Dimension(0, 0)); // 크기를 0으로 설정
		verticalScrollBar.setUI(new CustomScrollBarUI()); // 사용자 정의 UI 설정

		return scrollPane;
	}

	// 할 일 패널 추가
	public void updateTodoPanel() {
		vlist = db.selectTodo(id);

		for (int i = 0; i < vlist.size(); i++) {
			newPanel = new JPanel();
			newPanel.setOpaque(false);
			newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

			newJLabel = new JLabel("● " + vlist.get(i).getTodo_contents());
			newJLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

			newPanel.add(newJLabel);
			contentPanel.add(newPanel);
		}

		contentPanel.revalidate();
		contentPanel.repaint();
	}

	private void updateChatPanel() {
		// 현재 표시 중인 패널의 이름을 가져오기
		CardLayout cardLayout = (CardLayout) cardPanel.getLayout();

		// 채팅 패널만 업데이트
		 JPanel chatPanel = createChatPanel();

		// 기존 ChatPanel을 찾아서 교체
		for (Component comp : cardPanel.getComponents()) {
			if (comp.getName() != null && comp.getName().equals("ChatPanel")) {
				// 기존 패널을 교체
				cardPanel.remove(comp);
				break;
			}
		}

		// 새로 생성된 chatPanel을 추가 (스크롤 가능하게 처리)
		 JScrollPane chatScrollPane = createScrollPane(chatPanel);
		 chatScrollPane.setName("ChatPanel");
		 cardPanel.add(chatScrollPane, "ChatPanel");

		// 레이아웃 재계산 및 화면 갱신
		cardPanel.revalidate();
		cardPanel.repaint();

		// ChatPanel로 다시 전환
		cardLayout.show(cardPanel, "ChatPanel");
	}

	// 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == vacationButton) {
			if (db.CheckManagerEmployee(id)) {
				new VacationConfirm();
			} else {
				Vacation vaca = new Vacation();
				vaca.setId(id);
				new Vacation();
			}
		} else if (obj == dateButton) {
			if (db.CheckManagerEmployee(id)) {
				NoticeCreate nc = new NoticeCreate();
				nc.setId(id);
				//setVisible(false);
				dispose();
			} else {
				new NoticeView();
			}
		} else if (obj == myPageButton) {
			MyPage mypage = new MyPage();
			mypage.setEm_id(id);
			new MyPage();
		} else if (obj == QAButton) {
			if(db.CheckManagerEmployee(id)) {
				QACheck qa = new QACheck(id);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "권한이 없습니다.", "문의사항", JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj == todoButton) {
			ToDoList todo = new ToDoList();
			todo.setEm_id(id);
			new ToDoList();
			dispose();
		}
		else if(obj == logOut) {
			dispose();
			new EmLogin();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeMain frame = new EmployeeMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
