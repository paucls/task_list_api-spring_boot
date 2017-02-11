package io.tasklist.repository;

import io.tasklist.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryIntegrationTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void testFindAll() {
        List<Task> taskList = taskRepository.findAll();

        assertThat(taskList.size(), is(0));
    }

}
