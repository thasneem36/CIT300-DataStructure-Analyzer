# Data Structure & Graph Performance Analyzer

CIT300 Data Structures and Algorithms, Graded Practical Assignment 2 (Week 12)

A console program in Java where you can play with an array, stack, queue, linked list and a graph from one menu. It also counts steps and time for a few algorithms (linear vs binary search, array vs linked list insert, BFS vs DFS) so you can see the complexity difference with real numbers.

We wrote all the structures ourselves. The stack, queue, array and linked list use no JDK collections. The graph uses a `LinkedHashMap` of `ArrayList`s for the adjacency list and a `HashSet` for visited vertices.

## Group members

**Member 1 (group leader)**
- Name: HMM. Thasneem
- Student ID: 23DA2-0473
- Responsibility: Graph, performance comparison and integration
- Contribution:
  - wrote `Graph` with add vertex, add edge, display, BFS and DFS
  - BFS uses our `MyQueue`, DFS uses our `MyStack`
  - wrote `PerformanceTest` and `ResultLog`
  - main menu, input checking and putting all the modules together in `Main`
  - final testing of the whole program
  - set up the repository and reviewed the pull requests

**Member 2**
- Name: 
- Student ID: 
- Responsibility: Array and Searching
- Contribution:
  - wrote `MyArray` (insert at position, delete, sort, display)
  - wrote `Searcher` with linear and binary search, both count steps and time
  - Array menu and Searching menu in `Main`
  - tested binary search on an unsorted and an empty array

**Member 3**
- Name: 
- Student ID: 
- Responsibility: Stack and Queue
- Contribution:
  - wrote `MyStack` and `MyQueue` (the queue is circular)
  - empty stack / empty queue handling
  - Stack and Queue menus in `Main`
  - tested growing past the starting size and wrap-around in the queue

**Member 4**
- Name: A. Maryam Amani
- Student ID: 23DA2-0857
- Responsibility: Linked List
- Contribution:
  - wrote `MyLinkedList` (insert head/tail, delete by value, search, display)
  - Linked List menu in `Main`
  - tested deleting the first node, the last node and a value that isn't there

## Features

- Array: insert at a position, append, delete, search, sort, display
- Stack: push, pop, peek, display
- Queue: enqueue, dequeue, front, display
- Linked list: insert at head or tail, delete a value, search, display
- Searching: linear, binary, or both on the same value. Binary search won't run if the array isn't sorted.
- Graph: add vertex, add edge, display, BFS, DFS
- Performance comparison: asks for n, then compares the three pairs above
- Display all results: prints every measurement from the session in one table

Bad input is handled everywhere: letters instead of numbers, menu choices out of range, popping an empty stack, dequeuing an empty queue, edges to vertices that don't exist, duplicate vertices and edges.

## Complexity (what the numbers should show)

- Linear search is O(n), binary search is O(log n). With n = 20000 and the target at the end we got 20000 steps vs 15.
- Inserting at the front of an array is O(n) each time because everything shifts. A linked list does it in O(1). For 20000 inserts that was about 200 million shifts vs 20000 steps.
- BFS and DFS are both O(V + E). They give nearly the same step count because both go through every vertex and edge once. Only the visiting order is different.
- Stack and queue operations are O(1) (amortised, since the arrays double when full).

Step counts are what to compare. The nanosecond times change from run to run.

## Running it

You need Java 17 or newer. From the `DataStructureAnalyzer` folder:

```bash
javac -d out src/*.java
java -cp out Main
```

Then type the menu number you want. Try option 7 with `20000` to see the comparison table.

Things to know:
- sort the array first (Array menu) before using binary search
- the performance comparison only accepts n from 10 to 50000, because the array insert test is O(n^2) and gets very slow after that

## Files

```
src/
  Main.java
  MyArray.java
  MyStack.java
  MyQueue.java
  MyLinkedList.java
  Searcher.java
  Graph.java
  PerformanceTest.java
  ResultLog.java
```
