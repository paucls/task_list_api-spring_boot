package io.tasklist.api;

import java.util.List;

import io.tasklist.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;
import io.tasklist.model.Task;

@Controller
public class TasksApiController implements TasksApi {

    @Autowired
    private TaskRepository taskRepository;

	public ResponseEntity<List<Task>> tasksGet() {
		return new ResponseEntity<>(taskRepository.findAll(), HttpStatus.OK);
	}

    public ResponseEntity<Void> tasksIdDelete(
            @ApiParam(value = "Task identifier", required = true) @PathVariable("id") String id) {
        taskRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	public ResponseEntity<Void> tasksIdPost(
			@ApiParam(value = "Task identifier", required = true) @PathVariable("id") String id,
			@ApiParam(value = "Task to update", required = true) @RequestBody Task task

	) {
        Task existingTask = taskRepository.findOne(id);
        BeanUtils.copyProperties(task, existingTask);
		taskRepository.saveAndFlush(existingTask);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    public ResponseEntity<Task> tasksPost(
            @ApiParam(value = "Task to add to the list", required = true) @RequestBody Task task) {
        Task createdTask = taskRepository.saveAndFlush(task);
        return new ResponseEntity<>(createdTask, HttpStatus.OK);
    }

}
