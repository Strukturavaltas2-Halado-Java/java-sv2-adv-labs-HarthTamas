package activitytracker;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "track_points")
public class TrackPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate time;

    private double longitude;

    private double latitude;

    @ManyToOne
    @JoinColumn(name = "act_id")
    Activity activity;

    public TrackPoint() {
    }

    public TrackPoint(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public TrackPoint(LocalDate time, double longitude, double latitude) {
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "TrackPoint{" +
                "id=" + id +
                ", time=" + time +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
