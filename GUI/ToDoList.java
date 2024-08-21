package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;

import DB.DBMgr;
import DB.TodoBean;
import GUI.EmployeeMain;

public class ToDoList implements ActionListener {
	static String em_id = "";
	static String alba_id = "";

	public static void setEm_id(String em_id) {
		ToDoList.em_id = em_id;
	}

	public static void setAlba_id(String alba_id) {
		ToDoList.alba_id = alba_id;
	}

	JFrame frame;
	JPanel mainPanel;
	CentralDropShadowPanel topPanel;
	JPanel toDoPanel;
	ImageIcon icon;
	JTextField newTodo;
	CentralDropShadowArea addtaskField;
	JButton plusButton;
	JButton[] upButton = new JButton[5];
	JButton[] downButton = new JButton[5];
	JButton[] deleteButton = new JButton[5];
	JTextField showTodoField;
	JButton closeButton;
	DBMgr db = new DBMgr();
	Vector<TodoBean> vlist;

	public ToDoList() {
		if (!em_id.equals("")) { // 직원
			frame = new JFrame("To-Do List - " + em_id);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(600, 400);

			mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

			mainForm();
			refreshList(); // 투두 리스트를 데이터베이스에서 불러와서 표시
		} else if (!alba_id.equals("")) { // 알바
			frame = new JFrame("To-Do List - " + em_id);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(600, 400);

			mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

			mainForm();
			refreshList(); // 투두 리스트를 데이터베이스에서 불러와서 표시
		}

	}

	// 투두 추가
	private void addTask() {
		if (!em_id.equals("")) {
			vlist = db.selectTodo(em_id);
			TodoBean bean = new TodoBean();
			bean.setWriter_id(em_id);
			bean.setTodo_contents(newTodo.getText());
			if (db.insertTodo(bean)) {
				System.out.println("성공");
			}
			newTodo.setText("");
			newTodo.requestFocus();
			refreshList();
		} else if (!alba_id.equals("")) {
			vlist = db.selectTodo(alba_id);
			TodoBean bean = new TodoBean();
			bean.setWriter_id(alba_id);
			bean.setTodo_contents(newTodo.getText());
			if (db.insertTodo(bean)) {
				System.out.println("성공");
			}
			newTodo.setText("");
			newTodo.requestFocus();
			refreshList();
		}

	}

	// 투두 삭제
	private void deleteTask(int index) {
		TodoBean bean = vlist.get(index);
		if (db.deleteTodo(bean.getNum())) {
			System.out.println("삭제 성공");
			refreshList();
		}
	}

	// 투두 위치 변경
	private void swapTasks(int index1, int index2) {
		TodoBean bean1 = vlist.get(index1);
		TodoBean bean2 = vlist.get(index2);
		TodoBean temp = vlist.get(index1);

		db.changeTodo(bean1, bean2);
		db.changeTodo(bean2, temp);
		refreshList();
	}

	// 투두 새로고침
	public void refreshList() {
		mainPanel.removeAll();
		mainForm();
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	public void mainForm() {
		if (!em_id.equals("")) {
			vlist = db.selectTodo(em_id); // 데이터베이스에서 투두 리스트 불러오기

			topPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
			newTodo = new JTextField();
			plusButton = new RoundedButton("추가", 25);

			topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
			topPanel.setBackground(Color.WHITE);
			topPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
			newTodo.setBorder(null);
			newTodo.setBackground(Color.WHITE);
			topPanel.setPreferredSize(new Dimension(50, 50));
			topPanel.setMaximumSize(new Dimension(590, 40));
			newTodo.setMaximumSize(new Dimension(500, 30));
			plusButton.setMaximumSize(new Dimension(40, 40));
			plusButton.setBackground(Color.BLACK);
			mainPanel.setBackground(Color.WHITE);

			topPanel.add(newTodo);
			topPanel.add(Box.createHorizontalStrut(10));
			topPanel.add(plusButton);
			mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			mainPanel.add(topPanel);

			for (int i = 0; i < 5; i++) {
				toDoPanel = new JPanel();
				toDoPanel.setLayout(new BoxLayout(toDoPanel, BoxLayout.X_AXIS));
				toDoPanel.setBackground(Color.white);
				upButton[i] = new RoundedButton("▲", 15);
				downButton[i] = new RoundedButton("▼", 15);
				deleteButton[i] = new RoundedButton("삭제", 20);

				upButton[i].setBackground(Color.GRAY);
				downButton[i].setBackground(Color.GRAY);
				deleteButton[i].setBackground(Color.BLACK);

				upButton[i].setForeground(Color.WHITE);
				downButton[i].setForeground(Color.WHITE);

				upButton[i].setPreferredSize(new Dimension(30, 30));
				downButton[i].setPreferredSize(new Dimension(30, 30));
				addtaskField = new CentralDropShadowArea(6, Color.LIGHT_GRAY);
				addtaskField.setFocusable(false);

				if (vlist.size() <= i) {
					addtaskField.setText("");
				} else {
					addtaskField.setText(vlist.get(i).getTodo_contents());
				}

				toDoPanel.add(upButton[i]);
				toDoPanel.add(Box.createHorizontalStrut(5));
				toDoPanel.add(downButton[i]);
				toDoPanel.add(addtaskField);
				toDoPanel.add(deleteButton[i]);
				mainPanel.add(toDoPanel);

				deleteButton[i].addActionListener(this);
				upButton[i].addActionListener(this);
				downButton[i].addActionListener(this);
			}

			closeButton = new RoundedButton("닫기", 20);
			closeButton.setPreferredSize(new Dimension(50, 30));
			closeButton.setMaximumSize(new Dimension(50, 30));
			closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			mainPanel.add(Box.createVerticalStrut(10));
			mainPanel.add(closeButton);

			frame.add(mainPanel);
			frame.setVisible(true);
			frame.setResizable(false);

			closeButton.addActionListener(this);
			plusButton.addActionListener(this);
		} else if (!alba_id.equals("")) {
			vlist = db.selectTodo(alba_id); // 데이터베이스에서 투두 리스트 불러오기

			topPanel = new CentralDropShadowPanel(6, Color.LIGHT_GRAY);
			newTodo = new JTextField();
			plusButton = new RoundedButton("추가", 25);

			topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
			topPanel.setBackground(Color.WHITE);
			topPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
			newTodo.setBorder(null);
			newTodo.setBackground(Color.WHITE);
			topPanel.setPreferredSize(new Dimension(50, 50));
			topPanel.setMaximumSize(new Dimension(590, 40));
			newTodo.setMaximumSize(new Dimension(500, 30));
			plusButton.setMaximumSize(new Dimension(40, 40));
			plusButton.setBackground(Color.BLACK);
			mainPanel.setBackground(Color.WHITE);

			topPanel.add(newTodo);
			topPanel.add(Box.createHorizontalStrut(10));
			topPanel.add(plusButton);
			mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			mainPanel.add(topPanel);

			for (int i = 0; i < 5; i++) {
				toDoPanel = new JPanel();
				toDoPanel.setLayout(new BoxLayout(toDoPanel, BoxLayout.X_AXIS));
				toDoPanel.setBackground(Color.white);
				upButton[i] = new RoundedButton("▲", 15);
				downButton[i] = new RoundedButton("▼", 15);
				deleteButton[i] = new RoundedButton("삭제", 20);

				upButton[i].setBackground(Color.GRAY);
				downButton[i].setBackground(Color.GRAY);
				deleteButton[i].setBackground(Color.BLACK);

				upButton[i].setForeground(Color.WHITE);
				downButton[i].setForeground(Color.WHITE);

				upButton[i].setPreferredSize(new Dimension(30, 30));
				downButton[i].setPreferredSize(new Dimension(30, 30));
				addtaskField = new CentralDropShadowArea(6, Color.LIGHT_GRAY);
				addtaskField.setFocusable(false);

				if (vlist.size() <= i) {
					addtaskField.setText("");
				} else {
					addtaskField.setText(vlist.get(i).getTodo_contents());
				}

				toDoPanel.add(upButton[i]);
				toDoPanel.add(Box.createHorizontalStrut(5));
				toDoPanel.add(downButton[i]);
				toDoPanel.add(addtaskField);
				toDoPanel.add(deleteButton[i]);
				mainPanel.add(toDoPanel);

				deleteButton[i].addActionListener(this);
				upButton[i].addActionListener(this);
				downButton[i].addActionListener(this);
			}

			closeButton = new RoundedButton("닫기", 20);
			closeButton.setPreferredSize(new Dimension(50, 30));
			closeButton.setMaximumSize(new Dimension(50, 30));
			closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			mainPanel.add(Box.createVerticalStrut(10));
			mainPanel.add(closeButton);

			frame.add(mainPanel);
			frame.setVisible(true);
			frame.setResizable(false);

			closeButton.addActionListener(this);
			plusButton.addActionListener(this);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == plusButton) {
			if (vlist.size() >= 4) {
				JOptionPane.showMessageDialog(null, "너무 많습니다.", "경고", JOptionPane.ERROR_MESSAGE);
			} else {
				addTask();
			}
		} else if (obj == closeButton) {
			frame.dispose();
			if (!em_id.equals("")) {
				EmployeeMain em = new EmployeeMain();
				em.setId(em_id);
			} else if (!alba_id.equals("")) {
				AlbaMain am = new AlbaMain(alba_id);
				am.setId(alba_id);
			}
		} else {
			for (int i = 0; i < deleteButton.length; i++) {
				if (obj == deleteButton[i]) {
					deleteTask(i);
				} else if (obj == upButton[i] && i > 0) {
					swapTasks(i, i - 1);
				} else if (obj == downButton[i] && i < deleteButton.length - 1) {
					swapTasks(i, i + 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		new ToDoList(); // 실제 ID로 대체 필요
	}
}
