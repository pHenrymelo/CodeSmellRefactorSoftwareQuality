package org.example.studyplanner;

import java.time.LocalDateTime;
import java.util.List;

public class TimelineView {

    public String habitDateViewAll(HabitTracker ht){
        List<Habit> habits = ht.getHabits();
        StringBuilder response = new StringBuilder();
        for(Habit habit : habits){
            response.append(formatHabitWithRecords(ht, habit));
        }
        return response.toString();
    }

    private String formatHabitWithRecords(HabitTracker ht, Habit habit) {
        StringBuilder result = new StringBuilder();
        result.append("[ Habit: ")
                .append(habit.getName())
                .append(". Records: ");
        List<LocalDateTime> records = ht.getHabitRecords(habit.getId());
        for(LocalDateTime record : records){
            result.append(ht.formatHabitDate(record)).append(", ");
        }
        result.append("]");
        return result.toString();
    }

}
