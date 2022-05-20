/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack_and_queues;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author QUANG VAN
 */
public class Customer extends Thread{
    static Random rand = new Random(System.currentTimeMillis());
    long delay;
    LinkedList<String> queue;
    Thread acc;
    
    public Customer(String custName, long delay, Thread acc, LinkedList<String> queue){
        super(custName);
        this.delay = delay;
        this.acc = acc;
        this.queue = queue;
    }
    
    @Override
    public void run(){
        while (acc.isAlive()){
            try {
                int price = 100 + rand.nextInt(100);
                String msg = this.getName() + ", " + price + "$";
                queue.addLast(msg);
                if (!acc.isAlive())this.yield();
                else this.sleep(delay);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
