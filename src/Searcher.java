public class Searcher {
    public static class Result {
        public int index = -1;
        public long steps = 0;
        public long nanos = 0;
    }

    // steps = number of elements compared
    public static Result linear(MyArray arr, int target) {
        Result r = new Result();
        long start = System.nanoTime();
        for (int i = 0; i < arr.size(); i++) {
            r.steps++;
            if (arr.get(i) == target) {
                r.index = i;
                break;
            }
        }
        r.nanos = System.nanoTime() - start;
        return r;
    }

    // array has to be sorted already, the caller checks that
    public static Result binary(MyArray arr, int target) {
        Result r = new Result();
        long start = System.nanoTime();
        int low = 0;
        int high = arr.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            r.steps++;
            int midVal = arr.get(mid);
            if (midVal == target) {
                r.index = mid;
                break;
            } else if (midVal < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        r.nanos = System.nanoTime() - start;
        return r;
    }
}
