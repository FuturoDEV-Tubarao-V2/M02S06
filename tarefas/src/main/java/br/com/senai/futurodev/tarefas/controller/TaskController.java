package br.com.senai.futurodev.tarefas.controller;

import br.com.senai.futurodev.tarefas.model.Task;
import br.com.senai.futurodev.tarefas.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final String developerName;

    public TaskController(TaskService taskService, @Value("${developer.name}") String developerName) {
        this.taskService = taskService;
        this.developerName = developerName;
    }

    @GetMapping
    public ResponseEntity<List<Task>> list() {
        List<Task> tasks = this.taskService.list();
        System.out.println(this.developerName);
        return tasks.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody @Valid Task task) throws Exception {
        this.taskService.create(task);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable("id") Long id,
                                       @RequestBody Task task) throws Exception {
        Task updatedTask = this.taskService.update(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
