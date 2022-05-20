/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author QUANG VAN
 */
public class Maze {
    char entryChar='E', destChar='M';
    char emptyChar=')', blockedChar ='1';
    int rows = 0, cols = 0;
    Cell[][] cells = null;
    Cell entryCell = null;
    Cell desCell = null;
    boolean completed = false;
    boolean succeeded = false;

    public Maze() {
    }

    public Maze(char entryChar, char destChar, char emptyChar, char blockedChar) {
        this.entryChar = entryChar;
        this.destChar = destChar;
        this.emptyChar = emptyChar;
        this.blockedChar = blockedChar;
    }

    
    private boolean isValid(int row, int col) {
        return (row >= 0 && row < rows && col >= 0 && col < cols && cells[row][col].canBeVisited());
    }

    

    private ArrayList<Cell> getAdjs(Cell curCell) {
        ArrayList<Cell> adjs = new ArrayList();
        int row = curCell.row, col = curCell.col;
        if (isValid(row-1, cols)) {
            cells[row-1][col].previous = curCell;
            adjs.add(cells[row-1][col]);
        }
        if (isValid(row+1, cols)) {
            cells[row+1][col].previous = curCell;
            adjs.add(cells[row+1][col]);
        }if (isValid(row-1, cols)) {
            cells[row][col-1].previous = curCell;
            adjs.add(cells[row][col-1]);
        }if (isValid(row-1, cols)) {
            cells[row][col+1].previous = curCell;
            adjs.add(cells[row][col+1]);
        }
        return adjs;
    }

    public boolean loadFromFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            System.out.println("The file " + filename + "doesn't exitsted!");
            System.exit(0);
        }
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            ArrayList<String> list = new ArrayList();
            String line;
            while ((line = bf.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    list.add(line);
                }
            }
            bf.close();
            fr.close();

            this.rows = list.size();
            this.cols = list.get(0).length();
            this.cells = new Cell[rows][cols];
            for (int i = 0; i < list.size(); i++) {
                line = list.get(i);
                for (int j = 0; j < cols; j++) {
                    char ch = line.charAt(j);
                    cells[i][j] = new Cell(i, j, ch);
                    if (ch == blockedChar) {
                        cells[i][j].setBlock();
                    }
                    else if(ch==entryChar) this.entryCell = cells[i][j];
                    else if(ch==destChar) this.desCell = cells[i][j];
                }

            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        return true;
    }

    public boolean solve() {
       
        LinkedList<Cell> stack = new LinkedList();
        Cell curCell = this.entryCell;
        while (!completed) {
            curCell.visited = true;
            if (curCell == this.desCell) {
                completed = succeeded = true;
            } else {
                ArrayList<Cell>adjs = getAdjs(curCell);
                if (adjs.size() > 0) {
                    curCell = adjs.get(0);
                    for (int i = 1; i < adjs.size(); i++) {
                        stack.addFirst(adjs.get(i));
                    }
                } else if (!stack.isEmpty()) {
                    curCell = stack.removeFirst();
                } else {
                    completed = true;
                    succeeded = false;
                }
            }
        }
        return completed;
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(cells[i][j].value);
            }
            System.out.println();
        }
    }

    public LinkedList<Cell> getPath() {
        if (!succeeded) {
            return null;
        }
        LinkedList<Cell> path = new LinkedList();
        Cell cell = this.desCell;
        while (cell!= null) {
            path.addFirst(cell);
            cell = cell.previous;
        }
        return path;
    }

    
}
