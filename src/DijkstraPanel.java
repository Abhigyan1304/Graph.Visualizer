import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
class DijkstraPanel extends JPanel {
    public JTextArea resultArea;
    public  JTextField startField;
    public JButton startButton;
    
    private static final int NODE_COUNT = 10; // Example node count

    public DijkstraPanel() {
        this.setLayout(new BorderLayout());

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 30));
        startButton.setBackground(Color.BLUE);
        startButton.setForeground(Color.WHITE);

        startField = new JTextField(5);
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        resultArea.setBackground(Color.WHITE);
        resultArea.setForeground(Color.RED);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Start Node:"));
        inputPanel.add(startField);
        inputPanel.add(startButton);

        this.add(inputPanel, BorderLayout.NORTH);
        this.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int startNode;
                try {
                    startNode = Integer.parseInt(startField.getText());
                    if (startNode < 0 || startNode >= points.size()) {
                        throw new NumberFormatException();
                    }
                    runDijkstra(startNode);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DijkstraPanel.this, "Invalid node number.");
                }
            }
        });
    }

    public void reset() {
        resultArea.setText("");
    }

    public void displayResults(int[] dist, int[] parent, java.util.List<int[]> shortestPathEdges) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dist.length; i++) {
            sb.append("Node ").append(i).append(": Distance = ").append(dist[i])
                .append(", Path = ").append(getPath(parent, i)).append("\n");
        }
        resultArea.setText(sb.toString());
    }

    private String getPath(int[] parent, int node) {
        if (node == -1) return "";
        StringBuilder path = new StringBuilder();
        while (node != -1) {
            path.insert(0, node + " ");
            node = parent[node];
        }
        return path.toString().trim();
    }
}

void runDijkstra(int startNode) {
    int n = points.size();
    if (n == 0 || startNode < 0 || startNode >= n) {
        JOptionPane.showMessageDialog(this, "Invalid start node.");
        return;
    }

    dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[startNode] = 0;
    parent = new int[n];
    Arrays.fill(parent, -1);

    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    pq.offer(new int[]{startNode, 0});

    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int u = current[0];
        int uDist = current[1];

        if (uDist > dist[u]) continue;  // Skip if we already found a better path

        for (int[] edge : edges) {
            int v = -1;
            if (edge[0] == u) v = edge[1];
            if (edge[1] == u) v = edge[0];
            if (v != -1) {
                int weight = (int) Math.sqrt(Math.pow(points.get(u)[0] - points.get(v)[0], 2)
                        + Math.pow(points.get(u)[1] - points.get(v)[1], 2));
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }
    }

    shortestPathEdges.clear();
    for (int i = 0; i < parent.length; i++) {
        if (parent[i] != -1) {
            shortestPathEdges.add(new int[]{parent[i], i});
        }
    }
    dijkstraPanel.displayResults(dist, parent, shortestPathEdges);
    repaint();
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new MyFrame("Graph Algorithms"));
}
}
