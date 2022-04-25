package activitytracker;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "activity id generator")
    @TableGenerator(name = "activity id generator", table = "act_id_gen", pkColumnName = "id_gen", valueColumnName = "id_val")
    private Long id;

    @Column(name = "activity_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name="labels", joinColumns = @JoinColumn(name = "id_val"))
    @Column(name = "label")
    private List<String> labels;

    public Activity() {
    }

    public Activity(ActivityType type, String description, LocalDateTime startTime) {
        this.type = type;
        this.description = description;
        this.startTime = startTime;
    }

    @PreUpdate
    public void setUpdatedAtToCurrentTime() {
        updatedAt = LocalDateTime.now();

    }

    @PrePersist
    public void setCreatedAtToCurrentTime() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
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


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
