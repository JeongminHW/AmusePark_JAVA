package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import java.awt.Cursor;
import DB.*;

public class CreateQA extends JFrame {
	static String id;

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JTextField textField;
	DBMgr db = new DBMgr();
	JTextArea textArea = new JTextArea();
	CentralDropShadowPanel contentPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 20);
	JButton confirmButton = new RoundedButton("작성", 20);
	JButton cancelButton = new RoundedButton("취소", 20);
	CentralDropShadowPanel textContainerPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 15);
	JLabel titleLabel = new JLabel("제목");
	JPanel titlePanel = new JPanel();
	
	/**
	 * Create the frame.
	 */
	public CreateQA(String id) {

		this.id = id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		setVisible(true);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.WHITE);

		titlePanel.setOpaque(false);
		titlePanel.setBorder(new EmptyBorder(0, 5, 0, 5));
		titlePanel.setMaximumSize(new Dimension(32767, 40));
		titlePanel.setPreferredSize(new Dimension(10, 40));
		mainPanel.add(titlePanel);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titlePanel.add(titleLabel);

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

		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		cancelButton.setBackground(Color.GRAY);
		cancelButton.setForeground(Color.WHITE);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				AlbaMain am = new AlbaMain(id);
			}
		});
		titlePanel.add(cancelButton);

		titlePanel.add(Box.createHorizontalStrut(5));

		confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		confirmButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		confirmButton.setBackground(new Color(0, 148, 255));
		confirmButton.setForeground(Color.WHITE);
		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				InquireBean bean = new InquireBean();
				bean.setInquire_id(id);
				bean.setInquire_title(textField.getText());
				bean.setInquire_contents(textArea.getText());
				db.insertinquire(bean);
				JOptionPane.showMessageDialog(null, "작성 되었습니다.", "문의사항", JOptionPane.PLAIN_MESSAGE);
				dispose();
				AlbaMain am = new AlbaMain(id);
			}
		});
		titlePanel.add(confirmButton);

		contentPanel.setBackground(Color.WHITE);
		mainPanel.add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		textArea.setOpaque(false);
		textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPanel.add(textArea);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateQA frame = new CreateQA(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
