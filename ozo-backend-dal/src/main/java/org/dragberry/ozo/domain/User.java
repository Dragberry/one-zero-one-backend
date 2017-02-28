package org.dragberry.ozo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "USER")
@TableGenerator(
		name = "USER_GEN", 
		table = "GENERATOR",
		pkColumnName = "GEN_NAME", 
		pkColumnValue = "USER_GEN",
		valueColumnName = "GEN_VALUE",
		initialValue = 1000,
		allocationSize = 1)
@NamedQueries({
	@NamedQuery(
			name = User.FIND_BY_ID_QUERY,
			query = "SELECT u FROM User u WHERE u.userId = :userId")
})
public class User implements DomainEntity {

	private static final long serialVersionUID = 4746974079817918512L;
	
	public static final String FIND_BY_ID_QUERY = "User.FindById";
	
	@Id
	@Column(name = "USER_KEY")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_GEN")
	private Long entityKey;
	
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "EMAIL")
	private String email;

	@Override
	public Long getEntityKey() {
		return entityKey;
	}

	public void setEntityKey(Long entityKey) {
		this.entityKey = entityKey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
