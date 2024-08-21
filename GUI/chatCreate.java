package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Component;

import DB.*;

public class chatCreate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JTextField textField;
	String id;
	private DefaultListModel<EmployeeBean> friendListModel;
	private DefaultListModel<EmployeeBean> emptyListModel;
	private Vector<EmployeeBean> emvlist;
	private chatManager cm = new chatManager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chatCreate frame = new chatCreate("aaa");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public chatCreate(String id) {
		this.id = id;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));

		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

		JPanel titleFieldPanel = new JPanel();
		titleFieldPanel.setOpaque(false);
		titlePanel.add(titleFieldPanel);
		titleFieldPanel.setLayout(new GridLayout(0, 1, 0, 0));

		textField = new JTextField();
		textField.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		textField.setOpaque(false);
		textField.setBorder(new EmptyBorder(0, 10, 0, 10));
		textField.setText("제목을 입력하세요");
		textField.setColumns(10);
		titleFieldPanel.add(textField);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		titlePanel.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel leftTitlePanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) leftTitlePanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		leftTitlePanel.setOpaque(false);
		panel.add(leftTitlePanel);

		JLabel lblFriendList = new JLabel("친구 리스트");
		lblFriendList.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		leftTitlePanel.add(lblFriendList);

		JPanel rightTitlePanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) rightTitlePanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		rightTitlePanel.setOpaque(false);
		panel.add(rightTitlePanel);

		JLabel lblSelectedFriends = new JLabel("선택된 친구");
		lblSelectedFriends.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		rightTitlePanel.add(lblSelectedFriends);

		JPanel contentPanel = new JPanel();
		contentPanel.setOpaque(false);
		mainPanel.add(contentPanel);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel leftContentPanel = new JPanel();
		leftContentPanel.setOpaque(false);
		contentPanel.add(leftContentPanel);
		leftContentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		friendListModel = new DefaultListModel<>();
		emvlist = cm.selectEmployee(id);

		for (EmployeeBean employee : emvlist) {
			friendListModel.addElement(employee);
		}

		JList<EmployeeBean> list = new JList<>(friendListModel);
		list.setBorder(null);
		list.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		list.setCellRenderer(new EmployeeListCellRenderer()); // Custom renderer for EmployeeBean
		leftContentPanel.add(list);

		JPanel rightContentPanel = new JPanel();
		rightContentPanel.setOpaque(false);
		contentPanel.add(rightContentPanel);
		rightContentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		emptyListModel = new DefaultListModel<>();
		JList<EmployeeBean> list_1 = new JList<>(emptyListModel);
		list_1.setBorder(null);
		list_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		list_1.setCellRenderer(new EmployeeListCellRenderer()); // Custom renderer for EmployeeBean
		rightContentPanel.add(list_1);

		// 친구를 더블 클릭하면 빈 리스트로 옮기는 이벤트 추가
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					EmployeeBean selectedEmployee = list.getSelectedValue();
					if (selectedEmployee != null) {
						emptyListModel.addElement(selectedEmployee); // 빈 리스트에 추가
						friendListModel.removeElement(selectedEmployee); // 친구 리스트에서 제거
					}
				}
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		JButton createButton = new RoundedButton("채팅생성", 15);
		createButton.setForeground(Color.WHITE);
		createButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		createButton.setBackground(new Color(51, 153, 255));

		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String chatRoomName = textField.getText();
				if (!chatRoomName.trim().isEmpty() && !emptyListModel.isEmpty()) {
					Vector<String> userIds = new Vector<>();
					for (int i = 0; i < emptyListModel.size(); i++) {
						userIds.add(emptyListModel.getElementAt(i).getId());
					}
					if (cm.createChat(id, chatRoomName, userIds)) {
						dispose(); // 창 닫기
					} else {
						JOptionPane.showMessageDialog(null, "채팅 생성 실패", "오류", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "제목을 입력하고 친구를 선택하세요", "경고", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		buttonPanel.add(createButton);
	}

	private class EmployeeListCellRenderer extends JPanel implements ListCellRenderer<EmployeeBean> {

		private static final long serialVersionUID = 1L;
		private JLabel nameLabel;

		public EmployeeListCellRenderer() {
			setLayout(new BorderLayout());
			nameLabel = new JLabel();
			nameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
			add(nameLabel, BorderLayout.CENTER);
			setOpaque(true);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends EmployeeBean> list, EmployeeBean value, int index,
				boolean isSelected, boolean cellHasFocus) {
			nameLabel.setText(value.getName() + " " + value.getPosition() + " (" + value.getDepartment() + ")");
			if (isSelected) {
				setBackground(Color.LIGHT_GRAY);
			} else {
				setBackground(Color.WHITE);
			}
			return this;
		}
	}
}
