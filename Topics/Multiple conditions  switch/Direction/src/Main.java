import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();
        switch (choice){
            case 0:
                System.out.println("do not move");
                break;
            case 1:
                System.out.println("move up");
                break;
            case 2:
                System.out.println("move down");
                break;
            case 3:
                System.out.println("move left");
                break;
            case 4:
                System.out.println("move right");
                break;
            default:
                System.out.println("error!");
        }

        int[] numbers = { 4, 5, 6 };


        method(numbers);


        System.out.println(Arrays.toString(numbers));
    }

    public static void method(int[] array) {
        array = new int[] { 1, 2, 3 };
    }
}
