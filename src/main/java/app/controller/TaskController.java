package app.controller;

import app.model.Material;
import app.model.Task;
import app.model.User;
import app.services.MaterialServiceI;
import app.services.TaskServiceI;
import app.services.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * The REST Controller for handling requests for {@link app.model.Task Tasks}.
 */
@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    /**
     * This variable inject the {@code Logger} into the {@code Controller}.
     */
    Logger logger = LoggerFactory.getLogger(TaskController.class);

    /**
     * This variable autowires the {@link app.services.TaskService TaskService} into the {@code Controller}.
     */
    @Autowired
    private TaskServiceI taskService;

    /**
     * This variable autowires the {@link app.services.MaterialService MaterialService} into the {@code Controller}.
     */
    @Autowired
    private MaterialServiceI materialService;

    /**
     * This variable autowires the {@link app.services.UserService} into the {@code Controller}.
     */
    @Autowired
    private UserServiceI userService;

    /**
     * Finds the {@link app.model.Task Task} with the given {@code id}.
     * @param id The id of the requested {@link app.model.Task Task}
     * @return The {@link app.model.Task Task} with the given {@code id}
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Task> findById(@PathVariable("id") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());
        logger.info("[GET] /tasks/"+id+" UserID: " + currentUser.getId());
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * Creates a new {@link app.model.Task Task}.
     * @param postedTask The {@link app.model.Task Task} what should be saved in the database
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the created {@link app.model.Task Task}
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Task> createTask(@RequestBody Task postedTask) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());
        logger.info("[POST] /tasks/ UserID: " + currentUser.getId());
        Task task = taskService.addTask(postedTask);
        return ResponseEntity.created(URI.create("/tasks/" + task.getId())).body(task);
    }

    /**
     * Updates the {@link app.model.Task Task} with the given id.
     * @param id the id of the {@link app.model.Task Task}
     * @param patchedTask The {@link app.model.Task Task} what has updated properties
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the updated {@link app.model.Task Task}
     */
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") int id, @RequestBody Task patchedTask) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());
        logger.info("[PATCH] /tasks/"+id+" UserID: " + currentUser.getId());
        Task task = taskService.updateTask(id, patchedTask);
        return ResponseEntity.ok(task);
    }

    /**
     * Deletes the {@link app.model.Task Task} with the given id.
     * @param id The id of the {@link app.model.Task Task}
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the deleted {@link app.model.Task Task}
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());
        logger.info("[DELETE] /tasks/"+id+" UserID: " + currentUser.getId());
        Task project = taskService.deleteTask(id);
        return ResponseEntity.ok(project);
    }

    /**
     * Creates a new {@link app.model.Material Material}.
     * @param id The id of the {@link app.model.Task Task}
     * @param postedMaterial The posted {@link app.model.Material Material}
     * @return The created {@link app.model.Material}
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/material")
    public ResponseEntity<Material> addMaterial(@PathVariable("id") int id, @RequestBody Material postedMaterial) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());
        logger.info("[POST] /tasks/"+id+"/material UserID: " + currentUser.getId());
        postedMaterial.setTask(taskService.findById(id));
        Material material = materialService.addMaterial(postedMaterial);
        return ResponseEntity.ok(material);
    }


}
