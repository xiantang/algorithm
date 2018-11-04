package Chapter6;

import algs4.MinPQ;
import algs4.Particle;
import algs4.StdDraw;

import java.awt.*;

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
            if (b != null) countB = b.count();
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

    // 初始化方法
    public CollisionSystem(Particle[] particles) {
        this.particles = particles;
    }

    // 重绘事件
    public void redraw(double limit, double Hz) {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        if (t < limit)
            pq.insert(new Event(t + 1.0 / Hz, null, null));
    }

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
        if (dtX + t <= limit)
            pq.insert(new Event(t + dtX, a, null));
        double dtY = a.timeToHitVerticalWall();
        if (dtY + t <= limit)
            pq.insert(new Event(t + dtY, null, a));
    }

    private void simulate(double limit, double Hz) {
        pq = new MinPQ<Event>();
        // 预测所有粒子可能发生的碰撞
        for (int i = 0; i < particles.length; i++)
            predictCollisions(particles[i], limit);
        // 添加重绘事件
        pq.insert(new Event(0, null, null));

        while (!pq.isEmpty()) {
            // 吐出一个粒子
            Event event = pq.delMin();
            if (!event.isVaild()) continue;
            // 更新每个粒子的位置
            for (int i = 0; i < particles.length; i++) {
                particles[i].move(event.time - t);
            }
            t = event.time;
            Particle a = event.a, b = event.b;
            if      (a != null && b != null) a.bounceOff(b);
            else if (a != null && b == null) a.bounceOffVerticalWall();
            else if (a == null && b != null) b.bounceOffHorizontalWall();
            else if (a == null && b == null) redraw(limit,Hz);
            predictCollisions(a,limit);
            predictCollisions(b,limit);

        }
    }

    public static void main(String[] args) {
        StdDraw.show(0);
        Particle[] particles = new Particle[5];
        for (int i = 0; i <5 ; i++) {
            particles[i] = new Particle();
        }
        CollisionSystem collisionSystem = new CollisionSystem(particles);
        collisionSystem.simulate(10000,0.5);
    }
}
