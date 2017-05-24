package app.controller;

import app.model.Project;
import app.model.Task;
import app.services.TaskService;
import app.services.TaskServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private TaskServiceI taskService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Task>> findAll() {
        logger.info("[GET] /projects UserID: " + 1);
        Collection<Task> tasks = taskService.findAll(1);
        return ResponseEntity.ok(tasks);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Task> findById(@PathVariable("id") int id) {
        logger.info("[GET] /projects/"+id+" UserID: " + 1);
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Task> createTask(@RequestBody Task postedTask) {
        logger.info("[POST] /tasks UserID: " + 1);
        logger.debug(postedTask.toString());
        Task task = taskService.addTask(postedTask);
        return ResponseEntity.created(URI.create("/tasks/" + task.getId())).body(task);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") int id, @RequestBody Task patchedTask) {
        logger.info("[PATCH] /tasks/"+id+" UserID: " + 1);
        Task task = taskService.updateTask(id, patchedTask);
        return ResponseEntity.ok(task);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") int id) {
        logger.info("[DELETE] /projects/"+id+" UserID: " + 1);
        Task project = taskService.deleteTask(id);
        return ResponseEntity.ok(project);
    }

}
