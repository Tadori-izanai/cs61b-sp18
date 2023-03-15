package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze m;
    private final int startX = 1;
    private final int startY = 1;
    private final int s;
    private int[] myEdgeTo;

    private boolean cycleFound = false;
    private int end1;
    private int end2;


    public MazeCycles(Maze m) {
        super(m);
        this.m = m;
        s = m.xyTo1D(startX, startY);
        myEdgeTo = new int[m.V()];
        myEdgeTo[s] = s;
    }

    @Override
    public void solve() {
        // Your code here!
        dfs(s);
        if (cycleFound) {
            System.out.println("hello");
        }

        // show connection:
        int u = end2;
        while (u != end1) {
            edgeTo[u] = myEdgeTo[u];
            u = myEdgeTo[u];
        }
        edgeTo[u] = end2;

        announce();
    }

    // Helper methods go here
    private void dfs(int v) {
        if (cycleFound) {
            return;
        }
        marked[v] = true;
        announce();

        for (int w : m.adj(v)) {
            if (marked[w] && myEdgeTo[v] != w) {
                cycleFound = true;
                end2 = v;
                end1 = w;
                announce();
                return;
            }
        }

        for (int w : m.adj(v)) {
            if (!marked[w]) {
                myEdgeTo[w] = v;
                dfs(w);
            }
        }
    }

}

