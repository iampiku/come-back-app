package com.comeback.app.users.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.comeback.app.users.dto.CreateUserDTO;
import com.comeback.app.users.dto.ResponseUserDTO;
import com.comeback.app.users.dto.UpdateUserDTO;
import com.comeback.app.users.entity.User;
import com.comeback.app.users.exception.UserAlreadyExistsException;
import com.comeback.app.users.exception.UserNotFoundException;
import com.comeback.app.users.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	private boolean isEmailUnique(String email) {
		return !this.userRepository.existsByEmail(email);
	}

	private String normalizedEmail(String email) {
		return email.trim().toLowerCase();
	}

	private ResponseUserDTO toResponseDTO(User user) {
		return new ResponseUserDTO(user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getCreatedAt(),
				user.getUpdatedAt());
	}

	@Transactional
	public ResponseUserDTO createUser(CreateUserDTO user) {
		var normalizedEmail = this.normalizedEmail(user.email());

		if (!this.isEmailUnique(normalizedEmail))
			throw new UserAlreadyExistsException("Email already exists");

		var userEntity = new User();

		userEntity.setEmail(normalizedEmail);
		userEntity.setLastName(user.lastName());
		userEntity.setFirstName(user.firstName());
		userEntity.setHashedPassword(this.passwordEncoder.encode(user.password()));

		var userResponse = this.userRepository.save(userEntity);

		return this.toResponseDTO(userResponse);
	}

	@Transactional
	public ResponseUserDTO updateUser(UpdateUserDTO user) {
		var userEntity = this.userRepository.findById(user.id()).orElseThrow(
				() -> new UserNotFoundException(String.format("User with id %d not found", user.id())));

		if (user.firstName() != null)
			userEntity.setFirstName(user.firstName());

		if (user.lastName() != null)
			userEntity.setLastName(user.lastName());

		var normalizedEmail = this.normalizedEmail(user.email());
		if (normalizedEmail != null && this.isEmailUnique(normalizedEmail))
			userEntity.setEmail(normalizedEmail);

		return this.toResponseDTO(userEntity);
	}

	public ResponseUserDTO getCurrentUser(String email) {
		var userEntity = this.userRepository.findByEmail(this.normalizedEmail(email)).orElseThrow(
				() -> new UserNotFoundException(String.format("User with email %s not found", email)));

		return this.toResponseDTO(userEntity);
	}

	@Transactional
	public void deleteUser(Long id) {
		if (this.userRepository.existsById(id))
			this.userRepository.deleteById(id);
		else
			throw new UserNotFoundException(String.format("User with id %d not found", id));
	}

}
