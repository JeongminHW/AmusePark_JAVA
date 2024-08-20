package cmp.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Cursor;
import java.awt.Component;

public class NoticeCreate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoticeCreate frame = new NoticeCreate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NoticeCreate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		String[] Yparts = {"2020", "2021", "2022", "2023", "2024", "2025"};
		String[] Mparts = {"01 ", "02", "03", "04", "05"};
		String[] Dparts = {"01 ", "02", "03", "04", "05"};
		
		JPanel noticeTitlePanel = new JPanel();
		noticeTitlePanel.setBackground(new Color(255, 255, 255));
		noticeTitlePanel.setMaximumSize(new Dimension(32767, 80));
		FlowLayout flowLayout = (FlowLayout) noticeTitlePanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		mainPanel.add(noticeTitlePanel);
		
		JLabel noticeTitleLabel = new JLabel("제목");
		noticeTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		noticeTitlePanel.add(noticeTitleLabel);
		
		CentralDropShadowPanel noticeContentPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
		noticeContentPanel.setPreferredSize(new Dimension(34, 35));
		noticeContentPanel.setMaximumSize(new Dimension(32767, 35));
		mainPanel.add(noticeContentPanel);
		noticeContentPanel.setLayout(new BoxLayout(noticeContentPanel, BoxLayout.X_AXIS));
		noticeContentPanel.setBackground(Color.WHITE);
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(7, 30));
		textField.setOpaque(false);
		textField.setMinimumSize(new Dimension(7, 60));
		textField.setMaximumSize(new Dimension(2147483647, 30));
		textField.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBorder(new EmptyBorder(0, 15, 0, 15));
		noticeContentPanel.add(textField);
		
		JPanel contentTitlePanel = new JPanel();
		contentTitlePanel.setMaximumSize(new Dimension(32767, 80));
		contentTitlePanel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mainPanel.add(contentTitlePanel);
		contentTitlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		contentTitlePanel.setBackground(Color.WHITE);
		
		JLabel ContentTitleLabel = new JLabel("내용");
		ContentTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		contentTitlePanel.add(ContentTitleLabel);
		
		CentralDropShadowPanel contentPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
		contentPanel.setPreferredSize(new Dimension(34, 150));
		contentPanel.setMinimumSize(new Dimension(22, 150));
		contentPanel.setMaximumSize(new Dimension(32767, 150));
		mainPanel.add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		contentPanel.setBackground(Color.WHITE);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textArea.setBorder(new EmptyBorder(10, 15, 10, 15));
		textArea.setOpaque(false);
		contentPanel.add(textArea);
		
		JPanel dateTitlePanel = new JPanel();
		dateTitlePanel.setMaximumSize(new Dimension(32767, 80));
		mainPanel.add(dateTitlePanel);
		dateTitlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		dateTitlePanel.setBackground(Color.WHITE);
		
		JLabel dateTitleLabel = new JLabel("일시");
		dateTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		dateTitlePanel.add(dateTitleLabel);
		
		CentralDropShadowPanel dateContentPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
		mainPanel.add(dateContentPanel);
		dateContentPanel.setLayout(new BoxLayout(dateContentPanel, BoxLayout.Y_AXIS));
		dateContentPanel.setBackground(Color.WHITE);
		
		JPanel dateSPanel = new JPanel();
		dateSPanel.setBorder(new EmptyBorder(5, 20, 5, 20));
		dateSPanel.setOpaque(false);
		dateContentPanel.add(dateSPanel);
		dateSPanel.setLayout(new BoxLayout(dateSPanel, BoxLayout.X_AXIS));
		
		JPanel dateSYearPanel = new JPanel();
		dateSYearPanel.setBackground(new Color(255, 255, 255));
		dateSPanel.add(dateSYearPanel);
		dateSYearPanel.setLayout(new BoxLayout(dateSYearPanel, BoxLayout.Y_AXIS));
		
		JComboBox<String> dateSYearCombobox= new JComboBox<String>(Yparts);
		dateSYearCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateSYearCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateSYearCombobox.setBorder(new EmptyBorder(0, 20, 0, 10));
		dateSYearPanel.add(dateSYearCombobox);
		dateSYearCombobox.setBackground(Color.WHITE);
		dateSYearCombobox.setFocusable(false);
		
		dateSYearCombobox.setUI(new BasicComboBoxUI(){
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
            @Override
            protected void installDefaults(){
                super.installDefaults();
                LookAndFeel.uninstallBorder(dateSYearCombobox);
            }

        });
		
		JPanel line1 = new DrawLine();
		dateSYearPanel.add(line1);
		line1.setBackground(Color.WHITE);
		
		dateSPanel.add(Box.createHorizontalStrut(10));
		
		JLabel dateSYearLabel = new JLabel("년");
		dateSYearLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		dateSPanel.add(dateSYearLabel);
		
		dateSPanel.add(Box.createHorizontalStrut(10));
		
		JPanel dateSMonthPanel = new JPanel();
		dateSMonthPanel.setBackground(new Color(255, 255, 255));
		dateSPanel.add(dateSMonthPanel);
		dateSMonthPanel.setLayout(new BoxLayout(dateSMonthPanel, BoxLayout.Y_AXIS));
		
		JComboBox<String> dateSMonthCombobox= new JComboBox<String>(Mparts);
		dateSMonthCombobox.setBorder(new EmptyBorder(0, 10, 0, 10));
		dateSMonthCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateSMonthCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateSMonthPanel.add(dateSMonthCombobox);
		dateSMonthCombobox.setBackground(Color.WHITE);
		dateSMonthCombobox.setFocusable(false);
		
		dateSMonthCombobox.setUI(new BasicComboBoxUI(){
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
            @Override
            protected void installDefaults(){
                super.installDefaults();
                LookAndFeel.uninstallBorder(dateSMonthCombobox);
            }

        });
		
		JPanel line2 = new DrawLine();
		line2.setBackground(new Color(255, 255, 255));
		dateSMonthPanel.add(line2);
		
		dateSPanel.add(Box.createHorizontalStrut(10));
		
		JLabel dateSMonthLabel = new JLabel("월");
		dateSMonthLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		dateSPanel.add(dateSMonthLabel);
		
		dateSPanel.add(Box.createHorizontalStrut(10));
		
		JPanel dateSDayPanel = new JPanel();
		dateSDayPanel.setBackground(new Color(255, 255, 255));
		dateSPanel.add(dateSDayPanel);
		dateSDayPanel.setLayout(new BoxLayout(dateSDayPanel, BoxLayout.Y_AXIS));
		
		JComboBox<String> dateSDayCombobox= new JComboBox<String>(Dparts);
		dateSDayCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateSDayCombobox.setBorder(new EmptyBorder(0, 10, 0, 10));
		dateSDayCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateSDayPanel.add(dateSDayCombobox);
		dateSDayCombobox.setBackground(Color.WHITE);
		dateSDayCombobox.setFocusable(false);
		
		dateSDayCombobox.setUI(new BasicComboBoxUI(){
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
            @Override
            protected void installDefaults(){
                super.installDefaults();
                LookAndFeel.uninstallBorder(dateSDayCombobox);
            }

        });
		
		JPanel line3 = new DrawLine();
		line3.setBackground(Color.WHITE);
		dateSDayPanel.add(line3);
		
		dateSPanel.add(Box.createHorizontalStrut(10));
		
		JLabel dateSDayLabel = new JLabel("일 부터");
		dateSDayLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		dateSPanel.add(dateSDayLabel);
		
		JPanel dateEPanel = new JPanel();
		dateEPanel.setBorder(new EmptyBorder(5, 20, 5, 20));
		dateEPanel.setOpaque(false);
		dateContentPanel.add(dateEPanel);
		dateEPanel.setLayout(new BoxLayout(dateEPanel, BoxLayout.X_AXIS));
		
		JPanel dateEYearPanel = new JPanel();
		dateEYearPanel.setBackground(Color.WHITE);
		dateEPanel.add(dateEYearPanel);
		dateEYearPanel.setLayout(new BoxLayout(dateEYearPanel, BoxLayout.Y_AXIS));
		
		JComboBox<String> dateEYearCombobox= new JComboBox<String>(Yparts);
		dateEYearCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateEYearCombobox.setBorder(new EmptyBorder(0, 20, 0, 10));
		dateEYearCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateEYearPanel.add(dateEYearCombobox);
		dateEYearCombobox.setBackground(Color.WHITE);
		dateEYearCombobox.setFocusable(false);
		
		dateEYearCombobox.setUI(new BasicComboBoxUI(){
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
            @Override
            protected void installDefaults(){
                super.installDefaults();
                LookAndFeel.uninstallBorder(dateEYearCombobox);
            }

        });
		
		JPanel line4 = new DrawLine();
		line4.setBackground(Color.WHITE);
		dateEYearPanel.add(line4);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		dateEPanel.add(horizontalStrut);
		
		JLabel dateEYearLabel = new JLabel("년");
		dateEPanel.add(dateEYearLabel);
		dateEYearLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		dateEPanel.add(horizontalStrut_1);
		
		JPanel dateEMonthPanel = new JPanel();
		dateEMonthPanel.setBackground(Color.WHITE);
		dateEPanel.add(dateEMonthPanel);
		dateEMonthPanel.setLayout(new BoxLayout(dateEMonthPanel, BoxLayout.Y_AXIS));
		
		JComboBox<String> dateEMonthCombobox= new JComboBox<String>(Mparts);
		dateEMonthCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateEMonthCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateEMonthCombobox.setBorder(new EmptyBorder(0, 10, 0, 10));
		dateEMonthPanel.add(dateEMonthCombobox);
		dateEMonthCombobox.setBackground(Color.WHITE);
		dateEMonthCombobox.setFocusable(false);
		
		dateEMonthCombobox.setUI(new BasicComboBoxUI(){
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
            @Override
            protected void installDefaults(){
                super.installDefaults();
                LookAndFeel.uninstallBorder(dateEMonthCombobox);
            }

        });
		
		JPanel line5 = new DrawLine();
		line5.setBackground(Color.WHITE);
		dateEMonthPanel.add(line5);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		dateEPanel.add(horizontalStrut_2);
		
		JLabel dateEMonthLabel = new JLabel("월");
		dateEPanel.add(dateEMonthLabel);
		dateEMonthLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(10);
		dateEPanel.add(horizontalStrut_3);
		
		JPanel dateEDayPanel = new JPanel();
		dateEDayPanel.setBackground(Color.WHITE);
		dateEPanel.add(dateEDayPanel);
		dateEDayPanel.setLayout(new BoxLayout(dateEDayPanel, BoxLayout.Y_AXIS));
		
		JComboBox<String> dateEDayCombobox= new JComboBox<String>(Dparts);
		dateEDayCombobox.setBorder(new EmptyBorder(0, 10, 0, 10));
		dateEDayCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateEDayPanel.add(dateEDayCombobox);
		dateEDayCombobox.setBackground(Color.WHITE);
		dateEDayCombobox.setFocusable(false);
		
		dateEDayCombobox.setUI(new BasicComboBoxUI(){
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
            @Override
            protected void installDefaults(){
                super.installDefaults();
                LookAndFeel.uninstallBorder(dateEDayCombobox);
            }

        });
		
		JPanel line6 = new DrawLine();
		line6.setBackground(Color.WHITE);
		dateEDayPanel.add(line6);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(10);
		dateEPanel.add(horizontalStrut_4);
		
		JLabel dateEDayLabel = new JLabel("일 까지");
		dateEPanel.add(dateEDayLabel);
		dateEDayLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) buttonPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		mainPanel.add(buttonPanel);
		buttonPanel.setBackground(Color.WHITE);
		
		JButton cancelButton = new RoundedButton("취소",15);
		cancelButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		buttonPanel.add(cancelButton);
		cancelButton.setBackground(Color.GRAY);
		cancelButton.setForeground(Color.WHITE);
		
		JButton confrimButton = new RoundedButton("확인",15);
		confrimButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		buttonPanel.add(confrimButton);
		confrimButton.setBackground(new Color(0,148,255));
		confrimButton.setForeground(Color.WHITE);
	}

}
