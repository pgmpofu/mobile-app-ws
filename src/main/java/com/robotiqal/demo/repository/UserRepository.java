package com.robotiqal.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.robotiqal.demo.io.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findUserByEmail(String email);
}
