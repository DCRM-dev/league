package com.example.league.solution2;

public class Team{
//        implements Comparator<Team> {

    private String name;
    private int points;


    public Team(String name, int points) {
        this.name = name;
        this.points = points;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

//    @Override
//    public int compare(Team o1, Team o2) {
//        if(o1.getPoints() == o2.getPoints()){
//            return o1.getName().compareToIgnoreCase(o2.getName());
//        }
//        return 0;
//    }
}
