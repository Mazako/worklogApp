package pl.mazak.worklogappbackend.daily_time;

import org.springframework.context.annotation.Configuration;
import pl.mazak.worklogappbackend.persistence.DailyTimeRepository;

@Configuration
class DailyTimeConfig {
    DailyTimeService dailyTimeService(DailyTimeRepository dailyTimeRepository) {
        return new DailyTimeServiceImpl(dailyTimeRepository);
    }
}
