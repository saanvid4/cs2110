package diver;

import datastructures.PQueue;
import datastructures.SlowPQueue;
import game.*;
import graph.ShortestPaths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


/**
 * This is the place for your implementation of the {@code SewerDiver}.
 */
public class McDiver implements SewerDiver {


    /**
     * Moves McDiver to the unvisited node of state which is most in the direction of the exit, and
     * backtracks to the last visited node once there are no unvisited nodes in the immediate
     * vicinity of McDiver. The current node is added to the parameter visited, and position is
     * changed up to two times within state per recursive call.
     */
    public static void dfsWalk(SeekState state, List<Long> visited) {
        long current = state.currentLocation();
        visited.add(state.currentLocation());
        SlowPQueue ranking = new SlowPQueue<NodeStatus>();
        for (NodeStatus w : state.neighbors()) {
            if (!visited.contains(w.getId())) {
                ranking.add(w, w.getDistanceToRing());

            }
        }
        while (!ranking.isEmpty()) {
            NodeStatus x = (NodeStatus) ranking.extractMin();

            state.moveTo(x.getId());
            if (state.distanceToRing() == 0) {
                break;
            }

            dfsWalk(state, visited);
            if (state.distanceToRing() == 0) {
                break;
            }
            state.moveTo(current);
        }
    }

    /**
     * See {@code SewerDriver} for specification.
     */
    @Override
    public void seek(SeekState state) {
        List<Long> visited = new ArrayList<>();

        dfsWalk(state, visited);

        // TODO : Look for the ring and return.
        // DO NOT WRITE ALL THE CODE HERE. DO NOT MAKE THIS METHOD RECURSIVE.
        // Instead, write your method (it may be recursive) elsewhere, with a
        // good specification, and call it from this one.
        //
        // Working this way provides you with flexibility. For example, write
        // one basic method, which always works. Then, make a method that is a
        // copy of the first one and try to optimize in that second one.
        // If you don't succeed, you can always use the first one.
        //
        // Use this same process on the second method, scram.
    }

    /**
     * See {@code SewerDriver} for specification.
     */
    @Override
    public void scram(ScramState state) {
        // TODO: Get out of the sewer system before the steps are used up.
        // DO NOT WRITE ALL THE CODE HERE. Instead, write your method elsewhere,
        // with a good specification, and call it from this one.
        List<Edge> movePath;
        Maze maze = new Maze((Set<Node>) state.allNodes());
        HashMap<Node, ShortestPaths> pathMaps = new HashMap<>();
        for (Node n : state.allNodes()) {
            ShortestPaths pathFinder = new ShortestPaths(maze);
            pathFinder.singleSourceDistances(n);
            pathMaps.put(n, pathFinder);
        }
        while (true) {
            movePath = coinFind(state, state.stepsToGo(), pathMaps);
            for (Edge p : movePath) {
                state.moveTo(p.destination());
            }
            if (state.currentNode() == state.exit()) {
                return;
            }

        }
    }

    /**
     * Returns: a list of Edges which correspond to the shortest path between the current node and
     * the coin which has the greatest value per step required to reach the coin, or the path to the
     * exit if there are not enough remaining steps to reach any coins and then subsequently the
     * exit.
     */
    public List<Edge> coinFind(ScramState state, int steps, HashMap<Node, ShortestPaths> pathMaps) {
        Node bestNode = state.exit();
        double bestValue = 0.0;
        double value;
        double distance = pathMaps.get(state.currentNode()).getDistance(state.exit());
        ArrayList<Double> connectedValue = new ArrayList();
        List<Edge> path;
        List<Node> grouped;
        //PQueue<Node> p= new SlowPQueue<>();
        for (Node n : state.allNodes()) {
            if (n.getTile().coins() > 0) {
                distance = pathMaps.get(state.currentNode()).getDistance(n);
                grouped = new LinkedList<>();
                grouped.add(n);

                //connectedValue = considerConnectedValue(n, steps - distance, grouped);
                if (connectedValue.size() > 0) {
                    value = (n.getTile().coins() + connectedValue.get(0)) / (distance
                            + connectedValue.get(1));
                } else {
                    value = n.getTile().coins() / distance;
                }

                if (bestValue < (value)
                        && distance + pathMaps.get(n).getDistance(state.exit()) < steps) {
                    bestValue = n.getTile().coins() / distance;
                    bestNode = n;
                }
            }
        }
        return pathMaps.get(state.currentNode()).bestPath(bestNode);


    }
}





