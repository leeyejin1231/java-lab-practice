package week12;

import java.util.concurrent.ArrayBlockingQueue;  
import java.util.concurrent.BlockingQueue;  
 
 
public class week12 {  
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(5);  
      
    public static void main(String[] args) {  
    	Producer p = new Producer(queue);  p.start();  
        Consumer c = new Consumer(queue); c.start();  
    }  
      

    static class Producer extends Thread {  
        private volatile static int i = 0;  
          
        private BlockingQueue<Integer> queue;  
          
        public Producer(BlockingQueue<Integer> queue) {  
            this.queue = queue;  
        }  
          
        public void run() {   
            while(i<10) {  
                try {                   
                    queue.put(i);
                    System.out.println("Produced: " + i);
                    i++;
                    Thread.sleep(300); 
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
  
            }  
        }  
    }  
      
      
    static class Consumer extends Thread {  
        private BlockingQueue<Integer> queue;  
        
        public Consumer(BlockingQueue<Integer> queue) {  
            this.queue = queue;  
        }  
          
        public void run() {  
            while ( true ) {  
                try {  
                    Integer index = queue.take();  
                    System.err.println("Consumed: " + index);
                	Thread.sleep(1000); 
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
      
}  