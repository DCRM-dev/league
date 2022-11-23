package com.example.league;

import com.example.league.solution1.LeagueService;
import com.example.league.solution2.PremierLeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class LeagueApplication implements ApplicationRunner {

    @Autowired
    private PremierLeagueService premierLeagueService;
    @Autowired
    private LeagueService leagueService;

    public static void main(String[] args) {
        SpringApplication.run(LeagueApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (validateInputArguments(args.getNonOptionArgs())) {

            try {
                List<String> input = readInputFile(args.getNonOptionArgs().get(0));

//            List<String> result = leagueService.calculateRank(input);        // Solution1 -> use a TreeMap<String, Integer>
                List<String> result = premierLeagueService.calculateRank(input);   // Solution 2 -> Use a List<Team>

                for (int i = 0; i < result.size(); i++) {
                    System.out.println(result.get(i));
                }

            } catch (IOException e) {
                System.out.println("Error reading input file. " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private boolean validateInputArguments(List<String> args) {
        boolean isValid = true;
        //First validate the input args and length.
        if (args != null && args.size() >= 1) {
            //Here validate the args[0] = input File
            File inputFile = new File(args.get(0));
            if (!inputFile.exists()) {
                isValid = false;
                System.out.println("input file does not exist: " + inputFile);
            }
        } else {
            isValid = false;
            System.out.println("One argument is required as input file name");
        }

        return isValid;
    }

    private List<String> readInputFile(String filePath) throws IOException {

        List<String> allLines = Files.readAllLines(Paths.get(filePath));
        return allLines;

    }
}
