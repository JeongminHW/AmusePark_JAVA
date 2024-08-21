package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;

import org.xnio.channels.SslChannel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import DB.*;
import java.util.*;

public class VacationConfirm extends JFrame implements ActionListener {
	static String id;
	
	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		VacationConfirm.id = id;
	}

	static final long serialVersionUID = 1L;
	JPanel mainPanel;
	JPanel titlePanel = new JPanel();
	JPanel contentSubtitlePanel = new JPanel();
	JPanel subContentPanel = new JPanel();
	JPanel contentPanel1;
	JPanel element;
	JPanel panel;
	JPanel linePanel;
	JScrollBar scrollPane;
	FlowLayout flowLayout = (FlowLayout) titlePanel.getLayout();
	FlowLayout fl_panel = new FlowLayout();
	CentralDropShadowPanel contentPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
	JLabel titleLabel;
	JLabel nameLabel_1;
	JLabel nameLabel;
	JLabel startDateLabel;
	JLabel endDateLabel;
	JLabel ReasonLabel;
	JLabel startDateLabel_1;
	JLabel endDateLabel_1;
	JLabel reasonLabel_1;
	JButton cancelButton;
	JButton confirmButton;
	JButton backButton;

	DBMgr db = new DBMgr();
	Vector<VacationBean> vlist;

	/**
	 * Create the frame.
	 */
	public VacationConfirm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		setVisible(true);
		setTitle("휴가 컨펌 - " + id);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		titlePanel.setMaximumSize(new Dimension(32767, 40));
		flowLayout.setAlignment(FlowLayout.LEFT);
		mainPanel.add(titlePanel);

		titleLabel = new JLabel("휴가 신청 목록");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		titlePanel.add(titleLabel);

		backButton = new RoundedButton("뒤로", 20);
		backButton.setBackground(Color.white);
		titlePanel.add(Box.createHorizontalStrut(450));
		titlePanel.add(backButton, BorderLayout.EAST);

		contentPanel.setMaximumSize(new Dimension(32767, 500));
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		mainPanel.add(contentPanel);

		contentSubtitlePanel.setMaximumSize(new Dimension(32767, 30));
		contentSubtitlePanel.setOpaque(false);
		contentPanel.add(contentSubtitlePanel);
		contentSubtitlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		contentSubtitlePanel.add(Box.createHorizontalStrut(10));

		nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		contentSubtitlePanel.add(nameLabel);
		contentSubtitlePanel.add(Box.createHorizontalStrut(20));

		startDateLabel = new JLabel("시작 날짜");
		startDateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		contentSubtitlePanel.add(startDateLabel);
		contentSubtitlePanel.add(Box.createHorizontalStrut(25));

		endDateLabel = new JLabel("끝 날짜");
		endDateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		contentSubtitlePanel.add(endDateLabel);
		contentSubtitlePanel.add(Box.createHorizontalStrut(25));

		ReasonLabel = new JLabel("이유");
		ReasonLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		contentSubtitlePanel.add(ReasonLabel);

		mainForm();
		
		if (getTitle().equals("휴가 컨펌 - null")) {
			dispose();
		}
	}

	private void mainForm() {
		vlist = db.listVacation();

		for (int i = 0; i < vlist.size(); i++) {
			// 각 휴가 신청 항목마다 새로운 rowPanel 생성
			JPanel rowPanel = new JPanel();
			// rowPanel.setLayout(new GridLayout(1, 6, 10, 10));
			rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

			nameLabel_1 = new JLabel(vlist.get(i).getName());
			nameLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));

			startDateLabel_1 = new JLabel(vlist.get(i).getStart());
			startDateLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));

			endDateLabel_1 = new JLabel(vlist.get(i).getEnd());
			endDateLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));

			reasonLabel_1 = new JLabel(vlist.get(i).getReason());
			reasonLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));

			cancelButton = new RoundedButton("반려", 20);
			cancelButton.setBackground(new Color(192, 192, 192));
			cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));

			confirmButton = new RoundedButton("수락", 20);
			confirmButton.setForeground(Color.WHITE);
			confirmButton.setBackground(new Color(0, 148, 255));
			confirmButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));

			// rowPanel에 구성 요소 추가
			rowPanel.add(nameLabel_1);
			rowPanel.add(Box.createHorizontalStrut(10));
			rowPanel.add(startDateLabel_1);
			rowPanel.add(Box.createHorizontalStrut(10));
			rowPanel.add(endDateLabel_1);
			rowPanel.add(Box.createHorizontalStrut(10));
			rowPanel.add(reasonLabel_1);
			rowPanel.add(Box.createHorizontalStrut(250));
			rowPanel.add(cancelButton);
			rowPanel.add(confirmButton);

			// contentPanel에 rowPanel 추가
			contentPanel.add(rowPanel);
			
			backButton.addActionListener(this);
			cancelButton.addActionListener(this);
			confirmButton.addActionListener(this);
		}

		// 리스트가 많을 경우 스크롤이 가능하도록
		contentPanel.revalidate();
		contentPanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == backButton) {
			dispose();
		} else if (obj == cancelButton) {
			 // 클릭된 버튼의 rowPanel에서 해당하는 데이터를 가져옵니다.
	        JPanel sourcePanel = (JPanel) ((JButton) obj).getParent();

	        // Panel에 있는 Labels를 가져옵니다.
	        JLabel nameLabel = (JLabel) sourcePanel.getComponent(0);
	        JLabel startDateLabel = (JLabel) sourcePanel.getComponent(2);
	        JLabel endDateLabel = (JLabel) sourcePanel.getComponent(4);
	        JLabel reasonLabel = (JLabel) sourcePanel.getComponent(6);

	        // 시작 날짜와 종료 날짜를 가져와서 일수 계산
	        String startDate = startDateLabel.getText();
	        String endDate = endDateLabel.getText();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate start = LocalDate.parse(startDate, formatter);
	        LocalDate end = LocalDate.parse(endDate, formatter);
	        int diffDays = (int) ChronoUnit.DAYS.between(start, end) + 1; // 시작일과 종료일 포함
	        
			// 해당 신청 항목의 VacationBean 객체 찾기
	        VacationBean selectedVacation = null;
	        for (VacationBean bean : vlist) {
	            if (bean.getName().equals(nameLabel.getText()) &&
	                bean.getStart().equals(startDate) &&
	                bean.getEnd().equals(endDate) &&
	                bean.getReason().equals(reasonLabel.getText())) {
	                selectedVacation = bean;
	                break;
	            }
	        }

	        if (selectedVacation != null) {
	            int requestNum = selectedVacation.getNum();

	            // DB 업데이트 : 반려
	            db.acceptVacation(requestNum);
            	JOptionPane.showMessageDialog(null, "휴가 신청이 반려되었습니다.", "휴가 반려", JOptionPane.PLAIN_MESSAGE);
	            // UI에서 해당 신청 항목을 삭제
	            contentPanel.remove(sourcePanel);
	            contentPanel.revalidate();
	            contentPanel.repaint();
	        } else {
	            System.out.println("해당 휴가 신청 항목을 찾을 수 없습니다.");
	        }
		} else if (obj == confirmButton) {
			 // 클릭된 버튼의 rowPanel에서 해당하는 데이터를 가져옵니다.
	        JPanel sourcePanel = (JPanel) ((JButton) obj).getParent();

	        // Panel에 있는 Labels를 가져옵니다.
	        JLabel nameLabel = (JLabel) sourcePanel.getComponent(0);
	        JLabel startDateLabel = (JLabel) sourcePanel.getComponent(2);
	        JLabel endDateLabel = (JLabel) sourcePanel.getComponent(4);
	        JLabel reasonLabel = (JLabel) sourcePanel.getComponent(6);

	        // 시작 날짜와 종료 날짜를 가져와서 일수 계산
	        String startDate = startDateLabel.getText();
	        String endDate = endDateLabel.getText();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate start = LocalDate.parse(startDate, formatter);
	        LocalDate end = LocalDate.parse(endDate, formatter);
	        int diffDays = (int) ChronoUnit.DAYS.between(start, end) + 1; // 시작일과 종료일 포함

	        // 해당 신청 항목의 VacationBean 객체 찾기
	        VacationBean selectedVacation = null;
	        for (VacationBean bean : vlist) {
	            if (bean.getName().equals(nameLabel.getText()) &&
	                bean.getStart().equals(startDate) &&
	                bean.getEnd().equals(endDate) &&
	                bean.getReason().equals(reasonLabel.getText())) {
	                selectedVacation = bean;
	                break;
	            }
	        }

	        if (selectedVacation != null) {
	            int requestNum = selectedVacation.getNum();
	            String requestId = selectedVacation.getId();

	            // DB 업데이트: 신청 수락 및 남은 휴가 일수 업데이트
	            boolean accepted = db.acceptVacation(requestNum);
	            if (accepted) {
	                boolean updated = db.updateRemainVacation("asdf123", diffDays);
	                if (updated) {
	                	JOptionPane.showMessageDialog(null, "휴가 신청이 수락되었습니다.", "휴가 수락", JOptionPane.PLAIN_MESSAGE);
	                } else {
	                    System.out.println("남은 휴가 일수 업데이트에 실패했습니다.");
	                }
	            } else {
	            	JOptionPane.showMessageDialog(null, "휴가 신청 수락에 실패했습니다.", "휴가 수락", JOptionPane.ERROR_MESSAGE);
	            }

	            // UI에서 해당 신청 항목을 삭제
	            contentPanel.remove(sourcePanel);
	            contentPanel.revalidate();
	            contentPanel.repaint();
	        } else {
	            System.out.println("해당 휴가 신청 항목을 찾을 수 없습니다.");
	        }
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VacationConfirm frame = new VacationConfirm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
