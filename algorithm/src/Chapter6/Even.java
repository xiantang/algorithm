package Chapter6;

import algs4.Particle;

public class Even implements Comparable<Even> {
    // 发生的时间
    private final double time;
    // 和事件相关的粒子，可以为空
    private final Particle a, b;
    // 事件创建时候每个粒子所参与的碰触事件数量
    private final int countA, countB;

    public Even(double time, Particle a, Particle b) {
        this.time = time;
        this.a = a;
        this.b = b;
        if (a != null) countA = a.count();
        else countA = -1;
        if (a != null) countB = b.count();
        else countB = -1;
    }

    @Override
    public int compareTo(Even o) {
        return 0;
    }
}
