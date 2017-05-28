package app.dao;

import app.model.Note;

/**
 * The interface for manipulating {@link app.model.Note Notes} in the database
 */
public interface NoteDAOI {
    public Note findById(int noteId);
    public Note findByProjectId(int projectId);
    public Note addNote(Note note);
    public Note updateNote(int noteId, Note note);
    public Note deleteNote(int noteId);
}
