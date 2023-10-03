package pl.mazak.worklogappbackend.daily_time;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import pl.mazak.worklogappbackend.DockerDbTestCase;
import pl.mazak.worklogappbackend.persistence.DailyTime;
import pl.mazak.worklogappbackend.persistence.DailyTimeRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.mazak.worklogappbackend.daily_time.EditValue.END_HOUR;
import static pl.mazak.worklogappbackend.daily_time.EditValue.START_HOUR;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DailyTimeServiceTest extends DockerDbTestCase {

    @Autowired
    private DailyTimeService dailyTimeService;
    @Autowired
    private DailyTimeRepository dailyTimeRepository;

    @BeforeEach
    void addExampleData() {
        List<DailyTime> dailyTimes = List.of(
                new DailyTime(LocalDate.of(2023, 12, 4),
                        LocalTime.of(8, 0),
                        LocalTime.of(16, 0)),
                new DailyTime(LocalDate.of(2023, 12, 5),
                        LocalTime.of(9, 30),
                        LocalTime.of(16, 0))
        );
        dailyTimeRepository.saveAll(dailyTimes);
    }

    @AfterEach
    void resetDB() {
        dailyTimeRepository.deleteAll();
    }

    @Test
    void shouldAddTest() {
        Optional<DailyTime> day = dailyTimeService.getDay(LocalDate.of(2023, 12, 4));
        assertThat(day).isPresent();
        DailyTime dailyTime = day.get();
        assertThat(dailyTime.getDate()).isEqualTo(LocalDate.of(2023, 12, 4));
        assertThat(dailyTime.getStartHour()).isEqualTo(LocalTime.of(8, 0));
        assertThat(dailyTime.getEndHour()).isEqualTo(LocalTime.of(16, 0));
    }

    @Test
    void shouldUpdate() {
//        For first, update start day
        dailyTimeService.updateDay(LocalDate.of(2023, 12, 4), LocalTime.of(10, 0), START_HOUR);
        Optional<DailyTime> day1 = dailyTimeService.getDay(LocalDate.of(2023, 12, 4));
        assertThat(day1).isPresent();
        assertThat(day1.get().getStartHour()).isEqualTo(LocalTime.of(10, 0));
        assertThat(day1.get().getEndHour()).isEqualTo(LocalTime.of(16, 0));

//        Then, update end hour
        dailyTimeService.updateDay(LocalDate.of(2023, 12, 4), LocalTime.of(19, 0), END_HOUR);
        Optional<DailyTime> day2 = dailyTimeService.getDay(LocalDate.of(2023, 12, 4));
        assertThat(day2).isPresent();
        assertThat(day2.get().getStartHour()).isEqualTo(LocalTime.of(10, 0));
        assertThat(day2.get().getEndHour()).isEqualTo(LocalTime.of(19, 0));

    }

}