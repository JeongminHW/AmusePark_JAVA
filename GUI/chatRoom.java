package cmp.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JList;
import java.awt.Dimension;
import javax.swing.AbstractListModel;
import java.awt.FlowLayout;

import cmp.DB.*;

public class chatRoom extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JTextField writeField;
	private Vector<EmployeeBean> em2vlist;
	private chatManager cm = new chatManager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public chatRoom(int chatNo, String chatRoomName, String id) {
		
		setTitle("채팅방 - " + chatRoomName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 700);
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel chatPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) chatPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		chatPanel.setOpaque(false);
		mainPanel.add(chatPanel, BorderLayout.CENTER);
		
		JTextArea ta = new JTextArea();
		
		ta.setBorder(new EmptyBorder(10, 10, 10, 10));
		ta.setEditable(false);
		chatPanel.add(ta);
		
		CentralDropShadowPanel writePanel = new CentralDropShadowPanel(Color.LIGHT_GRAY, 20);
		writePanel.setPreferredSize(new Dimension(34, 40));
		writePanel.setMaximumSize(new Dimension(32767, 34));
		writePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		writePanel.setBackground(Color.WHITE);
		writePanel.setOpaque(false);
		mainPanel.add(writePanel, BorderLayout.SOUTH);
		writePanel.setLayout(new BorderLayout(0, 0));
		
		writeField = new JTextField();
		writeField.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		writeField.setBorder(new EmptyBorder(0, 10, 0, 0));
		writePanel.add(writeField, BorderLayout.CENTER);
		writeField.setColumns(10);
		
		JButton writeButton = new RoundedButton("전송",20);
		writeButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		writeButton.setBackground(new Color(51,153,255));
		writeButton.setForeground(Color.WHITE);
		writePanel.add(writeButton, BorderLayout.EAST);
		
		em2vlist = cm.myName(id);
	        String str = em2vlist.get(0).getName() + " " + em2vlist.get(0).getPosition();

	        chatContentsBean ccbean = new chatContentsBean();
	        
	        writeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String message = "[" + str + "] : " + writeField.getText();
                    if (!message.trim().isEmpty()) {
                    	ta.append(message + "\n");
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String formattedNow = now.format(formatter);

                        ccbean.setChat_date(formattedNow);
                        ccbean.setChat_contents(writeField.getText());
                        ccbean.setChat_no(chatNo);
                        ccbean.setUser_id(id);

                        try {
							if (cm.insertchatContents(ccbean)) {
							    System.out.println("성공");
							}
						} catch (Exception e1) {
							
							e1.printStackTrace();
						}

                        writeField.setText("");
                    }
                }
            });

            // Timer를 사용하여 주기적으로 채팅 내용을 업데이트
            Timer timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateChatArea(ta, chatNo);
                }
            });
            timer.start();
	        
	        
	}
	
    private void updateChatArea(JTextArea chatArea, int chatNo) {
        // 채팅 내용을 가져와서 JTextArea를 업데이트
        Vector<chatContentsBean> newContents = cm.selectChatContents(chatNo);
        StringBuilder updatedText = new StringBuilder();

        for (chatContentsBean content : newContents) {
            updatedText.append("[").append(cm.myName(content.getUser_id()).get(0).getName())
                .append(" ").append(cm.myName(content.getUser_id()).get(0).getPosition())
                .append("] : ").append(content.getChat_contents()).append("\n");
        }

        chatArea.setText(updatedText.toString());
    }


}
