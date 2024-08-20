package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class EmployeeMain{
	static String id;
	
    public static String getId() {
		return id;
	}

	public static void setId(String id) {
		EmployeeMain.id = id;
	}

	public static final Color LIGHT_ONE = new Color(200, 200, 200);
    public static final Color REAL_LIGHT = new Color(228,228,228);
    JFrame frame = new JFrame();

    public EmployeeMain() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);

        //Panels
        JPanel mainPanel = new JPanel();
        JPanel calendarPanel = new CalendarPanel();
       
        JPanel appPanel = new JPanel();
        //JPanel chatBox = new JPanel();
        JPanel upperLeft = new JPanel();
        JPanel downerLeft = new JPanel();
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        //Todo
        JPanel todoPanel = new JPanel();
        //Containers
        CentralDropShadowPanel calendarContainer = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
        CentralDropShadowPanel todoContainer = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
        CentralDropShadowPanel appContainer = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
        CentralDropShadowPanel chatBox = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
        //Apps
        ImageIcon icon = new ImageIcon("src/image/leftArrow.png");
        JPanel vacation = new Appmake(25, "휴가", icon);
        JPanel date = new Appmake(25,"일정", icon);
        JPanel myPage = new Appmake(25,"마이페이지", icon);
        JPanel ask = new Appmake(25, "문의사항", icon);
        JPanel todo = new Appmake(25, "할일", icon);
        JPanel work = new Appmake(25, "출근/퇴근", icon);
        JPanel userCheck = new Appmake(25, "사용자 집계", icon);
        JPanel itemCheck = new Appmake(25, "물품 관리", icon);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBackground(Color.WHITE);

        upperLeft.setLayout(new BoxLayout(upperLeft, BoxLayout.X_AXIS));
        upperLeft.setBackground(Color.WHITE);

        calendarContainer.setLayout(new BoxLayout(calendarContainer, BoxLayout.Y_AXIS));
        calendarContainer.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        appPanel.setLayout(new BoxLayout(appPanel, BoxLayout.X_AXIS));
        appPanel.setBackground(Color.WHITE);

        appContainer.setLayout(new BoxLayout(appContainer, BoxLayout.Y_AXIS));
        appContainer.setBackground(Color.WHITE);
        appContainer.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        chatBox.setLayout(new BoxLayout(chatBox, BoxLayout.Y_AXIS));
        chatBox.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));

        todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.Y_AXIS));
        todoPanel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));

        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        right.setBackground(Color.WHITE);

        calendarContainer.setBackground(Color.WHITE);
        downerLeft.setBackground(Color.DARK_GRAY);
        //calendarPanel.setBackground(Color.GREEN);
        todoPanel.setBackground(Color.WHITE);
        
        
        chatBox.setBackground(Color.WHITE);
        left.setBackground(Color.WHITE);
        todoContainer.setBackground(Color.WHITE);
       
        //Todo Panel
        todoPanel.setPreferredSize(new Dimension(260,200));
        JPanel TodoTitle = new JPanel();
        JLabel TodolistLabel = new JLabel("할일");

        TodoTitle.setLayout(new BoxLayout(TodoTitle, BoxLayout.X_AXIS));
        TodoTitle.setOpaque(false);
        TodolistLabel.setFont(new Font("돋움", Font.BOLD,24));
        TodoTitle.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
        TodoTitle.add(TodolistLabel);
        TodoTitle.add(Box.createHorizontalStrut(200));
        todoPanel.add(TodoTitle);

        for (int i = 0; i < 5; i++) {
            String str = "AAAAAA";
            JTextArea stringContainer = new JTextArea();
            stringContainer.setEditable(false);
            stringContainer.setFocusable(false);
            stringContainer.setText(" ● " + str);
            todoPanel.add(stringContainer);
        }

        //chatbox
        chatBox.setBorder(BorderFactory.createEmptyBorder(5, 6, 20, 6));
        JPanel chatTitle = new JPanel();
        JLabel chat = new JLabel("채팅");
        JButton makeChat = new RoundedButton(icon, 20);
        chatTitle.setLayout(new FlowLayout());
        chatTitle.setOpaque(false);
        //chatTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        //chatTitle.setBackground(Color.BLUE);
        makeChat.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        chat.setFont(new Font("돋움", Font.BOLD, 24));
        makeChat.setMaximumSize(new Dimension(40,40));
        makeChat.setPreferredSize(new Dimension(40,40));
        chatTitle.add(chat);
        chatTitle.add(Box.createHorizontalStrut(300));
        chatTitle.add(makeChat);
        chatBox.add(chatTitle);
        for (int i = 0; i < 8; i++) {
            JPanel chatlistPanel = new JPanel();
            chatlistPanel.setLayout(new BoxLayout(chatlistPanel, BoxLayout.Y_AXIS));
            //chatlistPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            JTextArea chatRoomName = new JTextArea();
            JTextArea recentChat = new JTextArea();
            chatRoomName.setText("AAAAAAAAAAAAAAAAAAAA");
            recentChat.setText("   " + "BBBBBBBBBBBBBBBBBBBB");

            chatRoomName.setFont(new Font("Arial", Font.BOLD, 20));
            recentChat.setFont(new Font("Arial", Font.PLAIN, 16));

            chatRoomName.setEditable(false);
            chatRoomName.setFocusable(false);
            recentChat.setEditable(false);
            recentChat.setFocusable(false);

            chatRoomName.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            recentChat.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

            chatlistPanel.add(chatRoomName);
            chatlistPanel.add(recentChat);
            //chatlistPanel.setMaximumSize(new Dimension(400,60));
            chatlistPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            //todoPanel.setBorder(BorderFactory.createEmptyBorder(10,0,20,0));
            if(i%2==0){
                chatlistPanel.setBackground(LIGHT_ONE);
                chatRoomName.setBackground(LIGHT_ONE);
                recentChat.setBackground(LIGHT_ONE);
            }else{
                chatlistPanel.setBackground(REAL_LIGHT);
                chatRoomName.setBackground(REAL_LIGHT);
                recentChat.setBackground(REAL_LIGHT);
            }
            chatBox.add(chatlistPanel);
        }


        //Sizes
        left.setPreferredSize(new Dimension(720,600));
        chatBox.setMaximumSize(new Dimension(450,600));
        upperLeft.setMaximumSize(new Dimension(700,350));
        downerLeft.setMaximumSize(new Dimension(700,240));
        calendarContainer.setMaximumSize(new Dimension(350,350));
        todoContainer.setMaximumSize(new Dimension(340,350));
        //appContainer.setMaximumSize(new Dimension(700,240));
        appPanel.setMaximumSize(new Dimension(660,200));

        right.setPreferredSize(new Dimension(450,600));
        chatBox.setPreferredSize(new Dimension(430,510));
        appPanel.add(vacation);
        appPanel.add(Box.createHorizontalStrut(20));
        appPanel.add(date);
        appPanel.add(Box.createHorizontalStrut(20));
        appPanel.add(myPage);
        appPanel.add(Box.createHorizontalStrut(20));
        appPanel.add(ask);
        appPanel.add(Box.createHorizontalStrut(20));
        appPanel.add(todo);
        appPanel.add(Box.createHorizontalStrut(20));
        appPanel.add(work);
        appPanel.add(Box.createHorizontalStrut(20));
        appPanel.add(userCheck);
        appPanel.add(Box.createHorizontalStrut(20));
        appPanel.add(itemCheck);

        calendarContainer.add(calendarPanel);
        todoContainer.add(todoPanel);
        appContainer.add(appPanel);

        upperLeft.add(calendarContainer);
        upperLeft.add(Box.createHorizontalStrut(10));
        upperLeft.add(todoContainer);

        //left.add(Box.createHorizontalStrut(10));
        left.add(upperLeft);
        left.add(appContainer);
        left.add(Box.createHorizontalStrut(20));
        left.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        right.setBorder(BorderFactory.createEmptyBorder(12,0,20,20));
        right.add(chatBox);
        //left.add(Box.createHorizontalStrut(10));

        mainPanel.add(left);
        mainPanel.add(right);
        //mainPanel.add(Box.createHorizontalStrut(10));
        
        

        frame.add(mainPanel);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        new EmployeeMain();
    }

}

