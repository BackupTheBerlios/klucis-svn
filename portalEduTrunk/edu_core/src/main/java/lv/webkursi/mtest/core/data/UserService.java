package lv.webkursi.mtest.core.data;

import java.util.ArrayList;

import lv.webkursi.mtest.core.vocabulary.MTEST;

import com.hp.hpl.jena.rdf.model.EmptyListException;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class UserService {
	private Model model;
	

	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * TODO: userService is not using the injected model here
	 * A convenience method returning a user Java object by its name
	 * 
	 * @param dataModel
	 * @param userName
	 * @return
	 */
	public User getUserByName(Model model, String userName) {
		return getObject(getUserResourceByName(model, userName));
	}

	/**
	 * TODO: userService is not using the injected model here
	 */
	public Resource getUserResourceByName(Model model, String userName) {
		if (userName == null) {
			throw new NullPointerException("Username cannot be null");
		}
		return RdfUtilities.getResourceByProperty(model, MTEST.User, MTEST.userName, userName);
	}

	/**
	 * Return User object described by the RDF resource, assuming it exists
	 * and has all the required properties 
	 * 
	 * @param resource 
	 * @return the User object
	 * TODO: user roles not initialized
	 */
	public User getObject(Resource resource) {
		String userName = resource.getRequiredProperty(MTEST.userName).getString();
		String password = resource.getRequiredProperty(MTEST.password).getString();
		User result = new User(userName, password, new ArrayList<Role>());
		return result;
	}

	public void addUser(UserForm userForm) {
		try {
			getUserByName( model, userForm.getUserName());
			throw new IllegalArgumentException("Such a user exists!");
		}
		catch (EmptyListException e) { 
			// this is expected behavior - should not have any users;
		}
		Resource r = model.createResource(MTEST.NS + userForm.getUserName());
		model.add(r, RDF.type, MTEST.User);
		model.add(r, MTEST.userName, userForm.getUserName());
		model.add(r, MTEST.name, userForm.getName());
		model.add(r, MTEST.email, userForm.getEmail());
		model.add(r, MTEST.password, userForm.getPassword());		

	}
}
