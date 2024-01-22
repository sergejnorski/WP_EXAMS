package mk.ukim.finki.wp.kol2022.g1.service.impl;

import mk.ukim.finki.wp.kol2022.g1.model.Employee;
import mk.ukim.finki.wp.kol2022.g1.model.EmployeeType;
import mk.ukim.finki.wp.kol2022.g1.model.Skill;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidEmployeeIdException;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidSkillIdException;
import mk.ukim.finki.wp.kol2022.g1.repository.EmployeeRepository;
import mk.ukim.finki.wp.kol2022.g1.repository.SkillRepository;
import mk.ukim.finki.wp.kol2022.g1.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, SkillRepository skillRepository) {
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Employee> listAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
    }

    @Override
    public Employee create(String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        List<Skill> skills = skillRepository.findAllById(skillId);
        Employee employee = new Employee(name,email,password,type,skills,employmentDate);

        employeeRepository.save(employee);

        return employee;
    }

    @Override
    public Employee update(Long id, String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        List<Skill> skills = skillRepository.findAllById(skillId);
        Employee employee = employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);

        employee.setName(name);
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setType(type);
        employee.setSkills(skills);
        employee.setEmploymentDate(employmentDate);

        employeeRepository.save(employee);

        return employee;
    }

    @Override
    public Employee delete(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
        employeeRepository.deleteById(id);
        return employee;
    }

    @Override
    public List<Employee> filter(Long skillId, Integer yearsOfService) {

        if(skillId == null && yearsOfService == null){
            return employeeRepository.findAll();
        }
        else if(skillId != null && yearsOfService != null){
            Skill skill = skillRepository.findById(skillId).orElseThrow(InvalidSkillIdException::new);
            LocalDate date = LocalDate.now().minusYears(yearsOfService);
            return employeeRepository.findAllBySkillsContainingAndEmploymentDateBefore(skill,date);
        }
        else if(skillId != null){
            Skill skill = skillRepository.findById(skillId).orElseThrow(InvalidSkillIdException::new);
            return employeeRepository.findAllBySkillsContaining(skill);
        }
        else{
            LocalDate date = LocalDate.now().minusYears(yearsOfService);
            return employeeRepository.findAllByEmploymentDateBefore(date);
        }

    }
}
