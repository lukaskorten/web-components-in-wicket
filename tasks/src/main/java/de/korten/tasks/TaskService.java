package de.korten.tasks;

import de.korten.tasks.persistence.Task;
import de.korten.tasks.persistence.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void save(TaskEntry taskEntry) {

        if (taskEntry.getText() == null) {
            return;
        }

        LOG.info("Neues Todo {}", taskEntry.getText());

        Task task = new Task();
        task.setText(taskEntry.getText());
        task.setCreated(LocalDateTime.now());

        taskRepository.save(task);
    }

    public List<TaskEntry> findAll() {
        LOG.info("Alle Tasks laden ...");
        return taskRepository.findAll(new Sort(Sort.Direction.DESC, "created")).stream().map(this::mapToTaskEntry).collect(Collectors.toList());
    }

    private TaskEntry mapToTaskEntry(Task entity) {
        TaskEntry taskEntry = new TaskEntry();
        taskEntry.setId(entity.getId());
        taskEntry.setText(entity.getText());
        taskEntry.setCreated(entity.getCreated());
        taskEntry.setCompleted(entity.getCompleted());

        return taskEntry;
    }

    @PostConstruct
    public void postConstruct() {
        LOG.info("TODOS SERVICE CONSTRUCTED!");
    }


    public void delete(TaskEntry taskEntry) {
        taskRepository.deleteById(taskEntry.getId());
    }

    public void complete(TaskEntry completedTask) {
        taskRepository.findById(completedTask.getId()).ifPresent(task -> {
            task.setCompleted(LocalDateTime.now());
            taskRepository.save(task);
        });
    }
}
