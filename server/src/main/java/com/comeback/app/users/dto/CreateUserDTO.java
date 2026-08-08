package com.comeback.app.users.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateUserDTO(
		@Size(min = 3, max = 50) 
		@NotBlank(message = "First name is required") 
		String firstName,
		
		@Size(min = 3, max = 50) 
		@NotBlank(message = "Last name is required") 
		String lastName,
		
		@Email(message = "Email should be valid") 
		@NotBlank(message = "Email is required") 
		String email,

		@Pattern(
			regexp = "^(?=.*[\\d])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
			message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
		)
		@NotBlank(message = "Password is required") 
		String password
) {
}