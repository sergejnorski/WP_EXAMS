package mk.ukim.finki.wp.kol2022.g1.repository;

import mk.ukim.finki.wp.kol2022.g1.model.Employee;
import mk.ukim.finki.wp.kol2022.g1.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //da se vrati vraboteni so odreden skill
    List<Employee> findAllBySkillsContaining(Skill skill);

    //da se vrati vraboten sto e zaposlen pred X vreme
    List<Employee> findAllByEmploymentDateBefore(LocalDate date);

    //ako filtirrame spored dvete

    List<Employee> findAllBySkillsContainingAndEmploymentDateBefore(Skill skill,LocalDate date);

}
