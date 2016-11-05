package io.tasklist.model;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * Task
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-05T12:06:02.530Z")

public class Task {
    private String id = null;

    private String name = null;

    private String userId = null;

    private Boolean done = false;

    public Task() {
    }

    public Task(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Task id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Unique identifier
     *
     * @return id
     **/
    @ApiModelProperty(value = "Unique identifier")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Task name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Task name
     *
     * @return name
     **/
    @ApiModelProperty(value = "Task name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * User that owns the task
     *
     * @return userId
     **/
    @ApiModelProperty(value = "User that owns the task")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Task done(Boolean done) {
        this.done = done;
        return this;
    }

    /**
     * Indicates if the task is already done or not.
     *
     * @return done
     **/
    @ApiModelProperty(value = "Indicates if the task is already done or not.")
    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(this.id, task.id) &&
                Objects.equals(this.name, task.name) &&
                Objects.equals(this.userId, task.userId) &&
                Objects.equals(this.done, task.done);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userId, done);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Task {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    done: ").append(toIndentedString(done)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

