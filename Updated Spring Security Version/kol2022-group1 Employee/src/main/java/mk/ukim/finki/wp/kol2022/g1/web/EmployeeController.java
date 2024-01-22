package mk.ukim.finki.wp.kol2022.g1.web;

import mk.ukim.finki.wp.kol2022.g1.model.Employee;
import mk.ukim.finki.wp.kol2022.g1.model.EmployeeType;
import mk.ukim.finki.wp.kol2022.g1.service.EmployeeService;
import mk.ukim.finki.wp.kol2022.g1.service.SkillService;

import java.time.LocalDate;
import java.util.List;

public class EmployeeController {

    private final EmployeeService service;
    private final SkillService skillService;

    public EmployeeController(EmployeeService service, SkillService skillService) {
        this.service = service;
        this.skillService = skillService;
    }

    /**
     * This method should use the "list.html" template to display all entities.
     * The method should be mapped on paths '/' and '/employees'.
     * The arguments that this method takes are optional and can be 'null'.
     *
     * @return The view "list.html".
     */
    public String showList(Long skillId, Integer yearsOfService) {
        List<Employee> employees;
        if (skillId == null && yearsOfService == null) {
            employees = this.service.listAll();
        } else {
            employees = this.service.filter(skillId, yearsOfService);
        }
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/employees/add'.
     *
     * @return The view "form.html".
     */
    public String showAdd() {
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the entity that is updated.
     * The method should be mapped on path '/employees/[id]/edit'.
     *
     * @return The view "form.html".
     */
    public String showEdit(Long id) {
        return "";
    }

    /**
     * This method should create an entity given the arguments it takes.
     * The method should be mapped on path '/employees'.
     * After the entity is created, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    public String create(String name,
                         String email,
                         String password,
                         EmployeeType type,
                         List<Long> skillId,
                         LocalDate employmentDate) {
        this.service.create(name, email, password, type, skillId, employmentDate);
        return "";
    }

    /**
     * This method should update an entity given the arguments it takes.
     * The method should be mapped on path '/employees/[id]'.
     * After the entity is updated, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    public String update(Long id,
                         String name,
                         String email,
                         String password,
                         EmployeeType type,
                         List<Long> skillId,
                         LocalDate employmentDate) {
        this.service.update(id, name, email, password, type, skillId, employmentDate);
        return "";
    }

    /**
     * This method should delete the entities that has the appropriate identifier.
     * The method should be mapped on path '/employees/[id]/delete'.
     * After the entity is deleted, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    public String delete(Long id) {
        this.service.delete(id);
        return "";
    }
}
