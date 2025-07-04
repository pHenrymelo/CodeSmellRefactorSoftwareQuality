package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ObjectiveData {
    public Integer id;
    public Integer priority;
    public Integer practicedDays;
    public int day;
    public int month;
    public int year;

    public String name;
    public String title;
    public String description;
    public String topic;
    public String objectiveInOneLine;
    public String objectiveFullDescription;
    public String motivation;

    public Double duration;
    public boolean isActive;

    public LocalDateTime getStartDate() {
        return LocalDateTime.of(year, month, day, 0, 0);
    }

    public static ObjectiveData fromProperties(List<Integer> intProps, List<String> stringProps, Double duration, boolean isActive) {
        ObjectiveData data = new ObjectiveData();
        populateIntegerFields(data, intProps);
        populateStringFields(data, stringProps);
        data.duration = duration;
        data.isActive = isActive;
        return data;
    }

    private static void populateIntegerFields(ObjectiveData data, List<Integer> intProps) {
        data.id = intProps.get(0);
        data.priority = intProps.get(1);
        data.practicedDays = intProps.get(2);
        data.day = intProps.get(3);
        data.month = intProps.get(4);
        data.year = intProps.get(5);
    }

    private static void populateStringFields(ObjectiveData data, List<String> stringProps) {
        data.name = stringProps.get(0);
        data.title = stringProps.get(1);
        data.description = stringProps.get(2);
        data.topic = stringProps.get(3);
        data.objectiveInOneLine = stringProps.get(4);
        data.objectiveFullDescription = stringProps.get(5);
        data.motivation = stringProps.get(6);
    }

    public static ObjectiveData fromUserInput(Scanner scanner) {
        ObjectiveData data = new ObjectiveData();
        readIntegerFields(data, scanner);
        readStringFields(data, scanner);
        readAdditionalFields(data, scanner);
        return data;
    }

    private static void readIntegerFields(ObjectiveData data, Scanner scanner) {
        data.id = Integer.parseInt(scanner.nextLine());
        data.priority = Integer.parseInt(scanner.nextLine());
        data.practicedDays = Integer.parseInt(scanner.nextLine());
        data.day = Integer.parseInt(scanner.nextLine());
        data.month = Integer.parseInt(scanner.nextLine());
        data.year = Integer.parseInt(scanner.nextLine());
    }

    private static void readStringFields(ObjectiveData data, Scanner scanner) {
        data.name = scanner.nextLine();
        data.title = scanner.nextLine();
        data.description = scanner.nextLine();
        data.topic = scanner.nextLine();
        data.objectiveInOneLine = scanner.nextLine();
        data.objectiveFullDescription = scanner.nextLine();
        data.motivation = scanner.nextLine();
    }

    private static void readAdditionalFields(ObjectiveData data, Scanner scanner) {
        data.duration = Double.parseDouble(scanner.nextLine());
        data.isActive = Boolean.parseBoolean(scanner.nextLine());
    }
}
