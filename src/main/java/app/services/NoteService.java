package app.services;

import app.dao.NoteDAO;
import app.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NoteService implements NoteServiceI {

    @Autowired
    private NoteDAO noteDAO;

    @Override
    public Note findById(int noteId) {
        return noteDAO.findById(noteId);
    }

    @Override
    public Note findByProjectId(int projectId) {
        return noteDAO.findByProjectId(projectId);
    }

    @Override
    public Note addNote(Note note) {
        return noteDAO.addNote(note);
    }

    @Override
    public Note updateNote(int noteId, Note note) {
        return noteDAO.updateNote(noteId, note);
    }

    @Override
    public Note deleteNote(int noteId) {
        return noteDAO.deleteNote(noteId);
    }
}
