package activitytracker;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ActivityId implements Serializable {

    private String desc;
    private Long id;

    public ActivityId() {
    }

    public ActivityId(String desc, Long id) {
        this.desc = desc;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityId that = (ActivityId) o;
        return Objects.equals(desc, that.desc) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desc, id);
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
