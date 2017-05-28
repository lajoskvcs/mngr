package app.controller;

import app.model.Task;
import app.services.TaskServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * The REST Controller for handling requests for {@link app.model.Task Tasks}
 */
@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private TaskServiceI taskService;

    /**
     * Finds the {@link app.model.Task Task} with the given {@code id}
     * @param id The id of the requested {@link app.model.Task Task}
     * @return The {@link app.model.Task Task} with the given {@code id}
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Task> findById(@PathVariable("id") int id) {
        logger.info("[GET] /tasks/"+id+" UserID: " + 1);
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * Creates a new {@link app.model.Task Task}
     * @param postedTask The {@link app.model.Task Task} what should be saved in the database
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the created {@link app.model.Task Task}
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Task> createTask(@RequestBody Task postedTask) {
        logger.info("[POST] /tasks UserID: " + 1);
        logger.debug(postedTask.toString());
        Task task = taskService.addTask(postedTask);
        return ResponseEntity.created(URI.create("/tasks/" + task.getId())).body(task);
    }

    /**
     * Updates the {@link app.model.Task Task} with the given id
     * @param id the id of the {@link app.model.Task Task}
     * @param patchedTask The {@link app.model.Task Task} what has updated properties
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the updated {@link app.model.Task Task}
     */
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") int id, @RequestBody Task patchedTask) {
        logger.info("[PATCH] /tasks/"+id+" UserID: " + 1);
        Task task = taskService.updateTask(id, patchedTask);
        return ResponseEntity.ok(task);
    }

    /**
     * Deletes the {@link app.model.Task Task} with the given id
     * @param id The id of the {@link app.model.Task Task}
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the deleted {@link app.model.Task Task}
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") int id) {
        logger.info("[DELETE] /tasks/"+id+" UserID: " + 1);
        Task project = taskService.deleteTask(id);
        return ResponseEntity.ok(project);
    }

}
