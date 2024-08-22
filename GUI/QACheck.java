package cmp.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import cmp.DB.*;

public class QACheck extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	String id;
	JButton cancleButton;
	DBMgr db = new DBMgr();
	Vector<InquireBean> vlist;
	reviewForm rf;
	ImageIcon back_icon = new ImageIcon("./cmp/IMG/back_img.png");
	JButton backButton = new RoundedButton(back_icon, 30);

	/**
	 * Create the frame.
	 */
	public QACheck(String id) {

		this.id = id;
		vlist = db.selectInquireManager();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		setVisible(true);
		setTitle("문의사항 검토 - " + id);
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// '뒤로' 버튼을 위한 새로운 패널 생성
		JPanel backButtonPanel = new JPanel();
		backButtonPanel.setLayout(new BoxLayout(backButtonPanel, BoxLayout.X_AXIS));
		backButtonPanel.setBackground(Color.WHITE);

		// '뒤로' 버튼을 별도의 패널에 추가
		backButton.setBackground(Color.WHITE);
		backButtonPanel.add(backButton);
		backButtonPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(backButtonPanel);

		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setMaximumSize(new Dimension(32767, 50));
		titlePanel.setBorder(new EmptyBorder(5, 10, 0, 10));
		mainPanel.add(titlePanel);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

		JPanel titleContentPanel = new JPanel();
		titleContentPanel.setOpaque(false);
		titlePanel.add(titleContentPanel);
		titleContentPanel.setLayout(new BoxLayout(titleContentPanel, BoxLayout.X_AXIS));

		titleContentPanel.add(Box.createHorizontalStrut(30));

		JPanel titleNamePanel = new JPanel();
		titleNamePanel.setOpaque(false);
		titleContentPanel.add(titleNamePanel);

		titleContentPanel.add(Box.createHorizontalStrut(150));
		titleNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel titleNameLabel = new JLabel("이름");
		titleNameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		titleNamePanel.add(titleNameLabel);

		JPanel titleTitlePanel = new JPanel();
		titleTitlePanel.setOpaque(false);
		titleContentPanel.add(titleTitlePanel);
		titleTitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		titleContentPanel.add(Box.createHorizontalStrut(150));

		JLabel titleTitleLabel = new JLabel("제목");
		titleTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		titleTitlePanel.add(titleTitleLabel);

		JPanel titleAnswerPanel = new JPanel();
		titleAnswerPanel.setOpaque(false);
		titleContentPanel.add(titleAnswerPanel);
		titleAnswerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		titleContentPanel.add(Box.createHorizontalStrut(30));

		JLabel titleAnswerLabel = new JLabel("답변");
		titleAnswerLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		titleAnswerPanel.add(titleAnswerLabel);

		JPanel titleLinePanel = new DrawLine();
		titleLinePanel.setPreferredSize(new Dimension(40, 3));
		titleLinePanel.setBackground(Color.BLACK);
		titleLinePanel.setMaximumSize(new Dimension(32767, 3));
		titlePanel.add(titleLinePanel);
		titleLinePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		contentPanel.setOpaque(false);
		mainPanel.add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		// -- 내용 파트 -- //
		for (int i = 0; i < 10; i++) {
			if (vlist.size() > i) {
				InquireBean bean = vlist.get(i);
				createQAPanel(contentPanel, bean);
			}
		}
		
		if(getTitle().equals("문의사항 검토 - null")) {
			dispose();
		}
	}

	public void createQAPanel(JPanel contentPanel, InquireBean bean) {
		JPanel contentAnswerContainerPanel = new JPanel();
		contentAnswerContainerPanel.setBorder(new EmptyBorder(5, 0, 0, 5));
		contentAnswerContainerPanel.setOpaque(false);
		contentAnswerContainerPanel.setMaximumSize(new Dimension(32767, 40));
		contentPanel.add(contentAnswerContainerPanel);
		contentAnswerContainerPanel.setLayout(new BoxLayout(contentAnswerContainerPanel, BoxLayout.Y_AXIS));

		JPanel contentAnswerPanel = new JPanel();
		contentAnswerPanel.setOpaque(false);
		contentAnswerContainerPanel.add(contentAnswerPanel);
		contentAnswerPanel.setLayout(new BorderLayout(0, 0));

		JLabel contentNameLabel = new JLabel(db.myName(bean.getInquire_id()));
		contentNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentNameLabel.setBorder(new EmptyBorder(0, 70, 0, 70));
		contentNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		contentNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentAnswerPanel.add(contentNameLabel, BorderLayout.WEST);

		JLabel contentTitlLabel = new JLabel(bean.getInquire_title());
		contentTitlLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentTitlLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		contentTitlLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		contentTitlLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentAnswerPanel.add(contentTitlLabel, BorderLayout.CENTER);

		JPanel buttonContainer = new JPanel();
		buttonContainer.setOpaque(false);
		buttonContainer.setBorder(new EmptyBorder(0, 47, 0, 47));
		contentAnswerPanel.add(buttonContainer, BorderLayout.EAST);

		RoundedButton answerButton = new RoundedButton("답변하기", 15);
		answerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		answerButton.setPreferredSize(new Dimension(60, 20));
		answerButton.setMaximumSize(new Dimension(60, 20));
		answerButton.setForeground(Color.WHITE);
		answerButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		answerButton.setBackground(new Color(0, 128, 255));
		answerButton.setAlignmentX(0.5f);
		buttonContainer.add(answerButton, BorderLayout.EAST);

		JPanel contentAnswerLinePanel = new DrawLine();
		contentAnswerLinePanel.setOpaque(false);
		contentAnswerLinePanel.setMaximumSize(new Dimension(32767, 5));
		contentAnswerContainerPanel.add(contentAnswerLinePanel);
		
		// 이벤트
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new EmployeeMain();
			}
		});

		answerButton.addActionListener(new ActionListener() {
			InquireBean bean2 = bean;
			@Override
			public void actionPerformed(ActionEvent e) {
				InquireBean bean = bean2;
				bean.setInquire_num(bean.getInquire_num());
				bean.setInquire_id(bean.getInquire_id());
				bean.setInquire_title(bean.getInquire_title());
				bean.setInquire_contents(bean.getInquire_contents());

				QACheck frame = new QACheck("qwer111");
				rf = new reviewForm(bean, frame);
			}
		});
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QACheck frame = new QACheck("qwer111");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}