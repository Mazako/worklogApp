package pl.mazak.worklogappbackend.daily_time;

import jakarta.transaction.Transactional;
import pl.mazak.worklogappbackend.persistence.DailyTime;
import pl.mazak.worklogappbackend.persistence.DailyTimeRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

class DailyTimeServiceImpl implements DailyTimeService {
    private final DailyTimeRepository dailyTimeRepository;

    public DailyTimeServiceImpl(DailyTimeRepository dailyTimeRepository) {
        this.dailyTimeRepository = dailyTimeRepository;
    }

    @Override
    public DailyTime addDay(LocalDate date, LocalTime start, LocalTime end) {
        var dailyTime = new DailyTime(date, start, end);
        return dailyTimeRepository.save(dailyTime);
    }

    @Override
    public Optional<DailyTime> getDay(LocalDate date) {
        return dailyTimeRepository.findDailyTimeByDate(date);
    }

    @Override
    public List<DailyTime> getDaysByMonth(Month month, int year) {
        return dailyTimeRepository.findDailyByMonthAndYear(month, year);
    }

    @Override
    @Transactional
    public Optional<DailyTime> updateDay(LocalDate date, LocalTime value, EditValue editValue) {
        return dailyTimeRepository.findDailyTimeByDate(date)
                .map(day -> {
                    if (Objects.requireNonNull(editValue) == EditValue.END_HOUR) {
                        day.setEndHour(value);
                    } else if (editValue == EditValue.START_HOUR) {
                        day.setStartHour(value);
                    }
                    return day;
                });
    }

    @Override
    public String generateHoursReport(Month month, int year) {
        AtomicReference<Double> totalHours = new AtomicReference<>();
        return getDaysByMonth(month, year).stream()
                .reduce(new StringBuilder("Data;czas;godzin\n"), (current, day) -> {
                    var duration = Duration.between(day.getStartHour(), day.getEndHour());
                    current.append(String.format("%s;%s-%s;%d%n",
                            day.getDate(),
                            day.getStartHour(),
                            day.getEndHour(),
                            duration.toHours()));
                    totalHours.set(totalHours.get() + duration.toHours());
                    return current;
                }, StringBuilder::append)
                .toString();

    }
}
