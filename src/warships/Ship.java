package warships;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Ship {
    private ArrayList<Point> shipCells = new ArrayList<Point>();
    private boolean destroyed = false;

    // Generator
    public Ship (int size){
        while (shipCells.isEmpty()) {
            int startX = new Random().nextInt(10);
            int startY = new Random().nextInt(10);

            if (startX + size <= 10 && IntStream.range(0, size).allMatch(i -> !Fleet.cellIsTaken(startX + i, startY))){
                IntStream.range(0, size).forEach(i -> shipCells.add(new Point(startX + i, startY)));
            }

            else if (startY + size <= 10 && IntStream.range(0, size).allMatch(i -> !Fleet.cellIsTaken(startX, startY + i))){
                IntStream.range(0, size).forEach(i -> shipCells.add(new Point(startX, startY + i)));
            }

            else if (startX - size > 0 && IntStream.range(0, size).allMatch(i -> !Fleet.cellIsTaken(startX - i, startY))){
                IntStream.range(0, size).forEach(i -> shipCells.add(new Point(startX - i, startY)));
            }

            else if (startY - size > 0 && IntStream.range(0, size).allMatch(i -> !Fleet.cellIsTaken(startX, startY - i))){
                IntStream.range(0, size).forEach(i -> shipCells.add(new Point(startX, startY - i)));
            }
        }
    }

    // Destroy methods
    public boolean isDestroyed() {
        return shipCells.stream().allMatch(cell -> Grid.getInstance().getCell(cell.x, cell.y));
    }

    public void setDestroyed(){
        destroyed = true;
    }

    public boolean getDestroyed(){
        return destroyed;
    }

    // Damage
    public boolean isDamaged(int x, int y){
        return shipCells.stream().anyMatch(cell -> cell.x == x && cell.y == y);
    }

    public boolean checkShipArea(int x, int y){
        return shipCells.stream().anyMatch(cell -> checkAreaAroundCell(x, y, cell));
    }

    private boolean checkAreaAroundCell(int x, int y, Point cell){
        return (
                x == cell.x - 1 && y == cell.y + 1 ||
                        x == cell.x && y == cell.y + 1 ||
                        x == cell.x + 1 && y == cell.y + 1 ||
                        x == cell.x - 1 && y == cell.y ||
                        x == cell.x && y == cell.y ||
                        x == cell.x + 1 && y == cell.y ||
                        x == cell.x - 1 && y == cell.y - 1 ||
                        x == cell.x && y == cell.y - 1 ||
                        x == cell.x + 1 && y == cell.y - 1
        );
    }
}
