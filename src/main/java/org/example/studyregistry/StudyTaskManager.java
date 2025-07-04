package org.example.studyregistry;

import org.example.studymaterial.Reference;

import java.util.ArrayList;
import java.util.List;

public class StudyTaskManager {
    private static StudyTaskManager instance;
    private StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    List<Registry> registryList;
    List<String> weekResponsibilities = List.of();

    private StudyTaskManager(){
        this.registryList = new ArrayList<>();
    }

    public static StudyTaskManager getStudyTaskManager(){
        if (instance == null) {
            instance = new StudyTaskManager();
        }
        return instance;
    }

    public List<String> getWeekResponsibilities() {
        return weekResponsibilities;
    }

    public void setUpWeek(WeekSetupData data){
        this.weekResponsibilities = new ArrayList<>(data.toList());
    }

    public void handleSetUpWeek(List<String> stringProperties){
        WeekSetupData data = new WeekSetupData.Builder()
                .planName(stringProperties.get(0))
                .objectiveTitle(stringProperties.get(1))
                .objectiveDescription(stringProperties.get(2))
                .materialTopic(stringProperties.get(3))
                .materialFormat(stringProperties.get(4))
                .goal(stringProperties.get(5))
                .reminderTitle(stringProperties.get(6))
                .reminderDescription(stringProperties.get(7))
                .mainTaskTitle(stringProperties.get(8))
                .mainHabit(stringProperties.get(9))
                .mainCardStudy(stringProperties.get(10))
                .build();

        setUpWeek(data);
    }

    public void addRegistry(Registry registry){
        registryList.add(registry);
    }

    public void removeRegistry(Registry registry){
        registryList.remove(registry);
    }

    public List<Registry> getRegistryList(){
        return registryList;
    }

    public List<String> searchInRegistries(String text){
        List<String> response = new ArrayList<>();
        for(Registry registry : registryList){
            String mix = (registry.getName() != null ? registry.getName() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())){
                response.add(registry.getName());
            }
        }
        return response;
    }
}
