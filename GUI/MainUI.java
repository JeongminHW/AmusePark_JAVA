package cmp.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainUI{
	// 로그인한 아이디 받아오기
	static String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	public MainUI() {
        JFrame frame = new JFrame("Main UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setTitle("Main UI - " + getId());
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        
        JPanel calendarPanel = createCalendarPanel();
        JPanel todoPanel = createTodoPanel();
        JPanel chatPanel = createChatPanel();
        JPanel menuPanel = createMenuPanel();
        
        panel.add(calendarPanel, BorderLayout.WEST);
        panel.add(todoPanel, BorderLayout.CENTER);
        panel.add(chatPanel, BorderLayout.EAST);
        panel.add(menuPanel, BorderLayout.SOUTH);
        
        frame.add(panel);
        frame.setVisible(true);
	}
    
    public JPanel createCalendarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel monthLabel = new JLabel("8월", SwingConstants.CENTER);
        JButton prevButton = new JButton("<");
        JButton nextButton = new JButton(">");
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(prevButton, BorderLayout.WEST);
        topPanel.add(monthLabel, BorderLayout.CENTER);
        topPanel.add(nextButton, BorderLayout.EAST);
        
        String[] days = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
        JPanel dayPanel = new JPanel(new GridLayout(7, 7, 5, 5));
        for (String day : days) {
            dayPanel.add(new JLabel(day, SwingConstants.CENTER));
        }
        
        for (int i = 1; i <= 31; i++) {
            JButton dayButton = new JButton(String.valueOf(i));
            dayButton.setBackground(i == 6 ? Color.CYAN : Color.YELLOW);
            dayPanel.add(dayButton);
        }
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(dayPanel, BorderLayout.CENTER);
        return panel;
    }
    
    public JPanel createTodoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("오늘 할 일");
        JTextArea todoArea = new JTextArea("A\nB\nC\nD");
        
        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(todoArea), BorderLayout.CENTER);
        return panel;
    }
    
    public JPanel createChatPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("채팅");
        JTextArea chatArea = new JTextArea("홍길동: AAAAA");
        
        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        return panel;
    }
    
    public JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 4, 10, 10));
        String[] buttons = {"휴가", "일정", "마이페이지", "문의 사항", "파일 공유", "물품 관리", "사용자 집계", "할 일"};
        for (String button : buttons) {
            JButton menuButton = new JButton(button);
            menuButton.setBackground(Color.GREEN);
            menuButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String btnText = menuButton.getText();
					if(btnText.equals("휴가")) {
						
					}
					else if(btnText.equals("일정")) {
						
					}
					else if(btnText.equals("마이페이지")) {
						MyPage mypage = new MyPage();
						mypage.setEm_id(id);
						new MyPage();
					}
					else if(btnText.equals("문의 사항")) {
						
					}
					else if(btnText.equals("파일 공유")) {
						
					}
					else if(btnText.equals("물품 관리")) {
						
					}
					else if(btnText.equals("사용자 집계")) {
						
					}
					else if(btnText.equals("할 일")) {
						
					}
				}
			});
            panel.add(menuButton);
        }
        
        return panel;
    }
    

    public static void main(String[] args) {
    	new MainUI();
    }

}
