package app.dao;

import app.model.Note;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * The Repository class for manipulating {@link app.model.Note Notes} in the database.
 */
@Repository
public class NoteDAO implements NoteDAOI {

    /**
     * This variable autowires the <code>SessionFactory</code> <code>Bean</code>.
     */
    @Autowired
    protected SessionFactory sessionFactory;

    /**
     * This method returns teh current Session.
     * @return The current Session
     */
    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Note findById(int noteId) {
        return null;
    }

    @Override
    public Note findByProjectId(int projectId) {
        Query resourceQuery = openSession().createQuery("FROM Note WHERE project_id = :projectId");
        resourceQuery.setParameter("projectId", projectId);

        Note note = (Note) resourceQuery.getResultList().get(0);

        if (note == null) {
            return null;
        }

        return note;
    }

    @Override
    public Note addNote(Note note) {
        openSession().save(note);
        return note;
    }

    @Override
    public Note updateNote(int noteId, Note note) {
        Note noteToUpdate = findById(noteId);
        noteToUpdate.setNote(note.getNote());
        openSession().update(noteToUpdate);
        return noteToUpdate;
    }

    @Override
    public Note deleteNote(int noteId) {
        Note note = findById(noteId);
        if (note == null) {
            return null;
        }
        openSession().delete(note);
        return note;
    }
}
