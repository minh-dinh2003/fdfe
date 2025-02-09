package com.tmss.backend.service;
import com.tmss.backend.dto.AccountDto;
import com.tmss.backend.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccount(String username);
    AccountDto updateAccount(AccountDto updatedAccount);
    void deleteAccount(String username);
    List<AccountDto> getAllAccounts();
    List<AccountDto> createAccounts(List<AccountDto> accountDtos);
    List<AccountDto> getAccountByRoles(List<Role> roles);
}
