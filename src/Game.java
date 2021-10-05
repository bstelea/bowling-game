import java.util.*;

public class Game {
    List<Player> players;
    int numberOfRounds;

    public Game() {
        this.players = new ArrayList<>();
        this.numberOfRounds = 10;
    }

    public Game(int numberOfRounds) {
        this.players = new ArrayList<>();
        this.numberOfRounds = numberOfRounds;
    }

    public void run() {
        // *** Setup ***
        System.out.println("Starting the Bowling Game...");
        System.out.println("Please enter the number of players: ");
        Scanner keyboard = new Scanner(System.in);
        String n = keyboard.nextLine();
        int numberOfPlayers = Integer.valueOf(n);
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("If player " + (i + 1) + " is human type y. Otherwise press Enter...");
            String isHuman = keyboard.nextLine();
            if (isHuman.equals("y")) {
                System.out.println("Player " + (i + 1) + " is Human");
                players.add(new Player(i + 1, true));
            } else {
                System.out.println("Player " + (i + 1) + " is AI");
                players.add(new Player(i + 1, false));
            }
        }
        Random random = new Random();

        // Iterate through rounds
        for (int i = 0; i < numberOfRounds; i++) {
            System.out.println("**** Round is now " + (i + 1) + " ****");
            for (Player player : players) {
                int target = 10;
                System.out.println("Now throwing Player " + player.getId() + "...");
                if (player.isHuman()) {
                    // Ask for input
                    System.out.println("Press enter to throw...");
                    String key = keyboard.nextLine();
                    int totalRoundScore = 0;
                    int numberOfPins = random.nextInt(target + 1);
                    totalRoundScore += numberOfPins;
                    player.addPoint(numberOfPins);
                    System.out.println("Player " + player.getId() + " took down: " + numberOfPins + " pins...");
                    if (player.getSpareRecord() > 0) {
                        player.addScore(numberOfPins);
                        player.decrementSpareRecord();
                    }
                    if (numberOfPins == target) {
                        System.out.println("STRIKE!!!");
                        player.incrementStrikeRecord();
                        continue;
                    } else {
                        target -= numberOfPins;
                        System.out.println("Press enter to throw again...");
                        key = keyboard.nextLine();
                        numberOfPins = random.nextInt(target + 1);
                        totalRoundScore += numberOfPins;
                        if (player.getStrikeRecord() > 1) {
                            player.addScore(totalRoundScore);
                            player.decrementStrikeRecord();
                        }
                        player.addPoint(numberOfPins);
                        System.out.println("Player " + player.getId() + " took down: " + numberOfPins + " pins...");
                        if (numberOfPins == target) {
                            System.out.println("SPARE!!!");
                            player.incrementSpareRecord();
                            continue;
                        }
                    }
                } else {
                    int numberOfPins = random.nextInt(target + 1);
                    player.addPoint(numberOfPins);
                    System.out.println("Player " + player.getId() + " took down: " + numberOfPins + " pins...");
                    if (numberOfPins == 10) {
                        System.out.println("STRIKE!!!");
                        continue;
                    } else {
                        target -= numberOfPins;
                        numberOfPins = random.nextInt(target + 1);
                        player.addPoint(numberOfPins);
                        System.out.println("Player " + player.getId() + " took down: " + numberOfPins + " pins...");
                        if (numberOfPins == target) {
                            System.out.println("SPARE!!!");
                        }
                    }
                }
            }
        }
        System.out.println("Final leaderboard: ");
        Collections.sort(players);
        Collections.reverse(players);
        int place = 1;
        for (Player p : players) {
            System.out.println(place + ". Player " + p.getId() + " got a score of: " + p.getScore());
            place++;
        }
        System.out.println("**** The winner is: Player " + players.get(0).getId() + " ****");
        System.out.println("Game ended...");
    }
}
