package com.comeback.app.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comeback.app.users.dto.CreateUserDTO;
import com.comeback.app.users.dto.ResponseUserDTO;
import com.comeback.app.users.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<ResponseUserDTO> registerUser(@RequestBody CreateUserDTO userDTO) {
    var savedUser = this.userService.createUser(userDTO);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  @GetMapping("/me")
  public ResponseEntity<ResponseUserDTO> getCurrentUser() {
    var currentUser = this.userService.getCurrentUser("");
    return ResponseEntity.ok(currentUser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    this.userService.deleteUser(id);
    return ResponseEntity.ok("User deleted successfully");
  }
}
