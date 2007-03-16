package lv.webkursi.mtest.lab02.data;

public class UserForm {
	private String userName;
	private String name;
	private String email;
	private String password;
	private String password2;
	public UserForm() {
		userName = "";
		name = "";
		email = "";
		password = "";
		password2 = "";
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
