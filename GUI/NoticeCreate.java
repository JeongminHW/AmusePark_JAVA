package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

import DB.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.Calendar;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Cursor;
import java.awt.Component;

public class NoticeCreate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JTextArea textArea;

	private JComboBox<String> dateSYearCombobox, dateSMonthCombobox, dateSDayCombobox;
	private JComboBox<String> dateEYearCombobox, dateEMonthCombobox, dateEDayCombobox;

	private static final DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();

	static String id;

	public static void setId(String id) {
		NoticeCreate.id = id;
	}

	/**
	 * Create the frame.
	 */
	public NoticeCreate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setVisible(true);
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel noticeTitlePanel = new JPanel();
		noticeTitlePanel.setBackground(new Color(255, 255, 255));
		noticeTitlePanel.setMaximumSize(new Dimension(32767, 80));
		FlowLayout flowLayout = (FlowLayout) noticeTitlePanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		mainPanel.add(noticeTitlePanel);

		JPanel contentTitlePanel = new JPanel();
		contentTitlePanel.setMaximumSize(new Dimension(32767, 80));
		contentTitlePanel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mainPanel.add(contentTitlePanel);
		contentTitlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		contentTitlePanel.setBackground(Color.WHITE);

		JLabel ContentTitleLabel = new JLabel("내용");
		ContentTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		contentTitlePanel.add(ContentTitleLabel);

		CentralDropShadowPanel contentPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
		contentPanel.setPreferredSize(new Dimension(34, 150));
		contentPanel.setMinimumSize(new Dimension(22, 150));
		contentPanel.setMaximumSize(new Dimension(32767, 150));
		mainPanel.add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		contentPanel.setBackground(Color.WHITE);

		textArea = new JTextArea();
		textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textArea.setBorder(new EmptyBorder(10, 15, 10, 15));
		textArea.setOpaque(false);
		contentPanel.add(textArea);

		JPanel dateTitlePanel = new JPanel();
		dateTitlePanel.setMaximumSize(new Dimension(32767, 80));
		mainPanel.add(dateTitlePanel);
		dateTitlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		dateTitlePanel.setBackground(Color.WHITE);

		JLabel dateTitleLabel = new JLabel("일시");
		dateTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		dateTitlePanel.add(dateTitleLabel);

		CentralDropShadowPanel dateContentPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
		mainPanel.add(dateContentPanel);
		dateContentPanel.setLayout(new BoxLayout(dateContentPanel, BoxLayout.Y_AXIS));
		dateContentPanel.setBackground(Color.WHITE);

		JPanel dateSPanel = new JPanel();
		dateSPanel.setBorder(new EmptyBorder(5, 20, 5, 20));
		dateSPanel.setOpaque(false);
		dateContentPanel.add(dateSPanel);
		dateSPanel.setLayout(new BoxLayout(dateSPanel, BoxLayout.X_AXIS));

		JPanel dateSYearPanel = new JPanel();
		dateSYearPanel.setBackground(new Color(255, 255, 255));
		dateSPanel.add(dateSYearPanel);
		dateSYearPanel.setLayout(new BoxLayout(dateSYearPanel, BoxLayout.Y_AXIS));

		dateSYearCombobox = new JComboBox<String>();
		dateSYearCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateSYearCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateSYearCombobox.setBorder(new EmptyBorder(0, 20, 0, 10));
		dateSYearPanel.add(dateSYearCombobox);
		dateSYearCombobox.setBackground(Color.WHITE);
		dateSYearCombobox.setFocusable(false);

		dateSYearCombobox.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				JButton button = new JButton("▼");
				button.setOpaque(false);
				// button.setBackground(Color.BLUE);
				button.setMaximumSize(new Dimension(10, 10));
				button.setPreferredSize(new Dimension(10, 10));

				button.setBorder(BorderFactory.createEmptyBorder()); // 버튼 경계선 제거
				button.setBorderPainted(false); // 경계선 페인팅 비활성화
				button.setContentAreaFilled(false); // 버튼 내부 채우기 비활성화
				button.setFocusPainted(false); // 포커스 경계선 비활성화
				button.setFocusable(false);
				button.setName("ComboBox.arrowButton"); // Mandatory, as per BasicComboBoxUI#createArrowButton().
				return button;
			}

			@Override
			protected void installDefaults() {
				super.installDefaults();
				LookAndFeel.uninstallBorder(dateSYearCombobox);
			}

		});

		JPanel line1 = new DrawLine();
		dateSYearPanel.add(line1);
		line1.setBackground(Color.WHITE);

		dateSPanel.add(Box.createHorizontalStrut(10));

		JLabel dateSYearLabel = new JLabel("년");
		dateSYearLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		dateSPanel.add(dateSYearLabel);

		dateSPanel.add(Box.createHorizontalStrut(10));

		JPanel dateSMonthPanel = new JPanel();
		dateSMonthPanel.setBackground(new Color(255, 255, 255));
		dateSPanel.add(dateSMonthPanel);
		dateSMonthPanel.setLayout(new BoxLayout(dateSMonthPanel, BoxLayout.Y_AXIS));

		dateSMonthCombobox = new JComboBox<String>();
		dateSMonthCombobox.setBorder(new EmptyBorder(0, 10, 0, 10));
		dateSMonthCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateSMonthCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateSMonthPanel.add(dateSMonthCombobox);
		dateSMonthCombobox.setBackground(Color.WHITE);
		dateSMonthCombobox.setFocusable(false);

		dateSMonthCombobox.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				JButton button = new JButton("▼");
				button.setOpaque(false);
				// button.setBackground(Color.BLUE);
				button.setMaximumSize(new Dimension(10, 10));
				button.setPreferredSize(new Dimension(10, 10));

				button.setBorder(BorderFactory.createEmptyBorder()); // 버튼 경계선 제거
				button.setBorderPainted(false); // 경계선 페인팅 비활성화
				button.setContentAreaFilled(false); // 버튼 내부 채우기 비활성화
				button.setFocusPainted(false); // 포커스 경계선 비활성화
				button.setFocusable(false);
				button.setName("ComboBox.arrowButton"); // Mandatory, as per BasicComboBoxUI#createArrowButton().
				return button;
			}

			@Override
			protected void installDefaults() {
				super.installDefaults();
				LookAndFeel.uninstallBorder(dateSMonthCombobox);
			}

		});

		JPanel line2 = new DrawLine();
		line2.setBackground(new Color(255, 255, 255));
		dateSMonthPanel.add(line2);

		dateSPanel.add(Box.createHorizontalStrut(10));

		JLabel dateSMonthLabel = new JLabel("월");
		dateSMonthLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		dateSPanel.add(dateSMonthLabel);

		dateSPanel.add(Box.createHorizontalStrut(10));

		JPanel dateSDayPanel = new JPanel();
		dateSDayPanel.setBackground(new Color(255, 255, 255));
		dateSPanel.add(dateSDayPanel);
		dateSDayPanel.setLayout(new BoxLayout(dateSDayPanel, BoxLayout.Y_AXIS));

		dateSDayCombobox = new JComboBox<String>();
		dateSDayCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateSDayCombobox.setBorder(new EmptyBorder(0, 10, 0, 10));
		dateSDayCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateSDayPanel.add(dateSDayCombobox);
		dateSDayCombobox.setBackground(Color.WHITE);
		dateSDayCombobox.setFocusable(false);

		dateSDayCombobox.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				JButton button = new JButton("▼");
				button.setOpaque(false);
				// button.setBackground(Color.BLUE);
				button.setMaximumSize(new Dimension(10, 10));
				button.setPreferredSize(new Dimension(10, 10));

				button.setBorder(BorderFactory.createEmptyBorder()); // 버튼 경계선 제거
				button.setBorderPainted(false); // 경계선 페인팅 비활성화
				button.setContentAreaFilled(false); // 버튼 내부 채우기 비활성화
				button.setFocusPainted(false); // 포커스 경계선 비활성화
				button.setFocusable(false);
				button.setName("ComboBox.arrowButton"); // Mandatory, as per BasicComboBoxUI#createArrowButton().
				return button;
			}

			@Override
			protected void installDefaults() {
				super.installDefaults();
				LookAndFeel.uninstallBorder(dateSDayCombobox);
			}

		});

		JPanel line3 = new DrawLine();
		line3.setBackground(Color.WHITE);
		dateSDayPanel.add(line3);

		dateSPanel.add(Box.createHorizontalStrut(10));

		JLabel dateSDayLabel = new JLabel("일 부터");
		dateSDayLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		dateSPanel.add(dateSDayLabel);

		JPanel dateEPanel = new JPanel();
		dateEPanel.setBorder(new EmptyBorder(5, 20, 5, 20));
		dateEPanel.setOpaque(false);
		dateContentPanel.add(dateEPanel);
		dateEPanel.setLayout(new BoxLayout(dateEPanel, BoxLayout.X_AXIS));

		JPanel dateEYearPanel = new JPanel();
		dateEYearPanel.setBackground(Color.WHITE);
		dateEPanel.add(dateEYearPanel);
		dateEYearPanel.setLayout(new BoxLayout(dateEYearPanel, BoxLayout.Y_AXIS));

		dateEYearCombobox = new JComboBox<String>();
		dateEYearCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateEYearCombobox.setBorder(new EmptyBorder(0, 20, 0, 10));
		dateEYearCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateEYearPanel.add(dateEYearCombobox);
		dateEYearCombobox.setBackground(Color.WHITE);
		dateEYearCombobox.setFocusable(false);

		dateEYearCombobox.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				JButton button = new JButton("▼");
				button.setOpaque(false);
				// button.setBackground(Color.BLUE);
				button.setMaximumSize(new Dimension(10, 10));
				button.setPreferredSize(new Dimension(10, 10));

				button.setBorder(BorderFactory.createEmptyBorder()); // 버튼 경계선 제거
				button.setBorderPainted(false); // 경계선 페인팅 비활성화
				button.setContentAreaFilled(false); // 버튼 내부 채우기 비활성화
				button.setFocusPainted(false); // 포커스 경계선 비활성화
				button.setFocusable(false);
				button.setName("ComboBox.arrowButton"); // Mandatory, as per BasicComboBoxUI#createArrowButton().
				return button;
			}

			@Override
			protected void installDefaults() {
				super.installDefaults();
				LookAndFeel.uninstallBorder(dateEYearCombobox);
			}

		});

		JPanel line4 = new DrawLine();
		line4.setBackground(Color.WHITE);
		dateEYearPanel.add(line4);

		Component horizontalStrut = Box.createHorizontalStrut(10);
		dateEPanel.add(horizontalStrut);

		JLabel dateEYearLabel = new JLabel("년");
		dateEPanel.add(dateEYearLabel);
		dateEYearLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		dateEPanel.add(horizontalStrut_1);

		JPanel dateEMonthPanel = new JPanel();
		dateEMonthPanel.setBackground(Color.WHITE);
		dateEPanel.add(dateEMonthPanel);
		dateEMonthPanel.setLayout(new BoxLayout(dateEMonthPanel, BoxLayout.Y_AXIS));

		dateEMonthCombobox = new JComboBox<String>();
		dateEMonthCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateEMonthCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateEMonthCombobox.setBorder(new EmptyBorder(0, 10, 0, 10));
		dateEMonthPanel.add(dateEMonthCombobox);
		dateEMonthCombobox.setBackground(Color.WHITE);
		dateEMonthCombobox.setFocusable(false);

		dateEMonthCombobox.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				JButton button = new JButton("▼");
				button.setOpaque(false);
				// button.setBackground(Color.BLUE);
				button.setMaximumSize(new Dimension(10, 10));
				button.setPreferredSize(new Dimension(10, 10));

				button.setBorder(BorderFactory.createEmptyBorder()); // 버튼 경계선 제거
				button.setBorderPainted(false); // 경계선 페인팅 비활성화
				button.setContentAreaFilled(false); // 버튼 내부 채우기 비활성화
				button.setFocusPainted(false); // 포커스 경계선 비활성화
				button.setFocusable(false);
				button.setName("ComboBox.arrowButton"); // Mandatory, as per BasicComboBoxUI#createArrowButton().
				return button;
			}

			@Override
			protected void installDefaults() {
				super.installDefaults();
				LookAndFeel.uninstallBorder(dateEMonthCombobox);
			}

		});

		JPanel line5 = new DrawLine();
		line5.setBackground(Color.WHITE);
		dateEMonthPanel.add(line5);

		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		dateEPanel.add(horizontalStrut_2);

		JLabel dateEMonthLabel = new JLabel("월");
		dateEPanel.add(dateEMonthLabel);
		dateEMonthLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		Component horizontalStrut_3 = Box.createHorizontalStrut(10);
		dateEPanel.add(horizontalStrut_3);

		JPanel dateEDayPanel = new JPanel();
		dateEDayPanel.setBackground(Color.WHITE);
		dateEPanel.add(dateEDayPanel);
		dateEDayPanel.setLayout(new BoxLayout(dateEDayPanel, BoxLayout.Y_AXIS));

		dateEDayCombobox = new JComboBox<String>();
		dateEDayCombobox.setBorder(new EmptyBorder(0, 10, 0, 10));
		dateEDayCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateEDayPanel.add(dateEDayCombobox);
		dateEDayCombobox.setBackground(Color.WHITE);
		dateEDayCombobox.setFocusable(false);

		dateEDayCombobox.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				JButton button = new JButton("▼");
				button.setOpaque(false);
				// button.setBackground(Color.BLUE);
				button.setMaximumSize(new Dimension(10, 10));
				button.setPreferredSize(new Dimension(10, 10));

				button.setBorder(BorderFactory.createEmptyBorder()); // 버튼 경계선 제거
				button.setBorderPainted(false); // 경계선 페인팅 비활성화
				button.setContentAreaFilled(false); // 버튼 내부 채우기 비활성화
				button.setFocusPainted(false); // 포커스 경계선 비활성화
				button.setFocusable(false);
				button.setName("ComboBox.arrowButton");
				return button;
			}

			@Override
			protected void installDefaults() {
				super.installDefaults();
				LookAndFeel.uninstallBorder(dateEDayCombobox);
			}

		});
		
		// 콤보박스 설정
				Calendar now = Calendar.getInstance();
				int year = now.get(Calendar.YEAR);
				int month = now.get(Calendar.MONTH) + 1;
				int day = now.get(Calendar.DATE);

				dateSYearCombobox.addItem("선택");
				dateSMonthCombobox.addItem("선택");
				dateSDayCombobox.addItem("선택");
				dateEYearCombobox.addItem("선택");
				dateEMonthCombobox.addItem("선택");
				dateEDayCombobox.addItem("선택");
				for (int i = year; i < year + 5; i++) {
					dateSYearCombobox.addItem(Integer.toString(i));
					dateEYearCombobox.addItem(Integer.toString(i));
				}

				for (int i = 1; i < 13; i++) {
					if (i < 10) {
						dateSMonthCombobox.addItem("0" + Integer.toString(i));
						dateEMonthCombobox.addItem("0" + Integer.toString(i));
					} else {
						dateSMonthCombobox.addItem(Integer.toString(i));
						dateEMonthCombobox.addItem(Integer.toString(i));
					}
				}

				for (int i = 1; i < now.getActualMaximum(Calendar.DAY_OF_MONTH) + 1; i++) {
					if (i < 10) {
						dateSDayCombobox.addItem("0" + Integer.toString(i));
						dateEDayCombobox.addItem("0" + Integer.toString(i));
					} else {
						dateSDayCombobox.addItem(Integer.toString(i));
						dateEDayCombobox.addItem(Integer.toString(i));
					}
				}

		JPanel line6 = new DrawLine();
		line6.setBackground(Color.WHITE);
		dateEDayPanel.add(line6);

		Component horizontalStrut_4 = Box.createHorizontalStrut(10);
		dateEPanel.add(horizontalStrut_4);

		JLabel dateEDayLabel = new JLabel("일 까지");
		dateEPanel.add(dateEDayLabel);
		dateEDayLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) buttonPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		mainPanel.add(buttonPanel);
		buttonPanel.setBackground(Color.WHITE);

		JButton cancelButton = new RoundedButton("취소", 15);
		cancelButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		buttonPanel.add(cancelButton);
		cancelButton.setBackground(Color.GRAY);
		cancelButton.setForeground(Color.WHITE);

		JButton confrimButton = new RoundedButton("확인", 15);
		confrimButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		buttonPanel.add(confrimButton);
		confrimButton.setBackground(new Color(0, 148, 255));
		confrimButton.setForeground(Color.WHITE);

		// 버튼 이벤트
		confrimButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					saveSchedule();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void saveSchedule() throws HeadlessException, Exception {
		String content = textArea.getText().trim();
		String startDate = dateSYearCombobox.getSelectedItem() + "-" + dateSMonthCombobox.getSelectedItem() + "-" + dateSDayCombobox.getSelectedItem();
		String endDate = dateEYearCombobox.getSelectedItem() + "-" + dateEMonthCombobox.getSelectedItem() + "-" + dateEDayCombobox.getSelectedItem();

		if (validateInputs(content, startDate, endDate)) {
			if (insertSchedule(id, content, startDate, endDate)) {
				JOptionPane.showMessageDialog(null, "일정이 성공적으로 추가되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "일정 추가에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private boolean validateInputs(String content, String startDate, String endDate) {
		return !content.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty();
	}

	private boolean insertSchedule(String writerId, String content, String startDate, String endDate) throws Exception {

		String sql = "INSERT INTO SCHEDULE (writer_id, schedule_contents, schedule_start, schedule_end) VALUES (?, ?, ?, ?)";
		try (Connection conn = dbMgr.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, writerId);
			pstmt.setString(2, content);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	///////////////////////// 추가

	void checkUserPermissionsAndShowGUI() throws HeadlessException, Exception {

		if (isUserManager(id)) {
			NoticeCreate noticeCreateFrame = new NoticeCreate();

			noticeCreateFrame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "일정 등록 권한이 없습니다.", "권한 오류", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	private boolean isUserManager(String userId) throws Exception {
		String sql = "SELECT em_manage FROM employee WHERE em_id = ?";
		try (Connection conn = dbMgr.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String emManage = rs.getString("em_manage");
				return emManage.equals("관리자"); // 관리자 권한 확인
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoticeCreate frame = new NoticeCreate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
