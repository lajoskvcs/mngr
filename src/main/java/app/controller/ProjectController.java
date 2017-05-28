package app.controller;

import app.model.Note;
import app.model.Project;
import app.model.Task;
import app.model.User;
import app.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

/**
 * The REST Controller for handling requests for {@link app.model.Project Projects}.
 */
@RestController
@RequestMapping(value = "/projects")
public class ProjectController {
    /**
     * This variable inject the {@code Logger} into the {@code Controller}.
     */
    Logger logger = LoggerFactory.getLogger(ProjectController.class);

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
     * This variable autowires the {@link app.services.NoteService} into the {@code Controller}.
     */
    @Autowired
    private NoteServiceI noteService;

    /**
     * This variable autowires the {@link app.services.TaskService} into the {@code Controller}.
     */
    @Autowired
    private TaskServiceI taskService;

    /**
     * Returns with all the {@link app.model.Project Projects} for the currently authenticated {@link app.model.User User}.
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the {@link app.model.Project Projects}
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Project>> findAll() {
        logger.info("[GET] /projects UserID: " + 1);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());
        Collection<Project> projects = projectService.findAll(currentUser.getId());
        return ResponseEntity.ok(projects);
    }

    /**
     * Finds the {@link app.model.Project Project} with the given {@code id}.
     * @param id The id of the requested {@link app.model.Project Project}
     * @return The {@link app.model.Project Project} with the given {@code id}
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Project> findById(@PathVariable("id") int id) {
        logger.info("[GET] /projects/"+id+" UserID: " + 1);
        Project project = projectService.findById(id);
        return ResponseEntity.ok(project);
    }

    /**
     * Returns with all the {@link app.model.Task Tasks} for the currently authenticated {@link app.model.User User}.
     * @param projectId The id of the selected {@link app.model.Project}
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the {@link app.model.Task Tasks}
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/tasks")
    public ResponseEntity<Collection<Task>> findAllTaskByProjectId(@PathVariable("id") int projectId) {
        logger.info("[GET] /tasks ");
        Collection<Task> tasks = taskService.findAll(projectId);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Creates a new {@link app.model.Project Project}.
     * @param postedProject The {@link app.model.Project Project} what should be saved in the database
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the created {@link app.model.Project Project}
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Project> createProject(@RequestBody Project postedProject) {
        logger.info("[POST] /projects UserID: " + 1);
        Project project = projectService.addProject(postedProject);
        Note note = new Note();
        note.setNote("");
        note.setProject(project);
        noteService.addNote(note);
        return ResponseEntity.created(URI.create("/projects/" + project.getId())).body(project);
    }

    /**
     * Updates the {@link app.model.Project Project} with the given id.
     * @param id the id of the {@link app.model.Project Project}
     * @param patchedProject The {@link app.model.Project Project} what has updated properties
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the updated {@link app.model.Project Project}
     */
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") int id, @RequestBody Project patchedProject) {
        logger.info("[PATCH] /projects/"+id+" UserID: " + 1);
        Project project = projectService.updateProject(id, patchedProject);
        return ResponseEntity.ok(project);
    }

    /**
     * Deletes the {@link app.model.Project Project} with the given id.
     * @param id The id of the {@link app.model.Project Project}
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the deleted {@link app.model.Project Project}
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable("id") int id) {
        logger.info("[DELETE] /projects/"+id+" UserID: " + 1);
        Project project = projectService.deleteProject(id);
        return ResponseEntity.ok(project);
    }


}
