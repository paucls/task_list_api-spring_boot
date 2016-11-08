package io.tasklist.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;
import io.tasklist.model.Task;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-05T12:06:02.530Z")

@Controller
public class TasksApiController implements TasksApi {

	public ResponseEntity<List<Task>> tasksGet() {
		return new ResponseEntity<List<Task>>(TasksStub.list(), HttpStatus.OK);
	}

	public ResponseEntity<Void> tasksIdDelete(
			@ApiParam(value = "Task identifier", required = true) @PathVariable("id") String id) {
		TasksStub.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> tasksIdPost(
			@ApiParam(value = "Task identifier", required = true) @PathVariable("id") String id,
			@ApiParam(value = "Task to update", required = true) @RequestBody Task task

	) {
		TasksStub.update(task);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Task> tasksPost(
			@ApiParam(value = "Task to add to the list", required = true) @RequestBody Task task) {
		Task createdTask = TasksStub.create(task);
		return new ResponseEntity<Task>(createdTask, HttpStatus.OK);
	}

}
