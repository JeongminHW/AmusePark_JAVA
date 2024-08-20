package GUI;

import java.awt.*;
import javax.swing.*;

public class CreateChatRoom {
    public static void main(String[] args) {
        JFrame frame = new JFrame("채팅방 생성");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JTextField chatRoomName = new JTextField("채팅방 이름을 입력하세요");
        mainPanel.add(chatRoomName, BorderLayout.NORTH);
        
        JPanel userListPanel = new JPanel(new GridLayout(7, 3));
        for (int i = 0; i < 7; i++) {
            JLabel userName = new JLabel("홍길동");
            JLabel department = new JLabel("영업부");
            JLabel rank = new JLabel("사원");
            JCheckBox selectBox = new JCheckBox();
            selectBox.setSelected(true);
            
            userListPanel.add(userName);
            userListPanel.add(department);
            userListPanel.add(rank);
            userListPanel.add(selectBox);
        }
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelButton = new JButton("취소");
        JButton createButton = new JButton("만들기");
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(createButton);
        
        mainPanel.add(new JScrollPane(userListPanel), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
