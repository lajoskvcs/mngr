package app.controller;

import app.model.Project;
import app.model.User;
import app.services.NoteServiceI;
import app.services.ProjectService;
import app.services.ProjectServiceI;
import app.services.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {
    Logger logger = LoggerFactory.getLogger(ProjectController.class);


    @Autowired
    private ProjectServiceI projectService;

    @Autowired
    private UserServiceI userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Project>> findAll() {
        logger.info("[GET] /projects UserID: " + 1);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());
        Collection<Project> projects = projectService.findAll(currentUser.getId());
        return ResponseEntity.ok(projects);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Project> findById(@PathVariable("id") int id) {
        logger.info("[GET] /projects/"+id+" UserID: " + 1);
        Project project = projectService.findById(id);
        return ResponseEntity.ok(project);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Project> createProject(@RequestBody Project postedProject) {
        logger.info("[POST] /projects UserID: " + 1);
        logger.debug(postedProject.toString());
        Project project = projectService.addProject(postedProject);
        return ResponseEntity.created(URI.create("/projects/" + project.getId())).body(project);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") int id, @RequestBody Project patchedProject) {
        logger.info("[PATCH] /projects/"+id+" UserID: " + 1);
        Project project = projectService.updateProject(id, patchedProject);
        return ResponseEntity.ok(project);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable("id") int id) {
        logger.info("[DELETE] /projects/"+id+" UserID: " + 1);
        Project project = projectService.deleteProject(id);
        return ResponseEntity.ok(project);
    }


}
