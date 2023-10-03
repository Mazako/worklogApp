package pl.mazak.worklogappbackend.daily_time;

import pl.mazak.worklogappbackend.persistence.DailyTime;

import java.time.LocalDate;
import java.time.LocalTime;

public record DailyTimeDTO(LocalDate date, LocalTime startHour, LocalTime endHour) {
    static DailyTimeDTO toDto(DailyTime dailyTime) {
        return new DailyTimeDTO(dailyTime.getDate(), dailyTime.getStartHour(), dailyTime.getEndHour());
    }
}
