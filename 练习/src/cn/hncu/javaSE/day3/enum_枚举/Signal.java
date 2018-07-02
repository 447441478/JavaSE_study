package cn.hncu.javaSE.day3.enum_枚举;

public enum Signal {
	RED("红色",1),GREEN("绿色",2),YELLOW("黄色",3);
	final private String name;
	final private int index;
	private Signal(String name, int index) {
		this.name = name;
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public int getIndex() {
		return index;
	}
	
	public int getIndexByName(String name){
		for (Signal en : Signal.values()) {
			if (en.getName().equals(name)) {
				return en.getIndex();
			}
		}
		throw new IllegalArgumentException("No such name:"+name+"is Signal!");
	}
	
	public String getNameByIndex(int index){
		for (Signal en : Signal.values()) {
			if (en.getIndex()==index) {
				return en.getName();
			}
		}
		
		throw new IllegalArgumentException("No such idnex:"+index+"is Signal!");
	}
	
	@Override
	public String toString() {
		return name+","+index;
	}
}
