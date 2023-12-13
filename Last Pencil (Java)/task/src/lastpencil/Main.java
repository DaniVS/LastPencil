package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final String[] playerNames = new String[] {"Johnny", "Aldus"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int pencils = getInitialPencils(scanner);

        Player[] players = getPlayers();

        Player firstPlayer = getUserChoice(players, scanner);
        Player theBot = players[1];

        printPencils(pencils);

        Player actualPlayer = firstPlayer;
        while (pencils > 0) {
            System.out.println(actualPlayer.getName() + "'s turn!");
            int toRemove = getPencilsToRemove(actualPlayer, pencils, scanner);

            pencils -= toRemove;

            printPencils(pencils);

            if (pencils > 0){
                actualPlayer = togglePlayers(actualPlayer, players);
            }
        }

        printWinner(actualPlayer, players);

    }

    private static Player getUserChoice(Player[] players, Scanner scanner) {
        String name1 = players[0].getName();
        String name2 = players[1].getName();
        System.out.println("Who will be the first (" + name1 + ", " + name2 + "):");

        String firstPlayer = scanner.nextLine().trim();

        while (!firstPlayer.equals(name1) && !firstPlayer.equals(name2)) {
            System.out.println("Choose between " + name1 + " and " + name2);
            firstPlayer = scanner.nextLine().trim();
        }

        if (firstPlayer.equals(name1)){
            return players[0];
        } else {
            return players[1];
        }
    }

    private static void printWinner(Player actual, Player[] players) {
        Player winner;

        if (actual.isTheBot()){
            winner = players[0];
        } else {
            winner = players[1];
        }

        System.out.println(winner.getName() + " won!");
    }

    private static int getPencilsToRemove(Player actualPlayer, int pencils, Scanner scanner) {
        int toRemove = 0;

        if (actualPlayer.isTheBot()) {
            toRemove = applyBotStrategy(pencils);
            System.out.println(toRemove);
        } else {
            toRemove = checkHumanMove(pencils, scanner, toRemove);
        }

        return toRemove;
    }

    private static int checkHumanMove(int pencils, Scanner scanner, int toRemove) {
        boolean inputIsCorrect = false;
        while (!inputIsCorrect) {
            try {
                toRemove = Integer.parseInt(scanner.nextLine().trim());

                if (toRemove < 1 || toRemove > 3) {
                    System.out.println("Possible values: '1', '2' or '3'");
                    continue;
                }

                if (toRemove > pencils) {
                    System.out.println("Too many pencils were taken");
                    continue;
                }

                inputIsCorrect = true;

            } catch (NumberFormatException ex){
                System.out.println("Possible values: '1', '2' or '3'");
            }
        }
        return toRemove;
    }

    private static Player[] getPlayers() {
        Player human = new Player(playerNames[0], false);
        Player bot = new Player(playerNames[1], true);

        return new Player[] {human, bot};
    }

    private static int getInitialPencils(Scanner scanner) {
        System.out.println("How many pencils would you like to use:");

        int pencils = 0;

        boolean correctInput = false;
        while (!correctInput) {
            try {
                pencils = Integer.parseInt(scanner.nextLine().trim());

                pencilsShouldBePositive(pencils);
                cannotHaveZeroPencils(pencils);

                correctInput = true;
            } catch (NumberFormatException ex) {
                System.out.println("The number of pencils should be numeric");
            } catch (ZeroPencilsException zpe) {
                System.out.println("The number of pencils should be positive");
            }
        }

        return pencils;
    }

    private static void printPencils(int pencils) {
        for (int i = 0; i < pencils; i++) {
            System.out.print("|");
        }

        System.out.println("");
    }

    private static Player togglePlayers(Player actual, Player[] players) {
        if (actual.isTheBot()){
            return players[0];
        } else {
            return players[1];
        }
    }

    private static void pencilsShouldBePositive(int pencils) {
        if (pencils < 0) {
            throw new NumberFormatException();
        }
    }

    private static void cannotHaveZeroPencils(int pencils) throws ZeroPencilsException {
        if (pencils == 0) {
            throw new ZeroPencilsException();
        }
    }

    /**
     * Bot in losing position when:
     * - 5,9,13,17.... (pencils % 4) == 1 (take any)
     * - 1 (take 1)
     *
     * Bot in winning position
     * - 4,8,12,16 (pencils % 4) == 0 (take 3)
     * - 3,7,11,15 (pencils % 4) == 3 (take 2)
     * - 2,6,10,14 (pencils % 4) == 2 (take 1)
     * */

    private static int applyBotStrategy(int pencils) {
        int toRemove = 0;

        if (pencils == 1){
            toRemove = 1;
        } else {
            switch (pencils % 4){
                case 0:
                    toRemove = 3;
                    break;
                case 1:
                    toRemove = new Random().nextInt(3) + 1;
                    break;
                case 2:
                    toRemove = 1;
                    break;
                case 3:
                    toRemove = 2;
                    break;
            }
        }

        return toRemove;
    }
}

