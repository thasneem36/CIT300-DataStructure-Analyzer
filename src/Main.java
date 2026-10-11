import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    private static final MyArray array = new MyArray();
    private static final MyStack stack = new MyStack();
    private static final MyQueue queue = new MyQueue();
    private static final MyLinkedList list = new MyLinkedList();
    private static final Graph graph = new Graph();
    private static final ResultLog log = new ResultLog();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("=============================================");
            System.out.println("     DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("=============================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");

            switch (readChoice(1, 9)) {
                case 1 -> arrayMenu();
                case 2 -> stackMenu();
                case 3 -> queueMenu();
                case 4 -> listMenu();
                case 5 -> searchMenu();
                case 6 -> graphMenu();
                case 7 -> performanceMenu();
                case 8 -> log.print();
                case 9 -> running = false;
            }
        }
        System.out.println("Bye.");
    }

    // ---------- menus ----------

    private static void arrayMenu() {
        while (true) {
            System.out.println("\n--------------- ARRAY OPERATIONS ---------------");
            System.out.println("1. Insert at position");
            System.out.println("2. Append to end");
            System.out.println("3. Delete at position");
            System.out.println("4. Search for a value");
            System.out.println("5. Sort array");
            System.out.println("6. Display array");
            System.out.println("7. Return to Main Menu");

            int choice = readChoice(1, 7);
            try {
                switch (choice) {
                    case 1 -> {
                        int pos = readInt("Position (0.." + array.size() + "): ");
                        int value = readInt("Value: ");
                        int shifts = array.insert(pos, value);
                        System.out.println("Inserted " + value + " at " + pos + " (" + shifts + " elements shifted).");
                    }
                    case 2 -> {
                        int value = readInt("Value: ");
                        array.insert(array.size(), value);
                        System.out.println("Appended " + value + ".");
                    }
                    case 3 -> {
                        if (array.size() == 0) {
                            System.out.println("Array is empty, nothing to delete.");
                            break;
                        }
                        int pos = readInt("Position to delete (0.." + (array.size() - 1) + "): ");
                        System.out.println("Deleted " + array.delete(pos) + ".");
                    }
                    case 4 -> {
                        int value = readInt("Value to find: ");
                        Searcher.Result r = Searcher.linear(array, value);
                        if (r.index == -1) {
                            System.out.println(value + " is not in the array (" + r.steps + " comparisons).");
                        } else {
                            System.out.println(value + " found at position " + r.index + " (" + r.steps + " comparisons).");
                        }
                    }
                    case 5 -> {
                        array.sort();
                        System.out.println("Sorted.");
                    }
                    case 6 -> array.display();
                    case 7 -> {
                        return;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void stackMenu() {
        while (true) {
            System.out.println("\n--------------- STACK OPERATIONS ---------------");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");

            int choice = readChoice(1, 5);
            try {
                switch (choice) {
                    case 1 -> {
                        int value = readInt("Value to push: ");
                        stack.push(value);
                        System.out.println("Pushed " + value + ", stack size is now " + stack.size() + ".");
                    }
                    case 2 -> System.out.println("Popped " + stack.pop() + ".");
                    case 3 -> System.out.println("Top of stack is " + stack.peek() + ".");
                    case 4 -> stack.display();
                    case 5 -> {
                        return;
                    }
                }
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void queueMenu() {
        while (true) {
            System.out.println("\n--------------- QUEUE OPERATIONS ---------------");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek front");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");

            int choice = readChoice(1, 5);
            try {
                switch (choice) {
                    case 1 -> {
                        int value = readInt("Value to enqueue: ");
                        queue.enqueue(value);
                        System.out.println("Enqueued " + value + ", queue size is now " + queue.size() + ".");
                    }
                    case 2 -> System.out.println("Dequeued " + queue.dequeue() + ".");
                    case 3 -> System.out.println("Front of queue is " + queue.front() + ".");
                    case 4 -> queue.display();
                    case 5 -> {
                        return;
                    }
                }
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void listMenu() {
        while (true) {
            System.out.println("\n------------- LINKED LIST OPERATIONS -------------");
            System.out.println("1. Insert at head");
            System.out.println("2. Insert at tail");
            System.out.println("3. Delete a value");
            System.out.println("4. Search for a value");
            System.out.println("5. Display list");
            System.out.println("6. Return to Main Menu");

            switch (readChoice(1, 6)) {
                case 1 -> list.insertFirst(readInt("Value: "));
                case 2 -> list.insertLast(readInt("Value: "));
                case 3 -> {
                    int value = readInt("Value to delete: ");
                    if (list.deleteValue(value)) {
                        System.out.println("Deleted " + value + ".");
                    } else {
                        System.out.println(value + " is not in the list.");
                    }
                }
                case 4 -> {
                    int value = readInt("Value to find: ");
                    int pos = list.search(value);
                    if (pos == -1) {
                        System.out.println(value + " is not in the list.");
                    } else {
                        System.out.println(value + " is at position " + pos + ".");
                    }
                }
                case 5 -> list.display();
                case 6 -> {
                    return;
                }
            }
        }
    }

    // works on the same array as menu 1
    private static void searchMenu() {
        while (true) {
            System.out.println("\n-------------- SEARCHING OPERATIONS --------------");
            System.out.println("1. Linear search");
            System.out.println("2. Binary search");
            System.out.println("3. Compare both on one value");
            System.out.println("4. Return to Main Menu");

            int choice = readChoice(1, 4);
            if (choice == 4) return;

            if (array.size() == 0) {
                System.out.println("The array is empty. Add some numbers in the Array menu first.");
                continue;
            }
            if (choice != 1 && !array.isSorted()) {
                System.out.println("Binary search needs a sorted array. Sort it from the Array menu first.");
                continue;
            }

            int target = readInt("Value to search for: ");
            if (choice == 1 || choice == 3) {
                Searcher.Result r = Searcher.linear(array, target);
                report("Linear Search", r, target);
            }
            if (choice == 2 || choice == 3) {
                Searcher.Result r = Searcher.binary(array, target);
                report("Binary Search", r, target);
            }
        }
    }

    private static void report(String name, Searcher.Result r, int target) {
        String where = r.index == -1 ? "not found" : "found at " + r.index;
        System.out.println(name + ": " + target + " " + where + ", " + r.steps + " steps, " + r.nanos + " ns");
        log.add("Search", name, r.steps, r.nanos, "array size " + array.size() + ", " + where);
    }

    private static void graphMenu() {
        while (true) {
            System.out.println("\n--------------- GRAPH OPERATIONS ---------------");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Return to Main Menu");

            switch (readChoice(1, 6)) {
                case 1 -> {
                    int id = readInt("Vertex id: ");
                    if (graph.addVertex(id)) {
                        System.out.println("Vertex " + id + " added.");
                    } else {
                        System.out.println("Vertex " + id + " already exists.");
                    }
                }
                case 2 -> {
                    int a = readInt("First vertex: ");
                    int b = readInt("Second vertex: ");
                    if (!graph.hasVertex(a) || !graph.hasVertex(b)) {
                        System.out.println("Both vertices have to exist before they can be joined.");
                    } else if (a == b) {
                        System.out.println("A vertex can't have an edge to itself here.");
                    } else if (graph.addEdge(a, b)) {
                        System.out.println("Edge " + a + " - " + b + " added.");
                    } else {
                        System.out.println("Edge " + a + " - " + b + " already exists.");
                    }
                }
                case 3 -> graph.display();
                case 4 -> traverse(true);
                case 5 -> traverse(false);
                case 6 -> {
                    return;
                }
            }
        }
    }

    private static void traverse(boolean breadthFirst) {
        if (graph.vertexCount() == 0) {
            System.out.println("Graph has no vertices yet.");
            return;
        }
        int start = readInt("Start vertex: ");
        if (!graph.hasVertex(start)) {
            System.out.println("Vertex " + start + " is not in the graph.");
            return;
        }
        List<Integer> order = breadthFirst ? graph.bfs(start) : graph.dfs(start);
        String name = breadthFirst ? "BFS" : "DFS";
        System.out.println(name + " from " + start + ": " + order);
        System.out.println(order.size() + " vertices reached, " + graph.lastSteps + " steps, " + graph.lastNanos + " ns");
        log.add("Graph Traversal", name, graph.lastSteps, graph.lastNanos,
                "start " + start + ", " + graph.vertexCount() + " vertices");
    }

    private static void performanceMenu() {
        System.out.println("\n============================================");
        System.out.println("          PERFORMANCE COMPARISON");
        System.out.println("============================================");
        int n = readInt("Input size n (10 - 50000): ");
        if (n < 10 || n > 50000) {
            System.out.println("n has to be between 10 and 50000, otherwise the array insert test takes too long.");
            return;
        }
        PerformanceTest.run(n, log);
        log.print();
        System.out.println("Linear search and array front-inserts grow with n, binary search grows with log n,");
        System.out.println("and BFS/DFS both visit every vertex and edge once so they land on the same step count.");
    }

    // ---------- input helpers ----------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = in.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("\"" + line + "\" is not a whole number.");
            }
        }
    }

    private static int readChoice(int min, int max) {
        while (true) {
            int choice = readInt("Enter your choice: ");
            if (choice >= min && choice <= max) return choice;
            System.out.println("Pick a number from " + min + " to " + max + ".");
        }
    }
}
