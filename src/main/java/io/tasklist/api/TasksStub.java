package io.tasklist.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.tasklist.model.Task;

public class TasksStub {

    private static long idsCount;
    private static Map<String, Task> tasks = new HashMap<>();

    static {
        tasks.put("task-1", new Task("task-1", "Buy milk."));
        tasks.put("task-2", new Task("task-2", "Wash the dishes."));
        tasks.put("task-3", new Task("task-3", "Go running."));
        idsCount = tasks.size();
    }

    public static List<Task> list() {
        return new ArrayList<>(tasks.values());
    }

    public static Task create(Task task) {
        String id = "task-" + ++idsCount;
        task.setId(id);
        tasks.put(id, task);

        return task;
    }

    public static Task update(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    public static void delete(String id) {
        tasks.remove(id);
    }
}
