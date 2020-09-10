package studio.akim.restaurantvoting.TestData;

import studio.akim.restaurantvoting.TestMatcher;
import studio.akim.restaurantvoting.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static studio.akim.restaurantvoting.TestData.RestaurantTestData.RSTRNT1;
import static studio.akim.restaurantvoting.TestData.RestaurantTestData.RSTRNT4;

public class VoteTestData {
    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Vote.class, "id");

    public static final LocalDateTime testDateTimeBefore11 = LocalDateTime.of(2020, 01, 30, 10, 00);
    public static final LocalDateTime testDateTimeAfter11 = LocalDateTime.of(2020, 01, 30, 12, 00);

    public static final Vote VOTE1 = new Vote(null, UserTestData.USER, RSTRNT4, testDateTimeBefore11.toLocalDate());
    public static final Vote VOTE2 = new Vote(null, UserTestData.USER1, RSTRNT4, testDateTimeBefore11.toLocalDate());
    public static final Vote VOTE_TODAY = new Vote(null, UserTestData.USER, RSTRNT4, LocalDate.now());
    public static final Vote VOTE_BASE = new Vote(null, UserTestData.USER, RSTRNT1, testDateTimeBefore11.toLocalDate());

}
