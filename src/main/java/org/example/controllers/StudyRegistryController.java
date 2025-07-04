package org.example.controllers;

import org.example.studyregistry.*;
import org.example.studymaterial.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.example.controllers.MainController.getInput;
import static org.example.controllers.MainController.validateInput;

public class StudyRegistryController {

    private final StudyTaskManager studyTaskManager = StudyTaskManager.getStudyTaskManager();
    private final StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    private final Map<String, Runnable> actions = new HashMap<>();

    public StudyRegistryController() {
        assignActions();
    }

    private void assignActions() {
        actions.put("1", this::handleAddStudyTask);
        actions.put("2", this::handleAddStudyGoal);
        actions.put("3", this::handleAddStudyMaterial);
        actions.put("4", this::handleAddStudyObjective);
        actions.put("5", this::handleAddStudyPlan);
        actions.put("6", this::handleSetUpWeek);
        actions.put("7", this::handleGetWeekResponsibilities);
    }

    private void handleMethodHeader(String header) {
        System.out.println("~~~~ " + header + " ~~~~\n");
    }

    // --- Study Task ---

    private Task getStudyTaskInfo() {
        handleMethodHeader("Study Task Creation");
        System.out.println("Type the following info: title, description, author");
        return new Task(getInput(), getInput(), getInput(), LocalDateTime.now());
    }

    private void handleAddStudyTask() {
        Task task = getStudyTaskInfo();
        studyTaskManager.addRegistry(task);
    }

    // --- Study Objective ---

    public void handleSetObjective(StudyObjective objective) {
        handleMethodHeader("Study Objective Edit");
        promptUserForObjectiveInput();
        ObjectiveData data = ObjectiveData.fromUserInput(new Scanner(System.in));
        objective.handleSetObjective(data);
    }

    private void promptUserForObjectiveInput() {
        System.out.println("Type the following info in order: " +
                "Integer id, Integer priority, Integer practicedDays, int day, int month, int year, " +
                "String name, String title, String description, String topic, " +
                "String objectiveInOneLine, String objectiveFullDescription, String motivation, " +
                "Double duration, boolean isActive\n");
    }

    private StudyObjective getStudyObjectiveInfo() {
        handleMethodHeader("Study Objective Creation");
        System.out.println("Type the following info: title, description");
        String title = getInput();
        String description = getInput();
        StudyObjective studyObjective = new StudyObjective(title, description);
        handleSetObjective(studyObjective);
        studyTaskManager.addRegistry(studyObjective);
        return studyObjective;
    }

    private void handleAddStudyObjective() {
        getStudyObjectiveInfo();
    }

    // --- Study Plan ---

    private StudyPlan getStudyPlanInfo() {
        handleMethodHeader("Study Plan Creation");
        System.out.println("Type the following info: name");
        String name = getInput();
        StudyObjective studyObjective = getStudyObjectiveInfo();
        StudyPlan plan = new StudyPlan(name, studyObjective, new ArrayList<>());
        studyTaskManager.addRegistry(plan);
        return plan;
    }

    private void handleSetSteps(StudyPlan studyPlan) {
        handleMethodHeader("Study Plan Edit");
        System.out.println("""
            Type the following info:
            firstStep, resetStudyMechanism, consistentStep, seasonalSteps, basicSteps,
            mainObjectiveTitle, mainGoalTitle, mainMaterialTopic, mainTask,
            numberOfSteps (int), isImportant (boolean), durationInDays (long)
        """);

        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime endsAt = createdAt.plusDays(Long.parseLong(getInput()));

        studyPlan.assignSteps(
                getInput(), getInput(), getInput(), getInput(), getInput(),
                getInput(), getInput(), getInput(), getInput(),
                Integer.parseInt(getInput()), Boolean.parseBoolean(getInput()),
                createdAt, endsAt
        );
    }

    private void handleAddStudyPlan() {
        getStudyPlanInfo();
        System.out.println("Study Plan Added");
    }

    // --- Study Goal ---

    private StudyGoal getStudyGoalInfo() {
        handleMethodHeader("Study Goal Creation");
        System.out.println("Type the following info: name");
        String name = getInput();
        StudyPlan studyPlan = getStudyPlanInfo();
        handleSetSteps(studyPlan);
        StudyObjective objective = studyPlan.getObjective();
        return new StudyGoal(name, objective, studyPlan);
    }

    private void handleAddStudyGoal() {
        StudyGoal goal = getStudyGoalInfo();
        studyTaskManager.addRegistry(goal);
    }

    // --- Study Material ---

    private void handleAddStudyMaterial() {
        Reference reference = addStudyMaterial();
        if (reference != null) {
            studyMaterial.addReference(reference);
        }
    }

    private Reference addStudyMaterial() {
        handleMethodHeader("Study Material Creation");
        System.out.println("Type the following info: (AUDIO | VIDEO | TEXT)");
        String type = getInput().toLowerCase();

        return switch (type) {
            case "audio" -> addAudioReference();
            case "video" -> addVideoReference();
            case "text" -> addTextReference();
            default -> null;
        };
    }

    // --- Audio ---

    private AudioReference addAudioReference() {
        handleMethodHeader("Audio Reference Creation");
        System.out.println("Type the following info: Audio Quality (LOW | MEDIUM | HIGH | VERY_HIGH)");
        AudioReference audio = new AudioReference(AudioReference.audioQualityAdapter(getInput()));
        editAudio(audio);
        return audio;
    }

    private void editAudio(AudioReference audioReference) {
        handleMethodHeader("Audio Edit");
        AudioMetadata metadata = getAudioMetadataFromInput();
        audioReference.editAudio(metadata);
    }

    private AudioMetadata getAudioMetadataFromInput() {
        System.out.println("""
            Type the following info:
            AudioQuality, isDownloadable, title, description, link,
            accessRights, license, language, rating, viewCount, shareCount
        """);

        return new AudioMetadata.Builder()
                .audioQuality(AudioReference.audioQualityAdapter(getInput()))
                .isDownloadable(Boolean.parseBoolean(getInput()))
                .title(getInput())
                .description(getInput())
                .link(getInput())
                .accessRights(getInput())
                .license(getInput())
                .language(getInput())
                .rating(Integer.parseInt(getInput()))
                .viewCount(Integer.parseInt(getInput()))
                .shareCount(Integer.parseInt(getInput()))
                .build();
    }

    // --- Video ---

    private VideoReference addVideoReference() {
        handleMethodHeader("Video Reference Creation");
        System.out.println("""
            Type the following info:
            isAvailable, title, description, resolution,
            frameRate, videoFormat, accessRights
        """);

        return new VideoReference(
                Boolean.parseBoolean(getInput()), getInput(), getInput(), getInput(),
                getInput(), getInput(), getInput()
        );
    }

    // --- Text ---

    private TextReference addTextReference() {
        handleMethodHeader("Text Reference Creation");
        System.out.println("Type the following info: title, language, wordCount, format, accessRights");
        return new TextReference(
                getInput(), getInput(), Integer.parseInt(getInput()),
                getInput(), getInput()
        );
    }

    // --- Week Setup ---

    private void handleSetUpWeek() {
        getWeekInfo();
    }

    private void getWeekInfo() {
        System.out.println("""
            (Study Task Manager Week Set Up) Type the following info:
            planName, objectiveTitle, objectiveDescription, materialTopic,
            materialFormat, goal, reminderTitle, reminderDescription,
            mainTaskTitle, mainHabit, mainCardStudy
        """);

        studyTaskManager.setUpWeek(
                getInput(), getInput(), getInput(), getInput(), getInput(),
                getInput(), getInput(), getInput(), getInput(), getInput(), getInput()
        );
    }

    // --- Responsibilities ---

    private void handleGetWeekResponsibilities() {
        List<String> responsibilities = studyTaskManager.getWeekResponsibilities();
        System.out.println(String.join(", ", responsibilities));
    }

    // --- CLI Controller ---

    public void handleRegistryInput() {
        try {
            while (true) {
                controllerOptions();
                String response = validateInput(actions);
                if (response == null) return;
                actions.get(response).run();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void controllerOptions() {
        System.out.println("""
            0 - return
            1 - add study task
            2 - add study goal
            3 - add study material (audio, video, text)
            4 - add study objective
            5 - add study plan
            6 - set up week
            7 - get week responsibilities
        """);
    }
}
