import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputNumbers = scanner.nextLine().split(" ");
        int k = Integer.valueOf(inputNumbers[0]);
        int n = Integer.valueOf(inputNumbers[1]);
        double m = Double.valueOf(inputNumbers[2]);

        boolean stop = false;

        while (!stop) {
            Random random = new Random(k);

            // get N random gaussian numbers
            boolean allGaussianMatchesCondition = true;
            for (int i = 0; i < n; i++) {
                double gaussian = random.nextGaussian();

                if (gaussian > m) {
                    allGaussianMatchesCondition = false;
                    k++;
                    break;
                }
            }

            stop = allGaussianMatchesCondition;
        }

        System.out.println(k);
    }
}
