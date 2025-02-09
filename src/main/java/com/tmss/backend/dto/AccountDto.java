package com.tmss.backend.dto;

import com.tmss.backend.entity.Role;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String bankAccount;
    private Role role;
    private Boolean active;
}
