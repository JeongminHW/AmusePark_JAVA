package cmp.DB;

public class TodoBean {
	private int num;
	private String writer_id;
	private String todo_contents;

	public int getNum() {
		return num;
	}

	public String getTodo_contents() {
		return todo_contents;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setTodo_contents(String todo_contents) {
		this.todo_contents = todo_contents;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
}
