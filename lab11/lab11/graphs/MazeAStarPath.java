package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;
import java.util.Comparator;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    /* inherits:
    protected int[] distTo;
    protected int[] edgeTo;
    protected boolean[] marked;
    protected Maze maze;
     */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        edgeTo[s] = s;

        // Ensure distTo[v] + h(v) is no more than Integer.MAX_VALUE
        int N = maze.N();
        for (int i = 0; i < maze.V(); i += 1) {
            distTo[i] -= 2 * N;
        }

        distTo[s] = 0;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
//        return -1;
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    private class SearchNode implements Comparable<SearchNode> {
        private final int priority;
        private final int vertex;

        public SearchNode(int v) {
            vertex = v;
            priority = h(v) + distTo[v];
        }

        @Override
        public int compareTo(SearchNode other) {
            return this.priority - other.priority;
        }
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(new SearchNode(s));
        marked[s] = true;
        announce();

        while (!pq.isEmpty()) {
            SearchNode p = pq.delMin();
            int v = p.vertex;
            marked[v] = true;

            if (v == t) {
                announce();
                return;
            }
            // relax:
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    if (distTo[v] + 1 < distTo[w]) {
                        distTo[w] = distTo[v] + 1;
                        edgeTo[w] = v;
                        pq.insert(new SearchNode(w));
                    }
                }
            }
            announce();
        }
    }

    public void solve() {
        astar(s);
    }
}

