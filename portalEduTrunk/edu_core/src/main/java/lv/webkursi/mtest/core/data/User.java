package lv.webkursi.mtest.core.data;

import java.util.List;
import java.util.Map;

import lv.webkursi.mtest.core.data.Role;
import lv.webkursi.mtest.core.util.FacetConfigurationEntry;


public class User {
	private String userName;

	private String password;

	private List<Role> roles;
	
	private Map<String,Object> userSelectFacetStates;
	
	private List<FacetConfigurationEntry> userAddList;

	public User() {
	}

	public User(String userName, String password, List<Role> roles) {
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User{userName:" + userName + "}";
	}

	public Map<String, Object> getUserSelectFacetStates() {
		return userSelectFacetStates;
	}

	public void setUserSelectFacetStates(Map<String, Object> userSelectFacetStates) {
		this.userSelectFacetStates = userSelectFacetStates;
	}

	public List<FacetConfigurationEntry> getUserAddList() {
		return userAddList;
	}

	public void setUserAddList(List<FacetConfigurationEntry> userAddList) {
		this.userAddList = userAddList;
	}

}
