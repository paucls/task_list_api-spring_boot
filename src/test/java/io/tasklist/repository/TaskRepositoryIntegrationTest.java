package io.tasklist.repository;

import io.tasklist.Swagger2SpringBoot;
import io.tasklist.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Swagger2SpringBoot.class)
@WebAppConfiguration
public class TaskRepositoryIntegrationTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void findAll_returnsAllTasks() {
        List<Task> taskList = taskRepository.findAll();

        assertThat(taskList.size(), is(0));
    }

}
