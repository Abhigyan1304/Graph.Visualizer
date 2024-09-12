import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class GraphPanel extends JPanel {
    private static final int NODE_RADIUS = 20;
    private static final int TEXT_OFFSET = 5;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.BLACK);

        // Draw nodes
        for (int i = 0; i < points.size(); i++) {
            int[] point = points.get(i);
            int x = 50 + point[0];
            int y = height - 50 - point[1];
            g.setColor(Color.BLUE);
            g.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(i), x - TEXT_OFFSET, y + TEXT_OFFSET);
        }

        // Draw edges
        g.setColor(Color.BLACK);
        for (int[] edge : edges) {
            int[] point1 = points.get(edge[0]);
            int[] point2 = points.get(edge[1]);
            int x1 = 50 + point1[0];
            int y1 = height - 50 - point1[1];
            int x2 = 50 + point2[0];
            int y2 = height - 50 - point2[1];
            g.drawLine(x1, y1, x2, y2);
            
            // Draw distance label
            double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            g.drawString(String.format("%.2f", distance), (x1 + x2) / 2 + 5, (y1 + y2) / 2 - 5);
        }

        // Highlight shortest path
        g.setColor(Color.RED);
        for (int[] edge : shortestPathEdges) {
            int[] point1 = points.get(edge[0]);
            int[] point2 = points.get(edge[1]);
            int x1 = 50 + point1[0];
            int y1 = height - 50 - point1[1];
            int x2 = 50 + point2[0];
            int y2 = height - 50 - point2[1];
            g.drawLine(x1, y1, x2, y2);
        }

        // Highlight MST
        g.setColor(Color.GREEN);
        for (int[] edge : mstEdges) {
            int[] point1 = points.get(edge[0]);
            int[] point2 = points.get(edge[1]);
            int x1 = 50 + point1[0];
            int y1 = height - 50 - point1[1];
            int x2 = 50 + point2[0];
            int y2 = height - 50 - point2[1];
            g.drawLine(x1, y1, x2, y2);
        }
    }
}

// Dijkstra's algorithm
void runDijkstra(int source) {
    int n = points.size();
    int[][] graph = new int[n][n];
    for (int[] edge : edges) {
        int u = edge[0];
        int v = edge[1];
        double distance = Math.sqrt(Math.pow(points.get(u)[0] - points.get(v)[0], 2)
                + Math.pow(points.get(u)[1] - points.get(v)[1], 2));
        graph[u][v] = (int) distance;
        graph[v][u] = (int) distance;
    }

    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    boolean[] visited = new boolean[n];
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
    pq.add(new int[]{source, 0});
    dist[source] = 0;

    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int u = current[0];
        int d = current[1];

        if (visited[u]) continue;
        visited[u] = true;

        for (int v = 0; v < n; v++) {
            if (graph[u][v] > 0 && !visited[v]) {
                int newDist = d + graph[u][v];
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.add(new int[]{v, newDist});
                    shortestPathEdges.add(new int[]{u, v});
                }
            }
        }
    }

}

// Prim's algorithm
void runPrim() {
    int n = points.size();
    int[][] graph = new int[n][n];
    for (int[] edge : edges) {
        int u = edge[0];
        int v = edge[1];
        double distance = Math.sqrt(Math.pow(points.get(u)[0] - points.get(v)[0], 2)
                + Math.pow(points.get(u)[1] - points.get(v)[1], 2));
        graph[u][v] = (int) distance;
        graph[v][u] = (int) distance;
    }

    boolean[] inMST = new boolean[n];
    int[] parent = new int[n];
    int[] key = new int[n];
    Arrays.fill(key, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);
    key[0] = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
    pq.add(new int[]{0, 0});

    // Clear existing edges before adding MST edges
    mstEdges.clear();

    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int u = current[0];

        if (inMST[u]) continue;
        inMST[u] = true;

        for (int v = 0; v < n; v++) {
            if (graph[u][v] > 0 && !inMST[v] && graph[u][v] < key[v]) {
                key[v] = graph[u][v];
                pq.add(new int[]{v, key[v]});
                parent[v] = u;
                mstEdges.add(new int[]{u, v});
            }
        }
    }

    // Update edges to only include MST edges
    edges.clear();
    edges.addAll(mstEdges);

    repaint();
}

public static void main(String[] args) {
    new MyFrame("Graph Visualization");
}
