package Chapter6;

import algs4.MinPQ;
import algs4.Particle;

public class CollisionSystem {
    private class Event implements Comparable<Event> {
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
            // 如果任何计数发生误差，那么这个事件就失效了
            if (a != null && a.count() != countA) return false;
            if (b != null && b.count() != countB) return false;
            return true;

        }
    }

    private MinPQ<Event> pq;      // 优先队列
    private double t = 0.0;       // 模拟时钟
    private Particle[] particles; // 粒子数组

    // 模拟器代码
    private void predictCollisions(Particle a, double limit) {
        if (a == null) return;
        for (int i = 0; i < particles.length; i++) {
            // 将particle[i] 发生的碰撞事件加入优先级队列
            double dt = a.timeToHit(particles[i]);
            if (t + dt <= limit)
                pq.insert(new Event(t + dt, a, particles[i]));
        }
        double dtX = a.timeToHitHorizontalWall();
        if (dtX+t<=limit)
            pq.insert(new Event(t+dtX,a,null));
        double dtY = a.timeToHitVerticalWall();
        if (dtY+t<=limit)
            pq.insert(new Event(t+dtY,null,a));
    }

}
