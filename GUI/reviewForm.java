
package cmp.GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cmp.DB.*;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;

public class reviewForm extends JFrame implements ActionListener {

	InquireBean bean = new InquireBean();
	DBMgr db = new DBMgr();

	JFrame frame;
	JPanel contentContainerPanel, buttonPanel;
	JButton cancelButton, submitButton;
	QACheck ir;
	private CentralDropShadowPanel idPanel;
	private JTextField idField;
	private CentralDropShadowPanel titlePanel;
	private JPanel contentPanel;
	private JPanel answerPanel;
	private JPanel idTitlePanel;
	private JLabel idLabel;
	private JPanel titleTitlePanel;
	private JLabel titleTitleLabel;
	private JTextField titleField;
	private JPanel contentTitlePanel;
	private JLabel contentTitleLabel;
	private CentralDropShadowPanel contentContentPanel;
	private JTextArea areaInquireContents;
	private JPanel answerTitlePanel;
	private JLabel answerTitleLabel;
	private CentralDropShadowPanel answerContentPanel;
	private JTextArea areaReviewContents;

	public reviewForm(InquireBean bean, QACheck ir) {

		this.ir = ir;
		this.bean = bean;
		frame = new JFrame("문의사항 검토");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setSize(600, 400);
		frame.setVisible(true);

		contentContainerPanel = new JPanel();
		contentContainerPanel.setOpaque(false);
		buttonPanel = new JPanel(new GridLayout(1, 2));
		buttonPanel.setOpaque(false);
		cancelButton = new RoundedButton("취소", 20);
		cancelButton.setFont(new Font("나눔고딕", Font.BOLD, 14));
		cancelButton.setBackground(Color.GRAY);
		cancelButton.setForeground(Color.WHITE);
		submitButton = new RoundedButton("작성",20);
		submitButton.setForeground(Color.WHITE);
		submitButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		submitButton.setBackground(new Color(102, 153, 255));

		cancelButton.addActionListener(this);
		submitButton.addActionListener(this);
		buttonPanel.add(cancelButton);
		buttonPanel.add(submitButton);

		frame.getContentPane().add(contentContainerPanel);
		GridBagLayout gbl_contentContainerPanel = new GridBagLayout();
		gbl_contentContainerPanel.columnWidths = new int[]{586, 0};
		gbl_contentContainerPanel.rowHeights = new int[] {40, 40, 166, 166, 0};
		gbl_contentContainerPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentContainerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentContainerPanel.setLayout(gbl_contentContainerPanel);
		
		idPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 15);
		idPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_idPanel = new GridBagConstraints();
		gbc_idPanel.fill = GridBagConstraints.BOTH;
		gbc_idPanel.insets = new Insets(0, 0, 5, 0);
		gbc_idPanel.gridx = 0;
		gbc_idPanel.gridy = 0;
		contentContainerPanel.add(idPanel, gbc_idPanel);
		idPanel.setLayout(new BorderLayout(0, 0));
		
		idTitlePanel = new JPanel();
		idTitlePanel.setOpaque(false);
		idPanel.add(idTitlePanel, BorderLayout.WEST);
		idTitlePanel.setLayout(new BoxLayout(idTitlePanel, BoxLayout.X_AXIS));
		
		idLabel = new JLabel("이름");
		idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		idTitlePanel.add(idLabel);
		
		idField = new JTextField(db.myName(bean.getInquire_id()));
		idField.setFocusable(false);
		idField.setBorder(new EmptyBorder(0, 10, 0, 10));
		idField.setOpaque(false);
		idField.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		idField.setEditable(false);
		idPanel.add(idField);
		
		titlePanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 15);
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setOpaque(false);
		GridBagConstraints gbc_titlePanel = new GridBagConstraints();
		gbc_titlePanel.fill = GridBagConstraints.BOTH;
		gbc_titlePanel.insets = new Insets(0, 0, 5, 0);
		gbc_titlePanel.gridx = 0;
		gbc_titlePanel.gridy = 1;
		contentContainerPanel.add(titlePanel, gbc_titlePanel);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		titleTitlePanel = new JPanel();
		titleTitlePanel.setOpaque(false);
		titlePanel.add(titleTitlePanel, BorderLayout.WEST);
		titleTitlePanel.setLayout(new BoxLayout(titleTitlePanel, BoxLayout.X_AXIS));
		
		titleTitleLabel = new JLabel("제목");
		titleTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		titleTitlePanel.add(titleTitleLabel);
		
		titleField = new JTextField(bean.getInquire_title());
		titleField.setFocusable(false);
		titleField.setBorder(new EmptyBorder(0, 10, 0, 10));
		titleField.setOpaque(false);
		titleField.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		titleField.setEditable(false);
		titlePanel.add(titleField);
		
		contentPanel = new JPanel();
		contentPanel.setOpaque(false);
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 2;
		contentContainerPanel.add(contentPanel, gbc_contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		contentTitlePanel = new JPanel();
		contentTitlePanel.setOpaque(false);
		FlowLayout fl_contentTitlePanel = (FlowLayout) contentTitlePanel.getLayout();
		fl_contentTitlePanel.setAlignment(FlowLayout.LEFT);
		contentPanel.add(contentTitlePanel);
		
		contentTitleLabel = new JLabel("내용");
		contentTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		contentTitlePanel.add(contentTitleLabel);
		
		contentContentPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 20);
		contentContentPanel.setOpaque(false);
		contentPanel.add(contentContentPanel);
		contentContentPanel.setLayout(new BoxLayout(contentContentPanel, BoxLayout.X_AXIS));
		
		areaInquireContents = new JTextArea(bean.getInquire_contents());
		areaInquireContents.setBorder(new EmptyBorder(10, 10, 10, 10));
		areaInquireContents.setFocusable(false);
		areaInquireContents.setEditable(false);
		contentContentPanel.add(areaInquireContents);
		
		answerPanel = new JPanel();
		answerPanel.setOpaque(false);
		GridBagConstraints gbc_answerPanel = new GridBagConstraints();
		gbc_answerPanel.fill = GridBagConstraints.BOTH;
		gbc_answerPanel.gridx = 0;
		gbc_answerPanel.gridy = 3;
		contentContainerPanel.add(answerPanel, gbc_answerPanel);
		answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));
		
		answerTitlePanel = new JPanel();
		answerTitlePanel.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) answerTitlePanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		answerPanel.add(answerTitlePanel);
		
		answerTitleLabel = new JLabel("답변");
		answerTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		answerTitlePanel.add(answerTitleLabel);
		
		answerContentPanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 20);
		answerPanel.add(answerContentPanel);
		answerContentPanel.setLayout(new BoxLayout(answerContentPanel, BoxLayout.X_AXIS));
		
		areaReviewContents = new JTextArea();
		areaReviewContents.setBorder(new EmptyBorder(5, 5, 5, 5));
		answerContentPanel.add(areaReviewContents);
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == cancelButton) {

			frame.dispose();

		} else if (obj == submitButton) {
			bean.setReply_contents(areaReviewContents.getText());
			if (db.reviewInquire(bean)) {
				System.out.println("성공");
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							QACheck frame = new QACheck(ir.id);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frame.dispose();
			}
		}
	}
}
