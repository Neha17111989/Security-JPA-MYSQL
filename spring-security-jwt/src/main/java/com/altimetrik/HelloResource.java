package com.altimetrik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

	//To JWT authenicate 
	@Autowired
	AuthenticationManager authenticationManager;
	
	//To JWT authenicate 
	@Autowired
	MyUserDetailService userDetailsService;

	////To JWT authenicate 
	@Autowired
	JWTUtil jwt;
	
	@GetMapping
	@RequestMapping("/user")
	public String user() {
		return "<h1> Welcome User</h1>";
	}

	@GetMapping
	@RequestMapping("/hello")
	public String hello() {
		return "<h1> hello world</h1>";
	}

	@GetMapping
	@RequestMapping("/admin")
	public String admin() {
		return "<h1> Welcome Admin</h1>";
	}

	////To JWT authenicate 
	@GetMapping
	@RequestMapping(value = "/aunthicate", method = RequestMethod.POST)
	public ResponseEntity<?> createAutnenticationToken(@RequestBody AutheticateRequest request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("user name not found");
		}
		final UserDetails userDetails=userDetailsService.loadUserByUsername(request.getUserName());
		final String jutil=jwt.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticateResponse(jutil));
	}
}
