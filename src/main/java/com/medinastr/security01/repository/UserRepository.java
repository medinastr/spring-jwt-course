package com.medinastr.security01.repository;

import com.medinastr.security01.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  @Query(
      """
        SELECT u FROM User u WHERE u.email = :email
        """)
  Optional<User> findByEmail(String email);
}
