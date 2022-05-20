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
public class MyMaze01 {
    public static void main(String[] args) {
        String filename = "maze01.txt";
        Maze maze = new Maze();
        maze.loadFromFile(filename);
        maze.print();
        maze.solve();
        if (maze.succeeded) {
            System.out.println("result path:");
            System.out.println(maze.getPath());
        } else {
            System.out.println("Failed!");
        }
    }
}
