package pl.mazak.worklogappbackend.daily_time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mazak.worklogappbackend.persistence.DailyTime;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping(path = "/dailyTime", produces = MediaType.APPLICATION_JSON_VALUE)
class DailyTimeController {

    private final DailyTimeService dailyTimeService;

    @Autowired
    public DailyTimeController(DailyTimeService dailyTimeService) {
        this.dailyTimeService = dailyTimeService;
    }

    @GetMapping("/get")
    ResponseEntity<DailyTimeDTO> getDay(@RequestParam LocalDate date) {
        return dailyTimeService.getDay(date)
                .map(DailyTimeDTO::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    ResponseEntity<Void> addDay(@RequestBody AddDayRequest addDayRequest) {
        DailyTime dailyTime = dailyTimeService.addDay(addDayRequest.date(), addDayRequest.start(), addDayRequest.end());
        return ResponseEntity
                .created(URI.create("http://localhost:8080/dailyTime/get?date=" + dailyTime.getDate().toString()))
                .build();
    }

    record AddDayRequest(LocalDate date, LocalTime start, LocalTime end) {}


}
