package mk.ukim.finki.wp.jan2022.g1.service.impl;

import mk.ukim.finki.wp.jan2022.g1.model.Task;
import mk.ukim.finki.wp.jan2022.g1.model.TaskCategory;
import mk.ukim.finki.wp.jan2022.g1.model.User;
import mk.ukim.finki.wp.jan2022.g1.model.exceptions.InvalidTaskIdException;
import mk.ukim.finki.wp.jan2022.g1.model.exceptions.InvalidUserIdException;
import mk.ukim.finki.wp.jan2022.g1.repository.TaskRepository;
import mk.ukim.finki.wp.jan2022.g1.repository.UserRepository;
import mk.ukim.finki.wp.jan2022.g1.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService
{

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> listAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(InvalidTaskIdException::new);
    }

    @Override
    public Task create(String title, String description, TaskCategory category, List<Long> assignees, LocalDate dueDate) {
        List<User> users = userRepository.findAllById(assignees);
        Task task = new Task(title,description,category,users,dueDate);

        taskRepository.save(task);

        return task;
    }

    @Override
    public Task update(Long id, String title, String description, TaskCategory category, List<Long> assignees) {
        List<User> users = userRepository.findAllById(assignees);
        Task task = taskRepository.findById(id).orElseThrow(InvalidTaskIdException::new);

        task.setTitle(title);
        task.setDescription(description);
        task.setCategory(category);
        task.setAssignees(users);

        taskRepository.save(task);

        return task;
    }

    @Override
    public Task delete(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(InvalidTaskIdException::new);

        taskRepository.deleteById(id);

        return task;
    }

    @Override
    public Task markDone(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(InvalidTaskIdException::new);

        task.setDone(true);

        taskRepository.save(task);

        return task;
    }

    @Override
    public List<Task> filter(Long assigneeId, Integer lessThanDayBeforeDueDate) {
        if(assigneeId != null && lessThanDayBeforeDueDate != null){
            User user = userRepository.findById(assigneeId).orElseThrow(InvalidUserIdException::new);
            LocalDate date = LocalDate.now().plusDays(lessThanDayBeforeDueDate);
            return taskRepository.findAllByAssigneesContainsAndDueDateBefore(user,date);
        }
        else if(assigneeId != null){
            User user = userRepository.findById(assigneeId).orElseThrow(InvalidUserIdException::new);
            return taskRepository.findAllByAssigneesContains(user);
        }
        else if(lessThanDayBeforeDueDate != null){
            LocalDate date = LocalDate.now().plusDays(lessThanDayBeforeDueDate);
            return taskRepository.findAllByDueDateIsBefore(date);
        }
        else{
            return taskRepository.findAll();
        }
    }
}
