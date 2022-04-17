package br.com.yvestaba.myecommerce.outbound.jpa;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import br.com.yvestaba.myecommerce.domain.Authority;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Profile implements GrantedAuthority{

	private static final long serialVersionUID = 6888061793933618743L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Setter
	@Enumerated(EnumType.STRING)
	private Authority auth;
	
	@Override
	public String getAuthority() {
		return this.auth.toString();
	}
}
