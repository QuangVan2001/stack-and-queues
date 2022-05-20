/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack_and_queues;

import java.util.LinkedList;

/**
 *
 * @author QUANG VAN
 */
public class Accountant implements Runnable{
    LinkedList<String> queue;
    int duty;
    int count = 0;
    
    public Accountant(int duty, LinkedList<String> queue){
        this.duty = duty;
        this.queue = queue;
    }
    
    @Override
    public void run(){
        while (count<duty){
            try{
                if (!queue.isEmpty()){
                    count++;
                    String info = "Invoice" + count + "/" + duty + 
                            ":" + queue.removeFirst();
                    System.out.println(info);
                }
                if (count == duty) Thread.currentThread().yield();
                else Thread.currentThread().sleep(500);      
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }   
}
