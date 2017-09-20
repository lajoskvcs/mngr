package app.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

/**
 * JUnit test calss for Task tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskTest {
    private Task task;

    private Set<Material> materials = new HashSet<Material>();
    private Set<Time> times = new HashSet<Time>();

    /**
     * Setup method for tests
     */
    @Before
    public void setUp() {
        Material material1 = new Material();
        material1.setId(1);
        material1.setName("asd1");
        material1.setStoreName("Store1");
        material1.setQuantity(10);
        material1.setPercent(0.2);
        material1.setListPrice(3000);

        Material material2 = new Material();
        material2.setId(2);
        material2.setName("asd2");
        material2.setStoreName("Store1");
        material2.setQuantity(3);
        material2.setPercent(0.5);
        material2.setListPrice(15000);

        Material material3 = new Material();
        material3.setId(3);
        material3.setName("asd3");
        material3.setStoreName("Store1");
        material3.setQuantity(5);
        material3.setPercent(0.1);
        material3.setListPrice(8000);

        Time time1 = new Time();
        time1.setId(1);
        time1.setTask(task);
        time1.setStartDate(LocalDateTime.of(2017,1,1,10,10));
        time1.setEndDate(LocalDateTime.of(2017,1,1,15,10));

        Time time2 = new Time();
        time2.setId(2);
        time2.setTask(task);
        time2.setStartDate(LocalDateTime.of(2017,1,6,8,0));
        time2.setEndDate(LocalDateTime.of(2017,1,6,16,0));

        times.add(time1);
        times.add(time2);

        materials.add(material1);
        materials.add(material2);
        materials.add(material3);

        task = new Task();
        task.setHourlySalary(3500);
        task.setPriority(3);
        task.setMaterials(materials);
        task.setTimes(times);
        task.setName("something");
        task.setStatus(2);
        task.setDescription("");
    }

    /**
     *
     */
    @Test
    public void test__getSummerMaterialPrice__should__return__the__correct__number() {
        assertThat(task.getSummedMaterialPrice(), is(equalTo(147500.0)));
    }

    /**
     *
     */
    @Test
    public void test__getWorkedHours__should__return__the__correct__number() {
        assertThat(task.getTimes().size(), is(equalTo(2)));
        assertThat(task.getWorkedHours(), is(equalTo(13.0)));
    }

    /**
     *
     */
    @Test
    public void test__getTotalPayment__should__return__the__correct__number() {
        assertThat(task.getTotalPayment(), is(equalTo(193000.0)));
    }

    /**
     *
     */
    @Test
    public void test__getTotalProfit__should__return__the__correct__number() {
        assertThat(task.getTotalProfit(), is(equalTo(54400.0)));
    }

}
