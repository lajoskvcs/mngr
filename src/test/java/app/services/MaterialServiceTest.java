package app.services;

import app.dao.MaterialDAO;
import app.dao.NoteDAO;
import app.model.Material;
import app.model.Note;
import app.model.Project;
import app.model.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MaterialServiceTest {

    /**
     *
     */
    private Material material1;

    /**
     *
     */
    private Collection<Material> materials = new ArrayList<Material>();

    /**
     *
     */
    @Mock
    private MaterialDAO materialDAO;

    /**
     *
     */
    @InjectMocks
    private MaterialService materialService = new MaterialService();

    /**
     *
     */
    @Before
    public void setUp() {
        Task task = new Task();
        task.setPriority(0);
        task.setName("asd");
        task.setId(1);

        material1 = new Material();
        material1.setId(1);
        material1.setTask(task);
        material1.setStoreName("asd");
        material1.setQuantity(10);
        material1.setPercent(10);
        material1.setListPrice(300);

        Material material2 = new Material();
        material1.setId(2);
        material2.setTask(task);
        material2.setStoreName("asd");
        material2.setQuantity(10);
        material2.setPercent(10);
        material2.setListPrice(300);

        Material material3 = new Material();
        material1.setId(3);
        material3.setTask(task);
        material3.setStoreName("asd");
        material3.setQuantity(10);
        material3.setPercent(10);
        material3.setListPrice(300);

        materials.add(material1);
        materials.add(material2);
        materials.add(material3);
    }

    /**
     *
     */
    @Test
    public void test__findByTaskId__should__return__the__notes() {
        when(materialDAO.findAllByTaskId(1)).thenReturn(materials);
        Collection<Material> returnedMaterial = materialService.findAllByTaskId(1);
        assertThat(returnedMaterial.size(), is(equalTo(3)));
    }

    /**
     *
     */
    @Test
    public void test__findById__should__return__with__the__correct__material() {
        when(materialDAO.findById(1)).thenReturn(material1);
        Material returnedMaterials = materialService.findById(1);
        assertThat(returnedMaterials.getName(), is(equalTo(material1.getName())));
    }

    /**
     *
     */
    @Test
    public void test__addMaterial__should__return__with__the__created() {
        when(materialDAO.addMaterial(material1)).thenReturn(material1);
        Material returnedMaterial = materialService.addMaterial(material1);
        assertThat(returnedMaterial.getName(), is(equalTo(material1.getName())));
    }

    /**
     *
     */
    @Test
    public void test__update__material__should__return__with__the__updated__material() {
        when(materialDAO.updateMaterial(1, material1)).thenReturn(material1);
        Material returnedMaterial = materialService.updateMaterial(1, material1);
        assertThat(returnedMaterial.getName(), is(equalTo(material1.getName())));
    }

    /**
     *
     */
    @Test
    public void test__delete__material__should__return__with__the__deleted__material() {
        when(materialDAO.deleteMaterial(1)).thenReturn(material1);
        Material returnedMaterial = materialService.deleteMaterial(1);
        assertThat(returnedMaterial.getName(), is(equalTo(material1.getName())));
    }

}
