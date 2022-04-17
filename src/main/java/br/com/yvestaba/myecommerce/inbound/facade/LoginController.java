package br.com.yvestaba.myecommerce.inbound.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yvestaba.myecommerce.business.dto.LoginForm;
import br.com.yvestaba.myecommerce.config.securityservices.TokenService;

@RestController
@RequestMapping("/login")
public class LoginController {

	private AuthenticationManager authenticationManager;
	private TokenService tokenService;
	
	public LoginController(AuthenticationManager authenticationManager, TokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}
	
	@PostMapping
	public ResponseEntity<String> login(@RequestBody LoginForm form){
		var auth = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());
		
		try {
			var authentication = authenticationManager.authenticate(auth);
			return ResponseEntity.ok(tokenService.generateToken(authentication));
		} catch(AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
