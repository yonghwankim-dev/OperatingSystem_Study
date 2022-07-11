package chap07_00_examples.chap07_00_02_ReaderWriter;

class ReaderWriter {
    public static void main(String[] args){
        Thread[] readers = new Thread[2];
        Thread[] writers = new Thread[1];
        SharedDB db = new SharedDB();

        // create thread of Reader
        for(int i = 0; i < readers.length; i++){
            readers[i] = new Thread(new Reader(db));
            readers[i].start();
        }

        // create thread of Writer
        for(int i = 0; i < writers.length; i++){
            writers[i] = new Thread(new Writer(db));
            writers[i].start();
        }
    }
}

class Reader implements Runnable{
    SharedDB sharedDB;

    public Reader(SharedDB sharedDB) {
        this.sharedDB = sharedDB;
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(1000);
                sharedDB.acquireReadLock();
                sharedDB.read();
                sharedDB.releaseReadLock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class Writer implements Runnable{
    SharedDB sharedDB;

    public Writer(SharedDB sharedDB) {
        this.sharedDB = sharedDB;
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(1000);
                sharedDB.acquireWriteLock();
                sharedDB.write();
                sharedDB.releaseWriteLock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}

class SharedDB{
    private int readerCount = 0;
    private boolean isWriting = false;

    public void read(){
        // read from the database here.
        System.out.println("현재 데이터베이스를 읽고 있습니다.");
    }

    public void write(){
        // write into the database here.
        System.out.println("현재 데이터베이스를 작성하고 있습니다.");
    }

    synchronized public void acquireReadLock(){
        while(isWriting){
            try{
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        readerCount++;
    }
    synchronized public void releaseReadLock(){
        readerCount--;
        if(readerCount == 0){
            notify();
        }
    }

    synchronized public void acquireWriteLock(){
        while(readerCount > 0 || isWriting){
            try{
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        isWriting = true;
    }

    synchronized  public void releaseWriteLock(){
        isWriting = false;
        notify();
    }
}

