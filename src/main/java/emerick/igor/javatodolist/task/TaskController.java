package emerick.igor.javatodolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/task")
public class TaskController {
  @Autowired
  private ITaskRepository taskRepository;
  
  @PostMapping("/")
  public ResponseEntity createTask(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    LocalDateTime currentTime = LocalDateTime.now();

    LocalDateTime startTime = taskModel.getStartTime();

    if (startTime != null && currentTime.isAfter(startTime)) {
      return ResponseEntity.status(400).body("Start time should be greater than current time!");
    }

    LocalDateTime finishTime = taskModel.getFinishTime();

    if (finishTime != null) {
      if (startTime == null) {
        return ResponseEntity.status(400).body("Finish time needs a start time to exists!");
      }
      
      if (finishTime.isBefore(startTime)) {
        return ResponseEntity.status(400).body("A finish time should be greater than start time!");
      }
    }

    UUID userId = (UUID) request.getAttribute("userId");

    taskModel.setUserId(userId);
    
    TaskModel task = this.taskRepository.save(taskModel);

    return ResponseEntity.status(201).body(task);
  }
}
