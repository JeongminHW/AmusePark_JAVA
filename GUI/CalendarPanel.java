package cmp.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;

import cmp.DB.*;

public class CalendarPanel extends JPanel {

	private static DBConnectionMgr dbConnectionMgr = DBConnectionMgr.getInstance();
	private JPanel calendarPanel;
	private JLabel monthLabel;
	private JButton prevButton, nextButton;
	private int currentMonth;
	private int currentYear;
	private int todayDate;
	private int todayMonth;
	private int todayYear;
	private boolean isDialogOpen = false;
	int check;

	Vector<noticeBean> nb = new Vector<noticeBean>();

	public CalendarPanel() {
		setLayout(new BorderLayout());
		initializeCalendar();
	}

	private void initializeCalendar() {
		// 현재 날짜를 기준으로 초기 설정
		Calendar cal = Calendar.getInstance();
		todayDate = cal.get(Calendar.DAY_OF_MONTH);
		todayMonth = cal.get(Calendar.MONTH);
		todayYear = cal.get(Calendar.YEAR);
		currentMonth = todayMonth;
		currentYear = todayYear;

		// 상단 패널 (월 표시 및 이동 버튼)
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setBackground(Color.WHITE);

		ImageIcon leftIcon = new ImageIcon("src/image/leftArrow.png");
		ImageIcon rightIcon = new ImageIcon("src/image/rightArrow.png");

		prevButton = new RoundedButton("◀", 50);
		nextButton = new RoundedButton("▶", 50);
		monthLabel = new JLabel(getMonthName(currentMonth) + " " + currentYear, JLabel.CENTER);
		monthLabel.setFont(new Font("Arial", Font.BOLD, 15));
		monthLabel.setForeground(Color.BLACK);

		prevButton.setPreferredSize(new Dimension(50, 50));
		nextButton.setPreferredSize(new Dimension(50, 50));
		prevButton.setBorder(null);
		nextButton.setBorder(null);
		topPanel.add(prevButton, BorderLayout.WEST);
		topPanel.add(monthLabel, BorderLayout.CENTER);
		topPanel.add(nextButton, BorderLayout.EAST);

		add(topPanel, BorderLayout.NORTH);

		// 달력 패널 (날짜 버튼)
		calendarPanel = new JPanel();
		calendarPanel.setLayout(new GridLayout(7, 7)); // 7행 7열로 고정
		calendarPanel.setBackground(Color.WHITE);

		updateCalendar();

		add(calendarPanel, BorderLayout.CENTER);

		// 이전/다음 월로 이동하는 버튼 이벤트
		prevButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentMonth--;
				if (currentMonth < 0) {
					currentMonth = 11;
					currentYear--;
				}
				try {
					updateCalendar();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentMonth++;
				if (currentMonth > 11) {
					currentMonth = 0;
					currentYear++;
				}
				try {
					updateCalendar();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void updateCalendar() {
		calendarPanel.removeAll();
		loadScheduleData();

		// 요일 이름 (일 ~ 토)
		String[] days = { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" };
		for (String day : days) {
			JLabel dayLabel = new JLabel(day, JLabel.CENTER);
			calendarPanel.add(dayLabel);
		}

		// 현재 달의 첫 번째 날과 총 일 수 계산
		Calendar cal = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		cal.set(currentYear, currentMonth, 1);

		int startDay = cal.get(Calendar.DAY_OF_WEEK); // 1일의 요일
		int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		// 이전 달의 마지막 날 계산
		Calendar prevMonthCal = Calendar.getInstance();
		prevMonthCal.set(currentYear, currentMonth - 1, 1);
		int daysInPrevMonth = prevMonthCal.getActualMaximum(Calendar.DAY_OF_MONTH);

		// 빈 셀에 이전 달의 마지막 며칠을 채우기
		for (int i = 1; i < startDay; i++) {
			JButton dayButton = new JButton(String.valueOf(daysInPrevMonth - startDay + i + 1));
			dayButton.setForeground(Color.LIGHT_GRAY);
			dayButton.setBackground(Color.WHITE);
			dayButton.setMaximumSize(new Dimension(50, 50));
			dayButton.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			dayButton.setFocusable(false);
			dayButton.setFont(new Font("Arial", Font.PLAIN, 15));
			calendarPanel.add(dayButton);
		}

		// 현재 달 날짜 버튼 채우기
		for (int i = 1; i <= daysInMonth; i++) {
			today.set(currentYear, currentMonth, i);
			JButton dayButton = new JButton(String.valueOf(i));
			dayButton.setMargin(new Insets(0, 0, 0, 0));
			dayButton.setBackground(Color.WHITE);
			dayButton.setPreferredSize(new Dimension(50, 50));
			dayButton.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			dayButton.setFont(new Font("Arial", Font.PLAIN, 15));
			dayButton.setFocusable(false);

			// 오늘 날짜에 동그라미 표시
			if (i == todayDate && currentMonth == todayMonth && currentYear == todayYear) {
				dayButton.setBorder(new LineBorder(new Color(0, 148, 255), 3));
				dayButton.setForeground(new Color(0, 148, 255));
			}

			for (int j = 0; j < nb.size(); j++) {
				String content = nb.get(j).getContent();
				int startD = nb.get(j).getStartD();
				int startM = nb.get(j).getStartM();
				int startY = nb.get(j).getStartY();
				int endD = nb.get(j).getEndD();
				int endM = nb.get(j).getEndM();
				int endY = nb.get(j).getEndY();
				int scno = nb.get(j).getSchedule_num();

				boolean isInRange = false;

				// 일정이 현재 연도와 월에 포함되는 경우만 처리
				if (startY == currentYear && startM == currentMonth + 1) {
					if (startM == endM) {
						if (i >= startD && i <= endD) {
							isInRange = true;
						}
					} else {
						if (i >= startD) {
							isInRange = true;
						}
					}
				} else if (endY == currentYear && endM == currentMonth + 1) {

					if (i <= endD) {
						isInRange = true;
					}
				}
				if (isInRange) {
					Color scheduleColor = new Color((scno * 57) % 256, (scno * 112) % 256, (scno * 159) % 256);
					dayButton.setBorder(new LineBorder(scheduleColor, 3));
					dayButton.setForeground(new Color(0, 148, 255));
				}

			}
			if (today.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				dayButton.setForeground(Color.BLUE);
			} else if (today.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				dayButton.setForeground(Color.RED);
			}

			calendarPanel.add(dayButton);
		}
		// 다음 달의 첫 주 며칠 채우기
		int remainingCells = 49 - calendarPanel.getComponentCount(); // 달력에 49개 셀이 존재 (7x7)
		for (int i = 1; i <= remainingCells; i++) {
			JButton dayButton = new JButton(String.valueOf(i));
			dayButton.setForeground(Color.LIGHT_GRAY);
			dayButton.setBackground(Color.WHITE);
			dayButton.setPreferredSize(new Dimension(50, 50));
			dayButton.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			dayButton.setFocusable(false);
			dayButton.setFont(new Font("Arial", Font.PLAIN, 15));
			calendarPanel.add(dayButton);
		}

		// 레이아웃 갱신
		calendarPanel.revalidate();
		calendarPanel.repaint();

		// 월 레이블 갱신
		monthLabel.setText(getMonthName(currentMonth) + " " + currentYear);
	}

	private String getMonthName(int month) {
		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		return months[month];
	}

	private void loadScheduleData() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = dbConnectionMgr.getConnection();
			String query = "SELECT \r\n" + "    `schedule_contents`,\r\n"
					+ "    YEAR(`schedule_start`) AS schedule_start_year,\r\n"
					+ "    MONTH(`schedule_start`) AS schedule_start_month,\r\n"
					+ "    DAY(`schedule_start`) AS schedule_start_day,\r\n"
					+ "    YEAR(`schedule_end`) AS schedule_end_year,\r\n"
					+ "    MONTH(`schedule_end`) AS schedule_end_month,\r\n"
					+ "    DAY(`schedule_end`) AS schedule_end_day,\r\n" + "    schedule_num\r\n"
					+ "FROM `schedule`;\r\n";

			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				noticeBean bean = new noticeBean();
				bean.setContent(resultSet.getString("schedule_contents"));
				bean.setStartD(resultSet.getInt("schedule_start_day"));
				bean.setStartM(resultSet.getInt("schedule_start_month"));
				bean.setStartY(resultSet.getInt("schedule_start_year"));
				bean.setEndD(resultSet.getInt("schedule_end_day"));
				bean.setEndM(resultSet.getInt("schedule_end_month"));
				bean.setEndY(resultSet.getInt("schedule_end_year"));
				bean.setSchedule_num(resultSet.getInt("schedule_num"));

				nb.addElement(bean);
				// 일정 정보를 화면에 추가

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

	public void printContent(String content) {
		JOptionPane.showMessageDialog(this, content, "일정", JOptionPane.PLAIN_MESSAGE);
	}

}