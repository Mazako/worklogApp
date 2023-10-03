package pl.mazak.worklogappbackend.daily_time;

import pl.mazak.worklogappbackend.persistence.DailyTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public interface DailyTimeService {
    DailyTime addDay(LocalDate date, LocalTime start, LocalTime end);
    Optional<DailyTime> getDay(LocalDate date);
    List<DailyTime> getDaysByMonth(Month month, int year);
    Optional<DailyTime> updateDay(LocalDate date, LocalTime value, EditValue editValue);
    String generateHoursReport(Month month, int year);
}
