package studio.akim.restaurantvoting.TestData;

import studio.akim.restaurantvoting.TestMatcher;
import studio.akim.restaurantvoting.model.AbstractBaseEntity;
import studio.akim.restaurantvoting.model.Food;
import studio.akim.restaurantvoting.util.DateTimeUtil;

import java.time.LocalDate;

import static studio.akim.restaurantvoting.TestData.RestaurantTestData.*;

public class FoodTestData {
    public static TestMatcher<Food> FOOD_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Food.class);
    public static TestMatcher<Food> FOOD_MATCHER_WITHOUT_RESTAURANT = TestMatcher.usingFieldsWithIgnoringAssertions(Food.class, "restaurant");

    public static final int RSTRNT1_OLD_FOOD_ID1 = AbstractBaseEntity.START_SEQ + 23;
    public static final int RSTRNT1_OLD_FOOD_ID2 = AbstractBaseEntity.START_SEQ + 24;

    public static final int RSTRNT1_TODAY_FOOD_ID1 = AbstractBaseEntity.START_SEQ + 45;
    public static final int RSTRNT1_TODAY_FOOD_ID2 = AbstractBaseEntity.START_SEQ + 46;
    public static final int RSTRNT2_TODAY_FOOD_ID1 = AbstractBaseEntity.START_SEQ + 47;
    public static final int RSTRNT2_TODAY_FOOD_ID2 = AbstractBaseEntity.START_SEQ + 48;
    public static final int RSTRNT3_TODAY_FOOD_ID1 = AbstractBaseEntity.START_SEQ + 49;
    public static final int RSTRNT3_TODAY_FOOD_ID2 = AbstractBaseEntity.START_SEQ + 50;
    public static final int RSTRNT4_TODAY_FOOD_ID1 = AbstractBaseEntity.START_SEQ + 51;
    public static final int RSTRNT4_TODAY_FOOD_ID2 = AbstractBaseEntity.START_SEQ + 52;
    public static final int RSTRNT5_TODAY_FOOD_ID1 = AbstractBaseEntity.START_SEQ + 53;
    public static final int RSTRNT5_TODAY_FOOD_ID2 = AbstractBaseEntity.START_SEQ + 54;
    public static final int RSTRNT6_TODAY_FOOD_ID1 = AbstractBaseEntity.START_SEQ + 55;
    public static final int RSTRNT6_TODAY_FOOD_ID2 = AbstractBaseEntity.START_SEQ + 56;
    public static final int RSTRNT6_TODAY_FOOD_ID3 = AbstractBaseEntity.START_SEQ + 57;
    public static final int RSTRNT6_TODAY_FOOD_ID4 = AbstractBaseEntity.START_SEQ + 58;
    public static final int RSTRNT6_TODAY_FOOD_ID5 = AbstractBaseEntity.START_SEQ + 59;
    public static final int RSTRNT7_TODAY_FOOD_ID1 = AbstractBaseEntity.START_SEQ + 60;
    public static final int RSTRNT7_TODAY_FOOD_ID2 = AbstractBaseEntity.START_SEQ + 61;

    public static final Food RSTRNT1_OLD_FOOD1 = new Food(RSTRNT1_OLD_FOOD_ID1, RSTRNT1, LocalDate.of(2020, 01, 30), "pasta", 300);
    public static final Food RSTRNT1_OLD_FOOD2 = new Food(RSTRNT1_OLD_FOOD_ID2, RSTRNT2, LocalDate.of(2020, 01, 30), "antipasto", 1400);

    public static final Food RSTRNT1_TODAY_FOOD1 = new Food(RSTRNT1_TODAY_FOOD_ID1, RSTRNT1, LocalDate.now(), "chef dish", 900);
    public static final Food RSTRNT1_TODAY_FOOD2 = new Food(RSTRNT1_TODAY_FOOD_ID2, RSTRNT2, LocalDate.now(), "potato", 6600);
    public static final Food RSTRNT2_TODAY_FOOD1 = new Food(RSTRNT2_TODAY_FOOD_ID1, RSTRNT1, LocalDate.now(), "fries", 700);
    public static final Food RSTRNT2_TODAY_FOOD2 = new Food(RSTRNT2_TODAY_FOOD_ID2, RSTRNT2, LocalDate.now(), "crisps", 400);
    public static final Food RSTRNT3_TODAY_FOOD1 = new Food(RSTRNT3_TODAY_FOOD_ID1, RSTRNT1, LocalDate.now(), "bread", 300);
    public static final Food RSTRNT3_TODAY_FOOD2 = new Food(RSTRNT3_TODAY_FOOD_ID1, RSTRNT2, LocalDate.now(), "cake", 300);
    public static final Food RSTRNT4_TODAY_FOOD1 = new Food(RSTRNT4_TODAY_FOOD_ID1, RSTRNT1, LocalDate.now(), "vine", 200);
    public static final Food RSTRNT4_TODAY_FOOD2 = new Food(RSTRNT4_TODAY_FOOD_ID1, RSTRNT2, LocalDate.now(), "watter", 500);
    public static final Food RSTRNT5_TODAY_FOOD1 = new Food(RSTRNT5_TODAY_FOOD_ID1, RSTRNT1, LocalDate.now(), "latte", 100);
    public static final Food RSTRNT5_TODAY_FOOD2 = new Food(RSTRNT5_TODAY_FOOD_ID1, RSTRNT2, LocalDate.now(), "pancakes", 600);
    public static final Food RSTRNT6_TODAY_FOOD1 = new Food(RSTRNT6_TODAY_FOOD_ID1, RSTRNT1, LocalDate.now(), "sandwich", 1200);
    public static final Food RSTRNT6_TODAY_FOOD2 = new Food(RSTRNT6_TODAY_FOOD_ID1, RSTRNT2, LocalDate.now(), "pasta", 1400);
    public static final Food RSTRNT6_TODAY_FOOD3 = new Food(RSTRNT6_TODAY_FOOD_ID1, RSTRNT3, LocalDate.now(), "antipasto", 1400);
    public static final Food RSTRNT6_TODAY_FOOD4 = new Food(RSTRNT6_TODAY_FOOD_ID1, RSTRNT4, LocalDate.now(), "beef", 400);
    public static final Food RSTRNT6_TODAY_FOOD5 = new Food(RSTRNT6_TODAY_FOOD_ID1, RSTRNT5, LocalDate.now(), "sausage", 80000);
    public static final Food RSTRNT7_TODAY_FOOD1 = new Food(RSTRNT7_TODAY_FOOD_ID1, RSTRNT1, LocalDate.now(), "soup carrot", 2300);
    public static final Food RSTRNT7_TODAY_FOOD2 = new Food(RSTRNT7_TODAY_FOOD_ID1, RSTRNT2, LocalDate.now(), "ne sup", 1400);


    public static Food getNew() {
        return new Food(null, RSTRNT1, DateTimeUtil.currentDate(), "new", 100);
    }
}
