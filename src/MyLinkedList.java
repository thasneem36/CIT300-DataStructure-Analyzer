public class MyLinkedList {
    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private int size;

    public int size() {
        return size;
    }

    public void insertFirst(int value) {
        Node n = new Node(value);
        n.next = head;
        head = n;
        size++;
    }

    public void insertLast(int value) {
        Node n = new Node(value);
        if (head == null) {
            head = n;
        } else {
            Node cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = n;
        }
        size++;
    }

    public boolean deleteValue(int value) {
        if (head == null) return false;
        if (head.value == value) {
            head = head.next;
            size--;
            return true;
        }
        Node cur = head;
        while (cur.next != null && cur.next.value != value) {
            cur = cur.next;
        }
        if (cur.next == null) return false;
        cur.next = cur.next.next;
        size--;
        return true;
    }

    // position of the first match, or -1
    public int search(int value) {
        int pos = 0;
        for (Node cur = head; cur != null; cur = cur.next) {
            if (cur.value == value) return pos;
            pos++;
        }
        return -1;
    }

    public void display() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.value).append(" -> ");
        }
        System.out.println(sb.append("null"));
    }
}
