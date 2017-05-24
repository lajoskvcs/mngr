package app.dao;

import app.model.Note;
import org.springframework.stereotype.Repository;

public interface NoteDAOI {
    public Note findById(int noteId);
    public Note findByProjectId(int projectId);
    public Note addNote(Note note);
    public Note updateNote(int noteId, Note note);
    public Note deleteNote(int noteId);
}
