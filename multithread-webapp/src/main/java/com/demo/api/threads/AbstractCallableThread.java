package com.demo.api.threads;

import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractCallableThread<V> implements Callable<V> {

	private int noOfRetriesLeft;

	@Override
	public V call() throws Exception {
		long start = System.currentTimeMillis();
		log.info("Started. ::{}", start);
		V result = null;
		do {
			try {
				result = performTask();
				noOfRetriesLeft = 0;
			} catch (Exception e) {
				log.error("Unknown exception occured. Will retry again. Total retries left {}.", e, noOfRetriesLeft);
			}
		} while (noOfRetriesLeft > 0);
		long end = System.currentTimeMillis();
		log.info("Completed. Total Time Taken::{}", (end - start));
		return result;
	}

	protected abstract V performTask() throws Exception;

	protected abstract int noOfRetries();

	protected int noOfRetriesLeft() {
		return noOfRetriesLeft;
	}

}
