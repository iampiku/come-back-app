package com.comeback.app.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(
		@NotBlank(message = "Id is required") Long id,
		@Size(min = 3) String firstName,
		@Size(min = 3) String lastName,
		@Email(message = "Email should be valid") String email) {
}
