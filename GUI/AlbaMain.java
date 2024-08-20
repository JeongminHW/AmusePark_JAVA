package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DB.DBMgr;
import GUI.RoundedButton;  // Assuming this is in the same package.

public class AlbaMain extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	static String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	JPanel leftPanel = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel calenderPanel = new CalendarPanel();
	JPanel QATitlePanel = new JPanel();
	JPanel QAExamplePanel = new JPanel();
	JPanel QAContentPanel = new JPanel();
	JPanel appContainerPanel1 = new JPanel();
	JPanel appContainerPanel2 = new JPanel();
	JPanel appContainerPanel3 = new JPanel();
	JPanel appContainerPanel4 = new JPanel();
	CentralDropShadowPanel leftUpperPanel = new CentralDropShadowPanel(6,Color.LIGHT_GRAY);
	CentralDropShadowPanel leftDownerPanel = new CentralDropShadowPanel(6,Color.LIGHT_GRAY);
	CentralDropShadowPanel centerPanel = new CentralDropShadowPanel(6,Color.LIGHT_GRAY);
	CentralDropShadowPanel rightPanel = new CentralDropShadowPanel(6,Color.LIGHT_GRAY);
	JPanel todoTitlePanel = new JPanel();
	FlowLayout flowLayout = (FlowLayout) todoTitlePanel.getLayout();
	JLabel todoTitleLabel = new JLabel("할일");
	JLabel QATitleLabel = new JLabel("문의사항");
	JLabel QAContentNameLabel = new JLabel("작성자");
	JLabel QAContentLabel = new JLabel("내용");
	JButton QAButton = new RoundedButton("작성",20);
	JButton cancelButton = new RoundedButton("취소", 20);
	JButton appButton1 = new RoundedButton("투두리스트", 20);
	JLabel appLabel1 = new JLabel("투두리스트");
	JButton appButton2 = new RoundedButton("문의사항", 20);
	JLabel appLabel2 = new JLabel("문의사항");
	JButton appButton3 = new RoundedButton("마이페이지", 20);
	JLabel appLabel3 = new JLabel("마이페이지");
	JButton appButton4 = new RoundedButton("출/퇴근", 20);
	JLabel appLabel4 = new JLabel("출/퇴근");
	JButton logOut = new RoundedButton("로그아웃", 15);
	
	DBMgr db = new DBMgr();

	public AlbaMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
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
		
		todoTitlePanel.setBorder(new EmptyBorder(0, 5, 0, 5));
		todoTitlePanel.setMaximumSize(new Dimension(32767, 34));
		todoTitlePanel.setOpaque(false);
		flowLayout.setAlignment(FlowLayout.LEFT);
		leftUpperPanel.add(todoTitlePanel);

		todoTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		todoTitlePanel.add(todoTitleLabel);

		leftDownerPanel.setPreferredSize(new Dimension(300, 225));
		leftDownerPanel.setMaximumSize(new Dimension(300, 225));
		leftDownerPanel.setBackground(Color.WHITE);
		leftPanel.add(leftDownerPanel);
		leftDownerPanel.setLayout(new BoxLayout(leftDownerPanel, BoxLayout.Y_AXIS));

		calenderPanel.setBorder(new EmptyBorder(15, 5, 15, 5));
		calenderPanel.setOpaque(false);
		leftDownerPanel.add(calenderPanel);

		centerPanel.setPreferredSize(new Dimension(100, 34));
		centerPanel.setMaximumSize(new Dimension(100, 32767));
		centerPanel.setBackground(Color.WHITE);
		mainPanel.add(centerPanel);
		
		rightPanel.setMaximumSize(new Dimension(32767, 450));
		rightPanel.setBackground(Color.WHITE);
		mainPanel.add(rightPanel);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		QATitlePanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		QATitlePanel.setMaximumSize(new Dimension(32767, 40));
		QATitlePanel.setOpaque(false);
		rightPanel.add(QATitlePanel);
		QATitlePanel.setLayout(new BorderLayout(0, 0));
		
		QATitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		QATitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		QATitlePanel.add(QATitleLabel, BorderLayout.WEST);
		
		QAButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		QAButton.setForeground(Color.WHITE);
		QAButton.setBackground(new Color(0,148,255));
		QATitlePanel.add(QAButton, BorderLayout.EAST);
		
		QAExamplePanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		QAExamplePanel.setPreferredSize(new Dimension(10, 50));
		QAExamplePanel.setMaximumSize(new Dimension(32767, 50));
		rightPanel.add(QAExamplePanel);
		QAExamplePanel.setLayout(new BorderLayout(0, 0));
		
		// 문의사항
		QAContentPanel.setOpaque(false);
		QAExamplePanel.add(QAContentPanel, BorderLayout.CENTER);
		QAContentPanel.setLayout(new BoxLayout(QAContentPanel, BoxLayout.Y_AXIS));

		QAContentNameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		QAContentPanel.add(QAContentNameLabel);

		QAContentLabel.setBorder(new EmptyBorder(0, 5, 0, 0));
		QAContentLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		QAContentPanel.add(QAContentLabel);

		cancelButton.setBackground(Color.GRAY);
		cancelButton.setForeground(Color.WHITE);
		QAExamplePanel.add(cancelButton, BorderLayout.EAST);

		appContainerPanel1.setOpaque(false);
		appContainerPanel1.setBorder(new EmptyBorder(10, 0, 7, 0));
		centerPanel.add(appContainerPanel1);
		appContainerPanel1.setLayout(new BoxLayout(appContainerPanel1, BoxLayout.Y_AXIS));

		// 버튼
		appButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
		appButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		appButton1.setBackground(new Color(0,148,255));
		appButton1.setForeground(Color.white);
		appButton1.setMaximumSize(new Dimension(50,50));
		appButton1.setPreferredSize(new Dimension(50,50));
		appContainerPanel1.add(appButton1);

		appLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
		appLabel1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		appContainerPanel1.add(appLabel1);
		
		appContainerPanel2.setOpaque(false);
		appContainerPanel2.setBorder(new EmptyBorder(10, 0, 7, 0));
		centerPanel.add(appContainerPanel2);
		appContainerPanel2.setLayout(new BoxLayout(appContainerPanel2, BoxLayout.Y_AXIS));

		appButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
		appButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		appButton2.setBackground(new Color(0,148,255));
		appButton2.setForeground(Color.white);
		appButton2.setMaximumSize(new Dimension(50,50));
		appButton2.setPreferredSize(new Dimension(50,50));
		appContainerPanel2.add(appButton2);

		appLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
		appLabel2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		appContainerPanel2.add(appLabel2);

		appContainerPanel3.setOpaque(false);
		appContainerPanel3.setBorder(new EmptyBorder(10, 0, 7, 0));
		centerPanel.add(appContainerPanel3);
		appContainerPanel3.setLayout(new BoxLayout(appContainerPanel3, BoxLayout.Y_AXIS));

		appButton3.setAlignmentX(Component.CENTER_ALIGNMENT);
		appButton3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		appButton3.setBackground(new Color(0,148,255));
		appButton3.setForeground(Color.white);
		appButton3.setMaximumSize(new Dimension(50,50));
		appButton3.setPreferredSize(new Dimension(50,50));
		appContainerPanel3.add(appButton3);

		appLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
		appLabel3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		appContainerPanel3.add(appLabel3);


		appContainerPanel4.setOpaque(false);
		appContainerPanel4.setBorder(new EmptyBorder(10, 0, 7, 0));
		centerPanel.add(appContainerPanel4);
		appContainerPanel4.setLayout(new BoxLayout(appContainerPanel4, BoxLayout.Y_AXIS));

		appButton4.setAlignmentX(Component.CENTER_ALIGNMENT);
		appButton4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		appButton4.setBackground(new Color(0,148,255));
		appButton4.setForeground(Color.white);
		appButton4.setMaximumSize(new Dimension(50,50));
		appButton4.setPreferredSize(new Dimension(50,50));
		appContainerPanel4.add(appButton4);

		appLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
		appLabel4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		appContainerPanel4.add(appLabel4);
		

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
	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlbaMain frame = new AlbaMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == appButton1) {
			ToDoList todolist = new ToDoList(id);
			System.out.println("sssss");
		} else if (obj == appButton2) {
			if (db.CheckManagerEmployee(getId()) == false) {
				JOptionPane.showMessageDialog(null, "권한이 없습니다.", "권한", JOptionPane.ERROR_MESSAGE);
			} else {
				
			}
		} else if (obj == appButton3) {
			MyPage mypage = new MyPage();
			mypage.setEm_id(id);
			new MyPage();
		} else if (obj == appButton4) {
			
		} else if(obj == logOut) {
			
		}
	}
}