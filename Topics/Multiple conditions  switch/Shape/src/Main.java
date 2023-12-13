import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();
        String shape;

        switch (choice) {
            case 1:
                shape = "square";
                System.out.println("You have chosen a " + shape);
                break;
            case 2:
                shape = "circle";
                System.out.println("You have chosen a " + shape);
                break;
            case 3:
                shape = "triangle";
                System.out.println("You have chosen a " + shape);
                break;
            case 4:
                shape = "rhombus";
                System.out.println("You have chosen a " + shape);
                break;
            default:
                System.out.println("There is no such shape!");
        }
    }
}
