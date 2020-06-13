package com.aks.security.rback.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static com.aks.security.rback.constants.Constants.TOKEN_EXPIRE_TIME;;

@Entity
@Table( name = "RBAC_PASSWORD_TOKEN")
public class PasswordResetToken {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String token;
	
	@Temporal(TemporalType.DATE)
	Date	expiryDate;

	@OneToOne(fetch = FetchType.EAGER, targetEntity = UserBO.class)
	@JoinColumn(name = "userid")
	private UserBO	user;

	
    public PasswordResetToken() {
		super();
	}

	public PasswordResetToken(String token, UserBO user) {
		super();
		this.token = token;
		this.user = user;
		this.expiryDate = calculateExpiryDate();
	}

	/*
	 * Prepate Date to have expire time as 2 days
	 */
	private Date calculateExpiryDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE, TOKEN_EXPIRE_TIME);
		return new Date(cal.getTime().getTime());
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        final PasswordResetToken other = (PasswordResetToken) obj;
        if (expiryDate == null) {
            if (other.expiryDate != null) {
                return false;
            }
        } else if (!expiryDate.equals(other.expiryDate)) {
            return false;
        }
        if (token == null) {
            if (other.token != null) {
                return false;
            }
        } else if (!token.equals(other.token)) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Token [String=").append(token).append("]").append("[Expires").append(expiryDate).append("]");
        return builder.toString();
    }
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public UserBO getUser() {
		return user;
	}

	public void setUser(UserBO user) {
		this.user = user;
	}
	
	
	
}
