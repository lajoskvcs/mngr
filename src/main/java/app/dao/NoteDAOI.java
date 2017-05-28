package app.dao;

import app.model.Note;

/**
 * The interface for manipulating {@link app.model.Note Notes} in the database.
 */
public interface NoteDAOI {
    /**
     * This method returns the {@link app.model.Note} with the given id.
     * @param noteId The id of the {@link app.model.Note}
     * @return The {@link app.model.Note} with the given id
     */
    public Note findById(int noteId);
    /**
     * This method returns the {@link app.model.Note} with the given {@code projectId}.
     * @param projectId The id of the {@link app.model.Project}
     * @return The {@link app.model.Note} for the given {@link app.model.Project}
     */
    public Note findByProjectId(int projectId);
    /**
     * This method adds a new {@link app.model.Note} to the database.
     * @param note The {@link app.model.Note} to be added to the database
     * @return The added {@link app.model.Note}
     */
    public Note addNote(Note note);

    /**
     * This method updates a {@link app.model.Note} with the given id.
     * @param noteId The id of the {@link app.model.Note} to be updated
     * @param note The {@link app.model.Note} object what contains teh changes
     * @return The updated {@link app.model.Note}
     */
    public Note updateNote(int noteId, Note note);

    /**
     * This method deletes a {@link app.model.Note} with the given id.
     * @param noteId The id of the {@link app.model.Note} to be deleted
     * @return The deleted {@link app.model.Note}
     */
    public Note deleteNote(int noteId);
}
