package com.aks.security.rback.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RBAC_ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleid;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Collection<UserBO> users;

    @OneToMany( fetch = FetchType.EAGER )
    @JoinTable(name = "RBAC_ROLES_PRIVILEGES", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleid"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "privilegeid"))
    private Collection<Privilege> privileges;

    private String name;

    public Role() {
        super();
    }

    public Role(final String name) {
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
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role role = (Role) obj;
        if (!role.equals(role.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Role [name=").append(name).append("]").append("[roleid=").append(roleid).append("]");
        return builder.toString();
    }

    //Setter & Getters...

    public int getId() {
        return roleid;
    }

    public void setId(final int id) {
        this.roleid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Collection<UserBO> getUsers() {
        return users;
    }

    public void setUsers(final Collection<UserBO> users) {
        this.users = users;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(final Collection<Privilege> privileges) {
        this.privileges = privileges;
    }
}
