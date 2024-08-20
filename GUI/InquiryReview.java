package GUI;

import java.awt.*;
import javax.swing.*;

public class InquiryReview {
    public static void main(String[] args) {
        JFrame frame = new JFrame("문의사항 검토");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        JPanel mainPanel = new JPanel(new GridLayout(6, 3));
        
        String[] titles = {"제목 1", "제목 2", "제목 3", "제목 4", "제목 5"};
        String[] contents = {"내용 1", "내용 2", "내용 3", "내용 4", "내용 5"};
        
        for (int i = 0; i < titles.length; i++) {
            JLabel titleLabel = new JLabel(titles[i]);
            JLabel contentLabel = new JLabel(contents[i]);
            JButton modifyButton = new JButton("수정");
            JButton replyButton = new JButton("답변하기");
            JCheckBox checkBox = new JCheckBox();
            
            mainPanel.add(titleLabel);
            mainPanel.add(contentLabel);
            mainPanel.add(checkBox);
            mainPanel.add(modifyButton);
            mainPanel.add(replyButton);
        }
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
