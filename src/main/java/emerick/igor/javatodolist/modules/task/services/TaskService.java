package emerick.igor.javatodolist.modules.task.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emerick.igor.javatodolist.modules.task.database.entities.TaskEntity;
import emerick.igor.javatodolist.modules.task.database.repositories.models.ITaskRepository;
import emerick.igor.javatodolist.shared.errors.HttpError;

@Service
public class TaskService {
  @Autowired
  private ITaskRepository taskRepository;

  public TaskEntity create(UUID userId, String descritption, String title, LocalDateTime startTime,
      LocalDateTime finishTime, String priority) throws HttpError {
    LocalDateTime dateTime = LocalDateTime.now();

    if (startTime != null && startTime.isBefore(dateTime))
      throw new HttpError(400, "Start time must be a future date!");

    if (finishTime != null && (startTime == null || finishTime.isBefore(startTime)))
      throw new HttpError(400, "Finish time needs a lower start time to exists!");

    TaskEntity task = new TaskEntity(userId, descritption, title);

    task.setStartTime(startTime);
    task.setFinishTime(finishTime);
    task.setPriority(priority);

    return this.taskRepository.save(task);
  }

  public List<TaskEntity> getUserTasks(UUID userId) {
    return this.taskRepository.findByUserId(userId);
  }
}
