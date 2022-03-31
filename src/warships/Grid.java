package warships;

import warships.exceptions.AlreadyShotException;
import warships.exceptions.CellOutOfBoundException;

import java.lang.ArrayIndexOutOfBoundsException;

public class Grid {
    // Singleton
    private static final Grid INSTANCE = new Grid();

    public static Grid getInstance(){
        return INSTANCE;
    }

    private final boolean[][] cells = new boolean[10][10];

    public void setCell(int x, int y) throws CellOutOfBoundException, AlreadyShotException{
        try {
            boolean cell = this.getCell(x, y);

            if (cell) {
                throw new AlreadyShotException("You have already made shot at this cell");
            }

            this.cells[x][y] = true;
        } catch (ArrayIndexOutOfBoundsException e){
            throw new CellOutOfBoundException("Choose indexes 1-10 for x and y");
        }
    }

    public boolean getCell(int x, int y) throws ArrayIndexOutOfBoundsException{
        return this.cells[x][y];
    }
}
