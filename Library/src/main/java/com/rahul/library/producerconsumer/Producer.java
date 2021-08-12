package com.rahul.library.producerconsumer;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	// Queue
	private BlockingQueue<Integer> queue;
	private Integer poison = -1;

	// Constructor to initialize
	public Producer(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	// run method for producer threaad
	public void run() {
		// handle excception
		try {
			// loop
			for (int i = 0; i < 10; i++) {
				// put task/message in the queue
				queue.put(produce());
				// sleep for 500ms
				Thread.sleep(500);
			}
			// put poison element
			queue.put(poison); // indicates end of producing
			System.out.println("Producer STOPPED.");
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	// Method to produce message/task
	private Integer produce() {
		// task/message
		Integer number = new Integer((int) (Math.random() * 100));
		System.out.println("Producing number => " + number);
		return number;
	}
}
