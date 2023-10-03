package pl.mazak.worklogappbackend.daily_time;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mazak.worklogappbackend.persistence.DailyTimeRepository;

@Configuration
class DailyTimeConfig {
    @Bean
    DailyTimeService dailyTimeService(DailyTimeRepository dailyTimeRepository) {
        return new DailyTimeServiceImpl(dailyTimeRepository);
    }
}
