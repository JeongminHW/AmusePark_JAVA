package cmp.GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;

import java.util.*;
import cmp.DB.*;

public class AlbaMain extends JFrame implements ActionListener {
	static String id;

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		AlbaMain.id = id;
	}

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	DBMgr db = new DBMgr();
	static Vector<InquireBean> vlist;
	static Vector<TodoBean> tlist;
	JPanel contentPanel = new JPanel();
	JPanel newPanel;
	JLabel newJLabel;
	CentralDropShadowPanel rightPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
	CentralDropShadowPanel leftUpperPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
	CentralDropShadowPanel leftDownerPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
	JLabel appLabel1 = new JLabel("할 일");
	JLabel appLabel2 = new JLabel("일정");
	JLabel appLabel3 = new JLabel("마이페이지");
	JLabel appLabel4 = new JLabel("출근");
	JLabel appLabel5 = new JLabel("대타");
	ImageIcon mypage_icon = new ImageIcon("./cmp/IMG/user_img.png");
	ImageIcon todo_icon = new ImageIcon("./cmp/IMG/todo_img.png");
	ImageIcon schedule_icon = new ImageIcon("./cmp/IMG/schedule_img.png");
	ImageIcon workin_icon = new ImageIcon("./cmp/IMG/commute_img.png");
	ImageIcon workout_icon = new ImageIcon("./cmp/IMG/exit_img.png");
	ImageIcon substitute_icon = new ImageIcon("./cmp/IMG/change_img.png");
	JButton appButton1 = new RoundedButton(todo_icon, 20);
	JButton appButton2 = new RoundedButton(schedule_icon, 20);
	JButton appButton3 = new RoundedButton(mypage_icon, 20);
	JButton appButton4 = new RoundedButton(workin_icon, 20);
	JButton appButton5 = new RoundedButton(substitute_icon, 20);
	JButton logOut = new RoundedButton("로그아웃", 15);

	/**
	 * Create the frame.
	 */
	public AlbaMain(String id) {

		this.id = id;
		vlist = db.selectinquire(id);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		setVisible(true);
		setTitle("알바 - " + id);
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		JPanel leftPanel = new JPanel();
		leftPanel.setOpaque(false);
		leftPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		mainPanel.add(leftPanel);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		leftUpperPanel.setMaximumSize(new Dimension(300, 32767));
		leftUpperPanel.setPreferredSize(new Dimension(300, 34));
		leftUpperPanel.setBackground(Color.WHITE);
		leftUpperPanel.setOpaque(false);
		leftPanel.add(leftUpperPanel);
		leftUpperPanel.setLayout(new BoxLayout(leftUpperPanel, BoxLayout.Y_AXIS));

		leftDownerPanel.setPreferredSize(new Dimension(300, 225));
		leftDownerPanel.setMaximumSize(new Dimension(300, 225));
		leftDownerPanel.setBackground(Color.WHITE);
		leftPanel.add(leftDownerPanel);
		leftDownerPanel.setLayout(new BoxLayout(leftDownerPanel, BoxLayout.Y_AXIS));

		JPanel calenderPanel = new CalendarPanel();
		calenderPanel.setBorder(new EmptyBorder(15, 5, 15, 5));
		calenderPanel.setOpaque(false);
		leftUpperPanel.add(calenderPanel);

		JPanel todoTitlePanel = new JPanel();
		todoTitlePanel.setBorder(new EmptyBorder(0, 5, 0, 5));
		todoTitlePanel.setMaximumSize(new Dimension(32767, 34));
		todoTitlePanel.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) todoTitlePanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		leftDownerPanel.add(todoTitlePanel);

		JLabel todoTitleLabel = new JLabel("할일");
		todoTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		todoTitlePanel.add(todoTitleLabel);
		CentralDropShadowPanel centerPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
		centerPanel.setPreferredSize(new Dimension(100, 34));
		centerPanel.setMaximumSize(new Dimension(100, 32767));
		centerPanel.setBackground(Color.WHITE);
		mainPanel.add(centerPanel);

		rightPanel.setMaximumSize(new Dimension(32767, 450));
		rightPanel.setBackground(Color.WHITE);
		mainPanel.add(rightPanel);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		creatrQAPanel();

		JPanel appContainerPanel1 = new JPanel();
		appContainerPanel1.setOpaque(false);
		appContainerPanel1.setBorder(new EmptyBorder(10, 0, 7, 0));
		centerPanel.add(appContainerPanel1);
		appContainerPanel1.setLayout(new BoxLayout(appContainerPanel1, BoxLayout.Y_AXIS));

		appButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
		appButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		appButton1.setBackground(Color.white);
		appButton1.setMaximumSize(new Dimension(50, 50));
		appButton1.setPreferredSize(new Dimension(50, 50));
		appContainerPanel1.add(appButton1);

		appLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
		appLabel1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		appContainerPanel1.add(appLabel1);

		JPanel appContainerPanel2 = new JPanel();
		appContainerPanel2.setOpaque(false);
		appContainerPanel2.setBorder(new EmptyBorder(10, 0, 7, 0));
		centerPanel.add(appContainerPanel2);
		appContainerPanel2.setLayout(new BoxLayout(appContainerPanel2, BoxLayout.Y_AXIS));

		appButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
		appButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		appButton2.setBackground(Color.white);
		appButton2.setMaximumSize(new Dimension(50, 50));
		appButton2.setPreferredSize(new Dimension(50, 50));
		appContainerPanel2.add(appButton2);

		appLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
		appLabel2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		appContainerPanel2.add(appLabel2);

		JPanel appContainerPanel3 = new JPanel();
		appContainerPanel3.setOpaque(false);
		appContainerPanel3.setBorder(new EmptyBorder(10, 0, 7, 0));
		centerPanel.add(appContainerPanel3);
		appContainerPanel3.setLayout(new BoxLayout(appContainerPanel3, BoxLayout.Y_AXIS));

		appButton3.setAlignmentX(Component.CENTER_ALIGNMENT);
		appButton3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		appButton3.setBackground(Color.white);
		appButton3.setMaximumSize(new Dimension(50, 50));
		appButton3.setPreferredSize(new Dimension(50, 50));
		appContainerPanel3.add(appButton3);

		appLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
		appLabel3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		appContainerPanel3.add(appLabel3);

		JPanel appContainerPanel4 = new JPanel();
		appContainerPanel4.setOpaque(false);
		appContainerPanel4.setBorder(new EmptyBorder(10, 0, 7, 0));
		centerPanel.add(appContainerPanel4);
		appContainerPanel4.setLayout(new BoxLayout(appContainerPanel4, BoxLayout.Y_AXIS));

		appButton4.setAlignmentX(Component.CENTER_ALIGNMENT);
		appButton4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		appButton4.setBackground(Color.white);
		appButton4.setMaximumSize(new Dimension(50, 50));
		appButton4.setPreferredSize(new Dimension(50, 50));
		appContainerPanel4.add(appButton4);

		appLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
		appLabel4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		appContainerPanel4.add(appLabel4);

		JPanel appContainerPanel5 = new JPanel();
		appContainerPanel5.setOpaque(false);
		appContainerPanel5.setBorder(new EmptyBorder(10, 0, 7, 0));
		centerPanel.add(appContainerPanel5);
		appContainerPanel5.setLayout(new BoxLayout(appContainerPanel5, BoxLayout.Y_AXIS));

		appButton5.setAlignmentX(Component.CENTER_ALIGNMENT);
		appButton5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		appButton5.setBackground(Color.white);
		appButton5.setMaximumSize(new Dimension(50, 50));
		appButton5.setPreferredSize(new Dimension(50, 50));
		appContainerPanel5.add(appButton5);

		appLabel5.setAlignmentX(Component.CENTER_ALIGNMENT);
		appLabel5.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		appContainerPanel5.add(appLabel5);

		logOut.setMaximumSize(new Dimension(60, 23));
		logOut.setPreferredSize(new Dimension(60, 23));
		logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
		logOut.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		logOut.setBackground(Color.GRAY);
		logOut.setForeground(Color.WHITE);
		centerPanel.add(logOut);

		appButton1.addActionListener(this);
		appButton2.addActionListener(this);
		appButton3.addActionListener(this);
		appButton4.addActionListener(this);
		logOut.addActionListener(this);

		refreshQAList();
		updateTodoPanel();
	}

	public void creatrQAPanel() {

		JPanel QATitlePanel = new JPanel();
		QATitlePanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		QATitlePanel.setMaximumSize(new Dimension(32767, 40));
		QATitlePanel.setOpaque(false);
		rightPanel.add(QATitlePanel);
		QATitlePanel.setLayout(new BorderLayout(0, 0));

		JLabel QATitleLabel = new JLabel("문의사항");
		QATitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		QATitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		QATitlePanel.add(QATitleLabel, BorderLayout.WEST);

		JButton QAButton = new RoundedButton("작성", 20);
		QAButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		QAButton.setForeground(Color.WHITE);
		QAButton.setBackground(new Color(0, 148, 255));
		QAButton.addActionListener(new ActionListener() { // 작성 버튼 클릭
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateQA frame = new CreateQA(id);
				dispose();
			}
		});
		QATitlePanel.add(QAButton, BorderLayout.EAST);

		for (int i = 0; i < vlist.size(); i++) {
			createQAContent(vlist.get(i));
		}
	}

	// 본인이 작성한 문의사항 보여주기
	public void createQAContent(InquireBean bean) {
		JPanel QAExamplePanel = new JPanel();
		QAExamplePanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		QAExamplePanel.setPreferredSize(new Dimension(10, 50));
		QAExamplePanel.setMaximumSize(new Dimension(32767, 50));
		rightPanel.add(QAExamplePanel);
		QAExamplePanel.setLayout(new BorderLayout(0, 0));

		JPanel QAContentPanel = new JPanel();
		QAContentPanel.setOpaque(false);
		QAExamplePanel.add(QAContentPanel, BorderLayout.CENTER);
		QAContentPanel.setLayout(new BoxLayout(QAContentPanel, BoxLayout.Y_AXIS));

		JLabel QAContentLabel = new JLabel(bean.getInquire_title());
		QAContentLabel.setBorder(new EmptyBorder(0, 5, 0, 0));
		QAContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		QAContentPanel.add(QAContentLabel);

		// 두 개의 버튼을 담을 패널 생성
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0)); // 오른쪽 정렬, 버튼 사이에 5px 간격
		buttonPanel.setOpaque(false); // 투명하게 설정
		QAExamplePanel.add(buttonPanel, BorderLayout.EAST);

		// 확인 버튼 생성 및 추가
		JButton checkButton = new RoundedButton("확인", 20);
		checkButton.setBackground(Color.BLUE);
		checkButton.setForeground(Color.WHITE);
		checkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkQA(bean);
			}
		});
		buttonPanel.add(checkButton); // buttonPanel에 추가

		// 삭제 버튼 생성 및 추가
		JButton cancelButton = new RoundedButton("삭제", 20);
		cancelButton.setBackground(Color.GRAY);
		cancelButton.setForeground(Color.WHITE);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (db.deleteInquire(bean.getInquire_num())) {
					System.out.println("삭제 성공");
					refreshQAList();
				}
			}
		});
		buttonPanel.add(cancelButton); // buttonPanel에 추가

	}

	// 리스트 새로고침
	public void refreshQAList() {
		vlist = db.selectinquire(id); // 문의사항 리스트 재로드
		rightPanel.removeAll(); // 기존의 문의사항 UI 제거

		creatrQAPanel();

		rightPanel.revalidate(); // 레이아웃 재설정
		rightPanel.repaint(); // 화면 다시 그리기
	}

	// 답변 유무 확인
	public void checkQA(InquireBean bean) {
		if (bean.getReply_contents() == null) {
			JOptionPane.showMessageDialog(null, "답변이 없습니다.", "문의사항 답변", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "작성 되었습니다.", "문의사항", JOptionPane.PLAIN_MESSAGE);
		}
	}

	// 할 일 패널 추가
	public void updateTodoPanel() {
		tlist = db.selectTodo(id);

		for (int i = 0; i < vlist.size(); i++) {
			newPanel = new JPanel();
			newPanel.setOpaque(false);
			newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

			newJLabel = new JLabel("● " + tlist.get(i).getTodo_contents());
			newJLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

			newPanel.add(newJLabel);
			leftDownerPanel.add(newPanel);
		}

		leftDownerPanel.revalidate();
		leftDownerPanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		AlbaBean bean = new AlbaBean();
		if (obj == appButton1) {
			ToDoList todolist = new ToDoList();
			todolist.setAlba_id(id);
			new ToDoList();
			setVisible(false);
		} else if (obj == appButton2) { // 일정 버튼
			new NoticeView();
		} else if (obj == appButton3) {
			MyPage mypage = new MyPage();
			mypage.setAlba_id(id);
			new MyPage();
		} else if (obj == appButton4) {
			if (appLabel4.getText().equals("출근")) {
				int popup = JOptionPane.showConfirmDialog(null, "출근 하시겠습니까?", "출퇴근", JOptionPane.YES_NO_OPTION);
				if (popup == JOptionPane.YES_OPTION) {
					db.updateWorkcheckTrue(id);
					db.updateTotalwork(id);
					appButton4.setIcon(workout_icon);
					appLabel4.setText("퇴근");
				}
			} else if (appLabel4.getText().equals("퇴근")) {
				int popup = JOptionPane.showConfirmDialog(null, "퇴근 하시겠습니까?", "출퇴근", JOptionPane.YES_NO_OPTION);
				if (popup == JOptionPane.YES_OPTION) {
					db.updateWorkcheckFalse(id);
					appButton4.setIcon(workin_icon);
					appLabel4.setText("출근");
				}
			}
		} else if (obj == logOut) {
			dispose();
			new PTLogin();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AlbaMain(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
