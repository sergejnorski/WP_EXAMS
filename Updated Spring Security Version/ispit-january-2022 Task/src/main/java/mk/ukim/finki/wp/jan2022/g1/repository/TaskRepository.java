package mk.ukim.finki.wp.jan2022.g1.repository;

import mk.ukim.finki.wp.jan2022.g1.model.Task;
import mk.ukim.finki.wp.jan2022.g1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findAllByAssigneesContains(User user);

    List<Task> findAllByDueDateIsBefore(LocalDate date);

    List<Task> findAllByAssigneesContainsAndDueDateBefore(User user, LocalDate date);

}
