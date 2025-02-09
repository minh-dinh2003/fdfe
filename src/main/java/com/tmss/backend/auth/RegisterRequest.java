package com.tmss.backend.auth;

import com.tmss.backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
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
