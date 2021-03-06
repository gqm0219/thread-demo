package com.me.countdownlaunch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLaunch 线程同步器，用于多个线程执行完成后，主线程继续执行
 */
public class Main {
	private int sum1,sum2;

	public static void main(String[] args) {
		try {
			new Main().execute();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void execute() throws InterruptedException {
		int sum = 0;

		// 参数的设置取决于执行的线程数
		CountDownLatch countDownLatch = new CountDownLatch(2);

		new Thread(()->{
			sum1 = sum(1,100);
			countDownLatch.countDown();
		}).start();

		new Thread(()-> {
			sum2 = sum(101,200);
			countDownLatch.countDown();
		}).start();

		// 等待上面两个线程执行完毕后，继续执行
		countDownLatch.await();
		sum = sum1 + sum2;
		System.out.println(sum);
	}

	public int sum(int a,int b){
		int sum = 0;
		for (int i = a; i <= b; i++) {
			sum = sum + i;
		}
		return sum;
	}

}
