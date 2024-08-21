package DB;

public class ScheduleBean {
	private int schedule_num;
	private String writer_id;
	private String scheduel_contents;
	private String schedule_start;
	private String schedule_end;
	
	public int getSchedule_num() {
		return schedule_num;
	}
	public void setSchedule_num(int schedule_num) {
		this.schedule_num = schedule_num;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public String getScheduel_contents() {
		return scheduel_contents;
	}
	public void setScheduel_contents(String scheduel_contents) {
		this.scheduel_contents = scheduel_contents;
	}
	public String getSchedule_start() {
		return schedule_start;
	}
	public void setSchedule_start(String schedule_start) {
		this.schedule_start = schedule_start;
	}
	public String getSchedule_end() {
		return schedule_end;
	}
	public void setSchedule_end(String schedule_end) {
		this.schedule_end = schedule_end;
	}
}
