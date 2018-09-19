package Chapter3;

import algs4.Queue;

public class SparseVector {
    private LinearProbingHashST<Integer, Double> st;

    public SparseVector() {
        st = new LinearProbingHashST<>();
    }

    public int size() {
        return st.size();
    }

    public void put(int i, double x) {
        st.put(i, x);
    }

    public double get(int i) {
        if (!st.contain(i)) {
            if (!st.contain(i)) return 0.0;
        }
        return st.get(i);

    }

    public double dot(double[] that) {
        double sum = 0.0;
        Queue<Integer> queue = st.keys();
        for (int i : queue
        ) {
            sum += that[i] * this.get(i);
        }
        return sum;

    }

    public static void main(String[] args) {
        SparseVector sparseVector = new SparseVector();
        sparseVector.put(1, 1.0);
        sparseVector.put(2, 1.0);
        sparseVector.put(3, 1.0);
//        sparseVector.dot();
    }


}
