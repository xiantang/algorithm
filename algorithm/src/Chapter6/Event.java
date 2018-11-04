package Chapter6;

import algs4.Particle;

public class Event implements Comparable<Event> {
    // 发生的时间
    private final double time;
    // 和事件相关的粒子，可以为空
    private final Particle a, b;
    // 事件创建时候每个粒子所参与的碰触事件数量
    private final int countA, countB;

    public Event(double time, Particle a, Particle b) {
        // 创建一个发生在时间t并且a与b相关的新事件
        this.time = time;
        this.a = a;
        this.b = b;
        if (a != null) countA = a.count();
        else countA = -1;
        if (a != null) countB = b.count();
        else countB = -1;
    }

    @Override
    public int compareTo(Event that) {
        if (this.time < that.time) return -1;
        else if (this.time > that.time) return 1;
        else return 0;
    }

    public boolean isVaild() {
        if (a!=null&& a.count()!=countA) return false;
        if (b!=null&& b.count()!=countB) return false;
        return true;

    }
}
