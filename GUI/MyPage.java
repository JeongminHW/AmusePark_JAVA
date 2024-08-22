package cmp.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.*;

import org.xnio.channels.SslChannel;

import cmp.DB.*;

public class MyPage implements ActionListener {
	static String em_id = "";
	static String alba_id = "";

	public static String getEm_id() {
		return em_id;
	}

	public static void setEm_id(String em_id) {
		MyPage.em_id = em_id;
	}

	public static String getAlba_id() {
		return alba_id;
	}

	public static void setAlba_id(String alba_id) {
		MyPage.alba_id = alba_id;
	}

	DBMgr db = new DBMgr();
	EmployeeBean Embean = new EmployeeBean();
	AlbaBean Albabean = new AlbaBean();
	ParttimeBean Ptbean = new ParttimeBean();
	Vector<EmployeeBean> vlist;

	JFrame frame = new JFrame("마이페이지");
	JPanel mainPanel = new JPanel(new GridLayout(10, 2, 10, 10));
	JLabel idLabel = new JLabel("ID");
	JLabel nameLabel = new JLabel("이름");
	JLabel currentPasswordLabel = new JLabel("현재 비밀번호");
	JLabel newPasswordLabel = new JLabel("새 비밀번호");
	JLabel confirmPasswordLabel = new JLabel("비밀번호 확인");
	JLabel phoneLabel = new JLabel("전화번호");
	JLabel positionLabel = new JLabel("직급");
	JLabel adminLabel = new JLabel("관리자 여부");

	JTextField idField = new JTextField();
	JTextField nameField = new JTextField("");
	JPasswordField currentPasswordField = new JPasswordField("");
	JPasswordField newPasswordField = new JPasswordField("");
	JPasswordField confirmPasswordField = new JPasswordField("");
	JTextField phoneField = new JTextField("");
	JTextField positionField = new JTextField("");
	JTextField partTimeClock = new JTextField("");
	JCheckBox adminCheckBox = new JCheckBox("YES");
	JButton saveButton = new RoundedButton("저장", 20);
	JButton cancelButton = new RoundedButton("취소", 20);

	public MyPage() {
		if (!alba_id.equals("")) { // 알바 마이페이지
			frame.setTitle("마이페이지 - " + alba_id);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(500, 400);
			frame.setVisible(true);

			// 정보 불러오기
			loadAlba(alba_id);
			Form(alba_id);
			positionLabel.setText("파트타임");
			positionLabel.setText("시간");
			partTimeClock.setEnabled(false);
			mainPanel.remove(adminCheckBox);
			mainPanel.add(partTimeClock, 15);
		} else if (!em_id.equals("")) { // 직원 마이페이지
			frame.setTitle("마이페이지 - " + getEm_id());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(500, 400);
			frame.setVisible(true);

			// 정보 불러오기
			loadEmployeeInfo(em_id);
			Form(em_id);

			// 매니저 여부 체크
			if (db.CheckManagerEmployee(em_id)) {
				adminCheckBox.setSelected(true);
			} else {
				adminCheckBox.setSelected(false);
			}
		}

	}

	public void Form(String id) {
		idField.setText(id);
		idField.setEnabled(false);
		idField.setForeground(Color.black);
		// positionField.setEnabled(false);

		adminCheckBox.setBackground(Color.white);

		boolean checkboxSelect = adminCheckBox.isSelected();

		saveButton.addActionListener(this);
		cancelButton.addActionListener(this);

		mainPanel.setBackground(Color.white);

		mainPanel.add(idLabel);
		mainPanel.add(idField);
		mainPanel.add(nameLabel);
		mainPanel.add(nameField);
		mainPanel.add(currentPasswordLabel);
		mainPanel.add(currentPasswordField);
		mainPanel.add(newPasswordLabel);
		mainPanel.add(newPasswordField);
		mainPanel.add(confirmPasswordLabel);
		mainPanel.add(confirmPasswordField);
		mainPanel.add(phoneLabel);
		mainPanel.add(phoneField);
		mainPanel.add(positionLabel);
		mainPanel.add(positionField);
		mainPanel.add(adminLabel);
		mainPanel.add(adminCheckBox);
		mainPanel.add(cancelButton);
		mainPanel.add(saveButton);

		frame.add(mainPanel);
		frame.setVisible(true);

		/*
		 * if(frame.getTitle().equals("마이페이지 - ")) { frame.dispose(); }
		 */
	}

	// 직원 정보 불러오기
	private void loadEmployeeInfo(String id) {
		Embean = db.listEmployee(id);
		nameField.setText(Embean.getName());
		phoneField.setText(Embean.getPhone());
		positionField.setText(Embean.getPosition());
	}

	// 알바 정보 불러오기
	private void loadAlba(String id) {
		Albabean = db.listAlba(id);
		Ptbean = db.infoParttime(id);
		nameField.setText(Albabean.getname());
		phoneField.setText(Albabean.getphone());
		positionField.setText(Albabean.getPart_time());
		partTimeClock.setText(Ptbean.getStart_time() + " - " + Ptbean.getEnd_time());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String oldpw = new String(currentPasswordField.getPassword());
		String newpw = new String(newPasswordField.getPassword());
		String pwChk = new String(confirmPasswordField.getPassword());
		String name = nameField.getText();
		String phone = phoneField.getText();
		String position = positionField.getText();
		boolean managerChk = adminCheckBox.isSelected();
		if (obj == saveButton) {
			if (!oldpw.equals("") && !newpw.equals("") && !pwChk.equals("") && positionLabel.getText().equals("직급")) { // 직원
				if (newpw.equals(pwChk)) {
					Embean.setPw(pwChk);
					Embean.setName(name);
					Embean.setPhone(phone);
					Embean.setPosition(position);
					db.updateEmployee(Embean);
					JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.", "수정", JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.", "수정", JOptionPane.ERROR_MESSAGE);
				}
			} else if (!oldpw.equals("") && !newpw.equals("") && !pwChk.equals("") && positionLabel.getText().equals("파트타임")) { // 알바
				if (newpw.equals(pwChk)) {
					Albabean.setpw(pwChk);
					Albabean.setname(name);
					Albabean.setphone(phone);
					Albabean.setPart_time(position);
					db.updateAlba(Albabean);
					JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.", "수정", JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.", "수정", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (obj == cancelButton) {
			frame.dispose();
		}
	}

	public static void main(String[] args) {
		new MyPage();
	}
}
