package com.devkmc.Bank_Security_Service.repository;

import com.devkmc.Bank_Security_Service.entity.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SecurityUserRepository extends JpaRepository<SecurityUser, Long> {
    Optional<SecurityUser> findByUsername(String username);
}
