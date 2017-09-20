package app.services;

import app.dao.NoteDAO;
import app.model.Note;
import app.model.Project;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {

    /**
     *
     */
    private Note note;

    /**
     *
     */
    @Mock
    private NoteDAO noteDAO;

    /**
     *
     */
    @InjectMocks
    private NoteService noteService = new NoteService();

    /**
     *
     */
    @Before
    public void setUp() {
        Project project = new Project();
        project.setId(1);
        project.setDescription("Some description");
        project.setDueDate(LocalDate.of(2017,10,10));
        project.setName("Some name");

        note = new Note();
        note.setNote("asduffmoviie");
        note.setId(1);
        note.setProject(project);

    }

    /**
     *
     */
    @Test
    public void test__findByProjectId__should__return__the__note() {
        when(noteDAO.findByProjectId(1)).thenReturn(note);
        Note returnedNote = noteService.findByProjectId(1);
        assertThat(returnedNote.getNote(), is(equalTo(note.getNote())));
    }

    /**
     *
     */
    @Test
    public void test__findById__should__return__with__the__correct__note() {
        when(noteDAO.findById(1)).thenReturn(note);
        Note returnedNotes = noteService.findById(1);
        assertThat(returnedNotes.getNote(), is(equalTo(note.getNote())));
    }

    /**
     *
     */
    @Test
    public void test__addNote__should__return__with__the__created() {
        when(noteDAO.addNote(note)).thenReturn(note);
        Note returnedNote = noteService.addNote(note);
        assertThat(returnedNote.getNote(), is(equalTo(note.getNote())));
    }

    /**
     *
     */
    @Test
    public void test__update__note__should__return__with__the__updated__note() {
        when(noteDAO.updateNote(1, note)).thenReturn(note);
        Note returnedNote = noteService.updateNote(1, note);
        assertThat(returnedNote.getNote(), is(equalTo(note.getNote())));
    }

    /**
     *
     */
    @Test
    public void test__delete__note__should__return__with__the__deleted__note() {
        when(noteDAO.deleteNote(1)).thenReturn(note);
        Note returnedNote = noteService.deleteNote(1);
        assertThat(returnedNote.getNote(), is(equalTo(note.getNote())));
    }
}
