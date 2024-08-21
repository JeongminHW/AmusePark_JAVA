package GUI;
//package cmp.GUI;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;

import DB.DBMgr;
import DB.InquireBean;
import cmp.GUI.ToDoList;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;

public class Main_s_ver2 extends JFrame {

	static String id;
	public static String getId(){
		return id;
	}
	public static void setId(String id){
		Main_s_ver2.id = id;
	}

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;

	DBMgr db = new DBMgr();
	static Vector<InquireBean> vlist;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_s_ver2 frame = new Main_s_ver2(id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main_s_ver2(String id) {
		this.id = id;
		vlist = db.selectinquire(id);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700,400);
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] {300, 100, 300};
		gbl_mainPanel.rowHeights = new int[] {200, 200};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 1.0};
		gbl_mainPanel.rowWeights = new double[]{0.0, 1.0};
		mainPanel.setLayout(gbl_mainPanel);
		
		CentralDropShadowPanel leftUpperPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 30);
		GridBagConstraints gbc_leftUpperPanel = new GridBagConstraints();
		gbc_leftUpperPanel.fill = GridBagConstraints.BOTH;
		leftUpperPanel.setBackground(Color.WHITE);
		gbc_leftUpperPanel.gridx = 0;
		gbc_leftUpperPanel.gridy = 0;
		mainPanel.add(leftUpperPanel, gbc_leftUpperPanel);
		leftUpperPanel.setLayout(new BoxLayout(leftUpperPanel, BoxLayout.X_AXIS));
		
		JPanel calendarPanel = new CalendarPanel();
		calendarPanel.setOpaque(false);
		calendarPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
		leftUpperPanel.add(calendarPanel);
		calendarPanel.setLayout(new BoxLayout(calendarPanel, BoxLayout.Y_AXIS));
		
		CentralDropShadowPanel leftdownerPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 30);
		GridBagConstraints gbc_leftdownerPanel = new GridBagConstraints();
		leftdownerPanel.setBackground(Color.WHITE);
		gbc_leftdownerPanel.fill = GridBagConstraints.BOTH;
		gbc_leftdownerPanel.gridx = 0;
		gbc_leftdownerPanel.gridy = 1;
		mainPanel.add(leftdownerPanel, gbc_leftdownerPanel);
		leftdownerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel leftDownerTitlePanel = new JPanel();
		leftDownerTitlePanel.setOpaque(false);
		leftDownerTitlePanel.setMaximumSize(new Dimension(32767, 30));
		FlowLayout flowLayout = (FlowLayout) leftDownerTitlePanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		leftdownerPanel.add(leftDownerTitlePanel, BorderLayout.NORTH);
		
		JLabel leftDownerTitleLabel = new JLabel("할일");
		leftDownerTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftDownerTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		
		leftDownerTitlePanel.add(leftDownerTitleLabel);
		
		JScrollPane leftDownerContentPanel = new JScrollPane();
		leftDownerContentPanel.setBackground(Color.WHITE);
		leftDownerContentPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		leftDownerContentPanel.setBorder(new EmptyBorder(0, 10, 10, 0));
        leftDownerContentPanel.setOpaque(false);
        leftdownerPanel.add(leftDownerContentPanel);

		JScrollBar verticalScrollBar = leftDownerContentPanel.getVerticalScrollBar();
		verticalScrollBar.setPreferredSize(new Dimension(0, 0));
		verticalScrollBar.setUI(new CustomScrollBarUI());

		JScrollBar horizontalScrollBar = leftDownerContentPanel.getHorizontalScrollBar();
		horizontalScrollBar.setUI(new CustomScrollBarUI());

        // JList 생성 및 모델 설정
        JList<String> list = new JList<>();
        list.setBackground(Color.WHITE);
        list.setOpaque(false);
        list.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        list.setBorder(new EmptyBorder(0, 0, 0, 0));
        list.setModel(new AbstractListModel<String>() {
            String[] values = new String[] {"할 일 1", "할 일 2", "할 일 3", "할 일 4", "할 일 5", "할 일 6", "할 일 7", "할 일 8", "할 일 9", "할 일 10"};
            public int getSize() {
                return values.length;
            }
            public String getElementAt(int index) {
                return values[index];
            }
        });

        // JList를 JScrollPane의 뷰포트에 설정
        leftDownerContentPanel.setViewportView(list);
		//

		CentralDropShadowPanel centerPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 30);
		centerPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
		GridBagConstraints gbc_centerPanel = new GridBagConstraints();
		gbc_centerPanel.fill = GridBagConstraints.BOTH;
		centerPanel.setBackground(Color.WHITE);
		gbc_centerPanel.gridheight = 2;
		gbc_centerPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_centerPanel.gridx = 1;
		gbc_centerPanel.gridy = 0;
		mainPanel.add(centerPanel, gbc_centerPanel);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		JPanel AppContainerPanel = new JPanel();
		AppContainerPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
		AppContainerPanel.setOpaque(false);
		centerPanel.add(AppContainerPanel);
		AppContainerPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel todoAppPanel = new JPanel();
		todoAppPanel.setOpaque(false);
		AppContainerPanel.add(todoAppPanel);
		todoAppPanel.setLayout(new BoxLayout(todoAppPanel, BoxLayout.Y_AXIS));
		
		JButton todoButton = new RoundedButton("", 20);
		todoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		todoButton.setBackground(new Color(0, 204, 0));
		todoButton.setPreferredSize(new Dimension(40,40));
		todoButton.setMaximumSize(new Dimension(40,40));
		todoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		todoAppPanel.add(todoButton);
		
		JLabel todoLabel = new JLabel("할일");
		todoLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		todoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		todoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		todoAppPanel.add(todoLabel);
		
		todoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				ToDoList todolist = new ToDoList();
				todolist.setAlba_id(id);
				dispose();
			}
		});

		JPanel myPagePanel = new JPanel();
		myPagePanel.setOpaque(false);
		AppContainerPanel.add(myPagePanel);
		myPagePanel.setLayout(new BoxLayout(myPagePanel, BoxLayout.Y_AXIS));
		
		JButton myPageButton = new RoundedButton("", 20);
		myPageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		myPageButton.setBackground(new Color(0, 255, 204));
		myPageButton.setPreferredSize(new Dimension(40,40));
		myPageButton.setMaximumSize(new Dimension(40,40));
		myPageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		myPagePanel.add(myPageButton);

		myPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				MyPage mypage = new MyPage();
				mypage.setAlba_id(id);
				new MyPage();
			}
		});

		JLabel myPageLabel = new JLabel("마이페이지");
		myPageLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		myPageLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		myPageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		myPagePanel.add(myPageLabel);


		JPanel datePanel = new JPanel();
		datePanel.setOpaque(false);
		AppContainerPanel.add(datePanel);
		datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS));
		
		JButton dateButton = new RoundedButton("", 20);
		dateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateButton.setBackground(new Color(204, 153, 0));
		dateButton.setPreferredSize(new Dimension(40,40));
		dateButton.setMaximumSize(new Dimension(40,40));
		dateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		datePanel.add(dateButton);

		dateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				NoticeView nv = new NoticeView();
				dispose();
			}
		});

		JLabel dateLabel = new JLabel("일정");
		dateLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		dateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		datePanel.add(dateLabel);

		JPanel workPanel = new JPanel();
		workPanel.setOpaque(false);
		AppContainerPanel.add(workPanel);
		workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));
		
		JButton workButton = new RoundedButton("", 20);
		workButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		workButton.setBackground(new Color(102, 0, 153));
		workButton.setPreferredSize(new Dimension(40,40));
		workButton.setMaximumSize(new Dimension(40,40));
		workButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		workPanel.add(workButton);
		 
		JLabel workLabel = new JLabel("출근");
		workLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		workLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		workLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		workPanel.add(workLabel);
		
		workButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				if (workLabel.getText().equals("출근")) {
				int popup = JOptionPane.showConfirmDialog(null, "출근 하시겠습니까?", "출퇴근", JOptionPane.YES_NO_OPTION);
				if (popup == JOptionPane.YES_OPTION) {
					db.updateWorkcheckTrue(id);
					db.updateTotalwork(id);
					workButton.setIcon(workout_icon);
					workLabel.setText("퇴근");
				}
			} else if (workLabel.getText().equals("퇴근")) {
				int popup = JOptionPane.showConfirmDialog(null, "퇴근 하시겠습니까?", "출퇴근", JOptionPane.YES_NO_OPTION);
				if (popup == JOptionPane.YES_OPTION) {
					db.updateWorkcheckFalse(id);
					workButton.setIcon(workin_icon);
					workLabel.setText("출근");
				}
			}
			}
		});

		JButton logInOut = new RoundedButton("로그아웃", 20);
		logInOut.setForeground(Color.WHITE);
		logInOut.setBackground(Color.GRAY);
		logInOut.setFont(new Font("나눔고딕", Font.BOLD, 14));
		logInOut.setPreferredSize(new Dimension(60,25));
		logInOut.setMaximumSize(new Dimension(60,25));
		logInOut.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(logInOut);
		
		logInOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				dispose();
				new PTLogin();
			}
		});

		CentralDropShadowPanel rightPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 30);
		GridBagConstraints gbc_rightPanel = new GridBagConstraints();
		gbc_rightPanel.fill = GridBagConstraints.BOTH;
		rightPanel.setBackground(Color.WHITE);
		gbc_rightPanel.gridheight = 2;
		gbc_rightPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_rightPanel.gridx = 2;
		gbc_rightPanel.gridy = 0;
		mainPanel.add(rightPanel, gbc_rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel QATitlePanel = new JPanel();
		QATitlePanel.setOpaque(false);
		rightPanel.add(QATitlePanel, BorderLayout.NORTH);
		QATitlePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel QATitleLabel = new JLabel("문의사항");
		QATitleLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		QATitleLabel.setFont(new Font("나눔고딕", Font.BOLD, 14));
		QATitleLabel.setBackground(Color.WHITE);
		QATitlePanel.add(QATitleLabel, BorderLayout.CENTER);
		
		JPanel QATitleButtonPanel = new JPanel();
		QATitleButtonPanel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		QATitleButtonPanel.setOpaque(false);
		QATitlePanel.add(QATitleButtonPanel, BorderLayout.EAST);
		
		RoundedButton QATitleButton = new RoundedButton("문의하기", 20);
		QATitleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		QATitleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		QATitleButton.setText("작성");
		QATitleButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		QATitleButton.setForeground(Color.WHITE);
		QATitleButton.setBorder(new EmptyBorder(1, 10, 10, 10));
		QATitleButton.setBackground(new Color(102, 153, 255));
		QATitleButtonPanel.add(QATitleButton);

		JScrollPane QAContentScrollPanel = new JScrollPane();
		QAContentScrollPanel.setOpaque(false);
		QAContentScrollPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
		rightPanel.add(QAContentScrollPanel);
		
		JPanel scrollContentPanel = new JPanel();
		scrollContentPanel.setOpaque(false);
		scrollContentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		//QAContentScrollPanel.add(scrollContentPanel, BorderLayout.CENTER);

		QAContentScrollPanel.setViewportView(scrollContentPanel);
		scrollContentPanel.setLayout(new BoxLayout(scrollContentPanel, BoxLayout.Y_AXIS));
		
		//QA문의사항 불러오기
		for (int i = 0; i < vlist.size(); i++) {
			createQAContent(vlist.get(i));
		}

	}

	private static class CustomScrollBarUI extends javax.swing.plaf.basic.BasicScrollBarUI {
        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = new Color(0, 0, 0, 0); // 투명한 thumb 설정
            this.trackColor = new Color(0, 0, 0, 0); // 투명한 track 설정
        }
    }

	public void createQAContent(InquireBean bean, JPanel scrollContentPanel){
		//문의사항 목록 불러오가
		
		JPanel newPanel = new JPanel();
		newPanel.setMaximumSize(new Dimension(32767, 40));
		newPanel.setPreferredSize(new Dimension(10, 40));
		newPanel.setLayout(new BorderLayout());

		JLabel newLabel = new JLabel(bean.getInquire_title());
		newLabel.setBorder(new EmptyBorder(0, 5, 0, 0));
		JPanel deletePanel = new JPanel();
		deletePanel.setBorder(null);

		//삭제버튼
		
		if(checkQA(bean) == false){
			JButton deleteButton = new RoundedButton("삭제", 20);
			deleteButton.setBackground(new Color(102, 153, 255));
			deleteButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));

			deleteButton.setForeground(Color.WHITE);
			deleteButton.setBorder(new EmptyBorder(5, 10, 5, 10));

			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					if(db.deleteInquire(bean.getInquire_num())){
						System.out.println("삭제 성공");
						refreshQAList(bean, scrollContentPanel);
					}
				}
			});
			deletePanel.add(deleteButton);
		}

		
		newPanel.add(newLabel, BorderLayout.CENTER);
		deletePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		newPanel.add(deletePanel, BorderLayout.EAST);
		scrollContentPanel.add(newPanel);

		//문의사항 목록 불러오기
	}

	public void refreshQAList(InquireBean bean, JPanel scrollContentPanel){
		vlist = db.selectinquire(id);
		scrollContentPanel.removeAll();

		createQAContent(bean, scrollContentPanel);
		//QAScrollPanel의 내용만 다시 그림(작성버튼 및 제목 패널 다시 안그림)

		scrollContentPanel.validate();
		scrollContentPanel.repaint();
	}

	public boolean checkQA(InquireBean bean){
		if(bean.getReply_contents() == null){
			return false;
		}else{
			return true;
		}
	}

}
