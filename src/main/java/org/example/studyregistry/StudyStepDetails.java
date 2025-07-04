package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyStepDetails {
    private String firstStep;
    private String resetStudyMechanism;
    private String consistentStep;
    private String seasonalSteps;
    private String basicSteps;
    private String mainObjectiveTitle;
    private String mainGoalTitle;
    private String mainMaterialTopic;
    private String mainTask;
    private Integer numberOfSteps;
    private boolean isImportant;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private StudyStepDetails(Builder builder) {
        assignStepsInfo(builder);
        assignMainTitles(builder);
        assignMetadata(builder);
    }

    private void assignStepsInfo(Builder builder) {
        this.firstStep = builder.firstStep;
        this.resetStudyMechanism = builder.resetStudyMechanism;
        this.consistentStep = builder.consistentStep;
        this.seasonalSteps = builder.seasonalSteps;
        this.basicSteps = builder.basicSteps;
    }

    private void assignMainTitles(Builder builder) {
        this.mainObjectiveTitle = builder.mainObjectiveTitle;
        this.mainGoalTitle = builder.mainGoalTitle;
        this.mainMaterialTopic = builder.mainMaterialTopic;
        this.mainTask = builder.mainTask;
    }

    private void assignMetadata(Builder builder) {
        this.numberOfSteps = builder.numberOfSteps;
        this.isImportant = builder.isImportant;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
    }

    public List<String> toStepList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return new ArrayList<>(Arrays.asList(
                firstStep,
                resetStudyMechanism,
                consistentStep,
                seasonalSteps,
                basicSteps,
                "Number of steps: " + numberOfSteps,
                "Is it important to you? " + isImportant,
                startDate.format(formatter),
                endDate.format(formatter),
                mainObjectiveTitle,
                mainGoalTitle,
                mainMaterialTopic,
                mainTask
        ));
    }

    public static class Builder {
        private String firstStep;
        private String resetStudyMechanism;
        private String consistentStep;
        private String seasonalSteps;
        private String basicSteps;
        private String mainObjectiveTitle;
        private String mainGoalTitle;
        private String mainMaterialTopic;
        private String mainTask;
        private Integer numberOfSteps;
        private boolean isImportant;
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public Builder firstStep(String firstStep) {
            this.firstStep = firstStep;
            return this;
        }

        public Builder resetStudyMechanism(String resetStudyMechanism) {
            this.resetStudyMechanism = resetStudyMechanism;
            return this;
        }

        public Builder consistentStep(String consistentStep) {
            this.consistentStep = consistentStep;
            return this;
        }

        public Builder seasonalSteps(String seasonalSteps) {
            this.seasonalSteps = seasonalSteps;
            return this;
        }

        public Builder basicSteps(String basicSteps) {
            this.basicSteps = basicSteps;
            return this;
        }

        public Builder mainObjectiveTitle(String mainObjectiveTitle) {
            this.mainObjectiveTitle = mainObjectiveTitle;
            return this;
        }

        public Builder mainGoalTitle(String mainGoalTitle) {
            this.mainGoalTitle = mainGoalTitle;
            return this;
        }

        public Builder mainMaterialTopic(String mainMaterialTopic) {
            this.mainMaterialTopic = mainMaterialTopic;
            return this;
        }

        public Builder mainTask(String mainTask) {
            this.mainTask = mainTask;
            return this;
        }

        public Builder numberOfSteps(Integer numberOfSteps) {
            this.numberOfSteps = numberOfSteps;
            return this;
        }

        public Builder isImportant(boolean isImportant) {
            this.isImportant = isImportant;
            return this;
        }

        public Builder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public StudyStepDetails build() {
            return new StudyStepDetails(this);
        }
    }
}
