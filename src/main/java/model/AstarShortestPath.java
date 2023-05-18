package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AstarShortestPath {
    private static Map map;
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static List<Cell> findShortestPath(Map map,Cell start, Cell end) {
        AstarShortestPath.map = map;
        PriorityQueue<Cell> openSet = new PriorityQueue<>();
        boolean[][] visited = new boolean[map.getSizeOfTheMap()][map.getSizeOfTheMap()];
        start.setG(0);
        start.setF(start.getG() + heuristic(start, end));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Cell current = openSet.poll();
            if (current.getX() == end.getX() && current.getY() == end.getY()) {
                return reconstructPath(current);
            }

            visited[current.getX()][current.getY()] = true;

            for (int[] direction : DIRECTIONS) {
                int newRow = current.getX() + direction[0];
                int newCol = current.getY() + direction[1];

                if (isValidCell(newRow, newCol, map.getSizeOfTheMap()) && !visited[newRow][newCol] && !map.getMapCells(newRow, newCol).isObstacle()) {
                    int tentativeG = current.getG() + 1;

                    Cell neighbour = map.getMapCells(newRow, newCol);
                    neighbour.setG(tentativeG);
                    neighbour.setF(neighbour.getG() + heuristic(neighbour, end));
                    neighbour.setParent(current);

                    if (!openSet.contains(neighbour)) {
                        openSet.add(neighbour);
                    } else if (tentativeG >= neighbour.getG()) {
                        continue;
                    }
                }
            }
        }
        return Collections.emptyList(); // No path found
    }

    private static boolean isValidCell(int newRow, int newCol, int sizeOfTheMap) {
        return newRow >= 0 && newRow < sizeOfTheMap && newCol >= 0 && newCol < sizeOfTheMap;
    }

    private static List<Cell> reconstructPath(Cell current) {
        List<Cell> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = current.getParent();
        }

        Collections.reverse(path);
        map.resetMapFGParent();
        return path;
    }

    private static int heuristic(Cell start, Cell end) {
        return Math.abs(start.getX() - end.getX()) + Math.abs(start.getY() - end.getY());
    }
}
