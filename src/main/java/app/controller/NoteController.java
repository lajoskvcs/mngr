package app.controller;

import app.model.Note;
import app.model.User;
import app.services.NoteServiceI;
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
 * The REST Controller what handles requests about {@link app.model.Note Note} objects.
 */
@RestController
@RequestMapping(value = "/projects")
public class NoteController {
    /**
     * This variable inject the {@code Logger} into the {@code Controller}.
     */
    Logger logger = LoggerFactory.getLogger(NoteController.class);

    /**
     * This variable autowires the {@link app.services.NoteService} into the {@code Controller}.
     */
    @Autowired
    private NoteServiceI noteService;

    /**
     * This variable autowire the {@link app.services.UserService} into the {@code Controller}.
     */
    @Autowired
    private UserServiceI userService;

    /**
     * Returns the {@link app.model.Note Note} for the selected {@link app.model.Project Project}.
     * @param projectId The id of the {@link app.model.Project Project}
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the {@link app.model.Note Note} object
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/note")
    public ResponseEntity<Note> findById(@PathVariable("id") int projectId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());
        logger.info("[GET] /projects/"+projectId+"/note UserID: " + currentUser.getId());
        Note note = noteService.findByProjectId(projectId);
        return ResponseEntity.ok(note);
    }

    /**
     * Creates a Note for the selected Project.
     * @param id The id of the Project
     * @param postedNote The Note what should be save in to the database
     * @return A ResponseEntity filled with the created Note
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/note")
    public ResponseEntity<Note> createNote(@PathVariable("id") int id, @RequestBody Note postedNote) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());
        logger.info("[POST] /projects/"+id+"/note UserID: " + currentUser.getId());
        Note note = noteService.findByProjectId(id);
        if(note == null) {
            Note newNote = noteService.addNote(postedNote);
            return ResponseEntity.created(URI.create("/projects/" + id + "/note")).body(newNote);
        } else {
            return ResponseEntity.badRequest().body(note);
        }
    }

    /**
     * Updates the {@link app.model.Note Note} for the {@link app.model.Project Project}.
     * @param id id of the {@link app.model.Project Project}
     * @param patchedNote The {@link app.model.Note Note} what should be saved in to the database
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the updated {@link app.model.Note Note}
     */
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}/note")
    public ResponseEntity<Note> updateNote(@PathVariable("id") int id, @RequestBody Note patchedNote) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());
        logger.info("[PATCH] /projects/"+id+"/note UserID: " + currentUser.getId());
        Note note = noteService.updateNote(id, patchedNote);
        return ResponseEntity.ok(note);
    }

    /**
     * Deletes the {@link app.model.Note Note} for the {@link app.model.Project Project}.
     * @param id id of the {@link app.model.Project Project}
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the deleted {@link app.model.Note Note}
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/note")
    public ResponseEntity<Note> updateNote(@PathVariable("id") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());
        logger.info("[DELETE] /projects/"+id+"/note UserID: " + currentUser.getId());
        Note noteToDelete = noteService.findByProjectId(id);
        Note note = noteService.deleteNote(noteToDelete.getId());
        return ResponseEntity.ok(note);
    }

}
