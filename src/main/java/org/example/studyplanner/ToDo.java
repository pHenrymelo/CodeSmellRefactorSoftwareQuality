package org.example.studyplanner;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority;
    private List<LocalDateTime> executionLog = new ArrayList<>();

    public ToDo(Integer id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void updateDetails(String newTitle, String newDescription, int newPriority) {
        if (newTitle == null || newTitle.isBlank()) throw new IllegalArgumentException("Title cannot be empty");
        if (newPriority < 1 || newPriority > 5) throw new IllegalArgumentException("Priority must be between 1 and 5");

        this.title = newTitle;
        this.description = newDescription;
        this.priority = newPriority;
    }

    public boolean isHighPriority() {
        return this.priority <= 2;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }

    public void trackExecution() {
        executionLog.add(LocalDateTime.now());
    }

    public List<String> getFormattedExecutions() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<String> result = new ArrayList<>();
        for (LocalDateTime ldt : executionLog) {
            result.add(ldt.format(formatter));
        }
        return result;
    }

    public boolean matchesSearch(String keyword) {
        String lower = keyword.toLowerCase();
        return title.toLowerCase().contains(lower) || description.toLowerCase().contains(lower);
    }

    // Getters e Setters

    public void setId(Integer id) { this.id = id; }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }


}
