package cn.hncu.javaSE.threadReinforce.shareStack;

/**
 * 2018年5月1日 上午10:24:48
 * @author <a href="mailto:447441478@qq.com">宋进宇</a> 
 * 	共享栈：
 * 多线程共享一个“栈” 只有 信号(signal) 为 true 才能 放(push)
 * 只有 信号(signal) 为 false 才能 取(pop)
 */
public class MyStack {
	private char[] chs = new char[6];
	private int index = 0;//栈顶的位置
	
	private boolean signal = true;
	/*
	 * 这里不做越界判断
	 */
	public synchronized void push(char c) {
		while(!signal) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		chs[index] = c;
		index++;
		signal = false;
		notifyAll();
	}

	/*
	 * 这里不做越界判断
	 */
	public synchronized char pop() {
		while(signal) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		index--;
		System.out.println(chs[index]);
		signal = true;
		notifyAll();
		return chs[index];
	}
}
