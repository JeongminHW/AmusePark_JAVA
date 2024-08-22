package cmp.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import cmp.DB.*;

public class EmSignUp implements ActionListener, MouseListener {
	DBMgr db = new DBMgr();
	JFrame frame = new JFrame("Staff Sign Up");
	JPanel panel = new JPanel();
	JPanel buttonPanel = new JPanel();
	String[] parts = { "어트랙션 A", "어트랙션 B", "어트랙션 C", "어트랙션 D", "사파리", "티켓/그리팅", "퍼레이드" };
	String[] ranks = { "사원", "대리", "과장", "차장", "부장" };
	JTextField idField = new JTextField("아이디");
	JPasswordField passwordField = new JPasswordField("비밀번호");
	JPasswordField confirmPasswordField = new JPasswordField("비밀번호 확인");
	JTextField nameField = new JTextField("이름");
	JTextField birthDateField = new JTextField("생년월일");
	JTextField phoneNumberField = new JTextField("전화번호");
	JPanel line[] = new JPanel[8];
	JComboBox<String> partComboBox = new JComboBox<>(parts);
	JComboBox<String> rankComboBox = new JComboBox<>(ranks);
	JButton idchkButton = new RoundedButton("중복확인", 20);
	JButton SignUpBtn = new RoundedButton("확인", 32);
	JButton cancelBtn = new RoundedButton("취소", 32);

	public EmSignUp() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 630);
		frame.setResizable(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);

		buttonPanel.setBounds(105, 500, 170, 40);
		buttonPanel.setBackground(Color.white);

		for (int i = 0; i < 8; i++) {
			line[i] = new DrawLine(); // 줄을 그릴 패널 생성
			line[i].setBackground(Color.BLACK);
			line[i].setMaximumSize(new Dimension(200, 3));
		}

		idField.setMaximumSize(new Dimension(180, 40));
		passwordField.setMaximumSize(new Dimension(180, 40));
		confirmPasswordField.setMaximumSize(new Dimension(180, 40));
		nameField.setMaximumSize(new Dimension(180, 40));
		birthDateField.setMaximumSize(new Dimension(180, 40));
		phoneNumberField.setMaximumSize(new Dimension(180, 40));
		partComboBox.setMaximumSize(new Dimension(180, 40));
		rankComboBox.setMaximumSize(new Dimension(180, 40));

		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		idField.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirmPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		birthDateField.setAlignmentX(Component.CENTER_ALIGNMENT);
		phoneNumberField.setAlignmentX(Component.CENTER_ALIGNMENT);
		partComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		rankComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

		idField.setAlignmentY(Component.CENTER_ALIGNMENT);
		passwordField.setAlignmentY(Component.CENTER_ALIGNMENT);
		confirmPasswordField.setAlignmentY(Component.CENTER_ALIGNMENT);
		nameField.setAlignmentY(Component.CENTER_ALIGNMENT);
		birthDateField.setAlignmentY(Component.CENTER_ALIGNMENT);
		phoneNumberField.setAlignmentY(Component.CENTER_ALIGNMENT);
		partComboBox.setAlignmentY(Component.CENTER_ALIGNMENT);
		rankComboBox.setAlignmentY(Component.CENTER_ALIGNMENT);

		idField.setBorder(null);
		passwordField.setBorder(null);
		confirmPasswordField.setBorder(null);
		nameField.setBorder(null);
		birthDateField.setBorder(null);
		phoneNumberField.setBorder(null);
		partComboBox.setBorder(new EmptyBorder(0, 0, 0, 0));
		rankComboBox.setBorder(new EmptyBorder(0, 0, 0, 0));

		NumberFormatter birthnum = new NumberFormatter(); // 생년월일 최대 8글자 설정
		birthnum.setValueClass(Integer.class);
		birthnum.setMinimum(1);
		birthnum.setMaximum(8);

		for (int i = 0; i < partComboBox.getComponentCount(); i++) {
			if (partComboBox.getComponent(i) instanceof JComponent) {
				((JComponent) partComboBox.getComponent(i)).setBorder(new EmptyBorder(0, 0, 0, 0));
			}
			if (partComboBox.getComponent(i) instanceof AbstractButton) {
				((AbstractButton) partComboBox.getComponent(i)).setBorderPainted(false);
			}
		}

		for (int i = 0; i < rankComboBox.getComponentCount(); i++) {
			if (rankComboBox.getComponent(i) instanceof JComponent) {
				((JComponent) rankComboBox.getComponent(i)).setBorder(new EmptyBorder(0, 0, 0, 0));
			}
			if (rankComboBox.getComponent(i) instanceof AbstractButton) {
				((AbstractButton) rankComboBox.getComponent(i)).setBorderPainted(false);
			}
		}

		// 이벤트
		idField.addMouseListener(this);
		passwordField.addMouseListener(this);
		confirmPasswordField.addMouseListener(this);
		nameField.addMouseListener(this);
		birthDateField.addMouseListener(this);
		phoneNumberField.addMouseListener(this);
		idchkButton.addMouseListener(this);
		cancelBtn.addMouseListener(this);
		SignUpBtn.addMouseListener(this);
		idchkButton.addActionListener(this);
		cancelBtn.addActionListener(this);
		SignUpBtn.addActionListener(this);
		birthDateField.addKeyListener(new KeyAdapter() { // 생년월일 8자리 제한
			@Override
			public void keyTyped(KeyEvent e) {
				if(birthDateField.getText().length()>=8)
					e.consume();
			}
		});

		partComboBox.setBackground(Color.WHITE);
		rankComboBox.setBackground(Color.white);

		idchkButton.setBounds(300, 65, 60, 30);
		idchkButton.setHorizontalAlignment(JButton.CENTER);
		idchkButton.setVerticalAlignment(JButton.CENTER);
		idchkButton.setForeground(Color.white);
		idchkButton.setBackground(Color.black);
		idchkButton.addActionListener(this);

		SignUpBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		SignUpBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
		SignUpBtn.setHorizontalAlignment(JButton.CENTER);
		SignUpBtn.setVerticalAlignment(JButton.CENTER);
		SignUpBtn.setFont(new Font("돋움", Font.PLAIN, 15));
		SignUpBtn.setForeground(Color.WHITE);
		SignUpBtn.setBackground(Color.BLACK);

		cancelBtn.setHorizontalAlignment(JButton.CENTER);
		cancelBtn.setVerticalAlignment(JButton.CENTER);
		cancelBtn.setFont(new Font("돋움", Font.PLAIN, 15));
		cancelBtn.setForeground(Color.WHITE);
		cancelBtn.setBackground(Color.BLACK);

		panel.add(Box.createVerticalStrut(60));
		panel.add(idField);
		panel.add(line[0]);
		panel.add(Box.createVerticalStrut(10));
		panel.add(passwordField);
		panel.add(line[1]);
		panel.add(Box.createVerticalStrut(10));
		panel.add(confirmPasswordField);
		panel.add(line[2]);
		panel.add(Box.createVerticalStrut(10));
		panel.add(nameField);
		panel.add(line[3]);
		panel.add(Box.createVerticalStrut(10));
		panel.add(birthDateField);
		panel.add(line[4]);
		panel.add(Box.createVerticalStrut(10));
		panel.add(phoneNumberField);
		panel.add(line[5]);
		panel.add(Box.createVerticalStrut(10));
		panel.add(partComboBox);
		panel.add(line[6]);
		panel.add(Box.createVerticalStrut(10));
		panel.add(rankComboBox);
		panel.add(line[7]);
		panel.add(Box.createVerticalStrut(20));
		panel.add(SignUpBtn);
		buttonPanel.add(cancelBtn);
		buttonPanel.add(Box.createHorizontalStrut(20));
		buttonPanel.add(SignUpBtn);

		frame.add(idchkButton);
		frame.add(buttonPanel);

		frame.add(panel);
		frame.setVisible(true);
	}

	// 회원가입 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		String id = idField.getText();
		String pw = new String(passwordField.getPassword());
		String pwchk = new String(confirmPasswordField.getPassword());
		String name = nameField.getText();
		String birthday = birthDateField.getText();
		String phone = phoneNumberField.getText();
		String part = partComboBox.getSelectedItem().toString();
		String rank = rankComboBox.getSelectedItem().toString();
		EmployeeBean bean = new EmployeeBean();

		if (e.getSource() == SignUpBtn) { // 확인버튼 클릭
			char birthChar = 0;
			for (int i = 0; i < birthday.length(); i++) {
				birthChar = birthday.charAt(i);
				System.out.println(birthChar);
			}
			if (!id.equals("") && !id.equals("아이디") && !pw.equals("") && !pw.equals("비밀번호") && !pwchk.equals("")
					&& !pwchk.equals("비밀번호 확인") && !name.equals("") && !name.equals("이름") && !birthday.equals("")
					&& !birthday.equals("생년월일") && !phone.equals("") && !phone.equals("전화번호")) {
				if (!Character.isDigit(birthChar)) {
					JOptionPane.showMessageDialog(null, "생년월일을 숫자로 입력해주세요.", "비밀번호 오류", JOptionPane.ERROR_MESSAGE);
					birthDateField.setText("");
				} else if (pw.equals(pwchk)) { // 비밀번호가 같을 경우
					bean.setId(id);
					bean.setPw(pw);
					bean.setName(name);
					bean.setBirthday(birthday);
					bean.setPhone(phone);
					bean.setPosition(rank);
					bean.setDepartment(part);
					db.SignUpEmployee(bean);
					db.VacationEmployee(bean);
					JOptionPane.showMessageDialog(null, "회원가입에 성공하였습니다.", "회원가입 성공", JOptionPane.PLAIN_MESSAGE);
					new EmLogin();
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "비밀번호 오류", JOptionPane.ERROR_MESSAGE);
				}

			} else {
				if (id.equals("") || id.equals("아이디")) { // 아이디 공란
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "아이디 입력", JOptionPane.ERROR_MESSAGE);
				} else if (pw.equals("") || pw.equals("비밀번호")) {// 비밀번호 공란
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "비밀번호 입력", JOptionPane.ERROR_MESSAGE);
				} else if (pwchk.equals("") || pwchk.equals("비밀번호 확인")) {// 비밀번호 확인 공란
					JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요.", "비밀번호 확인 입력", JOptionPane.ERROR_MESSAGE);
				} else if (name.equals("") || name.equals("이름")) {// 이름 공란
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요.", "이름 입력", JOptionPane.ERROR_MESSAGE);
				} else if (birthday.equals("") || birthday.equals("생년월일")) {
					// 생년월일 공란
					JOptionPane.showMessageDialog(null, "생년월일을 입력해주세요.(20000101 형식)", "생년월일 입력", JOptionPane.ERROR_MESSAGE);
				} else if (phone.equals("") || phone.equals("전화번호")) { // 전화번호 공란
					JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.", "전화번호 입력", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (e.getSource() == idchkButton) { // 중복 확인 버튼 클릭
			if (db.IdCheckEmployee(id) && !id.equals("아이디")) {
				JOptionPane.showMessageDialog(null, "중복된 아이디 입니다.", "아이디 중복 확인", JOptionPane.ERROR_MESSAGE);
			} else if (id.equals("") || id.equals("아이디")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "아이디 중복 확인", JOptionPane.ERROR_MESSAGE);
				System.out.println(id);
			} else if (!db.IdCheckEmployee(id) && !id.equals("아이디")) {
				JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.", "아이디 중복 확인", JOptionPane.PLAIN_MESSAGE);
			}
		} else if (e.getSource() == cancelBtn) { // 취소 버튼 클릭
			new EmLogin();
			frame.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		String password = new String(passwordField.getPassword());
		String passwordchk = new String(confirmPasswordField.getPassword());
		if (obj == idField) {
			if (idField.getText().equals("아이디")) {
				idField.setText("");
			}
		} else if (obj == passwordField) {
			if (password.equals("비밀번호")) {
				passwordField.setText("");
			}
		} else if (obj == confirmPasswordField) {
			if (passwordchk.equals("비밀번호 확인")) {
				confirmPasswordField.setText("");
			}
		} else if (obj == nameField) {
			if (nameField.getText().equals("이름")) {
				nameField.setText("");
			}
		} else if (obj == birthDateField) {
			if (birthDateField.getText().equals("생년월일")) {
				birthDateField.setText("");
			}
		} else if (obj == phoneNumberField) {
			if (phoneNumberField.getText().equals("전화번호")) {
				phoneNumberField.setText("");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object obj = e.getSource();
		if (obj == cancelBtn) {
			cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else if (obj == SignUpBtn) {
			SignUpBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else if (obj == idchkButton) {
			idchkButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public static void main(String[] args) {
		new EmSignUp();
	}
}
