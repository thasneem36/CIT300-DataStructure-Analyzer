import java.util.Arrays;

public class MyStack {
    private int[] data = new int[4];
    private int top = 0;   // next free slot, also the element count

    public void push(int value) {
        if (top == data.length) {
            data = Arrays.copyOf(data, top * 2);
        }
        data[top++] = value;
    }

    public int pop() {
        if (top == 0) {
            throw new IllegalStateException("Stack is empty, nothing to pop.");
        }
        return data[--top];
    }

    public int peek() {
        if (top == 0) {
            throw new IllegalStateException("Stack is empty, nothing to peek at.");
        }
        return data[top - 1];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }

    public void display() {
        if (top == 0) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("TOP");
        for (int i = top - 1; i >= 0; i--) {
            System.out.println("  | " + data[i] + " |");
        }
        System.out.println("BOTTOM");
    }
}
