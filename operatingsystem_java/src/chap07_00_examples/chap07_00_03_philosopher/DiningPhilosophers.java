package chap07_00_examples.chap07_00_03_philosopher;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

enum State{
    THINKING, HUNGRY, EATING
}

class DiningPhilosophers {
    public static void main(String[] args){
        int numOfPhils = 5;
        Philosopher[] philosophers = new Philosopher[numOfPhils];
        DiningPhilosopherMonitor monitor = new DiningPhilosopherMonitor(numOfPhils);

        for(int i = 0; i < philosophers.length; i++){
            new Thread(new Philosopher(i, monitor)).start();
        }
    }
}

class Philosopher implements Runnable{
    private int id;
    private DiningPhilosopherMonitor monitor;

    public Philosopher(int id, DiningPhilosopherMonitor monitor) {
        this.id = id;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while(true){
            think();
            monitor.pickup(id);
            eat();
            monitor.putdown(id);
        }
    }

    private void think(){
        try{
            System.out.printf("%d: Now, I'm Thinking\n", id);
            Thread.sleep((long)Math.random() * 500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void eat(){
        try{
            System.out.printf("%d: Now, I'm Eating\n", id);
            Thread.sleep((long)Math.random()*50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class DiningPhilosopherMonitor{
    private int numOfPhils;
    private State[] state;
    private Condition[] self;
    private Lock lock;

    public DiningPhilosopherMonitor(int numOfPhils) {
        this.numOfPhils = numOfPhils;
        state = new State[numOfPhils];
        self  = new Condition[numOfPhils];
        lock  = new ReentrantLock();

        for(int i = 0; i < numOfPhils; i++){
            state[i] = State.THINKING;
            self[i]  = lock.newCondition();
        }
    }

    private int leftOf(int id){
        return (id + numOfPhils - 1) % numOfPhils;
    }

    private int rightOf(int id){
        return (id + 1) % numOfPhils;
    }

    private void test(int id){
        if(state[id] == State.HUNGRY &&
           state[leftOf(id)] != State.EATING &&
           state[rightOf(id)] != State.EATING){
            state[id] = State.EATING;
            self[id].signal();
        }
    }

    public void pickup(int id){
        lock.lock();
        try{
            state[id] = State.HUNGRY;
            test(id);
            if(state[id] != State.EATING){
                self[id].await();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }
    public void putdown(int id){
        lock.lock();
        try{
            state[id] = State.THINKING;
            test(leftOf(id));
            test(rightOf(id));
        }finally {
            lock.unlock();
        }
    }

}