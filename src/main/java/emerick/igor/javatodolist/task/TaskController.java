package emerick.igor.javatodolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emerick.igor.javatodolist.errors.HttpError;
import emerick.igor.javatodolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/task")
public class TaskController {
  @Autowired
  private ITaskRepository taskRepository;
  
  @PostMapping("/")
  public ResponseEntity<TaskModel> createTask(@RequestBody TaskModel taskModel, HttpServletRequest request) throws HttpError {
    LocalDateTime currentTime = LocalDateTime.now();

    LocalDateTime startTime = taskModel.getStartTime();

    if (startTime != null && currentTime.isAfter(startTime)) {
      throw new HttpError(400, "Start time must be greater than current time!");
    }

    LocalDateTime finishTime = taskModel.getFinishTime();

    if (finishTime != null) {
      if (startTime == null) {
        throw new HttpError(400, "Is not possible set a finish time without a start time!");
      }
      
      if (finishTime.isBefore(startTime)) {
        throw new HttpError(400, "Finish time must be grater than start time!");
      }
    }

    UUID userId = (UUID) request.getAttribute("userId");

    taskModel.setUserId(userId);
    
    TaskModel task = this.taskRepository.save(taskModel);

    return ResponseEntity.status(201).body(task);
  }

  @GetMapping("/")
  public ResponseEntity<List<TaskModel>> getTasks(HttpServletRequest request) {
    UUID userId = (UUID) request.getAttribute("userId");
    
    List<TaskModel> tasks = this.taskRepository.findByUserId(userId);

    return ResponseEntity.ok(tasks);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskModel> updateTask(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) throws HttpError {
    TaskModel task = this.taskRepository.findById(id).get();

    if (task == null) {
      throw new HttpError(404, "Task not found!");
    }

    UUID userId = (UUID) request.getAttribute("userId");

    if (!task.getUserId().equals(userId)) {
      throw new HttpError(403, "Unauthorized to update this task!");
    }

    Utils.copyNonNullProperties(taskModel, task);

    taskModel.setId(id);

    TaskModel updatedTask = this.taskRepository.save(taskModel);

    return ResponseEntity.ok(updatedTask);
  }
}
