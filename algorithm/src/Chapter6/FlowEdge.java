package Chapter6;

import Chapter4.Edge;

public class FlowEdge {
    private final int v;    // 边的起点
    private final int w;    // 边的终点
    private final double capacity;  //  容量
    private double flow;    // 流量

    public FlowEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = 0.0;
    }

    // 起点
    public int from() {
        return v;
    }

    // 终点
    public int to() {
        return w;
    }

    // 容量
    public double capacity() {
        return capacity;
    }

    // 流量
    public double flow() {
        return flow;
    }

    // 另一边
    public int other(int vertex) {
        if (vertex == v) return w;
        if (vertex == w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    // 剩余流量
    public double residualCapacityTo(int vertex) {
        if (vertex == v) return flow;
        else if (vertex == w) return capacity - flow;
        else throw new RuntimeException("Inconsistent edge");
    }

    // 为v增加delta流量
    public void addResidualFlowTo(int vertex, double delta) {
        if (vertex == v) flow -= delta;
        else if (vertex == w) flow += delta;
        else throw new RuntimeException("Inconsistent edge");
    }

    public String toString() {
        return String.format("%d->%d %.2f %.2f", v, w, capacity, flow);
    }

}
