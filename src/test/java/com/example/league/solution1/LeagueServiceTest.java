package com.example.league.solution1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LeagueServiceTest {

    @Autowired
    private LeagueService leagueService;

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void calculateRank_test() throws Exception {

        List<String> input = Arrays.asList(
                "Lions 3, Snakes 3",
                "Tarantulas 1, FC Awesome 0",
                "Lions 1, FC Awesome 1",
                "Tarantulas 3, Snakes 1",
                "Lions 4, Grouches 0");

        List<String> expectedOutput = Arrays.asList(
                "1. Tarantulas, 6 pts",
                "2. Lions, 5 pts",
                "3. FC Awesome, 1 pt",
                "3. Snakes, 1 pt",
                "5. Grouches, 0 pts"
        );

        List<String> result = leagueService.calculateRank(input);

        Assert.assertEquals(expectedOutput, result);

    }


    @Test
    public void calculateRank_test_only_draws() throws Exception {

        List<String> input = Arrays.asList(
                "Lions 3, Snakes 3",
                "Tarantulas 1, FC Awesome 1",
                "Lions 1, FC Awesome 1",
                "Tarantulas 1, Snakes 1",
                "Lions 0, Grouches 0");

        List<String> expectedOutput = Arrays.asList(
                "1. Lions, 3 pts",
                "2. FC Awesome, 2 pts",
                "2. Snakes, 2 pts",
                "2. Tarantulas, 2 pts",
                "5. Grouches, 1 pt"
        );


        List<String> result = leagueService.calculateRank(input);

        Assert.assertEquals(expectedOutput, result);

    }
}