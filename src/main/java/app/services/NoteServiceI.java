package app.services;

import app.model.Note;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface NoteServiceI {
    public Note findById(int noteId);
    public Note findByProjectId(int projectId);
    public Note addNote(Note note);
    public Note updateNote(int noteId, Note note);
    public Note deleteNote(int noteId);
}
