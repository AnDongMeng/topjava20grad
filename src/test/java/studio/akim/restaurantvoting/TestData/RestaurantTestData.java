package studio.akim.restaurantvoting.TestData;

import studio.akim.restaurantvoting.TestMatcher;
import studio.akim.restaurantvoting.model.AbstractBaseEntity;
import studio.akim.restaurantvoting.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static studio.akim.restaurantvoting.TestData.FoodTestData.*;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Restaurant.class, "todaysFood");
    public static TestMatcher<Restaurant> RESTAURANT_WITH_FOOD_MATCHER = TestMatcher.usingAssertions(Restaurant.class,
            (a, e) -> assertThat(a).usingRecursiveComparison()
                    .ignoringFields("todaysFood.restaurant").ignoringAllOverriddenEquals().isEqualTo(e),
            (a, e) -> {
                assertThat(a).usingElementComparatorIgnoringFields("todaysFood").isEqualTo(e);
//                throw new UnsupportedOperationException();
            });

    public static final int WRONG_ID = 999;

    public static final int RSTRNT1_ID = AbstractBaseEntity.START_SEQ + 12;
    public static final int RSTRNT2_ID = AbstractBaseEntity.START_SEQ + 13;
    public static final int RSTRNT3_ID = AbstractBaseEntity.START_SEQ + 14;
    public static final int RSTRNT4_ID = AbstractBaseEntity.START_SEQ + 15;
    public static final int RSTRNT5_ID = AbstractBaseEntity.START_SEQ + 16;
    public static final int RSTRNT6_ID = AbstractBaseEntity.START_SEQ + 17;
    public static final int RSTRNT7_ID = AbstractBaseEntity.START_SEQ + 18;
    public static final int RSTRNT8_ID = AbstractBaseEntity.START_SEQ + 19;
    public static final int RSTRNT9_ID = AbstractBaseEntity.START_SEQ + 20;
    public static final int RSTRNT10_ID = AbstractBaseEntity.START_SEQ + 21;
    public static final int RSTRNT11_ID = AbstractBaseEntity.START_SEQ + 22;

    public static final Restaurant RSTRNT1 = new Restaurant(RSTRNT1_ID, "restorano");
    public static final Restaurant RSTRNT2 = new Restaurant(RSTRNT2_ID, "caffeeterio");
    public static final Restaurant RSTRNT3 = new Restaurant(RSTRNT3_ID, "romano");
    public static final Restaurant RSTRNT4 = new Restaurant(RSTRNT4_ID, "petros");
    public static final Restaurant RSTRNT5 = new Restaurant(RSTRNT5_ID, "ivano");
    public static final Restaurant RSTRNT6 = new Restaurant(RSTRNT6_ID, "divano");
    public static final Restaurant RSTRNT7 = new Restaurant(RSTRNT7_ID, "lomano");
    public static final Restaurant RSTRNT8 = new Restaurant(RSTRNT8_ID, "grigorio");
    public static final Restaurant RSTRNT9 = new Restaurant(RSTRNT9_ID, "alehandro");
    public static final Restaurant RSTRNT10 = new Restaurant(RSTRNT10_ID, "condrato");
    public static final Restaurant RSTRNT11 = new Restaurant(RSTRNT11_ID, "borshtchi u ruslana");

    public static final List<Restaurant> RESTAURANTS = List.of(RSTRNT1, RSTRNT2, RSTRNT3, RSTRNT4, RSTRNT5, RSTRNT6, RSTRNT7, RSTRNT8, RSTRNT9, RSTRNT10, RSTRNT11);
//    public static final List<Restaurant> RESTAURANTS = List.of(RSTRNT11,RSTRNT10,RSTRNT9,RSTRNT8,RSTRNT7,RSTRNT6,RSTRNT5,RSTRNT4,RSTRNT3,RSTRNT2,RSTRNT1);

    static {
        RSTRNT1.setTodaysFood(List.of(RSTRNT1_TODAY_FOOD1, RSTRNT1_TODAY_FOOD2));
        RSTRNT2.setTodaysFood(List.of(RSTRNT2_TODAY_FOOD1, RSTRNT2_TODAY_FOOD2));
        RSTRNT3.setTodaysFood(List.of(RSTRNT3_TODAY_FOOD1, RSTRNT3_TODAY_FOOD2));
        RSTRNT4.setTodaysFood(List.of(RSTRNT4_TODAY_FOOD1, RSTRNT4_TODAY_FOOD2));
        RSTRNT5.setTodaysFood(List.of(RSTRNT5_TODAY_FOOD1, RSTRNT5_TODAY_FOOD2));
        RSTRNT6.setTodaysFood(List.of(RSTRNT6_TODAY_FOOD1, RSTRNT6_TODAY_FOOD2, RSTRNT6_TODAY_FOOD3, RSTRNT6_TODAY_FOOD4, RSTRNT6_TODAY_FOOD5));
        RSTRNT7.setTodaysFood(List.of(RSTRNT7_TODAY_FOOD1, RSTRNT7_TODAY_FOOD2));
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "new");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RSTRNT1_ID, "Edited");
    }
}
