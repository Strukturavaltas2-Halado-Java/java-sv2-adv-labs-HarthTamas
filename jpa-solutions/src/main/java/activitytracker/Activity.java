package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="type")
    ActivityType type;

    @Column(name="description")
    String description;

    @Column(name="start_time")
    LocalDateTime startTime;


    public Activity() {
    }

    public Activity(ActivityType type, String description, LocalDateTime startTime) {
        this.type = type;
        this.description = description;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
