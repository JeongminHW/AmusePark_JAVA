package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class CalendarPanel extends JPanel {

    private JPanel calendarPanel;
    private JLabel monthLabel;
    private JButton prevButton, nextButton;
    private int currentMonth;
    private int currentYear;
    private int todayDate;
    private int todayMonth;
    private int todayYear;

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

        prevButton.setPreferredSize(new Dimension(50,50));
        nextButton.setPreferredSize(new Dimension(50,50));
        prevButton.setBorder(null);
        nextButton.setBorder(null);
        topPanel.add(prevButton, BorderLayout.WEST);
        topPanel.add(monthLabel, BorderLayout.CENTER);
        topPanel.add(nextButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // 달력 패널 (날짜 버튼)
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(7, 7));  // 7행 7열로 고정
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
                updateCalendar();
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
                updateCalendar();
            }
        });
    }

    private void updateCalendar() {
        calendarPanel.removeAll();

        // 요일 이름 (일 ~ 토)
        String[] days = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
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

            if (today.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                dayButton.setForeground(Color.BLUE);
            } else if (today.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                dayButton.setForeground(Color.RED);
            }

            // 특정 날짜 강조 표시 ---- DB 연동해서 수정 필요
            if (i == 21 || i == 22) {
                dayButton.setBackground(new Color(0, 148, 255));
                dayButton.setForeground(Color.WHITE);
            } else if (i == 9 || i == 10 || i == 11 || i == 12 || i == 13) {
                dayButton.setBackground(new Color(255, 199, 0));
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
        String[] months = {"January", "February", "March", "April", "May", "June",
                           "July", "August", "September", "October", "November", "December"};
        return months[month];
    }

    // public static void main(String[] args) {
    //     JFrame frame = new JFrame("Calendar Test");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.setSize(600, 600);
    //     frame.add(new CalendarPanel());
    //     frame.setVisible(true);
    // }
}
