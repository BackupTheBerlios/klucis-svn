package lv.webkursi.mtest.lab02.domain;

import java.util.HashSet;
import java.util.Set;

public class Person implements JsonSerializable {
	protected Long id; 
	
	protected String login;
	
	protected String firstName;
	
	protected String lastName;
	
	protected String email;
	
	protected String password;
	
	/**
	 * This field is not persisted in the database, but is used only 
	 * as a backup object in a form. 
	 */
	protected String password2;

	protected Set<Module> modules = new HashSet<Module>();
	
	protected Set<Session> sessions = new HashSet<Session>();
	
	protected Set<Assessment> assessments = new HashSet<Assessment>();
	
	/**
	 * Empty constructor (Notice, that person object does not become valid
	 * until firstName, lastName, login and password are initialized
	 */
	public Person() {		
	}
	
	/**
	 * Constructor, which sets all the mandatory attributes
	 * @param firstName
	 * @param lastName
	 * @param login
	 * @param email
	 */
	public Person(String firstName, String lastName, String login, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	public void removeSession(Session s) {
		sessions.remove(s);
	}
	
	public void removeAssessment(Assessment a) {
		assessments.remove(a);
	}
	
	public void removeModule(Module m) {		
		modules.remove(m);
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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Set<Session> getSessions() {
		return sessions;
	}

	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Person)) {
			return false;
		}
		else {
			boolean result = true;
			Person p = (Person)o;
			result &= p.getEmail().equals(email);
			result &= p.getFirstName().equals(firstName);
			result &= p.getLastName().equals(lastName);
			result &= p.getLogin().equals(login);
			return result;
		}
	}
	
	@Override
	public int hashCode() {
		return email.hashCode() ^ firstName.hashCode() ^ lastName.hashCode() ^ login.hashCode();
	}	
	
	@Override
	public String toString() {
		return "{\"Person\":{"
		+ Utils.jsonParamList(getParamList(), getValueList()) + "}}";

	}

	public String[] getParamList() {
		return new String[] { "id", "firstName", "lastName", "login", "email" };
	}

	public Object[] getValueList() {
		return new Object[] { id, firstName, lastName, login, email };
	}
}
