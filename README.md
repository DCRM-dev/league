# Getting Started

## Problem Statement and Instructions
The problem statement and instructions are present in the file Problem Statement and Instructions.pdf file in this project.

You can fine this file in the same path location as this readme file. 

## Reference Documentation
### JDK
This is a spring boot application. 
Use SDK java 11.
Use spring-boot-2.7.5

### GRADLE
This application uses gradle as build automation tool.

### BUILD AND RUN
Open a terminal in the project application directory

Run next command to clean and build the application

./gradlew clean build

Run next command to run the application

java -jar \build\libs\league-0.0.1-SNAPSHOT.jar "<PATH_FILE>\input.txt"

You can change the input text file argument according your requirements.

### Application arguments to run in commandline
This application take from command line argument the input text file.
The first argument in the command line is the input text file

Example
java -jar \build\libs\league-0.0.1-SNAPSHOT.jar  "myFile.txt"

args[0] = path and input file name.

Example

java -jar \build\libs\league-0.0.1-SNAPSHOT.jar  "C:\tmp\test\input.txt"


### Message error if input argument is not present
If the input argument is not present an error message error will print:

input file does not exist.  --> Means the input file from argument doesn't exist in path file.

argument is not present.  --> Means that the applications was run without argument

### INPUT FILE FORMAT
The input file format is one match result per line. The format of the file is described in Problem Statement and Instructions.pdf
file in this project.

###Output

The output format is one team per line. The format of the output is described in Problem Statement and Instructions.pdf file in this project.

The application prints in standard output the league rank, one line per team.


##Solution
This application has two approach to solve the problem.

If you want to switch between solution1 and solution2 you can comment/uncomment the lines  37 and 38 in LeagueApplication class.

//List<String> result = leagueService.calculateRank(input);        // Solution1 -> use a TreeMap<String, Integer>

List<String> result = premierLeagueService.calculateRank(input);   // Solution 2 -> Use a List<Team>

If you change it, then follow the Build and Run steps described above.

###Solution 1
It is contained in solution1 package.
LeagueService class contains the List<String> calculateRank(List<Sting> input) method.

This solution use a TreeMap to store the <teamName,teamPoints> and use a customComparator to sort the elements.

Here the result map is sorted by teamPoints

#### Logic in this solution1

This solution will store the result in a Map<String, Integer> map result to store the name and points of the team.

This solution take List<String> as input that contains the result match from the input file.

Stream the list, and for each match split the string by ", ".

Then parse the home and visit team name and score.

Then add the home and visit team to the map list only if the team is not present yet, and is added with '0' points. This validation is done using the containsKey method  in the map result.

Follow the logic: 3 points for the winner, 1 point for draw. These points are added to the team(s) in the map list.

After process all the input list the result map is sorted by points. This is using streams and sorted methods.

Once the result map is sorted, a new output List<String> is generated with the required output format described in Problem Statement & Instructions.pdf

The output list is returned by the calculateRank method.


###Solution 2
It is contained in solution2 package.

LeaguePremierService class contains the List<String> calculateRank(List<Sting> input) method.

This solution use a List< Team > to store the teams.

Team class contains the team definition, with name and points as attributes.

The result list is sorted by teamPoints and then by teamName using stream.

As here I define a Team class. More information about the team can be added in future if is required (like total number of goals scored and goals conceded).

#### Logic in this solution2
This solution will store the result in a List<Team> list.

This solution take List< String > as input that contains the result match from the input file.

Stream the list, and for each match split the string by ", ".

Then parse the home/visit team name and score.

Then add the home/visit team to the result list only if the team is not present yet. It is added with '0' points. This validation is done by search by team name in the result list. 

Follow the logic: 3 points for the winner, 1 point for draw. These points are added to the team(s) in the result list.

After process all the input list the result list is sorted by points and then by names. This is using streams and sorted methods.

Once the result list is sorted, a new output List<String> is generated with the required output format described in Problem Statement & Instructions.pdf

The output list is returned by the calculateRank method.

###Junit
LeagueServiceTest and PremierLeagueServiceTest contains junit test for each implementation.

### Junit from input/output files.

PremierLeagueServiceTest contains the test method calculateRank_input_from_file_test()

In this method the input/output resource files are using for input and expected output for the application.

From test resources folder:

input/inputFile.txt  contains the input text file for the application with the format described in Problem Statement & Instructions.pdf
output/outputFile.txt contains the output text file for the application with the format described in Problem Statement & Instructions.pdf