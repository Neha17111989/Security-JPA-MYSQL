package com.altimetrik;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
	

	private String userName;
	private String password;
	private int active;
	private List<GrantedAuthority> authorities;
	
	MyUserDetails(){}

	public MyUserDetails(User users) {
		super();
		this.userName = users.getUserName();
		this.password = users.getPassword();
		this.active = users.getActive();
		this.authorities = Arrays.stream(users.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

	/*
	 * private String name;
	 * 
	 * MyUserDetails(String name) { this.name = name; }
	 * 
	 * public MyUserDetails(){
	 * 
	 * }
	 * 
	 * @Override public Collection<? extends GrantedAuthority> getAuthorities() { //
	 * TODO Auto-generated method stub return Arrays.asList(new
	 * SimpleGrantedAuthority("ROLE_USER")); }
	 * 
	 * @Override public String getPassword() { // TODO Auto-generated method stub
	 * return "pass"; }
	 * 
	 * @Override public String getUsername() { // TODO Auto-generated method stub
	 * return name; }
	 * 
	 * @Override public boolean isAccountNonExpired() { // TODO Auto-generated
	 * method stub return true; }
	 * 
	 * @Override public boolean isAccountNonLocked() { // TODO Auto-generated method
	 * stub return true; }
	 * 
	 * @Override public boolean isCredentialsNonExpired() { // TODO Auto-generated
	 * method stub return true; }
	 * 
	 * @Override public boolean isEnabled() { // TODO Auto-generated method stub
	 * return true; }
	 */

}
