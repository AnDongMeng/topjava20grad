package studio.akim.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "food", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date", "name"}, name = "menus_unique_restaurant_date_name_idx")})
public class Food extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "name must not be empty")
    @Size(min = 3, max = 100, message = "name length must be between 2 and 100")
    private String name;

    @Column(name = "price")
    @Range(min = 1, max = 1_000_000, message = "price must be between 1 and 1 000 000")
    private int price;

    public Food() {

    }

    public Food(Integer id, Restaurant restaurant, LocalDate date, String name, int price) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String text) {
        this.name = text;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
