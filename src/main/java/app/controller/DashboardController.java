package app.controller;

import app.dto.Dashboard;
import app.model.User;
import app.services.ProjectServiceI;
import app.services.TaskServiceI;
import app.services.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The REST Controller what handles statistical requests
 */
@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController {

    @Autowired
    private TaskServiceI taskService;

    @Autowired
    private ProjectServiceI projectService;
    @Autowired
    private UserServiceI userService;

    /**
     * Returns statistical datas about {@link app.model.Project Projects} and {@link app.model.Task Tasks}
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with a {@link app.dto.Dashboard DashboardDTO}
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Dashboard> getDashboard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());

        Dashboard dashboard = new Dashboard();
        dashboard.setProjectNumber(projectService.countAll(currentUser.getId()));


        dashboard.setInProgressTaskNumber(taskService.findInProgressTasks(currentUser.getId()));
        dashboard.setInPlanTaskNumber(taskService.findInPlanTasks(currentUser.getId()));
        dashboard.setDoneTaskNumber(taskService.findDoneTasks(currentUser.getId()));
        return ResponseEntity.ok(dashboard);
    }
}
