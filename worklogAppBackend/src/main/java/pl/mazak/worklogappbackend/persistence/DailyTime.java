package pl.mazak.worklogappbackend.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "daily_time")
public class DailyTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false, unique = true)
    private LocalDate date;

    @NotNull
    @Column(name = "start_hour", nullable = false)
    private LocalTime startHour;

    @NotNull
    @Column(name = "end_hour", nullable = false)
    private LocalTime endHour;

    public DailyTime() {

    }

    public DailyTime(LocalDate date, LocalTime startHour, LocalTime endHour) {
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyTime dailyTime = (DailyTime) o;
        return Objects.equals(id, dailyTime.id) && Objects.equals(date, dailyTime.date) && Objects.equals(startHour, dailyTime.startHour) && Objects.equals(endHour, dailyTime.endHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, startHour, endHour);
    }

    @Override
    public String toString() {
        return "DailyTime{" +
                "id=" + id +
                ", date=" + date +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                '}';
    }
}