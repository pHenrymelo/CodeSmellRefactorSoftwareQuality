package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class HabitData {
    public String name;
    public String motivation;
    public Integer dailyMinutesDedication;
    public Integer dailyHoursDedication;
    public Integer year;
    public Integer month;
    public Integer day;
    public Integer hour;
    public Integer minute;
    public Integer seconds;
    public boolean isConcluded;

    public LocalTime getDailyDedicationTime() {
        return LocalTime.of(this.dailyHoursDedication, this.dailyMinutesDedication);
    }

    public LocalDateTime getStartDate() {
        return LocalDateTime.of(year, month, day, hour, minute, seconds);
    }

    public void fillBasicInfo(List<String> stringProperties) {
        this.name = stringProperties.get(0);
        this.motivation = stringProperties.get(1);
    }

    public void fillTimeInfo(List<Integer> intProperties) {
        this.dailyMinutesDedication = intProperties.get(0);
        this.dailyHoursDedication = intProperties.get(1);
        this.year = intProperties.get(2);
        this.month = intProperties.get(3);
        this.day = intProperties.get(4);
        this.hour = intProperties.get(5);
        this.minute = intProperties.get(6);
        this.seconds = intProperties.get(7);
    }

    public void fillStartDateFields(LocalDateTime start) {
        this.year = start.getYear();
        this.month = start.getMonthValue();
        this.day = start.getDayOfMonth();
        this.hour = start.getHour();
        this.minute = start.getMinute();
        this.seconds = start.getSecond();
    }
}
