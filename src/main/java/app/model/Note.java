package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * This class represents a Note in the database.
 */
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @OneToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;
    @Column(name = "note")
    private String note;

    /**
     * Returns the id of the note.
     * @return the id of the note
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the note.
     * @param id the id to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the Project where the note belongs to.
     * @return the Project where the note belongs to
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets the Project where the note belongs to.
     * @param project the project to be set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Returns the note text.
     * @return the note text
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note text.
     * @param note the not text to be set
     */
    public void setNote(String note) {
        this.note = note;
    }
}
