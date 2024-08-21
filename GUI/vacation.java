package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.DefaultComboBoxModel;

import DB.*;

import java.util.*;

public class Vacation extends JFrame implements ActionListener {
	static String id;

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		Vacation.id = id;
	}

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;

	// Define
	CentralDropShadowPanel leftPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
	CentralDropShadowPanel rightUpperPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
	CentralDropShadowPanel rightDownerPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);

	JPanel calendarPanel = new CalendarPanel();
	JPanel vacationReasonPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	JPanel vacationReasonLine = new DrawLine();

	JPanel rightUpperTitlePanel = new JPanel();
	JPanel vacationDateSPanel = new JPanel();
	JPanel dateSYearPanel = new JPanel();
	JPanel rightUpperContent = new JPanel();
	JPanel leftDownerTitlePanel = new JPanel();
	JPanel linePanel1 = new DrawLine();
	JPanel dateSMonthPanel = new JPanel();
	JPanel linePanel2 = new DrawLine();
	JPanel dateSDayPanel = new JPanel();
	JPanel linePanel3 = new DrawLine();
	JPanel linePanel4 = new DrawLine();
	JPanel dateEMonthPanel = new JPanel();
	JPanel linePanel5 = new DrawLine();
	JPanel vacationDateEPanel = new JPanel();
	JPanel dateEYearPanel = new JPanel();
	JPanel DateEDayPanel = new JPanel();
	JPanel linePanel6 = new DrawLine();
	JPanel vacationSubtitlePanel = new JPanel();
	JPanel vacationLeftPanel = new JPanel();
	JPanel buttonPanel = new JPanel();

	JLabel leftDownerTitleLabel = new JLabel("휴가 신청");
	JLabel rightUpperTitleLabel = new JLabel("일정");
	JLabel dateSYearLabel = new JLabel("년");
	JLabel dateSMonthLabel = new JLabel("월");
	JLabel dateSDayLabel = new JLabel("일 부터");
	JLabel dateEYearLabel = new JLabel("년");
	JLabel dateEMonthLabel = new JLabel("월");
	JLabel dateEDayLabel = new JLabel("일 까지");
	JLabel vacationReasonTitleLabel = new JLabel("사유");
	JLabel lblNewLabel_4 = new JLabel("남은 휴가일수 : ");

	JTextArea textArea = new JTextArea();
	JTextField vacationReasonTextfield = new JTextField();

	JComboBox<String> dateSYearCombobox = new JComboBox<String>();
	JComboBox<String> dateSMonthCombobox = new JComboBox<String>();
	JComboBox<String> dateSDayCombobox = new JComboBox<String>();
	JComboBox<String> dateEYearCombobox = new JComboBox<String>();
	JComboBox<String> dateEMonthCombobox = new JComboBox<String>();
	JComboBox<String> dateEDayCombobox = new JComboBox<String>();

	JButton cancelButton = new RoundedButton("취소", 25);
	JButton confirmButton = new RoundedButton("확인", 25);

	DBMgr db = new DBMgr();
	Vector<EmployeeBean> vlist;

	/**
	 * Create the frame.
	 * 
	 *
	 */
	public Vacation() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		setVisible(true);
		setTitle("휴가 신청 - " + id);
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		// set Size
		rightPanel.setMaximumSize(new Dimension(550, 390));
		rightPanel.setPreferredSize(new Dimension(550, 390));
		vacationReasonLine.setMaximumSize(new Dimension(420, 3));
		rightUpperTitlePanel.setMaximumSize(new Dimension(getWidth(), 40));
		leftDownerTitlePanel.setMaximumSize(new Dimension(32767, 40));
		vacationDateSPanel.setMaximumSize(new Dimension(380, 40));
		dateSYearCombobox.setMaximumSize(new Dimension(80, 32767));
		dateSMonthCombobox.setMaximumSize(new Dimension(80, 32767));
		dateSDayCombobox.setMaximumSize(new Dimension(80, 32767));
		vacationDateEPanel.setMaximumSize(new Dimension(380, 40));
		vacationSubtitlePanel.setMaximumSize(new Dimension(32767, 20));
		vacationReasonTextfield.setMaximumSize(new Dimension(420, 40));
		vacationLeftPanel.setMaximumSize(new Dimension(getWidth(), 40));
		dateEYearCombobox.setMaximumSize(new Dimension(80, 32767));
		dateEMonthCombobox.setMaximumSize(new Dimension(80, 32767));
		dateEDayCombobox.setMaximumSize(new Dimension(80, 32767));

		// set Borders
		rightUpperPanel.setBorder(new EmptyBorder(5, 10, 15, 10));
		rightDownerPanel.setBorder(new EmptyBorder(5, 10, 10, 10));
		leftPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
		vacationLeftPanel.setLayout(new BorderLayout(0, 0));
		vacationReasonTextfield.setBorder(null);

		// setUI
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
				LookAndFeel.uninstallBorder(comboBox);
			}

		});

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
				LookAndFeel.uninstallBorder(dateSMonthCombobox);
			}

		});

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
				LookAndFeel.uninstallBorder(dateSMonthCombobox);
			}

		});

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
				LookAndFeel.uninstallBorder(dateSMonthCombobox);
			}

		});

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
				button.setName("ComboBox.arrowButton"); // Mandatory, as per BasicComboBoxUI#createArrowButton().
				return button;
			}

			@Override
			protected void installDefaults() {
				super.installDefaults();
				LookAndFeel.uninstallBorder(dateSMonthCombobox);
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
		System.out.println(getId());

		// 휴가 일수
		int Totalvacation =  db.TotalVacation(id);
		lblNewLabel_4.setText("남은 휴가 : " + Totalvacation);

		// setOpaque
		rightUpperTitlePanel.setOpaque(false);
		leftDownerTitlePanel.setOpaque(false);
		vacationLeftPanel.setOpaque(false);

		// set Backgrounds
		leftPanel.setBackground(new Color(255, 255, 255));
		rightPanel.setBackground(new Color(255, 255, 255));
		buttonPanel.setBackground(new Color(255, 255, 255));
		rightUpperPanel.setBackground(new Color(255, 255, 255));
		rightDownerPanel.setBackground(new Color(255, 255, 255));
		rightUpperTitlePanel.setBackground(new Color(255, 255, 255));
		rightUpperContent.setBackground(new Color(255, 255, 255));
		leftDownerTitlePanel.setBackground(new Color(255, 255, 255));
		leftDownerTitleLabel.setBackground(new Color(255, 255, 255));
		vacationDateSPanel.setBackground(new Color(255, 255, 255));
		dateSYearPanel.setBackground(new Color(255, 255, 255));
		dateSYearCombobox.setBackground(new Color(255, 255, 255));
		linePanel1.setBackground(new Color(255, 255, 255));
		dateSYearLabel.setBackground(new Color(255, 255, 255));
		dateSMonthPanel.setBackground(new Color(255, 255, 255));
		dateSMonthCombobox.setBackground(Color.WHITE);
		linePanel2.setBackground(new Color(255, 255, 255));
		dateSMonthLabel.setBackground(new Color(255, 255, 255));
		dateSDayPanel.setBackground(new Color(255, 255, 255));
		dateSDayCombobox.setBackground(Color.WHITE);
		linePanel3.setBackground(new Color(255, 255, 255));
		vacationDateEPanel.setBackground(new Color(255, 255, 255));
		dateEYearPanel.setBackground(new Color(255, 255, 255));
		linePanel4.setBackground(new Color(255, 255, 255));
		linePanel5.setBackground(new Color(255, 255, 255));
		linePanel6.setBackground(new Color(255, 255, 255));
		vacationSubtitlePanel.setBackground(new Color(255, 255, 255));
		vacationLeftPanel.setBackground(new Color(255, 255, 255));
		cancelButton.setBackground(Color.LIGHT_GRAY);
		confirmButton.setBackground(new Color(0, 148, 255));
		vacationReasonLine.setBackground(new Color(255, 255, 255));
		dateEMonthPanel.setBackground(new Color(255, 255, 255));
		DateEDayPanel.setBackground(new Color(255, 255, 255));
		dateEYearCombobox.setBackground(Color.WHITE);
		dateEMonthCombobox.setBackground(Color.WHITE);
		dateEDayCombobox.setBackground(Color.WHITE);

		// set Foreground
		cancelButton.setForeground(Color.WHITE);
		confirmButton.setForeground(Color.WHITE);

		// set Layout
		FlowLayout flowLayout = (FlowLayout) rightUpperTitlePanel.getLayout();
		FlowLayout flowLayout_1 = (FlowLayout) leftDownerTitlePanel.getLayout();
		FlowLayout flowLayout_2 = (FlowLayout) rightUpperContent.getLayout();
		FlowLayout flowLayout_3 = (FlowLayout) vacationSubtitlePanel.getLayout();

		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		rightUpperPanel.setLayout(new BoxLayout(rightUpperPanel, BoxLayout.Y_AXIS));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightDownerPanel.setLayout(new BoxLayout(rightDownerPanel, BoxLayout.Y_AXIS));
		vacationDateSPanel.setLayout(new BoxLayout(vacationDateSPanel, BoxLayout.X_AXIS));
		dateSYearPanel.setLayout(new BoxLayout(dateSYearPanel, BoxLayout.Y_AXIS));
		dateSMonthPanel.setLayout(new BoxLayout(dateSMonthPanel, BoxLayout.Y_AXIS));
		dateSDayPanel.setLayout(new BoxLayout(dateSDayPanel, BoxLayout.Y_AXIS));
		vacationDateEPanel.setLayout(new BoxLayout(vacationDateEPanel, BoxLayout.X_AXIS));
		dateEYearPanel.setLayout(new BoxLayout(dateEYearPanel, BoxLayout.Y_AXIS));
		dateEMonthPanel.setLayout(new BoxLayout(dateEMonthPanel, BoxLayout.Y_AXIS));
		DateEDayPanel.setLayout(new BoxLayout(DateEDayPanel, BoxLayout.Y_AXIS));
		vacationReasonPanel.setLayout(new BoxLayout(vacationReasonPanel, BoxLayout.Y_AXIS));

		// set Font
		rightUpperTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		leftDownerTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		dateSYearCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateSYearLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateSMonthCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateSMonthLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateSDayLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		vacationReasonTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		cancelButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		confirmButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		dateEYearCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateEYearLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateEMonthCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateEMonthLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateEDayLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dateEDayCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 12));

		// set Alignment
		rightUpperTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_3.setAlignment(FlowLayout.LEFT);

		// set Focusable
		dateSYearCombobox.setFocusable(false);
		dateSMonthCombobox.setFocusable(false);
		dateSDayCombobox.setFocusable(false);
		dateEYearCombobox.setFocusable(false);
		dateEMonthCombobox.setFocusable(false);
		dateEDayCombobox.setFocusable(false);

		// set Cursor
		dateSYearCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateSMonthCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateSDayCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateEYearCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateEMonthCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateEDayCombobox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// set Other
		vacationReasonTextfield.setColumns(10);

		// add
		rightUpperContent.add(textArea);
		rightUpperTitlePanel.add(rightUpperTitleLabel);
		rightUpperPanel.add(rightUpperTitlePanel);
		rightUpperPanel.add(rightUpperContent);

		leftDownerTitlePanel.add(leftDownerTitleLabel);
		rightDownerPanel.add(leftDownerTitlePanel);
		rightDownerPanel.add(vacationDateSPanel);
		vacationDateSPanel.add(dateSYearPanel);
		vacationDateSPanel.add(Box.createHorizontalStrut(10));
		dateSYearPanel.add(dateSYearCombobox);
		dateSYearPanel.add(linePanel1);
		vacationDateSPanel.add(dateSYearLabel);
		vacationDateSPanel.add(Box.createHorizontalStrut(10));
		vacationDateSPanel.add(dateSMonthPanel);
		dateSMonthPanel.add(dateSMonthCombobox);
		dateSMonthPanel.add(linePanel2);
		vacationDateSPanel.add(Box.createHorizontalStrut(10));
		vacationDateSPanel.add(dateSMonthLabel);
		vacationDateSPanel.add(Box.createHorizontalStrut(10));
		vacationDateSPanel.add(dateSDayPanel);
		dateSDayPanel.add(dateSDayCombobox);
		dateSDayPanel.add(linePanel3);
		vacationDateSPanel.add(Box.createHorizontalStrut(10));
		vacationDateSPanel.add(dateSDayLabel);
		rightDownerPanel.add(vacationDateEPanel);
		vacationDateEPanel.add(dateEYearPanel);
		dateEYearPanel.add(dateEYearCombobox);
		dateEYearPanel.add(linePanel4);
		vacationDateEPanel.add(Box.createHorizontalStrut(10));
		vacationDateEPanel.add(dateEYearLabel);
		vacationDateEPanel.add(Box.createHorizontalStrut(10));
		vacationDateEPanel.add(dateEMonthPanel);
		dateEMonthPanel.add(dateEMonthCombobox);
		dateEMonthPanel.add(linePanel5);
		vacationDateEPanel.add(Box.createHorizontalStrut(10));
		vacationDateEPanel.add(dateEMonthLabel);
		vacationDateEPanel.add(Box.createHorizontalStrut(10));
		vacationDateEPanel.add(DateEDayPanel);
		DateEDayPanel.add(dateEDayCombobox);
		DateEDayPanel.add(linePanel6);
		vacationDateEPanel.add(Box.createHorizontalStrut(10));
		vacationDateEPanel.add(dateEDayLabel);
		rightDownerPanel.add(vacationSubtitlePanel);
		vacationSubtitlePanel.add(vacationReasonTitleLabel);
		rightDownerPanel.add(vacationReasonPanel);
		vacationReasonPanel.add(vacationReasonTextfield);
		vacationReasonPanel.add(vacationReasonLine);
		rightDownerPanel.add(vacationLeftPanel);
		vacationLeftPanel.add(lblNewLabel_4, BorderLayout.WEST);
		buttonPanel.add(cancelButton);
		buttonPanel.add(confirmButton);
		vacationLeftPanel.add(buttonPanel, BorderLayout.EAST);
		rightPanel.add(rightUpperPanel);
		rightPanel.add(rightDownerPanel);

		leftPanel.add(calendarPanel);

		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);

		cancelButton.addActionListener(this);
		confirmButton.addActionListener(this);
		
		if (getTitle().equals("휴가 신청 - null")) {
			dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String Syear = (String) dateSYearCombobox.getSelectedItem();
		String Smonth = (String) dateSMonthCombobox.getSelectedItem();
		String Sday = (String) dateSDayCombobox.getSelectedItem();
		String Eyear = (String) dateEYearCombobox.getSelectedItem();
		String Emonth = (String) dateEMonthCombobox.getSelectedItem();
		String Eday = (String) dateEDayCombobox.getSelectedItem();

		Object obj = e.getSource();
		if (obj == cancelButton) {
			dispose();
		} else if (obj == confirmButton) {
			if (!Syear.equals("선택") && !Smonth.equals("선택") && !Sday.equals("선택") && !Eyear.equals("선택")
					&& !Emonth.equals("선택") && !Eday.equals("선택")) {
				// 날짜 문자열
				String strDate = Syear + "" + Smonth + "" + Sday;
				String endDate = Eyear + "" + Emonth + "" + Eday;

				VacationBean bean = new VacationBean();
				bean.setId(id);
				bean.setStart(strDate);
				bean.setEnd(endDate);
				bean.setReason(vacationReasonTextfield.getText());
				db.saveVacationEmployee(bean);
				JOptionPane.showMessageDialog(null, "휴가 신청이 완료되었습니다.", "휴가 신청", JOptionPane.PLAIN_MESSAGE);
				dispose();
			}
		}

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Vacation();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}