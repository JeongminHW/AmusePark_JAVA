package cmp.DB;

public class chatContentsBean {

	String chat_date;
	String chat_contents;
	String user_id;
	int chat_no;
	
	public String getChat_contents() {
		return chat_contents;
	}
	
	public String getChat_date() {
		return chat_date;
	}
	
	public int getChat_no() {
		return chat_no;
	}
	public String getUser_id() {
		return user_id;
	}
	
	public void setChat_contents(String chat_contents) {
		this.chat_contents = chat_contents;
	}
	
	public void setChat_date(String chat_date) {
		this.chat_date = chat_date;
	}
	
	public void setChat_no(int chat_no) {
		this.chat_no = chat_no;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}