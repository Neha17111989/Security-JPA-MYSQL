package com.altimetrik;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {

	public Optional<User> findByUserName(String username);
}
