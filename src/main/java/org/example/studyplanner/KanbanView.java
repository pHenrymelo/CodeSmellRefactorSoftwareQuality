package org.example.studyplanner;

import java.util.*;

public class KanbanView {
    public enum State {
        TODO, DOING, DONE;
    }

    private final HabitTracker habitTracker;
    private final TodoTracker todoTracker;
    private final Map<State, List<PlannerMaterial>> kanban;

    public KanbanView(HabitTracker habitTracker, TodoTracker todoTracker) {
        this.habitTracker = habitTracker;
        this.todoTracker = todoTracker;
        this.kanban = new HashMap<>();
        for (State state : State.values()) {
            this.kanban.put(state, new ArrayList<>());
        }
    }

    public List<PlannerMaterial> getKanbanByState(State state) {
        return kanban.get(state);
    }

    private PlannerMaterial getMaterialById(Integer id, Class<?> clazz) throws Exception {
        if (clazz == Habit.class) {
            Habit habit = habitTracker.getHabitById(id);
            if (habit != null) return habit;
        } else if (clazz == ToDo.class) {
            ToDo todo = todoTracker.getToDoById(id);
            if (todo != null) return todo;
        }
        throw new Exception("Material not found with id: " + id);
    }

    public void addToKanban(State state, Integer id, Class<?> clazz) throws Exception {
        PlannerMaterial material = getMaterialById(id, clazz);
        kanban.get(state).add(material);
    }

    public void removeFromKanban(State state, Integer id, Class<?> clazz) throws Exception {
        PlannerMaterial material = getMaterialById(id, clazz);
        kanban.get(state).remove(material);
    }

    public String kanbanView() throws Exception {
        if (kanban.isEmpty()) {
            throw new Exception("No material found");
        }

        StringBuilder sb = new StringBuilder();
        for (State state : State.values()) {
            sb.append(formatStateView(state));
        }
        return sb.toString();
    }

    private String formatStateView(State state) {
        StringBuilder sb = new StringBuilder();
        sb.append(state.name()).append(":\n");
        List<PlannerMaterial> materials = kanban.get(state);
        if (materials.isEmpty()) {
            sb.append("No material found\n");
        } else {
            for (PlannerMaterial material : materials) {
                sb.append("- ").append(material.toString()).append("\n");
            }
        }
        return sb.toString();
    }



    public void addHabitToKanban(State state, int id) throws Exception {
        Habit habit = habitTracker.getHabitById(id);
        if (habit == null) throw new Exception("Habit not found with id: " + id);
        kanban.get(state).add(habit);
    }

    public void addToDoToKanban(State state, int id) throws Exception {
        ToDo toDo = todoTracker.getToDoById(id);
        if (toDo == null) throw new Exception("ToDo not found with id: " + id);
        kanban.get(state).add(toDo);
    }

    public void removeHabitFromKanban(State state, Integer id) throws Exception {
        removeFromKanban(state, id, Habit.class);
    }

    public void removeToDoFromKanban(State state, Integer id) throws Exception {
        removeFromKanban(state, id, ToDo.class);
    }
}
