package com.comeback.app.goals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comeback.app.goals.entity.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {
  void deleteAllByUserId(Long userId);
}
