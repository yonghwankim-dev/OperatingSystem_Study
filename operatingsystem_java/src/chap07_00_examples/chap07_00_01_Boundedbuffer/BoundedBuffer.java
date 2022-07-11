package chap07_00_examples.chap07_00_01_Boundedbuffer;

class BoundedBuffer {
    public static void main(String[] args){
        CashBox cashBox = new CashBox(1);
        Thread[] producers = new Thread[1];
        Thread[] consumers = new Thread[1];

        // create threads of producers
        for(int i = 0; i < producers.length; i++){
            producers[i] = new Thread(new Producer(cashBox));
            producers[i].start();
        }

        // create threads of  consumers
        for(int i = 0; i < consumers.length; i++) {
            consumers[i] = new Thread(new Consumer(cashBox));
            consumers[i].start();
        }
    }
}

class Producer implements Runnable{
    CashBox cashBox;

    public Producer(CashBox cashBox) {
        this.cashBox = cashBox;
    }

    @Override
    public void run() {
        try{
            while(true){
                Thread.sleep((long)Math.random()*500);
                int money = ((int) (1 + Math.random() * 9)) * 10000;
                cashBox.give(money);
            }
        }catch(InterruptedException e){
            System.out.println("인터럽트 예외 발생");
        }
    }
}

class Consumer implements Runnable{
    CashBox cashBox;

    public Consumer(CashBox cashBox) {
        this.cashBox = cashBox;
    }

    @Override
    public void run() {
        try{
            while(true){
                Thread.sleep((long)(Math.random() * 500));
                int money = cashBox.take();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class CashBox{
    private int[] buffer;

    private int count, in, out;

    public CashBox(int bufferSize){
        buffer = new int[bufferSize];
        count = in = out = 0;
    }

    synchronized public void give(int money){
        // critical section
        while(count == buffer.length){
            try{
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        buffer[in] = money;
        in = (in + 1) % buffer.length;
        count++;
        System.out.printf("여기있따, 용돈 : %d\n", money);

        notify();
    }

    synchronized  public int take(){
        // critical section
        while(count == 0){
            try{
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int money = buffer[out];
        out = (out + 1) % buffer.length;
        count--;
        System.out.printf("고마워요, 용돈 : %d\n", money);

        notify();
        return money;
    }
}