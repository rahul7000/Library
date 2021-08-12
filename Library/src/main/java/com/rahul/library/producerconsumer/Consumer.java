package com.rahul.library.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
	// queue
	private BlockingQueue<Integer> queue;
	// thread id
	private String threadId;
	// poison element
	private Integer poison = -1;

	// cons to initialize
	public Consumer(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	// overridden run method
	public void run() {
		threadId = "Consumer-" + Thread.currentThread().getId();
		// handle exception
		try {
			// loop
			while (true) {
				// poll from queue
				Integer number = queue.poll(5, TimeUnit.SECONDS);
				// check
				if (number == null || number == poison) {
					break;
				}
				// consume task
				consume(number);
				// sleep for 1000ms
				Thread.sleep(1000);
			}
			System.out.println(threadId + " STOPPED.");
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	// action performed by consumer thread
	private void consume(Integer number) {
		System.out.println(threadId + ": Consuming number <= " + number);
	}
}