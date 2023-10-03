package pl.mazak.worklogappbackend.daily_time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DailyTimeController {

    private final DailyTimeService dailyTimeService;

    @Autowired
    public DailyTimeController(DailyTimeService dailyTimeService) {
        this.dailyTimeService = dailyTimeService;
    }
}
