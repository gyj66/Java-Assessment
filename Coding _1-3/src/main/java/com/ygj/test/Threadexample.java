package com.ygj.test;

import java.util.LinkedList;

public class Threadexample {
     
	 public static void main(String[] args) 
		        throws InterruptedException 
		    { 
		        
		        final PC pc = new PC(); 
		  
		        // Create producer thread 
		        Thread t1 = new Thread(new Runnable() { 
		            @Override
		            public void run() 
		            { 
		                try { 
		                    pc.produce(); 
		                } 
		                catch (InterruptedException e) { 
		                    e.printStackTrace(); 
		                } 
		            } 
		        }); 
		  
		        // Create consumer thread 
		        Thread t2 = new Thread(new Runnable() { 
		            @Override
		            public void run() 
		            { 
		                try { 
		                    pc.consume(); 
		                } 
		                catch (InterruptedException e) { 
		                    e.printStackTrace(); 
		                } 
		            } 
		        }); 
		  
		        // Start both threads 
		        t1.start(); 
		        t2.start(); 
		  
		        // t1 finishes before t2 
		        t1.join(); 
		        t2.join(); 
		    } 
		  
		   
		    public static class PC { 
		  
		       
		        LinkedList<Order> list = new LinkedList<>(); 
		        int capacity = 5; 
		  
		        // Function called by producer thread 
		        public void produce() throws InterruptedException 
		        { 
		            int ordernum = 1; 
		            while (ordernum<=100) { 
		                synchronized (this) 
		                { 
		                    // producer thread waits while list 
		                    // is full 
		                    while (list.size() == capacity) 
		                    {
		                    	 System.out.println("Waiting for order process"); 
		                    	 wait(); 
		                    }
		                       
		  
		                   
		  
		                    // to insert the jobs in the list 
		                    list.add(new Order(ordernum++,"NEW")); 
		  
		                    // notifies the consumer thread that 
		                    // now it can start consuming 
		                    notify(); 
		  
		                    // makes the working of program easier 
		                    // to  understand 
		                    Thread.sleep(1000); 
		                } 
		            } 
		        } 
		  
		        // Function called by consumer thread 
		        public void consume() throws InterruptedException 
		        { 
		            while (true) { 
		                synchronized (this) 
		                { 
		                    // consumer thread waits while list 
		                    // is empty 
		                    while (list.size() == 0) 
		                        wait(); 
		  
		                    // to retrive the ifrst job in the list 
		                   Order element = list.removeFirst(); 
		                   
		                   element.state="FULFILLED";
		  
		                    System.out.println("Order"+element.id+ "has been processed"
		                                       ); 
		  
		                    // Wake up producer thread 
		                    notify(); 
		  
		                    // and sleep 
		                    Thread.sleep(1000); 
		                } 
		            } 
		        } 
		    } 
		}

 class Order
 {
  int id;
  String state;
  
  public Order(int id,String state)
  {
	  this.id=id;
	  this.state=state;
  }
  
 }

	

