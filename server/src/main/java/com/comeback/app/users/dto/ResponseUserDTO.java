package com.comeback.app.users.dto;

import java.time.Instant;

public record ResponseUserDTO(
		Long id,
		String firstName,
		String lastName,
		String email,
		Instant createdAt,
		Instant updatedAt) {
}