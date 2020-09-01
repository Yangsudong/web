package member;

public class MemberVO {
	private String id;		
	private String pass;
	private String gender;
	private String job; 
	private String mailYN;
	private String reason;
	private String hobby;
	private String regdate;
	
	
	public MemberVO() {}
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pass=" + pass + ", gender=" + gender + ", job=" + job + ", mailYN=" + mailYN + ", hobby=" + regdate 
				+ ", reason=" + reason + "]";
	}
	public MemberVO(String id, String pass, String gender, String job, String mailYN, String reason, String hobby, String regdate) {
		super();	
		this.id = id;
		this.pass = pass;
		this.gender = gender;
		this.job = job;
		this.mailYN = mailYN;
		this.reason = reason;
		this.hobby = hobby;
		this.regdate = regdate;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getMailYN() {
		return mailYN;
	}
	public void setMailYN(String mailYN) {
		this.mailYN = mailYN;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
	
}
