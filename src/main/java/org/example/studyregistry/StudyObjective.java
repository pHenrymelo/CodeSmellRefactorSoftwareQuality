package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.List;

public class StudyObjective extends Registry {
    private String title;
    private String description;
    private String topic;
    private Integer practicedDays;
    private LocalDateTime startDate;
    private Double duration;
    private String objectiveInOneLine;
    private String objectiveFullDescription;
    private String motivation;

    public StudyObjective(String title, String description) {
        this.title = title;
        this.description = description;
        this.name = title;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTopic() { return topic; }
    public Integer getPracticedDays() { return practicedDays; }
    public LocalDateTime getStartDate() { return startDate; }
    public Double getDuration() { return duration; }
    public String getObjectiveInOneLine() { return objectiveInOneLine; }
    public String getObjectiveFullDescription() { return objectiveFullDescription; }
    public String getMotivation() { return motivation; }

    // Setters
    public void setDescription(String description) {
        this.description = description;
    }

    // ---- Main Interface ----
    public void handleSetObjective(ObjectiveData data) {
        applyIdentityData(data);
        applyContentData(data);
        applyTrackingData(data);
    }

    public int handleSetObjectiveAdapter(List<Integer> intProps, List<String> stringProps, Double duration, boolean isActive) {
        ObjectiveData data = ObjectiveData.fromProperties(intProps, stringProps, duration, isActive);
        handleSetObjective(data);
        return data.id;
    }

    // ---- Extracted Private Methods ----
    private void applyIdentityData(ObjectiveData data) {
        this.id = data.id;
        this.name = data.name;
        this.priority = data.priority;
        this.isActive = data.isActive;
    }

    private void applyContentData(ObjectiveData data) {
        this.title = data.title;
        this.description = data.description;
        this.topic = data.topic;
        this.objectiveInOneLine = data.objectiveInOneLine;
        this.objectiveFullDescription = data.objectiveFullDescription;
        this.motivation = data.motivation;
    }

    private void applyTrackingData(ObjectiveData data) {
        this.practicedDays = data.practicedDays;
        this.duration = data.duration;
        this.startDate = data.getStartDate();
    }

    public boolean isLongTermObjective() {
        return duration != null && duration > 30.0;
    }

    public boolean hasStarted() {
        return startDate != null && startDate.isBefore(LocalDateTime.now());
    }

    public boolean isActiveNow() {
        return isActive && hasStarted();
    }

    @Override
    public String toString() {
        return "StudyObjective [title:" + title + ", description:" + description +
                (topic != null ? ", topic:" + topic : "") +
                (practicedDays != null ? ", practicedDays:" + practicedDays : "") +
                (duration != null ? ", duration:" + duration : "") +
                (objectiveInOneLine != null ? ", objective summary:" + objectiveInOneLine : "") +
                (objectiveFullDescription != null ? ", objective full description:" + objectiveFullDescription : "") +
                (motivation != null ? ", motivation:" + motivation : "") + "]";
    }
}
