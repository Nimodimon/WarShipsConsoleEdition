package warships;

import warships.exceptions.AlreadyShotException;
import warships.exceptions.CellOutOfBoundException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Grid grid = Grid.getInstance();
        Fleet.generateShips();

        while (true){
            System.out.println("Input x: ");
            int x = getInputInteger(scanner);
            System.out.println("Input y: ");
            int y = getInputInteger(scanner);

            try {
                grid.setCell(x, y);

                if (Fleet.isShipCell(x, y)){
                    if (Fleet.shipIsDestroyed()){
                        System.out.println("Destroyed!!!!");
                    } else {
                        System.out.println("Successful shot!");
                    }
                } else {
                    System.out.println("Off ship!");
                }
            } catch (AlreadyShotException|CellOutOfBoundException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static int getInputInteger(Scanner scanner){
        while (!scanner.hasNextInt()){
            System.out.println("Only Inegers!!!");
            scanner.next();
        }

        return scanner.nextInt();
    }
}
