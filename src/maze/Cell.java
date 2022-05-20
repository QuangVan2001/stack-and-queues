/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

/**
 *
 * @author QUANG VAN
 */
public class Cell {
    
    int row, col;
    char value;
    boolean blocked=false;
    boolean visited = false;
    Cell previous = null;

    public Cell(int row, int col, char value) {
        this.col = col;
        this.row = row;
        this.value = value;
    }
    
    public void setBlock(){
        this.blocked=true;
    }
    
    public boolean canBeVisited(){
        return !blocked && !visited;
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + "," + value + ")";
    }
    
}
