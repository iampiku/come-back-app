package com.comeback.app.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comeback.app.tasks.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
  void deleteAllByUserId(Long userId);
}
