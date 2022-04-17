package br.com.yvestaba.myecommerce.config.securityservices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.yvestaba.myecommerce.outbound.datasource.repositories.UserRepository;
import br.com.yvestaba.myecommerce.outbound.jpa.UserJpa;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserJpa> user = userRepository.findById(username);
		
		if(user.isPresent()){
			return user.get();
		}	
		throw new UsernameNotFoundException("This e-mail is not signed");
	}

	
}
