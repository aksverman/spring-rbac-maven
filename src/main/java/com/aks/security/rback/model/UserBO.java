package com.aks.security.rback.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RBAC_USER")
public class UserBO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	
	@Column(name = "USERNAME", length = 30)
	private String username;
	
	@Column(name = "EMAILID", length = 50)
	private String emailid;
	
	@Column(name = "PASSWORD", nullable = false, length = 30)
	private String password;

    /*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "RBAC_USERS_ROLE", 
    		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userid"), 
    		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleid"))
    private Collection<Role> roles;*/
	
	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "RBAC_USERS_ROLE", 
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userid"), 
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleid"))
	private	Role	role;
	
	
	
	@Override
	public String toString() {
		return "UserBO [userid=" + userid + ", username=" + username + ", emailid=" + emailid + ", password=" + password
				+ ", role=" + role + "]";
	}	
	

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) +/* ((emailid == null) ? 0 : emailid.hashCode()) +*/ username.hashCode();
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
        final UserBO user = (UserBO) obj;
        if (!username.equals(user.username) ) {
            return false;
        }
        return true;
    }
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

			
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

	
	/*public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserBO [USERID=" + userid + ", username=" + username + ", emailid=" + emailid + ", password=" + password
				+ ", roles=" + roles + "]";
	}*/
}
