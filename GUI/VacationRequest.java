package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class VacationRequest{
    
    public VacationRequest() {
        JFrame frame = new JFrame("휴가 신청");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

    //Define
        JPanel mainPanel = new JPanel();
        CentralDropShadowPanel left = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
        JPanel right = new JPanel();
        CentralDropShadowPanel rightUpper = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
        CentralDropShadowPanel rightDowner = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
        //left
        JPanel calendarPanel = new CalendarPanel();
        //right upper
        JPanel vacationDateTitlePanel = new JPanel();
        JTextArea dateTextArea = new JTextArea();
        JLabel vacationDateTitleLabel = new JLabel("일정");

        //right downer
        JPanel vacationTitlePanel = new JPanel();
        JLabel vacationTitleLabel = new JLabel("휴가 신청");
        JPanel vacationDateContainer = new JPanel();
        
        
        //Lines
        JPanel line[] = new JPanel[6];
        for(int i = 0 ;i < 6;i++){
            line[i] = new DrawLine(); // 줄을 그릴 패널 생성
            line[i].setBackground(Color.BLACK);
            line[i].setMaximumSize(new Dimension(80,3));
        }
        JPanel longline = new JPanel();
        longline = new DrawLine();
        longline.setBackground(Color.BLACK);
        longline.setMaximumSize(new Dimension(300,3));

        //SPanel
        JPanel vacationSPanel = new JPanel();  
        JPanel vacationSYearPanel = new JPanel();
        String[] Yparts = {"2020", "2021", "2022", "2023", "2024"};
        JComboBox<String> vacationSYearComboBox = new JComboBox<>(Yparts);
        JLabel vacationSYearLabel = new JLabel("년");
        JPanel vacationSMonthPanel = new JPanel();
        String[] Mparts = {"01 ", "02", "03", "04", "05"};
        JComboBox<String> vacationSMonthComboBox = new JComboBox<>(Mparts);
        JLabel vacationSMonthLabel = new JLabel("월");
        JPanel vacationSDatePanel = new JPanel();
        String[] Dparts = {"01", "02", "03", "04", "05"};
        JComboBox<String> vacationSDateComboBox = new JComboBox<>(Dparts);
        JLabel vacationSDateLabel = new JLabel("일 부터");
        //EPanel
        JPanel vacationEPanel = new JPanel();
        JPanel vacationEYearPanel = new JPanel();
        JComboBox<String> vacationEYearComboBox = new JComboBox<>(Yparts);
        JLabel vacationEYearLabel = new JLabel("년");
        JPanel vacationEMonthPanel = new JPanel();
        JComboBox<String> vacationEMonthComboBox = new JComboBox<>(Mparts);
        JLabel vacationEMonthLabel = new JLabel("월");
        JPanel vacationEDatePanel = new JPanel();
        JComboBox<String> vacationEDateComboBox = new JComboBox<>(Dparts);
        JLabel vacationEDateLabel = new JLabel("일 까지");
        //ReasonPanel
        JPanel vacationSubTitle = new JPanel();
        JPanel vacationReasonPanel = new JPanel();
        JLabel vacationReasonLabel = new JLabel("사유");
        JTextField vacationReasionField = new JTextField();
        JPanel vacationLeft = new JPanel();
        JLabel vacationLeftLabel = new JLabel("남은 휴가일수 : ");
        JButton confirmButton = new RoundedButton("확인", 20);
    //Fucntion
        vacationSYearComboBox.setUI(new BasicComboBoxUI(){
            @Override
            protected void installDefaults(){
                super.installDefaults();
                LookAndFeel.uninstallBorder(vacationSYearComboBox);
            }
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton("▼");
                button.setOpaque(false);
                //button.setBackground(Color.BLUE);
                button.setMaximumSize(new Dimension(10,10));
                button.setPreferredSize(new Dimension(10,10));
                
                button.setBorder(BorderFactory.createEmptyBorder()); // 버튼 경계선 제거
                button.setBorderPainted(false); // 경계선 페인팅 비활성화
                button.setContentAreaFilled(false); // 버튼 내부 채우기 비활성화
                button.setFocusPainted(false); // 포커스 경계선 비활성화
                button.setFocusable(false);
                button.setName("ComboBox.arrowButton"); //Mandatory, as per BasicComboBoxUI#createArrowButton().
                return button;
            }

        });
        vacationSMonthComboBox.setUI(new BasicComboBoxUI(){
            @Override
            protected void installDefaults(){
                super.installDefaults();
                LookAndFeel.uninstallBorder(vacationSMonthComboBox);
            }
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton("▼");
                button.setOpaque(false);
                //button.setBackground(Color.BLUE);
                button.setMaximumSize(new Dimension(10,10));
                button.setPreferredSize(new Dimension(10,10));
                
                button.setBorder(BorderFactory.createEmptyBorder()); // 버튼 경계선 제거
                button.setBorderPainted(false); // 경계선 페인팅 비활성화
                button.setContentAreaFilled(false); // 버튼 내부 채우기 비활성화
                button.setFocusPainted(false); // 포커스 경계선 비활성화
                button.setFocusable(false);
                button.setName("ComboBox.arrowButton"); //Mandatory, as per BasicComboBoxUI#createArrowButton().
                return button;
            }

        });
        vacationSDateComboBox.setUI(new BasicComboBoxUI(){
            @Override
            protected void installDefaults(){
                super.installDefaults();
                LookAndFeel.uninstallBorder(vacationSDateComboBox);
            }
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton("▼");
                button.setOpaque(false);
                //button.setBackground(Color.BLUE);
                button.setMaximumSize(new Dimension(10,10));
                button.setPreferredSize(new Dimension(10,10));
                
                button.setBorder(BorderFactory.createEmptyBorder()); // 버튼 경계선 제거
                button.setBorderPainted(false); // 경계선 페인팅 비활성화
                button.setContentAreaFilled(false); // 버튼 내부 채우기 비활성화
                button.setFocusPainted(false); // 포커스 경계선 비활성화
                button.setFocusable(false);
                button.setName("ComboBox.arrowButton"); //Mandatory, as per BasicComboBoxUI#createArrowButton().
                return button;
            }

        });
        vacationEYearComboBox.setUI(new BasicComboBoxUI(){
            @Override
            protected void installDefaults(){
                super.installDefaults();
                LookAndFeel.uninstallBorder(vacationEYearComboBox);
            }
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton("▼");
                button.setOpaque(false);
                //button.setBackground(Color.BLUE);
                button.setMaximumSize(new Dimension(10,10));
                button.setPreferredSize(new Dimension(10,10));
                
                button.setBorder(BorderFactory.createEmptyBorder()); // 버튼 경계선 제거
                button.setBorderPainted(false); // 경계선 페인팅 비활성화
                button.setContentAreaFilled(false); // 버튼 내부 채우기 비활성화
                button.setFocusPainted(false); // 포커스 경계선 비활성화
                button.setFocusable(false);
                button.setName("ComboBox.arrowButton"); //Mandatory, as per BasicComboBoxUI#createArrowButton().
                return button;
            }

        });
        vacationEMonthComboBox.setUI(new BasicComboBoxUI(){
            @Override
            protected void installDefaults(){
                super.installDefaults();
                LookAndFeel.uninstallBorder(vacationEMonthComboBox);
            }
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton("▼");
                button.setOpaque(false);
                //button.setBackground(Color.BLUE);
                button.setMaximumSize(new Dimension(10,10));
                button.setPreferredSize(new Dimension(10,10));
                
                button.setBorder(BorderFactory.createEmptyBorder()); // 버튼 경계선 제거
                button.setBorderPainted(false); // 경계선 페인팅 비활성화
                button.setContentAreaFilled(false); // 버튼 내부 채우기 비활성화
                button.setFocusPainted(false); // 포커스 경계선 비활성화
                button.setFocusable(false);
                button.setName("ComboBox.arrowButton"); //Mandatory, as per BasicComboBoxUI#createArrowButton().
                return button;
            }

        });
        vacationEDateComboBox.setUI(new BasicComboBoxUI(){
            @Override
            protected void installDefaults(){
                super.installDefaults();
                LookAndFeel.uninstallBorder(vacationEDateComboBox);
            }
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton("▼");
                button.setOpaque(false);
                //button.setBackground(Color.BLUE);
                button.setMaximumSize(new Dimension(10,10));
                button.setPreferredSize(new Dimension(10,10));
                
                button.setBorder(BorderFactory.createEmptyBorder()); // 버튼 경계선 제거
                button.setBorderPainted(false); // 경계선 페인팅 비활성화
                button.setContentAreaFilled(false); // 버튼 내부 채우기 비활성화
                button.setFocusPainted(false); // 포커스 경계선 비활성화
                button.setFocusable(false);
                button.setName("ComboBox.arrowButton"); //Mandatory, as per BasicComboBoxUI#createArrowButton().
                return button;
            }

        });

    //Setting
        //Layout
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        rightUpper.setLayout(new BoxLayout(rightUpper, BoxLayout.Y_AXIS));
        vacationDateTitlePanel.setLayout(new BoxLayout(vacationDateTitlePanel, BoxLayout.X_AXIS));
        rightDowner.setLayout(new BoxLayout(rightDowner, BoxLayout.Y_AXIS));
        vacationTitlePanel.setLayout(new FlowLayout());
        vacationDateContainer.setLayout(new BoxLayout(vacationDateContainer, BoxLayout.Y_AXIS));
        vacationSPanel.setLayout(new BoxLayout(vacationSPanel, BoxLayout.X_AXIS));
        vacationEPanel.setLayout(new BoxLayout(vacationEPanel, BoxLayout.X_AXIS));
        vacationSubTitle.setLayout(new FlowLayout());
        vacationReasonPanel.setLayout(new BoxLayout(vacationReasonPanel, BoxLayout.Y_AXIS));
        vacationLeft.setLayout(new BoxLayout(vacationLeft, BoxLayout.X_AXIS));
        vacationSYearPanel.setLayout(new BoxLayout(vacationSYearPanel, BoxLayout.Y_AXIS));
        vacationSMonthPanel.setLayout(new BoxLayout(vacationSMonthPanel, BoxLayout.Y_AXIS));
        vacationSDatePanel.setLayout(new BoxLayout(vacationSDatePanel, BoxLayout.Y_AXIS));

        //Aligns
        rightUpper.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightDowner.setAlignmentX(Component.RIGHT_ALIGNMENT);

        //Borders
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        left.setBorder(new EmptyBorder(20, 10, 20, 10));
        rightUpper.setBorder(new EmptyBorder(15, 10, 20, 10));
        rightDowner.setBorder(new EmptyBorder(20,10,20,10));
        vacationDateContainer.setBorder(new EmptyBorder(0,10,0,10));
        vacationSYearComboBox.setBorder(new EmptyBorder(0, 15, 0, 10));
        vacationSMonthComboBox.setBorder(new EmptyBorder(0, 15, 0, 10));
        vacationSDateComboBox.setBorder(new EmptyBorder(0, 15, 0, 10));
        vacationEYearPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        vacationEYearComboBox.setBorder(new EmptyBorder(0, 15, 0, 10));
        vacationEMonthComboBox.setBorder(new EmptyBorder(0, 15, 0, 10));
        vacationEDateComboBox.setBorder(new EmptyBorder(0, 15, 0, 10));
        vacationEPanel.setBorder(new EmptyBorder(5, 0, 5, 0));


        //Sizes
        // calendarPanel.setMaximumSize(new Dimension(350,320));
        // vacationSYearPanel.setMaximumSize(new Dimension(80,30));
        // vacationSMonthPanel.setMaximumSize(new Dimension(80,30));
        // vacationSDatePanel.setMaximumSize(new Dimension(80,30));
        // vacationEYearPanel.setMaximumSize(new Dimension(60,50));
        // //vacationEYearComboBox.setPreferredSize(new Dimension(40,25));
        // vacationEMonthPanel.setMaximumSize(new Dimension(60,50));
        // vacationEDatePanel.setMaximumSize(new Dimension(60,50));


        //ComboBox Sizes
        // Dimension comboBoxSize = new Dimension(80, 25);
        // // vacationSYearComboBox.setMaximumSize(comboBoxSize);
        // // vacationSMonthComboBox.setMaximumSize(comboBoxSize);
        // // vacationSDateComboBox.setMaximumSize(comboBoxSize);
        // vacationEYearComboBox.setPreferredSize(comboBoxSize);
        // vacationEMonthComboBox.setPreferredSize(comboBoxSize);
        // vacationEDateComboBox.setPreferredSize(comboBoxSize);

        //backgrounds
        mainPanel.setBackground(Color.WHITE);
        left.setBackground(Color.WHITE);
        right.setBackground(Color.WHITE);
        rightUpper.setBackground(Color.WHITE);
        rightDowner.setBackground(Color.WHITE);
        vacationDateTitlePanel.setBackground(Color.WHITE);
        vacationEYearPanel.setBackground(Color.BLUE);
        vacationEYearComboBox.setBackground(Color.RED);
        confirmButton.setBackground(new Color(0,148,255));
        confirmButton.setForeground(Color.WHITE);
        //Text - Font Setting
        vacationDateTitleLabel.setFont(new Font("돋움", Font.BOLD, 24));
        vacationTitleLabel.setFont(new Font("돋움", Font.BOLD, 20));
        vacationReasonLabel.setFont(new Font("돋움", Font.BOLD, 16));

        //Focusable
        vacationSYearComboBox.setFocusable(false);
        vacationSMonthComboBox.setFocusable(false);
        vacationSDateComboBox.setFocusable(false);
        vacationEYearComboBox.setFocusable(false);
        vacationEMonthComboBox.setFocusable(false);
        vacationEDateComboBox.setFocusable(false);

    //Add
        vacationSYearPanel.add(vacationSYearComboBox);
        vacationSYearPanel.add(line[0]);
        vacationSMonthPanel.add(vacationSMonthComboBox);
        vacationSMonthPanel.add(line[1]);
        vacationSDatePanel.add(vacationSDateComboBox);
        vacationSDatePanel.add(line[2]);

        vacationEYearPanel.add(vacationEYearComboBox);
        vacationEMonthPanel.add(vacationEMonthComboBox);
        vacationEDatePanel.add(vacationEDateComboBox);

        vacationEYearPanel.add(line[3]);
        vacationEMonthPanel.add(line[4]);
        vacationEDatePanel.add(line[5]);

        vacationSPanel.add(vacationSYearPanel);
        vacationSPanel.add(vacationSYearLabel);
        vacationSPanel.add(vacationSMonthPanel);
        vacationSPanel.add(vacationSMonthLabel);
        vacationSPanel.add(vacationSDatePanel);
        vacationSPanel.add(vacationSDateLabel);
        
        vacationEPanel.add(vacationEYearPanel);
        vacationEPanel.add(vacationEYearLabel);
        vacationEPanel.add(vacationEMonthPanel);
        vacationEPanel.add(vacationEMonthLabel);
        vacationEPanel.add(vacationEDatePanel);
        vacationEPanel.add(vacationEDateLabel);

        vacationDateContainer.add(vacationSPanel);
        vacationDateContainer.add(vacationEPanel);

        vacationReasonPanel.add(vacationReasionField);
        vacationReasonPanel.add(longline);
        vacationReasonPanel.add(Box.createVerticalStrut(10));

        vacationTitlePanel.add(vacationTitleLabel);
        vacationDateTitlePanel.add(vacationDateTitleLabel);
        vacationDateTitlePanel.add(Box.createHorizontalStrut(320));
        vacationSubTitle.add(vacationReasonLabel);
        
        vacationLeft.add(vacationLeftLabel);
        vacationLeft.add(Box.createHorizontalStrut(200));
        vacationLeft.add(confirmButton);

        rightUpper.add(vacationDateTitlePanel);
        rightUpper.add(dateTextArea);

        rightDowner.add(vacationTitlePanel);
        rightDowner.add(vacationDateContainer);
        rightDowner.add(Box.createVerticalStrut(5));
        rightDowner.add(vacationSubTitle);
        rightDowner.add(vacationReasonPanel);
        rightDowner.add(vacationLeft);


        right.add(rightUpper);
        right.add(Box.createVerticalStrut(5));
        right.add(rightDowner);

        left.add(calendarPanel);

        mainPanel.add(left);
        mainPanel.add(Box.createHorizontalStrut(5));
        mainPanel.add(right);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new VacationRequest();
    }
}
