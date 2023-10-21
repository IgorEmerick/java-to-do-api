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
import emerick.igor.javatodolist.modules.task.database.repositories.models.ITaskRepository;
import emerick.igor.javatodolist.modules.task.services.TaskService;
import emerick.igor.javatodolist.shared.errors.HttpError;
import emerick.igor.javatodolist.shared.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/task")
public class TaskController {
  @Autowired
  ApplicationContext context;

  @Autowired
  private ITaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity taskModel, HttpServletRequest request)
      throws HttpError {
    TaskService service = context.getBean(TaskService.class);

    UUID userId = (UUID) request.getAttribute("userId");

    TaskEntity task = service.create(userId, taskModel.getDescription(), taskModel.getTitle(), taskModel.getStartTime(),
        taskModel.getFinishTime(), taskModel.getPriority());

    return ResponseEntity.status(201).body(task);
  }

  @GetMapping("/")
  public ResponseEntity<List<TaskEntity>> getUserTasks(HttpServletRequest request) {
    UUID userId = (UUID) request.getAttribute("userId");

    TaskService service = this.context.getBean(TaskService.class);

    List<TaskEntity> tasks = service.getUserTasks(userId);

    return ResponseEntity.ok(tasks);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskEntity> updateTask(@RequestBody TaskEntity taskModel, HttpServletRequest request,
      @PathVariable UUID id) throws HttpError {
    TaskEntity task = this.taskRepository.findById(id).get();

    if (task == null) {
      throw new HttpError(404, "Task not found!");
    }

    UUID userId = (UUID) request.getAttribute("userId");

    if (!task.getUserId().equals(userId)) {
      throw new HttpError(403, "Unauthorized to update this task!");
    }

    Utils.copyNonNullProperties(taskModel, task);

    taskModel.setId(id);

    TaskEntity updatedTask = this.taskRepository.save(taskModel);

    return ResponseEntity.ok(updatedTask);
  }
}
