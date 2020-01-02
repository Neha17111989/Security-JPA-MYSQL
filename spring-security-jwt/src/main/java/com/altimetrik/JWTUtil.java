package com.altimetrik;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JWTUtil {

	private final String SECRET_KRY = "SECRET";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);

	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KRY).parseClaimsJwt(token).getBody();
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	

	}

	private String createToken(Map<String, Object> claims, String subject) {
		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KRY).compact();
	}

	public Boolean validToken(String token, UserDetails details) {
		final String userName = extractUsername(token);
		return (userName.equals(details.getUsername()) && !isTokenExpired(token));

	}

	private boolean isTokenExpired(String token) {
		return ExtractExpiration(token).before(new Date());
	}

	private Date ExtractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

}
