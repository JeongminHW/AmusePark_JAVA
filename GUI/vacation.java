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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.Point;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.DefaultComboBoxModel;

public class vacation extends JFrame {
	static String id;
	

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		vacation.id = id;
	}

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;
	private JTextField vacationReasonTextfield = new JTextField();

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

	String[] Yparts = { "2020", "2021", "2022", "2023", "2024", "2025" };
	JComboBox<String> dateSYearCombobox = new JComboBox<String>(Yparts);

	String[] Mparts = { "01 ", "02", "03", "04", "05" };
	JComboBox<String> dateSMonthCombobox = new JComboBox<String>(Mparts);

	String[] Dparts = { "01 ", "02", "03", "04", "05" };
	JComboBox<String> dateSDayCombobox = new JComboBox<String>(Dparts);

	JComboBox<String> dateEYearCombobox = new JComboBox<String>(Yparts);
	JComboBox<String> dateEMonthCombobox = new JComboBox<String>(Mparts);
	JComboBox<String> dateEDayCombobox = new JComboBox<String>(Dparts);

	JButton confirmButton = new RoundedButton("확인", 25);

	/**
	 * Create the frame.
	 */
	public vacation() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
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

		// setOpaque
		rightUpperTitlePanel.setOpaque(false);
		leftDownerTitlePanel.setOpaque(false);
		vacationLeftPanel.setOpaque(false);

		// set Backgrounds
		leftPanel.setBackground(new Color(255, 255, 255));
		rightPanel.setBackground(new Color(255, 255, 255));
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
		confirmButton.setBackground(new Color(0, 148, 255));
		vacationReasonLine.setBackground(new Color(255, 255, 255));
		dateEMonthPanel.setBackground(new Color(255, 255, 255));
		DateEDayPanel.setBackground(new Color(255, 255, 255));
		dateEYearCombobox.setBackground(Color.WHITE);
		dateEMonthCombobox.setBackground(Color.WHITE);
		dateEDayCombobox.setBackground(Color.WHITE);

		// set Foreground
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
		vacationLeftPanel.add(confirmButton, BorderLayout.EAST);
		rightPanel.add(rightUpperPanel);
		rightPanel.add(rightDownerPanel);

		leftPanel.add(calendarPanel);

		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					vacation frame = new vacation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}