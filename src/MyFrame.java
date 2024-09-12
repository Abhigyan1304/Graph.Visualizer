// import Components.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.Queue;
// import java.swing.Timer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyFrame extends JFrame implements ItemListener {
    public int startNode;
    Set<String> edgesSet;
    // Paint paint = new Paint();
    JButton bfsButton;
    JButton dfsButton;
    JButton addEdge; // Button to add edge
    JButton addNode; // Button to create node
    JButton runShortestPathDij; // Button to run Dijkstra's algorithm
    JButton runPrimMst; // Button to run Prim's algorithm
    JButton connectAll; // Button to connect all nodes
    JButton resetGraph; // Button to reset the graph
    JButton resetAlgorithm; // Button to reset algorithm results
    JButton addBulkNodes; // Button to add bulk nodes
    JTextField xField, yField; // Text fields for node coordinates
    JTextField nodeInputField; // Text field for bulk adding nodes
    JPanel distancePanel; // Panel to display distances between nodes
    DijkstraPanel dijkstraPanel;
    java.util.List<int[]> points = new ArrayList<>();
    java.util.List<int[]> shortestPathEdges = new ArrayList<>();
    java.util.List<int[]> edges = new ArrayList<>();
    java.util.List<int[]> bfsEdges = new ArrayList<>();
    // java.util.List<int[]> dfsEdges = new ArrayList<>();
    java.util.List<int[]> mstEdges = new ArrayList<>();
    // java.util.List<int[]> mstEdges = new ArrayList<>();
    int[] dist; // For Dijkstra's algorithm
    int[] parent; // To store parent nodes in Dijkstra's algorithm
    java.util.List<Integer> bfsArray, dfsArray;
    String whatTorun;
    // public static JComboBox nodeColorBox;//for colour of nodes

    public boolean runMST;
    public boolean runDij;
    public boolean runBfs;
    public boolean runDfs;

    public MyFrame(String name) {
        DijkstraPanel dp = new DijkstraPanel();
        this.getContentPane().setBackground(Color.CYAN);
        this.setBackground(Color.BLACK);
        this.setName(name);
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        runDij = false;
        runMST = false;
        runDfs = false;
        runBfs = false;
        // nodeColorBox = new JComboBox<>(colours);
        // nodeColorBox.addItemListener(this);
        bfsButton = new JButton("Show BFS");
        dfsButton = new JButton("Show DFS");
        addEdge = new JButton("Add Edge");
        addNode = new JButton("Add Node");
        runShortestPathDij = new JButton("Run Dijkstra");
        runPrimMst = new JButton("Run MST");
        connectAll = new JButton("Connect All");
        resetGraph = new JButton("Reset Graph");
        resetAlgorithm = new JButton("Reset Algorithm");
        addBulkNodes = new JButton("Add nodes in the format: x1 y1 x2 y2)");

        addEdge.setPreferredSize(new Dimension(120, 30));
        addNode.setPreferredSize(new Dimension(120, 30));
        runShortestPathDij.setPreferredSize(new Dimension(120, 30));
        runPrimMst.setPreferredSize(new Dimension(120, 30));
        connectAll.setPreferredSize(new Dimension(120, 30));
        resetGraph.setPreferredSize(new Dimension(120, 30));
        resetAlgorithm.setPreferredSize(new Dimension(120, 30));
         addBulkNodes.setPreferredSize(new Dimension(50, 30));


        addEdge.setBackground(Color.BLUE);
        addNode.setBackground(Color.BLUE);
        runShortestPathDij.setBackground(Color.BLUE);
        runPrimMst.setBackground(Color.BLUE);
        connectAll.setBackground(Color.BLUE);
        resetGraph.setBackground(Color.BLUE);
        resetAlgorithm.setBackground(Color.BLUE);
        addBulkNodes.setBackground(Color.BLUE);

        addEdge.setForeground(Color.BLUE);
        addNode.setForeground(Color.BLUE);
        runShortestPathDij.setForeground(Color.BLUE);
        runPrimMst.setForeground(Color.BLUE);
        connectAll.setForeground(Color.BLUE);
        resetGraph.setForeground(Color.BLUE);
        resetAlgorithm.setForeground(Color.BLUE);
        addBulkNodes.setForeground(Color.BLUE);

        dijkstraPanel = new DijkstraPanel();

        xField = new JTextField(5);
        yField = new JTextField(5);
        nodeInputField = new JTextField(30);

        distancePanel = new JPanel();
        distancePanel.setLayout(new BoxLayout(distancePanel, BoxLayout.Y_AXIS));

        addPropertiesToButton(addEdge);
        addPropertiesToButton(addNode);
        addPropertiesToButton(runShortestPathDij);
        addPropertiesToButton(runPrimMst);
        addPropertiesToButton(connectAll);
        addPropertiesToButton(resetGraph);
        addPropertiesToButton(resetAlgorithm);
        addPropertiesToButton(addBulkNodes);
        addPropertiesToButton(dp.startButton);
        addPropertiesToButton(bfsButton);
        addPropertiesToButton(dfsButton);

        JPanel topButtonPanel = new JPanel();
        topButtonPanel.setLayout(new FlowLayout());//2 2 5 5 




        JLabel XLabel = new JLabel("X:");
        topButtonPanel.add(XLabel,0);
        XLabel.setForeground(Color.BLUE);
        XLabel.setFont(new Font("Arial", Font.PLAIN, 14));


        JLabel YLabel = new JLabel("Y:");
        YLabel.setForeground(Color.BLUE);
        YLabel.setFont(new Font("Arial", Font.PLAIN, 18));


        topButtonPanel.add(xField, JPanel.LEFT_ALIGNMENT);
        topButtonPanel.add(YLabel,JPanel.LEFT_ALIGNMENT);
        // topButtonPanel.add(new JLabel("Y:",0));
        topButtonPanel.add(yField,JPanel.RIGHT_ALIGNMENT);
        topButtonPanel.add(addNode);
        topButtonPanel.add(runShortestPathDij);
        topButtonPanel.add(runPrimMst);
        topButtonPanel.add(dfsButton);
        topButtonPanel.add(bfsButton);
        topButtonPanel.add(connectAll);
        topButtonPanel.setBackground(Color.BLACK);

        // topButtonPanel.add(addBulkNodes);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLACK);
        // bottomPanel.setBackground(Color.PINK);
        bottomPanel.setLayout(new BorderLayout());
        // bottomPanel.add(new JLabel("Add nodes (format: x1 y1 x2 y2)"), BorderLayout.NORTH);
        bottomPanel.add(nodeInputField, BorderLayout.CENTER);
        bottomPanel.add(resetGraph, BorderLayout.SOUTH);
        bottomPanel.add(addBulkNodes,BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(resetGraph);
        buttonPanel.add(resetAlgorithm);

        this.setLayout(new BorderLayout());
        this.add(topButtonPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(distancePanel, BorderLayout.EAST);
        distancePanel.setBackground(Color.BLACK);
        GraphPanel gp = new GraphPanel();
        gp.setBackground(Color.BLACK);
        this.add(gp, BorderLayout.CENTER);
        this.add(dijkstraPanel, BorderLayout.EAST);
        edgesSet = new HashSet<>();
        startNode = 0;
        //  Graphics g = new Graphics() {
            
        //  };  
        dijkstraPanel.setBackground(Color.BLACK);

        bfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(runBfs){
                    runBfs = false;
                    repaint();
                    return;
                }
                runBfs = true;
                System.out.println("RUNNING BFS ");
                performBFSAlgo(startNode); // Call BFS with the starting node
                repaint();
                // System.out.println("BFS pressed");
                //repaint(); // Repaint to visualize the changes on the graph

            }
        });

        // ActionListener for DFS
        dfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(runDfs){
                    runDfs = false;
                    repaint();
                    return;
                }
                runDfs = true;
                System.out.println("RUNNING DFS");
                performDFSAlgo(startNode);

                repaint();
                
            }
        });
        addNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNode();
            }
        });
        addEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEdge();
            }
        });
        connectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectAllNodes();
            }
        });
        runShortestPathDij.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(runDij){
                    runDij = false;
                    repaint();
                    return;
                }
                runDij = true;
                System.out.println("RUNNING DIJ ALGO");
                runDijkstra(0); // Run Dijkstra from node 0

            }
        });
        runPrimMst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(runMST){
                    runMST = false;
                    repaint();
                    return;
                }
                runMST = true;
                System.out.println("Running MST Algo");
                runPrim();
                // repaint();
                // runMST = false;
            }
        });
        resetGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGraph();
            }
        });
        resetAlgorithm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAlgorithm();
            }
        });
        addBulkNodes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parseAndAddNodes(nodeInputField.getText());
            }
        });
        this.getContentPane().setBackground(Color.GREEN);
        this.setVisible(true);
    }

    void addPropertiesToButton(JButton beautifulButton) {
        beautifulButton.setFocusPainted(false);
        beautifulButton.setContentAreaFilled(false);
        beautifulButton.setForeground(Color.BLUE);
        beautifulButton.setFont(new Font("Arial", Font.BOLD, 16));
        beautifulButton.setBackground(Color.BLACK);   
        beautifulButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                beautifulButton.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                beautifulButton.setForeground(Color.RED);
            }
        });
        
    }

    void addNode() {
        try {
            int x = Integer.parseInt(xField.getText());
            int y = Integer.parseInt(yField.getText());
            points.add(new int[] { x, y });
            xField.setText("");
            yField.setText("");
            repaint();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid coordinates.");
        }
    }

    public void performDFSAlgo(int startNode) {
        java.util.List<java.util.List<Integer>> graph = new ArrayList<>();
        for(int i = 0 ; i < points.size(); i++)graph.add(new ArrayList<>());
        for (int i = 0; i < edges.size(); i++) {
            graph.get(edges.get(i)[0]).add(edges.get(i)[1]);

        }
        dfsArray = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];
        dfsUtil(startNode, visited, graph);
        // repaint();
        dijkstraPanel.displayDFS();
    }

    public void dfsUtil(int node, boolean[] visited, java.util.List<java.util.List<Integer>> graph) {
        visited[node] = true;
        // Visualize visiting the node
        // System.out.println("Visited node: " + node);
        dfsArray.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, visited, graph);
            }
        }
    }

    public void performBFSAlgo(int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[points.size()];
        java.util.List<java.util.List<Integer>> graph = new ArrayList<>(points.size());
        bfsArray = new ArrayList<>();
        // Initialize adjacency list
        for (int i = 0; i < points.size(); i++) {
            graph.add(new ArrayList<>());
        }

        // Fill the adjacency list using edges
        for (int i = 0; i < edges.size(); i++) {
            graph.get(edges.get(i)[0]).add(edges.get(i)[1]);
            graph.get(edges.get(i)[1]).add(edges.get(i)[0]); // Assuming undirected graph
        }

        // List to store BFS edges (visited edges during BFS traversal)
        // java.util.List<int[]> bfsEdges = new ArrayList<>();

        // Start BFS traversal
        queue.add(startNode);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            bfsArray.add(currentNode);
            for (int neighbor : graph.get(currentNode)) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;

                    // Add the edge to bfsEdges list (coordinates of current node and neighbor)
                    int[] currentPoint = points.get(currentNode);
                    int[] neighborPoint = points.get(neighbor);
                    // bfsEdges.add(new int[]{currentPoint[0], currentPoint[1], neighborPoint[0],
                    // neighborPoint[1]});
                }
            }
        }
        // for (int[] edge : mstEdges) {
        // int[] p1 = points.get(edge[0]);
        // int[] p2 = points.get(edge[1]);
        // drawLineWithArrow(g,p1[0], p1[1], p2[0], p2[1]);
        // // setDelay(5);
        // }}
        // for(int i = 0 ; i < bfsArray.size()-1 ; i++){
        // int curr = bfsArray.get(i);
        // int next = bfsArray.get(i);
        // int currX = edges.get(curr)[0];
        // int currY = edges.get(curr)[1];

        // int nextX = edges.get(next)[0];
        // int nextY = edges.get(next)[1];

        // int[] start = {currX,currY};
        // int[] end = {nextX,nextY};
        // bfsEdges.add
        // }

        dijkstraPanel.displayBFS();
    }

    // Print BFS edges
    // for (int[] edge : bfsEdges) {
    // System.out.println("Edge from (" + edge[0] + ", " + edge[1] + ") to (" +
    // edge[2] + ", " + edge[3] + ")");
    // }

    // Optionally, you can store bfsEdges for future visualization or use
    // repaint(); // To visually represent BFS traversal on your graph

    void addEdge() {
        if (points.size() < 2) {
            JOptionPane.showMessageDialog(this, "Need at least 2 nodes to add an edge.");
            return;
        }


        edges.clear(); // Clear existing edges
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                edges.add(new int[] { i, j });
            }
        }

        repaint();
    }

    void connectAllNodes() {
        edges.clear(); // Clear existing edges
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                edges.add(new int[] { i, j });
            }
        }
        repaint();
    }

    void parseAndAddNodes(String input) {
        String arr[] = input.split(" ");
        if (arr.length % 2 != 0) {
            JOptionPane.showMessageDialog(this, "Invalid format for coordinates: " + input);
            return;
        }
        
        for (int i = 0; i < arr.length; i += 2) {
            String temp = arr[i] + " " + arr[i + 1];
            if (edgesSet.contains(temp))
                continue;

            try {
                int x = Integer.parseInt(arr[i]);
                int y = Integer.parseInt(arr[i + 1]);
                points.add(new int[] { x, y });
                xField.setText("");
                yField.setText("");
                repaint();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid coordinates.");
            }
        }
        nodeInputField.setText("");
        repaint();
    }

    void resetGraph() {
        points.clear();
        edges.clear();
        shortestPathEdges.clear();
        mstEdges.clear();
        dijkstraPanel.reset(); // Reset the Dijkstra panel
        repaint();
    }

    void resetAlgorithm() {
        shortestPathEdges.clear();
        mstEdges.clear();
        dijkstraPanel.reset(); // Reset the Dijkstra panel
        repaint();
    }

    void runPrim() {
        int n = points.size();

        try {

            boolean[] inMST = new boolean[n];
            int[] parent = new int[n];
            int[] key = new int[n];
            Arrays.fill(key, Integer.MAX_VALUE);
            Arrays.fill(parent, -1);

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            pq.add(new int[] { 0, 0 });
            key[0] = 0;

            mstEdges.clear(); // Clear previous MST edges

            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int u = curr[0];
                int weight = curr[1];

                if (inMST[u])
                    continue;
                inMST[u] = true;

                for (int[] edge : edges) {
                    int v = -1;
                    int edgeWeight = 0;
                    if (edge[0] == u) {
                        v = edge[1];
                        edgeWeight = (int) Math.round(Math.sqrt(Math.pow(points.get(v)[0] - points.get(u)[0], 2)
                                + Math.pow(points.get(v)[1] - points.get(u)[1], 2)) / 10);
                    } else if (edge[1] == u) {
                        v = edge[0];
                        edgeWeight = (int) Math.round(Math.sqrt(Math.pow(points.get(v)[0] - points.get(u)[0], 2)
                                + Math.pow(points.get(v)[1] - points.get(u)[1], 2)) / 10);
                    }

                    if (v != -1 && !inMST[v] && edgeWeight < key[v]) {
                        key[v] = edgeWeight;
                        pq.add(new int[] { v, edgeWeight });
                        parent[v] = u;
                    }
                }
            }

            // Collect the edges forming the MST
            for (int i = 1; i < n; i++) {
                if (parent[i] != -1) {
                    mstEdges.add(new int[] { parent[i], i });
                }
            }

            repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    class GraphPanel extends JPanel {
        private static final int NODE_RADIUS = 15;
        private static final int TEXT_OFFSET = 5;

        @Override
        protected void paintComponent(Graphics g) {

        
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();

            // if (runBfs) {
            //     g.setColor(Color.PINK);
            //     for (int i = 0; i < bfsArray.size() - 1; i++) {
            //         int[] p1 = points.get(bfsArray.get(i));
            //         int[] p2 = points.get(bfsArray.get(i + 1));
            //         drawLineWithArrow(g, p1[0], p1[1], p2[0], p2[1]);
            //         // setDelay(2);
            //     }
            //     System.out.println("BFS is running");
            // }  
            // if (runDfs) {
            //     System.out.println("RUNNING DFS");
            // } 
            // if (runMST) {
            //     g.setColor(Color.GREEN);
            //     for (int[] edge : mstEdges) {
            //         int[] p1 = points.get(edge[0]);
            //         int[] p2 = points.get(edge[1]);
            //         drawLineWithArrow(g, p1[0], p1[1], p2[0], p2[1]);
            //         // setDelay(5);
            //     }
            // }
            
            // }else{
            //    g.setColor(Color.BLACK);
            //     for (int[] edge : edges) {
            //         int[] p1 = points.get(edge[0]);
            //         int[] p2 = points.get(edge[1]);
            //         g.drawLine(p1[0], p1[1], p2[0], p2[1]);
            //         // setDelay(2);
            //     } 
            // }
            if(!runDij && !runBfs && !runDfs && !runMST){

                g.setColor(Color.WHITE);
                for (int[] edge : edges) {
                    int[] p1 = points.get(edge[0]);
                    int[] p2 = points.get(edge[1]);
                    g.drawLine(p1[0], p1[1], p2[0], p2[1]);
                    // setDelay(2);
                } 
            }

            if(runDij){
                g.setColor(Color.RED);
                for (int[] edge : shortestPathEdges) {
                    int[] p1 = points.get(edge[0]);
                    int[] p2 = points.get(edge[1]);
                    drawLineWithArrow(g, p1[0], p1[1], p2[0], p2[1]);
                    setDelay(2);
                }
            }
            if(runMST){
                g.setColor(Color.GREEN);
                for (int[] edge : mstEdges) {
                    int[] p1 = points.get(edge[0]);
                    int[] p2 = points.get(edge[1]);
                    drawLineWithArrow(g, p1[0], p1[1], p2[0], p2[1]);
                    // setDelay(2);
                }
            }
            if (runBfs) {
                g.setColor(Color.PINK);
                for (int i = 0; i < bfsArray.size() - 1; i++) {
                    int[] p1 = points.get(bfsArray.get(i));
                    int[] p2 = points.get(bfsArray.get(i + 1));
                    drawLineWithArrow(g, p1[0], p1[1], p2[0], p2[1]);
                    // setDelay(2);
                }

            }  
            if(runDfs){
                g.setColor(Color.orange);
                for (int i = 0; i < dfsArray.size() - 1; i++) {
                    int[] p1 = points.get(dfsArray.get(i));
                    int[] p2 = points.get(dfsArray.get(i + 1));
                    drawLineWithArrow(g, p1[0], p1[1], p2[0], p2[1]);
                    // setDelay(2);
                }
            }
            
        

        //      // if(runDfs){
        //     // // for(int i = 0 ; i < dfs.size()-1 ; i++){
        //     // // int first = dfs.get(i);
        //     // // int second = dfs.get(i+1);
        //     // // int[] p1 = points.get(first);
        //     // // int[] p2 = points.get(second);
        //     // // g.drawLine(p1[0], p1[1], p2[0], p2[1]);
        //     // // // setDelay(2);
        //     // // }

        //     // }
        //     // if(runDij && !runMST){
        //     // g.setColor(Color.RED);
        //     // for (int[] edge : shortestPathEdges) {
        //     // int[] p1 = points.get(edge[0]);
        //     // int[] p2 = points.get(edge[1]);
        //     // drawLineWithArrow(g,p1[0], p1[1], p2[0], p2[1]);
        //     // // setDelay(2);
        //     // }
        //     // }

        //     // // Clear the panel and only draw the MST edges in green
        //     // if(runMST && !runDij){
        //     // g.setColor(Color.GREEN);
        //     // for (int[] edge : mstEdges) {
        //     // int[] p1 = points.get(edge[0]);
        //     // int[] p2 = points.get(edge[1]);
        //     // drawLineWithArrow(g,p1[0], p1[1], p2[0], p2[1]);
        //     // // setDelay(5);
        //     // }}

        //     // Draw the nodes
            g.setColor(Color.BLUE);
            for (int i = 0; i < points.size(); i++) {
                int[] p = points.get(i);
                g.fillOval(p[0] - NODE_RADIUS, p[1] - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
                g.setColor(Color.WHITE);
                g.drawString(Integer.toString(i), p[0] - TEXT_OFFSET, p[1] + TEXT_OFFSET);
                g.setColor(Color.BLUE);
            }

        //     // g.setColor(Color.PINK);

        // 
            
        }
    }

    class DijkstraPanel extends JPanel {
        public JTextArea resultArea;
        public JTextField startField;
        public JButton startButton;

        private static final int NODE_COUNT = 10; // Example node count

        public DijkstraPanel() {
            this.setLayout(new BorderLayout());

            startButton = new JButton("Set as Start Node");
            startButton.setPreferredSize(new Dimension(200, 30));
            
            addPropertiesToButton(startButton);
            startField = new JTextField(5);
            resultArea = new JTextArea(120, 10);
            resultArea.setEditable(false);
            resultArea.setBackground(Color.BLACK);
            resultArea.setForeground(Color.RED);
            resultArea.setBounds(0,0,200, 100);

            JPanel inputPanel = new JPanel();
            // JLabel startJl = new JLabel("Start Node:");
            // startJl.setFont(new Font("Arial", Font.BOLD, 18));
            // startJl.setForeground(Color.BLUE);
            // inputPanel.add(startJl);
            inputPanel.add(startField);
            inputPanel.add(startButton);
            // inputPanel.setBackground(Color.BLACK);
            inputPanel.setForeground(Color.RED);
            inputPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 5, Color.RED));
            inputPanel.setBackground(Color.BLACK);


            this.add(inputPanel, BorderLayout.NORTH);
            JScrollPane jsp = new JScrollPane(resultArea);
            this.setLayout( new BoxLayout (this, BoxLayout.Y_AXIS));    
            this.add(jsp, BorderLayout.CENTER);
            // this.add();

            
            ColorTextPanel ctp1 = new ColorTextPanel(Color.RED, "SHORTEST PATH");
            ColorTextPanel ctp2 = new ColorTextPanel(Color.GREEN, "MST");
            ColorTextPanel ctp3 = new ColorTextPanel(Color.PINK, "BFS");
            ColorTextPanel ctp4 = new ColorTextPanel(Color.YELLOW, "DFS");
            this.add(ctp1);
            this.add(ctp2);
            this.add(ctp3);
            this.add(ctp4);
            // jsp.setBackground(Color.RED);
            Border border = BorderFactory.createLineBorder(Color.RED, 5);  // Create a black line border with 2px thickness
            jsp.setBorder(border);

            // this.add()
            
            // this.add(MyFrame.nodeColorBox);
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int newStartNode;
                    try {
                        newStartNode = Integer.parseInt(startField.getText());
                        if (startNode < 0 || startNode >= points.size()) {
                            throw new NumberFormatException();
                        }
                        startNode = newStartNode;
                        // runDijkstra(startNode);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(DijkstraPanel.this, "Invalid node number.");
                    }
                }
            });
        }

       

        public void reset() {
            resultArea.setText("");
        }

        public void displayBFS() {
            StringBuilder sb = new StringBuilder();
            sb.append("BFS : ");
            if (bfsArray.size() != 0) {
                for (int i = 0; i < bfsArray.size(); i++) {
                    sb.append(Integer.toString(bfsArray.get(i)));
                    if (i != bfsArray.size() - 1)
                        sb.append(" -> ");
                }
            }
            resultArea.setText(sb.toString());

        }
        public void displayDFS() {
            StringBuilder sb = new StringBuilder();
            sb.append("DFS : ");
            if (dfsArray.size() != 0) {
                for (int i = 0; i < dfsArray.size(); i++) {
                    sb.append(Integer.toString(dfsArray.get(i)));
                    if (i != dfsArray.size() - 1)
                        sb.append(" -> ");
                }
            }
            resultArea.setText(sb.toString());

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
            if (node == -1)
                return "";
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
        pq.offer(new int[] { startNode, 0 });

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            int uDist = current[1];

            if (uDist > dist[u])
                continue; // Skip if we already found a better path

            for (int[] edge : edges) {
                int v = -1;
                if (edge[0] == u)
                    v = edge[1];
                if (edge[1] == u)
                    v = edge[0];
                if (v != -1) {
                    int weight = (int) Math.sqrt(Math.pow(points.get(u)[0] - points.get(v)[0], 2)
                            + Math.pow(points.get(u)[1] - points.get(v)[1], 2));
                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        parent[v] = u;
                        pq.offer(new int[] { v, dist[v] });
                    }
                }
            }
        }

        shortestPathEdges.clear();
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] != -1) {
                shortestPathEdges.add(new int[] { parent[i], i });
            }
        }
        dijkstraPanel.displayResults(dist, parent, shortestPathEdges);

        repaint();
    }

    public void setDelay(int time) {
        javax.swing.Timer timer = new javax.swing.Timer(time * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ((javax.swing.Timer) e.getSource()).stop(); // Stop the timer after one run
            }
        });
        timer.setRepeats(false); // Ensure the timer only fires once
        timer.start(); // Start the timer
    }

    private static int ARROW_SIZE = 25;

    private void drawLineWithArrow(Graphics g, int x1, int y1, int x2, int y2) {
        // Draw the line
        g.drawLine(x1, y1, x2, y2);

        // Calculate the midpoint
        int midX = (x1 + x2) / 2;
        int midY = (y1 + y2) / 2;

        // Calculate arrowhead points
        double angle = Math.atan2(y2 - y1, x2 - x1);
        int arrowX1 = midX - (int) (ARROW_SIZE * Math.cos(angle - Math.PI / 6));
        int arrowY1 = midY - (int) (ARROW_SIZE * Math.sin(angle - Math.PI / 6));
        int arrowX2 = midX - (int) (ARROW_SIZE * Math.cos(angle + Math.PI / 6));
        int arrowY2 = midY - (int) (ARROW_SIZE * Math.sin(angle + Math.PI / 6));

        // Draw the arrowhead
        int[] arrowX = { midX, arrowX1, arrowX2 };
        int[] arrowY = { midY, arrowY1, arrowY2 };
        g.fillPolygon(arrowX, arrowY, 3);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyFrame("Graph Algorithms"));
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'itemStateChanged'");
    }
    
}


// 200 200 400 400 200 400 400 200 600 600 700 733 499 33 98 123 657 32 0 0
//src