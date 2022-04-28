package movie;

import javax.persistence.Embeddable;

@Embeddable
public class Rating {

    private double rating;

    private String username;

    public Rating(double rating, String username) {
        this.rating = rating;
        this.username = username;
    }

    public Rating() {
    }

    @Override
    public String toString() {
        return "Rating{" +
                "rating=" + rating +
                ", username='" + username + '\'' +
                '}';
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
