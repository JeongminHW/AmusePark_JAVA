package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.BorderLayout;

public class NoticeView extends JFrame implements ActionListener{
	
	static String id;

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		NoticeView.id = id;
	}

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	CentralDropShadowPanel leftPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY,30);
	CentralDropShadowPanel rightPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 30);
	JPanel calendarPanel;
	JPanel TitlePanel;
	JPanel datePanel;
	JPanel contentPanel;
	JLabel titleLabel;
	JLabel dateExample1Label;
	JButton closeButton;
	
	DBMgr db = new DBMgr();
	Vector<ScheduleBean> vlist;
	/**
	 * Create the frame.
	 */
	public NoticeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 300);
		setVisible(true);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setBackground(Color.WHITE);
		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		leftPanel.setPreferredSize(new Dimension(210, 34));
		leftPanel.setMaximumSize(new Dimension(210, 32767));
		leftPanel.setBackground(Color.WHITE);
		mainPanel.add(leftPanel);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		calendarPanel = new CalendarPanel();
		calendarPanel.setMaximumSize(new Dimension(200, 2147483647));
		calendarPanel.setBorder(new EmptyBorder(15, 5, 15, 5));
		calendarPanel.setOpaque(false);
		leftPanel.add(calendarPanel);
		calendarPanel.setLayout(new BoxLayout(calendarPanel, BoxLayout.Y_AXIS));
		
		rightPanel.setMaximumSize(new Dimension(32767, 290));
		rightPanel.setBackground(Color.WHITE);
		mainPanel.add(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		TitlePanel = new JPanel();
		TitlePanel.setOpaque(false);
		TitlePanel.setMaximumSize(new Dimension(32767, 40));
		FlowLayout flowLayout_1 = (FlowLayout) TitlePanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		rightPanel.add(TitlePanel, BorderLayout.NORTH);
		
		titleLabel = new JLabel("일정");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		TitlePanel.add(titleLabel);
		TitlePanel.add(Box.createHorizontalStrut(300));
		
		closeButton = new RoundedButton("닫기",20);
		TitlePanel.add(closeButton, BorderLayout.SOUTH);
		
		datePanel = new JPanel();
		datePanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		datePanel.setOpaque(false);
		rightPanel.add(datePanel);
		datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
		
		contentPanel = new JPanel();
		contentPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPanel.setOpaque(false);
		contentPanel.setMaximumSize(new Dimension(32767, 40));
		FlowLayout flowLayout = (FlowLayout) contentPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		datePanel.add(contentPanel);
		
		dateExample1Label = new JLabel("● 8/10 ~ 9~10 어트렉션 점검");
		JLabel dateExample1Label1 = new JLabel("● 8/10 ~ 9~10 어트렉션 점검");
		dateExample1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
		dateExample1Label.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		contentPanel.add(dateExample1Label);
		contentPanel.add(dateExample1Label1);
		
		closeButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == closeButton) {
			dispose();
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoticeView frame = new NoticeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
