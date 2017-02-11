package io.tasklist.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.tasklist.model.Task;
import io.tasklist.repository.TaskRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TasksApiControllerWebIntegrationTest {

    private static String URL = "/tasks/";

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TestRestTemplate restTemplate;

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
        Task[] response = restTemplate.getForObject(URL, Task[].class);

        List<Task> allTasks = Arrays.asList(response);

        assertThat(allTasks.size(), is(testTasksCount));
    }

    @Test
    public void testTasksGet_statusCode() throws IOException {
        taskRepository.deleteAll();
        ResponseEntity response = restTemplate.getForEntity(URL, String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree((String) response.getBody());

        assertThat(responseJson.isMissingNode(), is(false));
        assertThat(responseJson.toString(), equalTo("[]"));
    }

    @Test
    public void testTasksPost() {
        HttpEntity<Task> request = new HttpEntity<>(new Task(null, "task-name"));
        Task createdTask = restTemplate.postForObject(URL, request, Task.class);

        assertThat(createdTask, notNullValue());
        assertThat(createdTask.getId(), notNullValue());
        assertThat(createdTask.getName(), is("task-name"));
    }

    @Test
    public void testTasksIdPost() {
        testTask.setDone(true);
        String entityUrl = URL + testTask.getId();
        HttpEntity<Task> request = new HttpEntity<>(testTask);

        restTemplate.postForObject(entityUrl, request, Void.class);

        Task updatedTask = taskRepository.findOne(testTask.getId());
        assertThat(updatedTask.getDone(), is(true));
    }

    @Test
    public void testTasksIdDelete() {
        String entityUrl = URL + testTask.getId();

        restTemplate.delete(entityUrl);

        assertThat((int) taskRepository.count(), is(testTasksCount - 1));
    }

}
