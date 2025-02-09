package com.tmss.backend.mapper;

import com.tmss.backend.dto.AccountDto;
import com.tmss.backend.entity.Account;

public class AccountMapper {

    // Chuyển từ Entity sang DTO
    public static AccountDto mapToAccountDto(Account account) {
        return AccountDto.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .fullName(account.getFullName())
                .email(account.getEmail())
                .phone(account.getPhone())
                .address(account.getAddress())
                .bankAccount(account.getBankAccount())
                .role(account.getRole())
                .active(account.getActive())
                .build();
    }

    // Chuyển từ DTO sang Entity
    public static Account mapToAccount(AccountDto accountDto) {
        return Account.builder()
                .username(accountDto.getUsername())
                .password(accountDto.getPassword())
                .fullName(accountDto.getFullName())
                .email(accountDto.getEmail())
                .phone(accountDto.getPhone())
                .address(accountDto.getAddress())
                .bankAccount(accountDto.getBankAccount())
                .role(accountDto.getRole())
                .active(accountDto.getActive())
                .build();
    }
}
