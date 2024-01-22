package mk.ukim.finki.wp.june2022.g1.web;

import mk.ukim.finki.wp.june2022.g1.model.VirtualServer;
import mk.ukim.finki.wp.june2022.g1.model.OSType;
import mk.ukim.finki.wp.june2022.g1.service.VirtualServerService;
import mk.ukim.finki.wp.june2022.g1.service.UserService;

import java.time.LocalDate;
import java.util.List;

public class VirtualServerController {

    private final VirtualServerService service;
    private final UserService userService;

    public VirtualServerController(VirtualServerService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    /**
     * This method should use the "list.html" template to display all entities.
     * The method should be mapped on paths '/' and '/VirtualServers'.
     * The arguments that this method takes are optional and can be 'null'.
     *
     * @return The view "list.html".
     */
    public String showList(Integer activeMoreThanDays, Long ownerId) {
        List<VirtualServer> virtualServers;
        if (ownerId == null && activeMoreThanDays == null) {
            virtualServers = this.service.listAll();
        } else {
            virtualServers = this.service.filter(ownerId, activeMoreThanDays);
        }
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/VirtualServers/add'.
     *
     * @return The view "form.html".
     */
    public String showAdd() {
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the entity that is updated.
     * The method should be mapped on path '/VirtualServers/[id]/edit'.
     *
     * @return The view "form.html".
     */
    public String showEdit(Long id) {
        return "";
    }

    /**
     * This method should create an entity given the arguments it takes.
     * The method should be mapped on path '/VirtualServers'.
     * After the entity is created, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    public String create(String instanceName,
                         String ipAddress,
                         OSType osType,
                         List<Long> owners,
                         LocalDate launchDate) {
        this.service.create(instanceName, ipAddress, osType, owners, launchDate);
        return "";
    }

    /**
     * This method should update an entity given the arguments it takes.
     * The method should be mapped on path '/VirtualServers/[id]'.
     * After the entity is updated, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    public String update(Long id,
                         String instanceName,
                         String ipAddress,
                         OSType osType,
                         List<Long> owners) {
        this.service.update(id, instanceName, ipAddress, osType, owners);
        return "";
    }

    /**
     * This method should delete the entities that have the appropriate identifier.
     * The method should be mapped on path '/VirtualServers/[id]/delete'.
     * After the entity is deleted, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    public String delete(Long id) {
        this.service.delete(id);
        return "";
    }

    /**
     * This method should mark the virtual server that has the appropriate identifier as terminated.
     * The method should be mapped on path '/VirtualServers/[id]/terminate'.
     * After its execution, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    public String terminate(Long id) {
        this.service.markTerminated(id);
        return "";
    }
}
