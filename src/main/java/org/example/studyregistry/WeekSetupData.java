package org.example.studyregistry;

import java.util.List;

public class WeekSetupData {
    public String planName;
    public String objectiveTitle;
    public String objectiveDescription;
    public String materialTopic;
    public String materialFormat;
    public String goal;
    public String reminderTitle;
    public String reminderDescription;
    public String mainTaskTitle;
    public String mainHabit;
    public String mainCardStudy;

    private WeekSetupData(Builder builder) {
        assignPlanInfo(builder);
        assignReminderInfo(builder);
        assignMainActions(builder);
    }

    private void assignPlanInfo(Builder builder) {
        this.planName = builder.planName;
        this.objectiveTitle = builder.objectiveTitle;
        this.objectiveDescription = builder.objectiveDescription;
        this.materialTopic = builder.materialTopic;
        this.materialFormat = builder.materialFormat;
        this.goal = builder.goal;
    }

    private void assignReminderInfo(Builder builder) {
        this.reminderTitle = builder.reminderTitle;
        this.reminderDescription = builder.reminderDescription;
    }

    private void assignMainActions(Builder builder) {
        this.mainTaskTitle = builder.mainTaskTitle;
        this.mainHabit = builder.mainHabit;
        this.mainCardStudy = builder.mainCardStudy;
    }

    public static class Builder {
        private String planName;
        private String objectiveTitle;
        private String objectiveDescription;
        private String materialTopic;
        private String materialFormat;
        private String goal;
        private String reminderTitle;
        private String reminderDescription;
        private String mainTaskTitle;
        private String mainHabit;
        private String mainCardStudy;

        public Builder planName(String planName) {
            this.planName = planName;
            return this;
        }

        public Builder objectiveTitle(String objectiveTitle) {
            this.objectiveTitle = objectiveTitle;
            return this;
        }

        public Builder objectiveDescription(String objectiveDescription) {
            this.objectiveDescription = objectiveDescription;
            return this;
        }

        public Builder materialTopic(String materialTopic) {
            this.materialTopic = materialTopic;
            return this;
        }

        public Builder materialFormat(String materialFormat) {
            this.materialFormat = materialFormat;
            return this;
        }

        public Builder goal(String goal) {
            this.goal = goal;
            return this;
        }

        public Builder reminderTitle(String reminderTitle) {
            this.reminderTitle = reminderTitle;
            return this;
        }

        public Builder reminderDescription(String reminderDescription) {
            this.reminderDescription = reminderDescription;
            return this;
        }

        public Builder mainTaskTitle(String mainTaskTitle) {
            this.mainTaskTitle = mainTaskTitle;
            return this;
        }

        public Builder mainHabit(String mainHabit) {
            this.mainHabit = mainHabit;
            return this;
        }

        public Builder mainCardStudy(String mainCardStudy) {
            this.mainCardStudy = mainCardStudy;
            return this;
        }

        public WeekSetupData build() {
            return new WeekSetupData(this);
        }
    }

    public List<String> toList() {
        return List.of(planName, objectiveTitle, objectiveDescription, materialTopic, materialFormat, goal,
                reminderTitle, reminderDescription, mainTaskTitle, mainHabit, mainCardStudy);
    }
}
