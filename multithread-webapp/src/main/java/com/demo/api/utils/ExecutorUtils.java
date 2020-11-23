package com.demo.api.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.util.CollectionUtils;

public abstract class ExecutorUtils {
	private static ExecutorService service = Executors.newFixedThreadPool(20);

	public static void executeAsycRunnables(List<? extends Runnable> taskList) {
		if (!CollectionUtils.isEmpty(taskList)) {
			taskList.forEach(task -> service.execute(task));
		}
	}

	public static void executeSingleAsycRunnable(Runnable task) {
		if (task != null) {
			executeAsycRunnables(Arrays.asList(task));
		}
	}

	public static <T> void submitAsycCallables(List<? extends Callable<T>> taskList) {
		if (!CollectionUtils.isEmpty(taskList)) {
			taskList.forEach(task -> service.submit(task));
		}
	}

	public static <T> void submitSingleAsycCallable(Callable<T> task) {
		if (task != null) {
			submitAsycCallables(Arrays.asList(task));
		}
	}

	public static void executeRunnables(List<? extends Runnable> taskList, int poolSize) {
		if (!CollectionUtils.isEmpty(taskList)) {
			ExecutorService service = Executors.newFixedThreadPool(poolSize <= 0 ? 5 : poolSize);
			taskList.forEach(task -> service.execute(task));
			service.shutdown();
		}
	}

	public static void executeSingleRunnable(Runnable task) {
		if (task != null) {
			executeRunnables(Arrays.asList(task), 1);
		}
	}

	public static <T> void submitCallables(List<? extends Callable<T>> taskList, int poolSize) {
		if (!CollectionUtils.isEmpty(taskList)) {
			ExecutorService service = Executors.newFixedThreadPool(poolSize <= 0 ? 5 : poolSize);
			taskList.forEach(task -> service.submit(task));
			service.shutdown();
		}
	}

	public static <T> void submitSingleCallable(Callable<T> task) {
		if (task != null) {
			submitCallables(Arrays.asList(task), 1);
		}
	}

	public static <T> List<T> tryExecuteCallables(List<? extends Callable<T>> taskList, int poolSize) {
		if (!CollectionUtils.isEmpty(taskList)) {
			ExecutorService service = Executors.newFixedThreadPool(poolSize <= 0 ? 5 : poolSize);
			List<Future<T>> futures = null;
			try {
				futures = service.invokeAll(taskList);
			} catch (InterruptedException e) {

			}
			service.shutdown();
			List<T> result = new ArrayList<T>();
			futures.forEach(future -> {
				try {
					result.add(future.get());
				} catch (InterruptedException | ExecutionException e) {
				}
			});
			return result;
		}
		return null;
	}
}
