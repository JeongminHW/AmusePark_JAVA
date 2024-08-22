package cmp.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cmp.DB.*;

public class NoticeView extends JFrame implements ActionListener{
	static String id;
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private static DBConnectionMgr dbConnectionMgr = DBConnectionMgr.getInstance();
	JButton closeButton;
	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		NoticeView.id = id;
	}

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

	public NoticeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 300);
		setVisible(true);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setBackground(Color.WHITE);
		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		CentralDropShadowPanel leftPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 30);
		leftPanel.setPreferredSize(new Dimension(210, 34));
		leftPanel.setMaximumSize(new Dimension(210, 32767));
		leftPanel.setBackground(Color.WHITE);
		mainPanel.add(leftPanel);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		JPanel calendarPanel = new CalendarPanel();
		calendarPanel.setMaximumSize(new Dimension(200, Integer.MAX_VALUE));
		calendarPanel.setBorder(new EmptyBorder(15, 5, 15, 5));
		calendarPanel.setOpaque(false);
		leftPanel.add(calendarPanel);
		calendarPanel.setLayout(new BoxLayout(calendarPanel, BoxLayout.Y_AXIS));

		CentralDropShadowPanel rightPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 30);
		rightPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 290));
		rightPanel.setBackground(Color.WHITE);
		mainPanel.add(rightPanel);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		rightPanel.add(titlePanel);

		JLabel titleLabel = new JLabel("일정");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		titlePanel.add(titleLabel);

		JPanel datePanel = new JPanel();
		datePanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		datePanel.setOpaque(false);
		rightPanel.add(datePanel);
		datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS));

		// 일정 데이터를 가져와서 표시하는 메서드 호출
		loadScheduleData(datePanel);

		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		closeButton = new RoundedButton("닫기",20);
		panel.add(closeButton);
		rightPanel.add(panel, BorderLayout.SOUTH);
		closeButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == closeButton) {
			dispose();
		}
	}

	private void loadScheduleData(JPanel datePanel) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = dbConnectionMgr.getConnection();
			String query = "SELECT schedule_contents, DATE_FORMAT(schedule_start, '%m-%d') AS schedule_start, DATE_FORMAT(schedule_end, '%m-%d') AS schedule_end FROM SCHEDULE"; // 전체
																																													// 데이터를
																																													// 가져옴
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String content = resultSet.getString("schedule_contents");
				String startDate = resultSet.getString("schedule_start");
				String endDate = resultSet.getString("schedule_end");

				// 일정 정보를 화면에 추가
				JLabel scheduleLabel = new JLabel("● " + startDate + " ~ " + endDate + " " + content);
				scheduleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
				datePanel.add(scheduleLabel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (connection != null)
				dbConnectionMgr.freeConnection(connection);
		}
	}
}