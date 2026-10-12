import java.util.Arrays;

public class MyArray {
    private int[] items = new int[8];
    private int size = 0;

    public int size() {
        return size;
    }

    public int get(int index) {
        return items[index];
    }

    // returns how many elements had to be shifted
    public int insert(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Position " + index + " is outside 0.." + size);
        }
        if (size == items.length) {
            items = Arrays.copyOf(items, size * 2);
        }
        int shifts = 0;
        for (int i = size; i > index; i--) {
            items[i] = items[i - 1];
            shifts++;
        }
        items[index] = value;
        size++;
        return shifts;
    }

    public int delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Position " + index + " is outside 0.." + (size - 1));
        }
        int removed = items[index];
        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        size--;
        return removed;
    }

    public void sort() {
        Arrays.sort(items, 0, size);
    }

    public boolean isSorted() {
        for (int i = 1; i < size; i++) {
            if (items[i - 1] > items[i]) return false;
        }
        return true;
    }

    public void display() {
        if (size == 0) {
            System.out.println("Array is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(items[i]);
            if (i < size - 1) sb.append(", ");
        }
        System.out.println(sb.append("]  (size " + size + ")"));
    }
}
