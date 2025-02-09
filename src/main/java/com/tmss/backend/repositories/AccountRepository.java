package com.tmss.backend.repositories;

import com.tmss.backend.entity.Account;
import com.tmss.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
    List<Account> findByRoleIn(List<Role> roles);
}
