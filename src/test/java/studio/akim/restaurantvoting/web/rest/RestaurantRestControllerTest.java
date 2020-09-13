package studio.akim.restaurantvoting.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import studio.akim.restaurantvoting.repository.VoteRepository;
import studio.akim.restaurantvoting.util.DateTimeUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static studio.akim.restaurantvoting.TestData.RestaurantTestData.*;
import static studio.akim.restaurantvoting.TestData.UserTestData.*;
import static studio.akim.restaurantvoting.TestData.VoteTestData.*;
import static studio.akim.restaurantvoting.TestUtil.userHttpBasic;

class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RSTRNT1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RSTRNT1));
    }

    @Test
    void getWrong() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + WRONG_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    void getWrongWithMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + WRONG_ID + "/with-menu")
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANTS));
    }


    @Test
    void getWithMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RSTRNT1_ID + "/with-menu")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_WITH_FOOD_MATCHER.contentJson(RSTRNT1));
    }

    @Test
    void getAllWithMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "with-menu")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_WITH_FOOD_MATCHER.contentJson(RESTAURANTS));
    }

    @Test
    void vote() throws Exception {
        assertThat(voteRepository.getVoteByUserAndDate(USER_ID, DateTimeUtil.currentDate())).isNull();
        perform(MockMvcRequestBuilders.post(REST_URL + RSTRNT4_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent())
                .andDo(print());
        assertThat(voteRepository.getVoteByUserAndDate(USER_ID, DateTimeUtil.currentDate())).isEqualToIgnoringGivenFields(VOTE_TODAY, "id");
    }


    @Test
    void voteAgain() throws Exception {
        DateTimeUtil.setMockDateTime(testDateTimeBefore11);
        DateTimeUtil.setUseRealTime(false);
        assertThat(voteRepository.getVoteByUserAndDate(USER_ID, DateTimeUtil.currentDate())).isEqualToIgnoringGivenFields(VOTE_BASE, "id");
        perform(MockMvcRequestBuilders.post(REST_URL + RSTRNT4_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent())
                .andDo(print());
        assertThat(voteRepository.getVoteByUserAndDate(USER_ID, DateTimeUtil.currentDate())).isEqualToIgnoringGivenFields(VOTE1, "id");
    }

    @Test
    void voteAfter11() throws Exception {
        DateTimeUtil.setMockDateTime(testDateTimeAfter11);
        DateTimeUtil.setUseRealTime(false);
        assertThat(voteRepository.getVoteByUserAndDate(USER_ID, DateTimeUtil.currentDate())).isEqualToIgnoringGivenFields(VOTE_BASE, "id");
        perform(MockMvcRequestBuilders.post(REST_URL + RSTRNT4_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNotAcceptable());
        VOTE_MATCHER.assertMatch(voteRepository.getVoteByUserAndDate(USER_ID, DateTimeUtil.currentDate()), VOTE_BASE);
    }

    @Test
    void firstVoteAfter11() throws Exception {
        DateTimeUtil.setMockDateTime(testDateTimeAfter11);
        DateTimeUtil.setUseRealTime(false);
        assertThat(voteRepository.getVoteByUserAndDate(USER_ID1, DateTimeUtil.currentDate())).isNull();
        perform(MockMvcRequestBuilders.post(REST_URL + RSTRNT4_ID + "/vote")
                .with(userHttpBasic(USER1)))
                .andExpect(status().isNoContent())
                .andDo(print());
        VOTE_MATCHER.assertMatch(voteRepository.getVoteByUserAndDate(USER_ID1, DateTimeUtil.currentDate()), VOTE2);
    }
}