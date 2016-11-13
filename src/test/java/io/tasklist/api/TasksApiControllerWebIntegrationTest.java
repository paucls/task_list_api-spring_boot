package io.tasklist.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.tasklist.Swagger2SpringBoot;
import io.tasklist.model.Task;
import io.tasklist.repository.TaskRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Swagger2SpringBoot.class)
@WebIntegrationTest
public class TasksApiControllerWebIntegrationTest {

    private static String URL = "http://localhost:8080/tasks";

    @Autowired
    TaskRepository taskRepository;

    private Task testTask;
    private int testTasksCount = 3;

    @Before
    public void setup() {
        testTask = taskRepository.saveAndFlush(new Task("task1-id", "task1-name"));
        taskRepository.saveAndFlush(new Task());
        taskRepository.saveAndFlush(new Task());
    }

    @After
    public void tearDown() {
        taskRepository.deleteAll();
    }

    @Test
    public void testTasksGet() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        Task[] response = restTemplate.getForObject(URL, Task[].class);

        List<Task> allTasks = Arrays.asList(response);

        assertThat(allTasks.size(), is(testTasksCount));
    }

    @Test
    public void testTasksGet_statusCode() throws IOException {
        taskRepository.deleteAll();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.getForEntity(URL, String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree((String) response.getBody());

        assertThat(responseJson.isMissingNode(), is(false));
        assertThat(responseJson.toString(), equalTo("[]"));
    }

    @Test
    public void testTasksPost() {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Task> request = new HttpEntity<>(new Task(null, "task-name"));
        Task createdTask = restTemplate.postForObject(URL, request, Task.class);

        assertThat(createdTask, notNullValue());
        assertThat(createdTask.getId(), notNullValue());
        assertThat(createdTask.getName(), is("task-name"));
    }

    @Test
    public void testTasksIdDelete() {
        String entityUrl = URL + "/" + testTask.getId();
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete(entityUrl);

        assertThat((int) taskRepository.count(), is(testTasksCount - 1));
    }

}
