package emerick.igor.javatodolist.modules.task;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emerick.igor.javatodolist.modules.task.database.entities.TaskEntity;
import emerick.igor.javatodolist.modules.task.services.TaskService;
import emerick.igor.javatodolist.shared.errors.HttpError;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/task")
public class TaskController {
  private TaskService taskService;

  public TaskController(@Autowired ApplicationContext context) {
    this.taskService = context.getBean(TaskService.class);
  }

  @PostMapping("/")
  public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity taskModel, HttpServletRequest request)
      throws HttpError {
    UUID userId = (UUID) request.getAttribute("userId");

    TaskEntity task = this.taskService.create(userId, taskModel.getDescription(), taskModel.getTitle(),
        taskModel.getStartTime(),
        taskModel.getFinishTime(), taskModel.getPriority());

    return ResponseEntity.status(201).body(task);
  }

  @GetMapping("/")
  public ResponseEntity<List<TaskEntity>> getUserTasks(HttpServletRequest request) {
    UUID userId = (UUID) request.getAttribute("userId");

    List<TaskEntity> tasks = this.taskService.getUserTasks(userId);

    return ResponseEntity.ok(tasks);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskEntity> updateTask(@RequestBody TaskEntity taskModel, HttpServletRequest request,
      @PathVariable UUID id) throws HttpError {
    UUID userId = (UUID) request.getAttribute("userId");

    TaskEntity task = this.taskService.updateTask(userId, taskModel);

    return ResponseEntity.ok(task);
  }
}
