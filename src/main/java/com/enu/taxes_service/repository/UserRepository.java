package com.enu.taxes_service.repository;

import com.enu.taxes_service.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);

    User findByUsername(String username);
}
