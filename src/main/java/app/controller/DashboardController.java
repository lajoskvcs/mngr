package app.controller;

import app.dto.Dashboard;
import app.model.User;
import app.services.ProjectServiceI;
import app.services.TaskServiceI;
import app.services.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The REST Controller what handles statistical requests.
 */
@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController {

    /**
     * This variable inject the {@code Logger} into the {@code Controller}.
     */
    Logger logger = LoggerFactory.getLogger(DashboardController.class);

    /**
     * This variable autowires the {@link app.services.TaskService} into the {@code Controller}.
     */
    @Autowired
    private TaskServiceI taskService;

    /**
     * This variable autowires the {@link app.services.ProjectService} into the {@code Controller}.
     */
    @Autowired
    private ProjectServiceI projectService;

    /**
     * This variable autowires the {@link app.services.UserService} into the {@code Controller}.
     */
    @Autowired
    private UserServiceI userService;

    /**
     * Returns statistical data about {@link app.model.Project Projects} and {@link app.model.Task Tasks}.
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with a {@link app.dto.Dashboard DashboardDTO}
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Dashboard> getDashboard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());

        logger.info("[GET] /dashboard/ UserID: " + currentUser.getId());

        Dashboard dashboard = new Dashboard();
        dashboard.setProjectNumber(projectService.countAll(currentUser.getId()));


        dashboard.setInProgressTaskNumber(taskService.findInProgressTasks(currentUser.getId()));
        dashboard.setInPlanTaskNumber(taskService.findInPlanTasks(currentUser.getId()));
        dashboard.setDoneTaskNumber(taskService.findDoneTasks(currentUser.getId()));
        return ResponseEntity.ok(dashboard);
    }
}
