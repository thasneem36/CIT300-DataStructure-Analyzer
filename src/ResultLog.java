import java.util.ArrayList;
import java.util.List;

// keeps every measurement taken during the session for menu option 8
public class ResultLog {
    private static class Entry {
        String operation;
        String algorithm;
        long steps;
        long nanos;
        String note;
    }

    private final List<Entry> entries = new ArrayList<>();

    public void add(String operation, String algorithm, long steps, long nanos, String note) {
        Entry e = new Entry();
        e.operation = operation;
        e.algorithm = algorithm;
        e.steps = steps;
        e.nanos = nanos;
        e.note = note;
        entries.add(e);
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    public void print() {
        if (entries.isEmpty()) {
            System.out.println("No results recorded yet. Run a search, a traversal or the performance comparison first.");
            return;
        }
        System.out.println("=============================================================================");
        System.out.printf("%-16s %-20s %12s %12s  %s%n", "Operation", "Algorithm", "Steps", "Time (ns)", "Note");
        System.out.println("-----------------------------------------------------------------------------");
        for (Entry e : entries) {
            System.out.printf("%-16s %-20s %12d %12d  %s%n", e.operation, e.algorithm, e.steps, e.nanos, e.note);
        }
        System.out.println("=============================================================================");
    }
}
