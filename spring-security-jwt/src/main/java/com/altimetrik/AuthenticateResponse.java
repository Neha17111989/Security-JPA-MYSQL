package com.altimetrik;

//To JWT authenicate 
public class AuthenticateResponse {

	public final String jwt;

	public String getJwt() {
		return jwt;
	}

	AuthenticateResponse(String jwt) {
		this.jwt = jwt;
	}

}
