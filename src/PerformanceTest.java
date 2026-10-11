public class PerformanceTest {

    public static void run(int n, ResultLog log) {
        System.out.println("Running with n = " + n + " ...");
        compareSearch(n, log);
        compareFrontInsert(n, log);
        compareTraversal(n, log);
    }

    // sorted array 0, 2, 4, ... and we look for the very last value
    private static void compareSearch(int n, ResultLog log) {
        MyArray arr = new MyArray();
        for (int i = 0; i < n; i++) {
            arr.insert(i, i * 2);
        }
        int target = (n - 1) * 2;

        Searcher.Result lin = Searcher.linear(arr, target);
        Searcher.Result bin = Searcher.binary(arr, target);
        log.add("Search", "Linear Search", lin.steps, lin.nanos, "O(n), target at the end");
        log.add("Search", "Binary Search", bin.steps, bin.nanos, "O(log n)");
    }

    // inserting at the front n times: the array shifts everything, the list just moves head
    private static void compareFrontInsert(int n, ResultLog log) {
        MyArray arr = new MyArray();
        long shifts = 0;
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            shifts += arr.insert(0, i) + 1;
        }
        long arrayTime = System.nanoTime() - start;

        MyLinkedList list = new MyLinkedList();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            list.insertFirst(i);
        }
        long listTime = System.nanoTime() - start;

        log.add("Insert at front", "Array", shifts, arrayTime, "O(n) per insert");
        log.add("Insert at front", "Linked List", n, listTime, "O(1) per insert");
    }

    // vertex i is joined to i+1, 2i+1 and 2i+2 (when they exist)
    private static void compareTraversal(int n, ResultLog log) {
        Graph g = new Graph();
        for (int i = 0; i < n; i++) {
            g.addVertex(i);
        }
        for (int i = 0; i < n; i++) {
            if (i + 1 < n) g.addEdge(i, i + 1);
            if (2 * i + 1 < n) g.addEdge(i, 2 * i + 1);
            if (2 * i + 2 < n) g.addEdge(i, 2 * i + 2);
        }

        g.bfs(0);
        log.add("Graph Traversal", "BFS", g.lastSteps, g.lastNanos, "O(V + E)");
        g.dfs(0);
        log.add("Graph Traversal", "DFS", g.lastSteps, g.lastNanos, "O(V + E)");
    }
}
