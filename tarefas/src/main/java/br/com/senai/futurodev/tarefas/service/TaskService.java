package br.com.senai.futurodev.tarefas.service;

import br.com.senai.futurodev.tarefas.model.Database;
import br.com.senai.futurodev.tarefas.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    public List<Task> list() {
        return Database.list();
    }

    public void create(Task task) throws Exception {
        if (Database.search(task.getId()) != null) {
            throw new Exception("Uma tarefa com este identificador já foi cadastrada!");
        }

        Database.add(task);
    }

    public Task update(Long id, Task task) throws Exception {
        return Database.update(id, task);
    }

    public void delete(Long id) {
        Database.delete(id);
    }
}
