package lv.webkursi.mtest.domain;

import java.util.Set;

public class Person {
	protected String login;
	
	protected String firstName;
	
	protected String lastName;
	
	protected String email;
	
	protected String password;

	protected Set<Module> modules;
	
	protected Set<Session> sessions;
	
	protected Set<Assessment> assessments;
	
	public void addSession(Session s) {
		sessions.add(s);
	}
	
	public void removeSession(Session s) {
		sessions.remove(s);
	}
	
	public void addAssessment(Assessment a) {
		assessments.add(a);
	}
	
	public void removeAssessment(Assessment a) {
		assessments.remove(a);
	}
	
	public void addModule(Module m) {
		modules.add(m);
	}
	
	public void removeModule(Module m) {
		modules.remove(m);
	}

	public Set<Assessment> getAssessments() {
		return assessments;
	}

	public void setAssessments(Set<Assessment> assessments) {
		this.assessments = assessments;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Session> getSessions() {
		return sessions;
	}

	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}
	
	
}
