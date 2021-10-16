package com.itis.servletsapp.dao;

import com.itis.servletsapp.dao.base.CrudRepository;
import com.itis.servletsapp.model.User;

import java.util.Optional;
//Not realized
public interface UsersRepository extends CrudRepository<User, Long> {
//    Optional<User> findByEmail(String email);
}
