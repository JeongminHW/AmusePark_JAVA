package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;

import DB.*;

public class reviewForm implements ActionListener {

	InquireBean bean = new InquireBean();
	DBMgr db = new DBMgr();

	Frame frame;
	Panel p1, p2;
	Button cancleButton, submitButton;
	TextField textTitle, textId;
	TextArea areaInquireContents, areaReviewContents;
	QACheck ir;

	public reviewForm(InquireBean bean, QACheck ir) {

		this.ir = ir;
		this.bean = bean;
		frame = new JFrame("문의사항 검토");
		frame.setSize(600, 400);
		frame.setVisible(true);

		p1 = new Panel(new GridLayout(4, 1));
		p2 = new Panel(new GridLayout(1, 2));

		textId = new TextField(db.myName(bean.getInquire_id()));
		textId.setEditable(false);
		textTitle = new TextField(bean.getInquire_title());
		textTitle.setEditable(false);
		areaInquireContents = new TextArea(bean.getInquire_contents());
		areaInquireContents.setEditable(false);
		areaReviewContents = new TextArea();
		cancleButton = new Button("취소");
		submitButton = new Button("작성");

		cancleButton.addActionListener(this);
		submitButton.addActionListener(this);

		p1.add(textId);
		p1.add(textTitle);
		p1.add(areaInquireContents);
		p1.add(areaReviewContents);
		p2.add(cancleButton);
		p2.add(submitButton);

		frame.add(p1);
		frame.add(p2, BorderLayout.SOUTH);

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		if (obj == cancleButton) {

			frame.dispose();
			;

		} else if (obj == submitButton) {

			bean.setReply_contents(areaReviewContents.getText());

			if (db.reviewInquire(bean)) {
				System.out.println("성공");
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							QACheck frame = new QACheck(ir.id);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frame.dispose();
				;
			}
		}
	}
}
