package cmp.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Login extends JFrame{

    public Login() throws HeadlessException {
        JFrame frame = new JFrame("직원/알바 선택");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        ImageIcon icon = new ImageIcon("src/image/TestImage.jpg"); // 이미지 경로를 입력
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // RoundedButton 클래스 사용
        JButton employButton = new RoundedButton("직원", 32);
        JButton partTimeButton = new RoundedButton("아르바이트", 32);
        
        employButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        employButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        employButton.setHorizontalAlignment(JButton.CENTER);
        employButton.setVerticalAlignment(JButton.CENTER);
        employButton.setFont(new Font("돋움", Font.PLAIN, 20));
        employButton.setForeground(Color.WHITE);
        employButton.setBackground(Color.BLACK);
        employButton.setOpaque(true);
        employButton.setContentAreaFilled(false);
        employButton.setBorderPainted(false);
        employButton.setFocusPainted(false);
        employButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new EmLogin();
                frame.dispose();
            }
        });

        
        partTimeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        partTimeButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        partTimeButton.setFont(new Font("돋움", Font.PLAIN,20));
        partTimeButton.setForeground(Color.WHITE);
        partTimeButton.setBackground(Color.BLACK);
        partTimeButton.setOpaque(true);
        partTimeButton.setContentAreaFilled(false);
        partTimeButton.setBorderPainted(false);
        partTimeButton.setFocusPainted(false);
        partTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new PTLogin();
                frame.dispose();
            }
        });
       

        employButton.setFocusPainted(false);
        partTimeButton.setFocusPainted(false);

        employButton.setMaximumSize(new Dimension(200,40));
        partTimeButton.setMaximumSize(new Dimension(200,40));

        mainPanel.add(Box.createVerticalStrut(50)); // 상단 여백
        mainPanel.add(imageLabel);
        mainPanel.add(Box.createVerticalStrut(100)); // 이미지와 버튼 사이의 간격
        mainPanel.add(employButton);
        mainPanel.add(Box.createVerticalStrut(20)); // 버튼 간의 간격
        mainPanel.add(partTimeButton);
        mainPanel.add(Box.createVerticalStrut(50)); // 하단 여백

        // 프레임에 메인 패널 추가
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}
