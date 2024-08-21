package GUI;
//디자인 변경 완

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import DB.*;

public class PTLogin implements ActionListener, MouseListener{
	DBMgr db = new DBMgr();
	
    JFrame frame = new JFrame("알바 로그인/회원가입");
    JPanel mainPanel = new JPanel();
    JPanel loginPanel = new JPanel();
    JPanel btnPanel = new JPanel();
    JTextField idField = new JTextField("ID");
    JPasswordField PwField = new JPasswordField("Password");
    JPanel linePanel1 = new DrawLine(); // 줄을 그릴 패널 생성
    JPanel linePanel2 = new DrawLine(); // 줄을 그릴 패널 생성
    JButton SignInBtn = new RoundedButton("로그인", 32);
    JButton SignUpBtn = new RoundedButton("회원가입", 32);

    public PTLogin() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(Color.WHITE);

        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.setBackground(Color.WHITE);

        ImageIcon icon = new ImageIcon("src/image/TestImage.jpg"); // 이미지 경로를 입력
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        idField.setFont(new Font("Airal", Font.PLAIN, 15));
        idField.setBorder(null);
       
        PwField.setFont(new Font("Airal", Font.PLAIN, 15));
        PwField.setBorder(null);

        linePanel1.setBackground(Color.BLACK);
        linePanel2.setBackground(Color.BLACK);

        SignInBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        SignInBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        SignInBtn.setHorizontalAlignment(JButton.CENTER);
        SignInBtn.setVerticalAlignment(JButton.CENTER);
        SignInBtn.setFont(new Font("돋움", Font.PLAIN, 15));
        SignInBtn.setForeground(Color.WHITE);
        SignInBtn.setBackground(Color.BLACK);
        
       


        SignUpBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        SignUpBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        SignUpBtn.setFont(new Font("돋움", Font.PLAIN,15));
        SignUpBtn.setForeground(Color.WHITE);
        SignUpBtn.setBackground(Color.BLACK);
        
        // 이벤트
        SignInBtn.addActionListener(this);
        SignUpBtn.addActionListener(this);
        idField.addMouseListener(this);
        PwField.addMouseListener(this);
        SignInBtn.addMouseListener(this);
        SignUpBtn.addMouseListener(this);
        
        
        SignInBtn.setMaximumSize(new Dimension(100,40));
        SignUpBtn.setMaximumSize(new Dimension(100,40));
        idField.setMaximumSize(new Dimension(180,40));
        PwField.setMaximumSize(new Dimension(180,40));
        linePanel1.setMaximumSize(new Dimension(200,2));
        linePanel2.setMaximumSize(new Dimension(200,2));
        
        loginPanel.add(idField);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(linePanel1); // 줄 추가
        loginPanel.add(Box.createVerticalStrut(20)); // ID와 PW 사이의 간격
        loginPanel.add(PwField);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(linePanel2); // 줄 추가

        btnPanel.add(SignUpBtn);
        btnPanel.add(Box.createHorizontalStrut(10)); // 버튼 사이의 간격
        btnPanel.add(SignInBtn);

        mainPanel.add(Box.createVerticalStrut(50)); // 상단 여백
        mainPanel.add(imageLabel);
        mainPanel.add(Box.createVerticalStrut(60)); // 이미지와 ID 사이의 간격
        mainPanel.add(loginPanel);
        mainPanel.add(Box.createVerticalStrut(20)); // 이미지와 ID 사이의 간격
        mainPanel.add(btnPanel);
    
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    //이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == SignInBtn) { // 로그인 버튼
			String id = idField.getText();
        	String pw = new String(PwField.getPassword());
        	if(db.LoginCheckAlba(id, pw)) {
        		System.out.println("로그인 성공");
        		AlbaMain albamain = new AlbaMain(id);
				albamain.setId(id);
				albamain.setVisible(true);
				frame.dispose();
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "아이디/비밀번호를 다시 입력해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
        		System.out.println("로그인 실패");
        	}
		}
		else if(obj == SignUpBtn) { // 회원가입 버튼
			new PTSignUp();
			frame.dispose();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
        String idtext = idField.getText();
    	String pwtext = new String(PwField.getPassword());
		if(obj == idField) {
			if(idtext.equals("ID")) {
	            idField.setText("");
	        }
		}
		else if(obj == PwField) {
			if(pwtext.equals("Password")) {
				PwField.setText("");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object obj = e.getSource();
		if (obj == SignInBtn) {
			SignInBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else if (obj == SignUpBtn) {
			SignUpBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        new PTLogin();
    }
}