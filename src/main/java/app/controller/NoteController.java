package app.controller;

import app.model.Note;
import app.services.NoteServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * The REST Controller what handles requests about {@link app.model.Note Note} objects
 */
@RestController
@RequestMapping(value = "/projects")
public class NoteController {
    Logger logger = LoggerFactory.getLogger(NoteController.class);
    @Autowired
    private NoteServiceI noteService;

    /**
     * Returns the {@link app.model.Note Note} for the selected {@link app.model.Project Project}
     * @param projectId The id of the {@link app.model.Project Project}
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the {@link app.model.Note Note} object
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/note")
    public ResponseEntity<Note> findById(@PathVariable("id") int projectId) {
        logger.info("[GET] /projects/"+projectId +"/note UserID: " + 1);
        Note note = noteService.findByProjectId(projectId);
        return ResponseEntity.ok(note);
    }

    /**
     * Creates a Note for the selected Project
     * @param id The id of the Project
     * @param postedNote The Note what should be save in to the database
     * @return A ResponseEntity filled with the created Note
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/note")
    public ResponseEntity<Note> createNote(@PathVariable("id") int id, @RequestBody Note postedNote) {
        logger.info("[POST] /projects/"+id+"/note UserID: " + 1);
        logger.debug(postedNote.toString());
        Note note = noteService.findByProjectId(id);
        if(note == null) {
            Note newNote = noteService.addNote(postedNote);
            return ResponseEntity.created(URI.create("/projects/" + id + "/note")).body(newNote);
        } else {
            return ResponseEntity.badRequest().body(note);
        }
    }

    /**
     * Updates the {@link app.model.Note Note} for the {@link app.model.Project Project}
     * @param id id of the {@link app.model.Project Project}
     * @param patchedNote The {@link app.model.Note Note} what should be saved in to the database
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the updated {@link app.model.Note Note}
     */
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}/note")
    public ResponseEntity<Note> updateNote(@PathVariable("id") int id, @RequestBody Note patchedNote) {
        logger.info("[PATCH] /projects/"+id+"/note UserID: " + 1);
        Note requestedNote = noteService.findByProjectId(id);
        Note note = noteService.updateNote(requestedNote.getId(), patchedNote);
        return ResponseEntity.ok(note);
    }

}
