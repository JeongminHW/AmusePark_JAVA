package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Component;

public class vacationConfirm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vacationConfirm frame = new vacationConfirm();
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
	public vacationConfirm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setMaximumSize(new Dimension(32767, 40));
		FlowLayout flowLayout = (FlowLayout) titlePanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		mainPanel.add(titlePanel);
		
		JLabel titleLabel = new JLabel("휴가 신청 목록");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		titlePanel.add(titleLabel);
		
		CentralDropShadowPanel contentPanel = new CentralDropShadowPanel(6,Color.LIGHT_GRAY);
		contentPanel.setMaximumSize(new Dimension(32767, 500));
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JPanel contentSubtitlePanel = new JPanel();
		contentSubtitlePanel.setMaximumSize(new Dimension(32767, 30));
		contentSubtitlePanel.setOpaque(false);
		contentPanel.add(contentSubtitlePanel);
		contentSubtitlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		
		contentSubtitlePanel.add(Box.createHorizontalStrut(10));

		JLabel nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		contentSubtitlePanel.add(nameLabel);
		contentSubtitlePanel.add(Box.createHorizontalStrut(20));
		
		JLabel startDateLabel = new JLabel("시작 날짜");
		startDateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		contentSubtitlePanel.add(startDateLabel);
		contentSubtitlePanel.add(Box.createHorizontalStrut(25));
		
		JLabel EndDateLabel = new JLabel("끝 날짜");
		EndDateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		contentSubtitlePanel.add(EndDateLabel);
		
		JPanel subContentPanel = new JPanel();
		contentPanel.add(subContentPanel);
		subContentPanel.setLayout(new BoxLayout(subContentPanel, BoxLayout.X_AXIS));
		
		JPanel contentPanel1 = new JPanel();
		contentPanel1.setBorder(new EmptyBorder(0, 10, 0, 10));
		contentPanel1.setMaximumSize(new Dimension(32767, 40));
		subContentPanel.add(contentPanel1);
		contentPanel1.setLayout(new BoxLayout(contentPanel1, BoxLayout.X_AXIS));
		
		JPanel element = new JPanel();
		contentPanel1.add(element);
		element.setLayout(new BoxLayout(element, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 26));
		element.add(panel);
		FlowLayout fl_panel = new FlowLayout();
		fl_panel.setAlignment(FlowLayout.LEFT);
		panel.setLayout(fl_panel);
		
		JLabel nameLabel_1 = new JLabel("홍길동");
		nameLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel.add(nameLabel_1);
		panel.add(Box.createHorizontalStrut(10));
		
		JLabel startDateLabel_1 = new JLabel("2024.02.25");
		startDateLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel.add(startDateLabel_1);
		panel.add(Box.createHorizontalStrut(10));

		
		JLabel endDateLabel = new JLabel("2024.02.25");
		endDateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel.add(endDateLabel);

		
		JPanel linePanel = new DrawLine();
		linePanel.setMaximumSize(new Dimension(300, 10));
		element.add(linePanel);
		
		contentPanel1.add(Box.createHorizontalStrut(20));
		
		JButton cancelButton = new RoundedButton("반려",20);
		cancelButton.setBackground(new Color(192, 192, 192));
		cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		contentPanel1.add(cancelButton);
		contentPanel1.add(Box.createHorizontalStrut(10));

		
		JButton confirmButton = new RoundedButton("수락",20);
		confirmButton.setForeground(new Color(255, 255, 255));
		confirmButton.setBackground(new Color(0,148,255));
		confirmButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		contentPanel1.add(confirmButton);
	}

}