package com.example.league.solution1;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
@Service
public class LeagueService {



    public LeagueService() {

    }

    public List<String> calculateRank(List<String> input) {

        Map<String, Integer> resultBoard = new TreeMap<>(new CustomComparator());  //Use custom comparator in case an upper/down case-sensitive alphabetic order is not required

        for (int i = 0; i < input.size(); i++) {
            String[] teams = input.get(i).split(", ");

            String homeTeamName = getTeamName(teams[0]);
            int homeScore = getTeamScore(teams[0]);

            String visitTeamName = getTeamName(teams[1]);
            int visitScore = getTeamScore(teams[1]);

            addTeamToBoard(resultBoard, homeTeamName);
            addTeamToBoard(resultBoard, visitTeamName);

            if (homeScore > visitScore) {
                addScore(resultBoard, homeTeamName, 3);
            } else if (homeScore < visitScore) {
                addScore(resultBoard, visitTeamName, 3);
            } else {
                addScore(resultBoard, homeTeamName, 1);
                addScore(resultBoard, visitTeamName, 1);
            }
        }
        resultBoard
                = resultBoard.entrySet()
                .stream()
//                .peek(System.out::println)
                .sorted((i1, i2) -> i2.getValue().compareTo(i1.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        List<String> outputList = new ArrayList<>();

        Iterator<Map.Entry<String, Integer>> itr = resultBoard.entrySet().iterator();

        int i = 1;
        int previousPoints = -1;
        int rank = 1;
        while (itr.hasNext()) {

            Map.Entry<String, Integer> entry = itr.next();

            if (previousPoints != entry.getValue()) {
                rank = i;
            }
            i++;
            String line = String.format("%s. %s, %s %s", rank, entry.getKey(), entry.getValue(), getSuffixPoints(entry.getValue()));
            previousPoints = entry.getValue();
            outputList.add(line);
        }

        return outputList;
    }

    private void addTeamToBoard(Map<String, Integer> resultBoard, String teamName) {
        if (!resultBoard.containsKey(teamName)) {
            resultBoard.put(teamName, 0);
        }
    }

    private void addScore(Map<String, Integer> resultBoard, String teamName, int pointsToAdd) {

        Integer points = resultBoard.get(teamName);
        resultBoard.put(teamName, points + pointsToAdd);

    }

    private String getTeamName(String input) {
        return input.substring(0, input.lastIndexOf(" "));
    }

    private int getTeamScore(String input) {
        return Integer.parseInt(input.substring(input.lastIndexOf(" ") + 1));
    }

    private String getSuffixPoints(Integer value) {
        if (value == 1)
            return "pt";
        else
            return "pts";
    }


}
