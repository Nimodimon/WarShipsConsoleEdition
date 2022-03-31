package warships;

import java.awt.Point;
import java.util.ArrayList;

public class Fleet {
    // Ships generating
    private static ArrayList<Ship> ships = new ArrayList<Ship>();

    public static void generateShips(){
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= i; j++) {
                ships.add(new Ship(j));
            }
        }
    }

    public static boolean isShipCell(int x, int y){
        return ships.stream().anyMatch(ship -> ship.isDamaged(x, y));
    }

    public static boolean cellIsTaken(int x, int y){
        return ships.stream().anyMatch(ship -> ship.checkShipArea(x, y));
    }

    public static boolean isDestroyed(){
        return ships.stream().allMatch(ship -> ship.isDestroyed());
    }

    public static boolean shipIsDestroyed(){
        for (Ship ship:ships) {
            if (ship.isDestroyed() && !ship.getDestroyed()){
                ship.setDestroyed();
                return true;
            }
        }

        return false;
    }
}
