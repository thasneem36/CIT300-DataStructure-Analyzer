// circular array queue, so dequeue doesn't have to shift anything
public class MyQueue {
    private int[] data = new int[4];
    private int head = 0;
    private int count = 0;

    public void enqueue(int value) {
        if (count == data.length) {
            grow();
        }
        data[(head + count) % data.length] = value;
        count++;
    }

    public int dequeue() {
        if (count == 0) {
            throw new IllegalStateException("Queue is empty, nothing to dequeue.");
        }
        int value = data[head];
        head = (head + 1) % data.length;
        count--;
        return value;
    }

    public int front() {
        if (count == 0) {
            throw new IllegalStateException("Queue is empty, no front element.");
        }
        return data[head];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void display() {
        if (count == 0) {
            System.out.println("Queue is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("FRONT -> ");
        for (int i = 0; i < count; i++) {
            sb.append(data[(head + i) % data.length]).append(" ");
        }
        System.out.println(sb.append("<- REAR"));
    }

    private void grow() {
        int[] bigger = new int[data.length * 2];
        for (int i = 0; i < count; i++) {
            bigger[i] = data[(head + i) % data.length];
        }
        data = bigger;
        head = 0;
    }
}
