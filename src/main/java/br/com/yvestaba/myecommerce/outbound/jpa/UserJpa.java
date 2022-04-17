package br.com.yvestaba.myecommerce.outbound.jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="users")
public class UserJpa implements UserDetails {
	
	private static final long serialVersionUID = -185223845266066079L;
	
	@Id
	private String email;
	private String password;
	private String name;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name= "user_profile", joinColumns = {@JoinColumn(name= "user_email")}, 
		inverseJoinColumns = {@JoinColumn(name= "profile_id")})
	private List<Profile> profiles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.profiles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
