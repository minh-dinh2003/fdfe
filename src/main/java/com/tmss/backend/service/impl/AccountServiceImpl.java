package com.tmss.backend.service.impl;

import com.tmss.backend.dto.AccountDto;
import com.tmss.backend.entity.Account;
import com.tmss.backend.entity.Role;
import com.tmss.backend.exception.ResourceNotFoundException;
import com.tmss.backend.mapper.AccountMapper;
import com.tmss.backend.repositories.AccountRepository;
import com.tmss.backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccount(String username) {
        var account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto updateAccount(AccountDto updatedAccount) {
        Account account = accountRepository.findByUsername(updatedAccount.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        account.setFullName(updatedAccount.getFullName());
        account.setEmail(updatedAccount.getEmail());
        account.setPhone(updatedAccount.getPhone());
        account.setAddress(updatedAccount.getAddress());
        account.setBankAccount(updatedAccount.getBankAccount());
        account.setRole(updatedAccount.getRole());
        account.setActive(updatedAccount.getActive());

        if (updatedAccount.getPassword() != null && !updatedAccount.getPassword().isEmpty()) {
            account.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
        }

        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public void deleteAccount(String username) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        accountRepository.delete(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> createAccounts(List<AccountDto> accountDtos) {
        List<AccountDto> createdAccounts = new ArrayList<>();
        for (AccountDto accountDto : accountDtos) {
            AccountDto createdAccount = createAccount(accountDto);
            createdAccounts.add(createdAccount);
        }
        return createdAccounts;
    }


    @Override
    public List<AccountDto> getAccountByRoles(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("Roles list must not be null or empty");
        }

        List<Account> accounts = accountRepository.findByRoleIn(roles);
        return accounts.stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }
}
