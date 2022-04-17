package br.com.yvestaba.myecommerce.config.securityservices;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.yvestaba.myecommerce.outbound.datasource.repositories.UserRepository;
import br.com.yvestaba.myecommerce.outbound.jpa.UserJpa;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Autowired
	private UserRepository userRepository;
	
	public String generateToken(Authentication authentication) {
		Date today = new Date();
		UserJpa user = (UserJpa) authentication.getPrincipal();
		
		return Jwts.builder()
				.setIssuer("Myecommerce")
				.setIssuedAt(today)
				.setExpiration(new Date(today.getTime() + 3600000l))
				.setSubject(user.getEmail())
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public UserJpa getUserByToken(String token) {
		String userId = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		return userRepository.findById(userId).get();
	}

}
