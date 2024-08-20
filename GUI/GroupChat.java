package GUI;

import java.awt.*;
import javax.swing.*;

public class GroupChat {
    public static void main(String[] args) {
        JFrame frame = new JFrame("단체 채팅");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel chatTitle = new JLabel("제목 예시 1", SwingConstants.CENTER);
        chatTitle.setOpaque(true);
        chatTitle.setBackground(Color.LIGHT_GRAY);
        
        JButton exitButton = new JButton("나가기");
        headerPanel.add(chatTitle, BorderLayout.CENTER);
        headerPanel.add(exitButton, BorderLayout.EAST);
        
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.append("홍길동: AAAA\n");
        chatArea.append("나: DDDDD\n");
        
        JTextField inputField = new JTextField();
        JButton sendButton = new JButton("보내기");
        
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
