package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HabitTracker {
    private List<Habit> habits;
    private Map<Integer, List<LocalDateTime>> tracker;
    private Integer nextId;

    private static HabitTracker instance;

    public static HabitTracker getHabitTracker() {
        if (instance == null) {
            instance = new HabitTracker();
        }
        return instance;
    }

    private HabitTracker(){
        this.habits = new ArrayList<>();
        this.tracker = new HashMap<>();
        this.nextId = 1;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        for (Habit habit : habits) {
            response.append(habit.toString()).append(", ");
        }
        return "Habits: " + response.toString();
    }

    public Habit getHabitById(Integer id){
        return this.habits.stream()
                .filter(habit -> Objects.equals(habit.getId(), id))
                .findFirst().orElse(null);
    }

    public List<Habit> getHabits() {
        return this.habits;
    }

    public String formatHabitDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(formatter);
    }

    public List<Integer> getTrackerKeys(){
        return new ArrayList<>(this.tracker.keySet());
    }

    public int addHabit(HabitData data) {
        LocalTime lt = data.getDailyDedicationTime();
        LocalDateTime startDate = data.getStartDate();
        Habit habit = new Habit(data.name, data.motivation, lt, this.nextId, startDate, data.isConcluded);
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
    }

    public int handleAddHabitAdapter(List<String> stringProperties, List<Integer> intProperties, boolean isConcluded) {
        HabitData data = buildHabitDataFromLists(stringProperties, intProperties, isConcluded);
        return addHabit(data);
    }

    private HabitData buildHabitDataFromLists(List<String> stringProperties, List<Integer> intProperties, boolean isConcluded) {
        HabitData data = new HabitData();
        data.fillBasicInfo(stringProperties);
        data.fillTimeInfo(intProperties);
        data.isConcluded = isConcluded;
        return data;
    }

    public int addHabit(String name, String motivation) {
        Habit habit = new Habit(name, motivation, this.nextId);
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
    }

    public void addHabitRecord(Integer id){
        tracker.get(id).add(LocalDateTime.now());
    }

    public void toggleConcludeHabit(Integer id) {
        for (Habit habit : this.habits) {
            if (habit.getId().equals(id)) {
                habit.setIsConcluded(!habit.getIsConcluded());
            }
        }
    }

    public void removeHabit(Integer id) {
        this.habits.removeIf(habit -> habit.getId().equals(id));
        this.tracker.remove(id);
    }

    public List<LocalDateTime> getHabitRecords(Integer id) {
        return this.tracker.get(id);
    }

    public List<String> searchInHabits(String search){
        List<String> habits = new ArrayList<>();
        for (Habit habit : this.habits) {
            if (habit.getName().toLowerCase().contains(search.toLowerCase()) || habit.getMotivation().toLowerCase().contains(search.toLowerCase())) {
                habits.add(habit.toString());
            }
        }
        return habits;
    }

    public String formatHabitWithRecords(Habit habit) {
        StringBuilder result = new StringBuilder();
        result.append("[ Habit: ")
                .append(habit.getName())
                .append(". Records: ");
        List<LocalDateTime> records = getHabitRecords(habit.getId());
        for(LocalDateTime record : records){
            result.append(formatHabitDate(record)).append(", ");
        }
        result.append("]");
        return result.toString();
    }

    public String habitDateViewAll() {
        StringBuilder response = new StringBuilder();
        for (Habit habit : this.getHabits()) {
            response.append(this.formatHabitWithRecords(habit));
        }
        return response.toString();
    }
}
