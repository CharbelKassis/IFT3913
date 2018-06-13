package uml.struct;

import uml.exception.UML_IllegalSyntaxException;

public final class UML_Association extends AbstractUML {

	private UML_Role[] roles;
	private int roleIndex;

	public UML_Association(String identifier) {

		super(identifier);
		roles = new UML_Role[2];
	}

	public void addRole(UML_Role role) {

		if(this.roleIndex > 1)
			
			throw new UML_IllegalSyntaxException("Role <= 2");
		
		this.roles[this.roleIndex++] = role;
	}

	public UML_Role[] getRoles() {

		return this.roles;
	}
	

}
