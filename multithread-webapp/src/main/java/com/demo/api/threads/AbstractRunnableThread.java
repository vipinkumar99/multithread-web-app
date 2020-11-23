package com.demo.api.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractRunnableThread implements Runnable {

	private int noOfRetriesLeft = noOfRetries();

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		log.info("Started. ::{}", start);
		do {
			preformTask();
			noOfRetriesLeft = 0;
		} while (noOfRetriesLeft > 0);
		long end = System.currentTimeMillis();
		log.info("Completed. Total Time Taken::{}", (end - start));
	}

	public abstract void preformTask();

	public abstract int noOfRetries();

	protected int noOfRetriesLeft() {
		return noOfRetriesLeft;
	}
}
