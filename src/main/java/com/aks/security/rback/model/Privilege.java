package com.aks.security.rback.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table ( name = "RBAC_PRIVILEGE" )
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int privilegeid;

    private String name;

    @ManyToOne( fetch = FetchType.EAGER)
    private Role role;

    public Privilege() {
        super();
    }

    public Privilege(final String name) {
        super();
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Privilege other = (Privilege) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

   

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Privilege [name=").append(name).append("]").append("[privilegeid=").append(privilegeid).append("]");
        return builder.toString();
    }
    
    //Setters & Getters...

    public int getId() {
        return privilegeid;
    }

    public void setId(final int id) {
        this.privilegeid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

   /* public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Collection<Role> roles) {
        this.roles = roles;
    }*/

    public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
