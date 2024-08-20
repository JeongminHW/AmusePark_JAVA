package GUI;

import javax.swing.*;
import java.awt.*;

public class PersonalChat {
    public static void main(String[] args) {
        JFrame frame = new JFrame("개인 채팅");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JLabel chatTitle = new JLabel("홍길동", SwingConstants.CENTER);
        chatTitle.setOpaque(true);
        chatTitle.setBackground(Color.LIGHT_GRAY);
        
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.append("홍길동: AAAA\n");
        chatArea.append("나: DDDDD\n");
        
        JTextField inputField = new JTextField();
        JButton sendButton = new JButton("보내기");
        
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        
        mainPanel.add(chatTitle, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
