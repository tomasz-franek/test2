package org.example.threads;

import org.example.domain.TaskObject;

import java.util.concurrent.LinkedBlockingQueue;

public class TaskRunnableThread extends Thread implements TaskRunnable {
	boolean running = true;
	LinkedBlockingQueue<TaskObject> queue;

	@Override
	public void stopRunnable() {
		this.running = false;
	}
}
