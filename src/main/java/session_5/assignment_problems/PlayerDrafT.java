import java.util.Arrays;

public class PlayerDrafT {

    static class Player implements Comparable<Player> {

        private String name;
        private int matchesPlayed;
        private double battingAverage;
        private boolean injured;

        public Player(String name,
                      int matchesPlayed,
                      double battingAverage,
                      boolean injured) {

            this.name = name;
            this.matchesPlayed = matchesPlayed;
            this.battingAverage = battingAverage;
            this.injured = injured;
        }

        public String getName() {
            return name;
        }

        public int getMatchesPlayed() {
            return matchesPlayed;
        }

        public double getBattingAverage() {
            return battingAverage;
        }

        public boolean isInjured() {
            return injured;
        }

        // Rule 1: Established players
        static boolean isDraftable(int matchesPlayed) {
            return matchesPlayed >= 10;
        }

        // Rule 2: Newer players
        static boolean isDraftable(int matchesPlayed,
                                   boolean injured) {

            return matchesPlayed >= 5 && !injured;
        }

        // Fantasy points
        private double fantasyPoints() {
            return matchesPlayed * battingAverage;
        }

        // Sort by fantasy points in descending order
        @Override
        public int compareTo(Player other) {

            return Double.compare(
                other.fantasyPoints(),
                this.fantasyPoints()
            );
        }
    }

    static String draftAndRank(Player[] players) {

        Player[] draftable = new Player[players.length];

        int count = 0;

        for (Player player : players) {

            if (Player.isDraftable(player.getMatchesPlayed())
                    || Player.isDraftable(
                        player.getMatchesPlayed(),
                        player.isInjured()
                    )) {

                draftable[count] = player;
                count++;
            }
        }

        Player[] finalList =
                Arrays.copyOf(draftable, count);

        Arrays.sort(finalList);

        String result = "";

        for (int i = 0; i < finalList.length; i++) {

            result += (i + 1)
                    + ". "
                    + finalList[i].getName();

            if (i < finalList.length - 1) {
                result += " | ";
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Player[] players = {

            new Player(
                "Virat",
                15,
                48.0,
                false
            ),

            new Player(
                "Rahul",
                7,
                55.0,
                false
            ),

            new Player(
                "Sameer",
                3,
                60.0,
                false
            ),

            new Player(
                "Dev",
                12,
                20.0,
                true
            )
        };

        System.out.println(
            draftAndRank(players)
        );
    }
}