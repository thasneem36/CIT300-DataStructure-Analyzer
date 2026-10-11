import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// undirected graph, vertices are int ids, stored as an adjacency list
public class Graph {
    private final Map<Integer, List<Integer>> adj = new LinkedHashMap<>();
    private int edgeCount = 0;

    // set by the last bfs/dfs call: vertices visited + edges looked at
    public long lastSteps = 0;
    public long lastNanos = 0;

    public int vertexCount() {
        return adj.size();
    }

    public boolean hasVertex(int id) {
        return adj.containsKey(id);
    }

    public boolean addVertex(int id) {
        if (adj.containsKey(id)) return false;
        adj.put(id, new ArrayList<>());
        return true;
    }

    public boolean addEdge(int a, int b) {
        if (a == b || !adj.containsKey(a) || !adj.containsKey(b)) return false;
        if (adj.get(a).contains(b)) return false;
        adj.get(a).add(b);
        adj.get(b).add(a);
        edgeCount++;
        return true;
    }

    public void display() {
        if (adj.isEmpty()) {
            System.out.println("Graph has no vertices yet.");
            return;
        }
        for (Map.Entry<Integer, List<Integer>> e : adj.entrySet()) {
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }
        System.out.println(adj.size() + " vertices, " + edgeCount + " edges");
    }

    public List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        long begin = System.nanoTime();
        lastSteps = 0;

        MyQueue queue = new MyQueue();
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        queue.enqueue(start);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            order.add(v);
            lastSteps++;
            for (int next : adj.get(v)) {
                lastSteps++;
                if (visited.add(next)) {
                    queue.enqueue(next);
                }
            }
        }
        lastNanos = System.nanoTime() - begin;
        return order;
    }

    public List<Integer> dfs(int start) {
        List<Integer> order = new ArrayList<>();
        long begin = System.nanoTime();
        lastSteps = 0;

        MyStack stack = new MyStack();
        Set<Integer> visited = new HashSet<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited.add(v)) continue;
            order.add(v);
            lastSteps++;
            // pushed backwards so the first neighbour is popped first
            List<Integer> neighbours = adj.get(v);
            for (int i = neighbours.size() - 1; i >= 0; i--) {
                lastSteps++;
                if (!visited.contains(neighbours.get(i))) {
                    stack.push(neighbours.get(i));
                }
            }
        }
        lastNanos = System.nanoTime() - begin;
        return order;
    }
}
