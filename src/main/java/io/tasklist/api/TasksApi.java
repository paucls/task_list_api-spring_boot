package io.tasklist.api;

import io.tasklist.model.Task;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-05T12:06:02.530Z")

@Api(value = "tasks", description = "the tasks API")
public interface TasksApi {

    @ApiOperation(value = "", notes = "Gets the list of `Task` objects for the current user. ", response = Task.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "An array of tasks", response = Task.class)})
    @RequestMapping(value = "/tasks",
            method = RequestMethod.GET)
    ResponseEntity<Task> tasksGet();

    @ApiOperation(value = "", notes = "Remove a `Task` for the authenticated user. ", response = Void.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Task deleted successfully", response = Void.class)})
    @RequestMapping(value = "/tasks/{id}",
            method = RequestMethod.DELETE)
    ResponseEntity<Void> tasksIdDelete(
            @ApiParam(value = "Task identifier", required = true) @PathVariable("id") String id
    );

    @ApiOperation(value = "", notes = "Updates an existing `Task` object. ", response = Void.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task updated successfully", response = Void.class)})
    @RequestMapping(value = "/tasks/{id}",
            method = RequestMethod.POST)
    ResponseEntity<Void> tasksIdPost(
            @ApiParam(value = "Task identifier", required = true) @PathVariable("id") String id
            ,
            @ApiParam(value = "Task to update", required = true) @RequestBody Task task

    );

    @ApiOperation(value = "", notes = "Creates a new `Task` object for the current user. ", response = Task.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task created successfully", response = Task.class)})
    @RequestMapping(value = "/tasks",
            method = RequestMethod.POST)
    ResponseEntity<Task> tasksPost(

            @ApiParam(value = "Task to add to the list", required = true) @RequestBody Task task
    );

}
