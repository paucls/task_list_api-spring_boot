package io.tasklist.repository;

import io.tasklist.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryIntegrationTest {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void testFindAll() {
        Task task = new Task();
        task.setName("task-name");
        this.entityManager.persist(task);

        List<Task> taskList = taskRepository.findAll();

        assertThat(taskList.size(), is(1));
        assertThat(taskList.get(0).getName(), is("task-name"));
    }

}
