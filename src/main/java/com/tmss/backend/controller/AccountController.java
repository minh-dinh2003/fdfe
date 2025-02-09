package com.tmss.backend.controller;

import com.tmss.backend.auth.AuthenticationService;
import com.tmss.backend.dto.AccountDto;
import com.tmss.backend.entity.Role;
import com.tmss.backend.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173/")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto result = accountService.createAccount(accountDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountDto> getAccountByUsername(@PathVariable("username") String username) {
        System.out.println("Fetching account for username: " + username); // Debug log
        AccountDto accountDto = accountService.getAccount(username);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto accountDto) {
        AccountDto result = accountService.updateAccount(accountDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteAccount(@PathVariable(name="username") String username) {
        accountService.deleteAccount(username);
        return ResponseEntity.ok("Account deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDto = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<AccountDto>> getAccountsByRoles(@RequestParam List<String> roles) {
        List<Role> roleList = roles.stream()
                .map(String::toUpperCase)
                .map(Role::valueOf)
                .collect(Collectors.toList());

        List<AccountDto> accounts = accountService.getAccountByRoles(roleList);
        return ResponseEntity.ok(accounts);
    }


}
