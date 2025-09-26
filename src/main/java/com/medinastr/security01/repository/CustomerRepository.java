package com.medinastr.security01.repository;

import com.medinastr.security01.model.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

  @Query("""
        SELECT c FROM Customer c WHERE c.email = :email
        """)
  Optional<Customer> findByEmail(String email);
}
