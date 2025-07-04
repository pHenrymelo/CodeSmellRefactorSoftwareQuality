package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        this.title = title;
        this.name = title;  // herdado de Registry
        this.description = description;
        this.author = author;
        this.date = date;
    }

    // Método que retorna resumo da tarefa
    public String getSummary() {
        return String.format("Task: %s\nAuthor: %s\nDate: %s", title, author, getFormattedDate());
    }

    // Método que retorna a data formatada
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return date.format(formatter);
    }

    // Atualiza a data para o momento atual
    public void updateDateToNow() {
        this.date = LocalDateTime.now();
    }

    // Getters e setters usados
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.name = title; // mantém coerência com Registry
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
