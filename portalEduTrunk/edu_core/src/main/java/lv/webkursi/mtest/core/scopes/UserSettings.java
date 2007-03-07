package lv.webkursi.mtest.core.scopes;

import java.util.ArrayList;
import java.util.List;

import lv.webkursi.mtest.core.data.User;
import lv.webkursi.mtest.core.util.FacetConfigurationEntry;

import com.hp.hpl.jena.rdf.model.Resource;

/**
 * <p>This session-scoped bean stores the logged-in user (or null, if 
 * user is anonymous), and some user/session preferences, which are user-specific
 * (rather than page-set specific), and do not fit anywhere else. 
 * For example, the user's facets for the given pageset should be
 * accessed through the pageset's CompositeFacetFactory object. 
 * But user's default language settings could be stored in this 
 * object (and NOT in the User object itself).</p>
 * 
 * <p>There are two different kinds of user settings - persistent
 * ones (i.e. user-scoped), and non-persistent (i.e. session-scoped).  
 * So far both are fine, but one should remember to load/store the 
 * persistent settings, when the user logins/logouts.</p>
 *   
 *
 */
public class UserSettings {
	private User user;
	
	private Resource userResource;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	// TODO: This is not good, since addLists should be accessed via 
	// compositeFacet configurations
	public List<FacetConfigurationEntry> getUserAddList() {
		if (user == null) {
			return new ArrayList<FacetConfigurationEntry>();
		}
		else {
			return user.getUserAddList();
		}
	}

	public Resource getUserResource() {
		return userResource;
	}

	public void setUserResource(Resource userResource) {
		this.userResource = userResource;
	}
}
