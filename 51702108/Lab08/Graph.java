
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

class Graph {

    private int v;
    private LinkedList<Integer> adj[];
    private String result;

    // constructor
    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        result = new String();
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    //
    private void DFStil(int v, boolean visited[]) {
        visited[v] = true;
        result = result + String.valueOf(v) + " ";
        Iterator<Integer> it = adj[v].listIterator();
        while (it.hasNext()) {
            int n = it.next();
            if (!visited[n]) {
                DFStil(n, visited);
            }
        }
    }

    public void DFS(int v) {
        boolean visited[] = new boolean[this.v];
        result = "result: ";
        DFStil(v, visited);
        JOptionPane.showMessageDialog(null, result, "DFS", JOptionPane.PLAIN_MESSAGE);
    }

    public void BFS(int v) {
        boolean visited[] = new boolean[this.v];
        result = "result: ";
        BFStil(v, visited);
        JOptionPane.showMessageDialog(null, result, "BFS", JOptionPane.PLAIN_MESSAGE);
    }

    private void BFStil(int v, boolean visited[]) {
        LinkedList<Integer> Q = new LinkedList<Integer>();
        visited[v] = true;
        Q.add(v);
        while (Q.size() != 0) {
            v = Q.poll();
            result = result + String.valueOf(v) + " ";

            Iterator<Integer> it = adj[v].listIterator();
            while (it.hasNext()) {
                int n = it.next();
                if (!visited[n]) {
                    visited[n] = true;
                    Q.add(n);
                }
            }
        }
    }

    private void DFS_cycle(int v) {
        boolean visited[] = new boolean[this.v];
        visited[v] = false;
        Integer pred[] = new Integer[v];
        for (int i = 0; i < v; i++) {
            pred[i] = -1;
        }
        for (int i = 0; i < v; i++) {
            if (visited[i] == false && pred[i] == -1) {
                DFS(i);
                pred[i] = 1;
            } else {
            }
        }

    }

    public static void main(String[] args) {
        Graph g = new Graph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(1, 2);
        g.addEdge(1, 4);
        g.addEdge(1, 0);
        g.addEdge(2, 1);
        g.addEdge(2, 4);
        g.addEdge(3, 1);
        g.addEdge(3, 0);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 2);
        g.addEdge(4, 1);
        g.addEdge(4, 3);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(5, 3);
        g.addEdge(5, 4);
        g.addEdge(5, 6);
        g.addEdge(6, 4);
        g.addEdge(6, 5);
        // System.out.print("Enter to exit");
        // g.DFS(0);
        // g.BFS(0);
        g.DFS_cycle(1);
        Scanner scan = new Scanner(System.in);
        System.out.println("Tarjan algorithm Test\n");
        System.out.println("Enter number of Vertices");
        /**
         * number of vertices *
         */
        int V = scan.nextInt();

        /**
         * make graph *
         */
        List<Integer>[] k = new List[V];
        for (int i = 0; i < V; i++) {
            k[i] = new ArrayList<Integer>();
        }
        /**
         * accpet all edges *
         */
        System.out.println("\nEnter number of edges");
        int E = scan.nextInt();
        /**
         * all edges *
         */
        System.out.println("Enter " + E + " x, y coordinates");
        for (int i = 0; i < E; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            k[x].add(y);
        }

        Tarjan t = new Tarjan();
        System.out.println("\nSCC : ");
        /**
         * print all strongly connected components *
         */
        List<List<Integer>> scComponents = t.getSCComponents(g);
        System.out.println(scComponents);
    }

}

