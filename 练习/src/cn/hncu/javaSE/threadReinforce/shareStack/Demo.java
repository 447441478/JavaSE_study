package cn.hncu.javaSE.threadReinforce.shareStack;
/**
 * 2018年5月1日 上午10:33:20
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *  演示共享栈
 */
public class Demo {
	public static void main(String[] args) {
		MyStack myStack = new MyStack();
		Demo demo = new Demo();
		Thread pushThread = demo.new PushThread(myStack);
		Thread pushThread2 = demo.new PushThread(myStack);
		Thread popThread = demo.new PopThread(myStack);
		Thread popThread2 = demo.new PopThread(myStack);
		popThread2.start();
		popThread.start();
		pushThread2.start();
		pushThread.start();
		
	}
	class PushThread extends Thread {
		MyStack myStack = null;
		public PushThread(MyStack myStack) {
			this.myStack = myStack;
		}
		@Override
		public void run() {
			for (int i = 97; i < 103; i++) {
				myStack.push((char)i);
			}
		}
	}
	private class PopThread extends Thread {
		MyStack myStack = null;
		public PopThread(MyStack myStack) {
			this.myStack = myStack;
		}
		@Override
		public void run() {
			for (int i = 97; i < 103; i++) {
				myStack.pop();
			}
		}
	}
}
