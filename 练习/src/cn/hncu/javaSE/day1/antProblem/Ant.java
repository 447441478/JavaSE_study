package cn.hncu.javaSE.day1.antProblem;

public class Ant {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private double position;//蚂蚁位置
	private boolean left;
	public Ant() {
	}
	public Ant(int position) {
		this.position = position;
	}
	public void move(){
		if (left) {
			position-=0.5;
		}else{
			position+=0.5;
		}
	}
	public double getPosition() {
		return position;
	}
	public void setPosition(double position) {
		this.position = position;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
}
