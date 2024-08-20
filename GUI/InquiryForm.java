package GUI;

import javax.swing.*;

import DB.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class InquiryForm implements ActionListener, MouseListener {

	static String id;

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		InquiryForm.id = id;
	}

	JFrame frame;
	JPanel headPanel, mainPanel;
	JTextField titleField;
	JTextArea contentArea;
	JPanel buttonPanel;
	JButton cancelButton, submitButton;
	DBMgr db = new DBMgr();
	Vector<TodoBean> vlist;

	public InquiryForm(String id) {
		this.id = id;

		frame = new JFrame("문의사항 작성");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);

		mainPanel = new JPanel();
		headPanel = new JPanel();

		titleField = new JTextField();
		titleField.setText("제목을 입력하세요.");

		contentArea = new JTextArea("내용을 입력하세요.");

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		cancelButton = new JButton("취소");
		submitButton = new JButton("작성");

		buttonPanel.add(cancelButton);
		buttonPanel.add(submitButton);

		headPanel.add(new JLabel("제목"));
		headPanel.add(titleField);
		mainPanel.add(new JScrollPane(contentArea), BorderLayout.SOUTH);

		frame.add(headPanel, BorderLayout.NORTH);
		frame.add(mainPanel);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setVisible(true);

		cancelButton.addActionListener(this);
		submitButton.addActionListener(this);
		titleField.addMouseListener(this);
		contentArea.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		if (obj == cancelButton) {
			frame.dispose();

		} else if (obj == submitButton) {
			addInquiry();
			frame.dispose();
		}
	}

	public void addInquiry() {
		InquireBean bean = new InquireBean();
		bean.setInquire_id(id);
		bean.setInquire_title(titleField.getText());
		bean.setInquire_contents(contentArea.getText());
		if (db.insertinquire(bean)) {
			System.out.println("성공");
		}
	}

	public static void main(String[] args) {
		new InquiryForm(id);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = new Object();
		String title = titleField.getText();
		String contents = contentArea.getText();
		if(obj == titleField) {
			if(title.equals("제목을 입력하세요.")) {
				titleField.setText("");
			}
		}
		else if(obj == contentArea) {
			if(contents.equals("내용을 입력하세요.")) {
				contentArea.setText("");
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}