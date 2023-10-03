package pl.mazak.worklogappbackend.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyTimeRepository extends CrudRepository<DailyTime, Long> {
    Optional<DailyTime> findDailyTimeByDate(LocalDate date);
    @Query("SELECT d FROM DailyTime d WHERE MONTH(d.date) = ?1 AND YEAR(d.date) = ?2")
    List<DailyTime> findDailyByMonthAndYear(Month month, int year);
}
