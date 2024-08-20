package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Cursor;

public class CreateQA extends JFrame {

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
					CreateQA frame = new CreateQA();
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
	public CreateQA() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.WHITE);

		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setBorder(new EmptyBorder(0, 5, 0, 5));
		titlePanel.setMaximumSize(new Dimension(32767, 40));
		titlePanel.setPreferredSize(new Dimension(10, 40));
		mainPanel.add(titlePanel);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		
		JLabel titleLabel = new JLabel("제목");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titlePanel.add(titleLabel);
		
		CentralDropShadowPanel textContainerPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 15);
		textContainerPanel.setBackground(Color.WHITE);
		titlePanel.add(textContainerPanel);
		textContainerPanel.setLayout(new BoxLayout(textContainerPanel, BoxLayout.X_AXIS));
		
		textField = new JTextField();
		textField.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		textField.setBorder(new EmptyBorder(0, 10, 0, 10));
		textField.setOpaque(false);
		textContainerPanel.add(textField);
		textField.setColumns(10);
		
		textContainerPanel.add(Box.createHorizontalStrut(5));

		JButton cancelButton = new RoundedButton("취소",20);
		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		cancelButton.setBackground(Color.GRAY);
		cancelButton.setForeground(Color.WHITE);
		titlePanel.add(cancelButton);
		
		titlePanel.add(Box.createHorizontalStrut(5));

		JButton confrimButton = new RoundedButton("작성",20);
		confrimButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		confrimButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		confrimButton.setBackground(new Color(0,148,255));
		confrimButton.setForeground(Color.WHITE);
		titlePanel.add(confrimButton);
		
		CentralDropShadowPanel contentPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY,20);
		contentPanel.setBackground(Color.WHITE);
		mainPanel.add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		textArea.setOpaque(false);
		textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPanel.add(textArea);
	}

}
