package studio.akim.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx")})
public class Restaurant extends AbstractBaseEntity {

    @NotBlank(message = "name must not be empty")
    @Size(min = 2, max = 100, message = "name length must be between 2 and 100")
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("date DESC")
    @JsonManagedReference
    private List<Food> todaysFood;

    public Restaurant() {

    }

    public Restaurant(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public List<Food> getTodaysFood() {
        return todaysFood;
    }

    public void setTodaysFood(List<Food> todaysFood) {
        this.todaysFood = todaysFood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", food='" + todaysFood + '\'' +
                '}';
    }


}
