package lv.webkursi.mtest.domain;

import java.util.HashSet;
import java.util.Set;

import lv.webkursi.mtest.dao.CommonDao;

public class Person {
	protected Long id; 
	
	protected String login;
	
	protected String firstName;
	
	protected String lastName;
	
	protected String email;
	
	protected String password;

	protected HashSet<Session> modules = new HashSet<Session>();
	
	protected Set<Session> sessions = new HashSet<Session>();
	
	protected Set<Assessment> assessments = new HashSet<Assessment>();
	
	protected CommonDao dao;
	
	
		
	public CommonDao getDao() {
		return dao;
	}

	public void setDao(CommonDao dao) {
		this.dao = dao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HashSet<Session> getModules() {
		return modules;
	}

	public void setModules(HashSet<Session> modules) {
		this.modules = modules;
	}

	public void removeSession(Session s) {
		sessions.remove(s);
		// TODO
//		dao.delete(s);
	}
	
	public void removeAssessment(Assessment a) {
		assessments.remove(a);
		// TODO
//		dao.delete(a);
	}
	
	public void removeModule(Module m) {		
		modules.remove(m);
		// TODO
//		dao.delete(m);
	}
	
	public Session createSession(Assessment a) {
		Session session = new Session();
		session.init(a);
		sessions.add(session);
		return session;
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
