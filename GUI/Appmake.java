package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Appmake extends JPanel {
    JLabel appLabel;
    JButton appButton;
    public Appmake(int radius, String label) {
        appButton = new RoundedButton("", radius);
        appLabel = new JLabel(label);

        appButton.setMinimumSize(new Dimension(50,50));   // 최소 크기 설정
        appButton.setMaximumSize(new Dimension(50,50));   // 최대 크기 설정
        appButton.setPreferredSize(new Dimension(50,50));
        appButton.setBorder(null);
        appButton.setFocusable(false);
        appButton.setBackground(Color.white);

        appLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        appLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(null);
        setBackground(Color.WHITE);

        add(appButton);
        add(Box.createVerticalStrut(10));  // 간격 조정
        add(appLabel);
    }

    public Appmake(int radius, String label, ImageIcon icon) {
        appButton = new RoundedButton(icon, radius);
        appLabel = new JLabel(label);

        appButton.setMinimumSize(new Dimension(60, 60));   // 최소 크기 설정
        appButton.setMaximumSize(new Dimension(60,60));   // 최대 크기 설정
        appButton.setBorder(null);
        appButton.setFocusable(false);
        appButton.setBackground(Color.BLACK);
        appButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        appButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        appLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        appLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        appButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

            }
        });
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(null);
        setBackground(Color.WHITE);

        add(appButton);
        add(Box.createVerticalStrut(10));  // 간격 조정
        add(appLabel);
        // 아이콘으로 초기화하는 생성자 구현 (필요 시)
    }

    public Appmake(int radius, String label, Color c) {
        appButton = new RoundedButton("", radius);
        appLabel = new JLabel(label);

        appButton.setMaximumSize(new Dimension(50,50));   // 최대 크기 설정
        appButton.setPreferredSize(new Dimension(50,50));
        appButton.setBorder(null);
        appButton.setFocusable(false);
        appButton.setBackground(c);

        //appLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //appLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());
        setBorder(null);
        setBackground(Color.WHITE);

        add(appButton, BorderLayout.NORTH);
        add(Box.createVerticalStrut(10));  // 간격 조정
        add(appLabel, BorderLayout.SOUTH);
    }

    
}
