package io.tasklist.api;

import io.tasklist.model.Task;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-05T12:06:02.530Z")

@Controller
public class TasksApiController implements TasksApi {

    private long idsCount = 3;
    private List<Task> stubTasks = buildStubTasks();

    private List<Task> buildStubTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("task-1", "Buy milk."));
        tasks.add(new Task("task-2", "Wash the dishes."));

        return tasks;
    }

    public ResponseEntity<List<Task>> tasksGet() {
        return new ResponseEntity<List<Task>>(stubTasks, HttpStatus.OK);
    }

    public ResponseEntity<Void> tasksIdDelete(
            @ApiParam(value = "Task identifier", required = true) @PathVariable("id") String id
    ) {
        stubTasks = stubTasks.stream()
                .filter(task -> !task.getId().equals(id))
                .collect(Collectors.toList());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> tasksIdPost(
            @ApiParam(value = "Task identifier", required = true) @PathVariable("id") String id,
            @ApiParam(value = "Task to update", required = true) @RequestBody Task task

    ) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Task> tasksPost(
            @ApiParam(value = "Task to add to the list", required = true) @RequestBody Task task
    ) {
        task.setId("task-" + idsCount++);
        stubTasks.add(task);

        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

}
