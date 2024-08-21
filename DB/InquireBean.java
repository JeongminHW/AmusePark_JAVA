package DB;

public class InquireBean {
	private int inquire_num;
	private String inquire_id;
	private String inquire_title;
	private String inquire_contents;
	private String reply_contents;
	
	public String getInquire_contents() {
		return inquire_contents;
	}
	
	public String getInquire_id() {
		return inquire_id;
	}
	
	public int getInquire_num() {
		return inquire_num;
	}
	
	public String getReply_contents() {
		return reply_contents;
	}
	
	public String getInquire_title() {
		return inquire_title;
	}
	
	public void setInquire_contents(String inquire_contents) {
		this.inquire_contents = inquire_contents;
	}
	
	public void setInquire_id(String inquire_id) {
		this.inquire_id = inquire_id;
	}
	
	public void setInquire_num(int inquire_num) {
		this.inquire_num = inquire_num;
	}
	
	public void setReply_contents(String reply_contents) {
		this.reply_contents = reply_contents;
	}
	
	public void setInquire_title(String inquire_title) {
		this.inquire_title = inquire_title;
	}

}
