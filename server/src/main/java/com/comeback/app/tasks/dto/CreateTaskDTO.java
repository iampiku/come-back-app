package com.comeback.app.tasks.dto;

import com.comeback.app.tasks.enums.RecurringOrder;
import com.comeback.app.tasks.enums.TaskPriority;
import com.comeback.app.tasks.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Instant;

public record CreateTaskDTO(
  @NotBlank(message = "Title is required")
  @Size(
    min = 1,
    max = 100,
    message = "Title must be between 1 and 100 characters"
  )
  String title,

  @NotBlank(message = "Description is required")
  @Size(
    min = 1,
    max = 1000,
    message = "Description must be between 1 and 255 characters"
  )
  String description,

  Instant dueDate,

  @NotBlank(message = "Priority is required") TaskPriority priority,

  @NotBlank(message = "Task status is required") TaskStatus status,

  RecurringOrder recurringOrder,

  boolean isRecurring,

  Long goalId,

  @NotBlank(message = "User id is required") Long userId
) {}
