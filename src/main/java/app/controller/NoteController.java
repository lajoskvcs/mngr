package app.controller;

import app.model.Note;
import app.services.NoteServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping(value = "/projects")
public class NoteController {
    Logger logger = LoggerFactory.getLogger(NoteController.class);
    @Autowired
    private NoteServiceI noteService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/note")
    public ResponseEntity<Note> findById(@PathVariable("id") int projectId) {
        logger.info("[GET] /projects/"+projectId +"/note UserID: " + 1);
        Note note = noteService.findByProjectId(projectId);
        return ResponseEntity.ok(note);
    }

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

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}/note")
    public ResponseEntity<Note> updateNote(@PathVariable("id") int id, @RequestBody Note patchedNote) {
        logger.info("[PATCH] /projects/"+id+"/note UserID: " + 1);
        Note requestedNote = noteService.findByProjectId(id);
        Note note = noteService.updateNote(requestedNote.getId(), patchedNote);
        return ResponseEntity.ok(note);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/note")
    public ResponseEntity<Note> deleteNote(@PathVariable("id") int id) {
        logger.info("[DELETE] /projects/"+id+"/note UserID: " + 1);
        Note requestedNote = noteService.findByProjectId(id);
        Note note = noteService.deleteNote(requestedNote.getId());
        return ResponseEntity.ok(note);
    }
}
