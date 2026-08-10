package com.comeback.app.tasks.entity;

import java.time.Instant;

import com.comeback.app.goals.entity.Goal;
import com.comeback.app.tasks.enums.RecurringOrder;
import com.comeback.app.tasks.enums.TaskPriority;
import com.comeback.app.tasks.enums.TaskStatus;
import com.comeback.app.users.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity()
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "goal_id")
  private Goal goal;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private String title;

  @Column
  private String description;

  @Column
  private Instant dueDate;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TaskStatus status = TaskStatus.TODO;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TaskPriority priority = TaskPriority.MEDIUM;

  @Column
  private boolean isRecurring;

  @Column
  @Enumerated(EnumType.STRING)
  private RecurringOrder recurringOrder;

  @Column(nullable = false, updatable = false)
  private Instant createdAt;

  @Column(nullable = false)
  private Instant updatedAt;

  @PrePersist
  public void prePersist() {
    this.createdAt = Instant.now();
    this.updatedAt = Instant.now();
  }

  @PreUpdate
  public void preUpdate() {
    this.updatedAt = Instant.now();
  }

  public Long getId() {
    return id;
  }

  public Goal getGoal() {
    return goal;
  }

  public void setGoal(Goal goal) {
    this.goal = goal;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Instant getDueDate() {
    return dueDate;
  }

  public void setDueDate(Instant dueDate) {
    this.dueDate = dueDate;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  public TaskPriority getPriority() {
    return priority;
  }

  public void setPriority(TaskPriority priority) {
    this.priority = priority;
  }

  public boolean isRecurring() {
    return isRecurring;
  }

  public void setRecurring(boolean isRecurring) {
    this.isRecurring = isRecurring;
  }

  public RecurringOrder getRecurringOrder() {
    return recurringOrder;
  }

  public void setRecurringOrder(RecurringOrder recurringOrder) {
    this.recurringOrder = recurringOrder;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
