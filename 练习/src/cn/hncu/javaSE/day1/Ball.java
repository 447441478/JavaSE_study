package cn.hncu.javaSE.day1;
/*
  一个球从100米高度自由落下，每次落地后反跳回原高度的一半；
  再落下，求它在第10次落地时，共经过多少米？第10次反弹多高？
法1：用面向对象的方式模拟出球的运动过程来求解。
法2：用面向过程的方式以for循环的方式模拟出球的运行过程来求解。
*/
public class Ball {
	private double curHight;
	private double len;
	public Ball() {
	}
	public Ball(double curHight) {
		this.curHight = curHight;
		len = -curHight;
	}
	public void fall(){
		len += curHight*2;
	}
	public void jump(){
		curHight /= 2;
	}
	public double getCurHight() {
		return curHight;
	}
	public void setCurHight(double curHight) {
		this.curHight = curHight;
	}
	public double getLen() {
		return len;
	}
	public void setLen(double len) {
		this.len = len;
	}
	public static void main(String[] args) {
		Ball ball = new Ball(100);
		for (int i = 0; i < 10; i++) {
			ball.fall();
			ball.jump();
			System.out.println(ball.getCurHight());
			System.out.println(ball.getLen());
		}
		System.out.println(ball.getCurHight());
		System.out.println(ball.getLen());
	}
}
