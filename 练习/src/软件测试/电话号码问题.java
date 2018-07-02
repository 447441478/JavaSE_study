package 软件测试;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 电话号码问题 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			Pattern p = Pattern.compile("^(\\d{4})?[2-9]{1}\\d{6}$");
			Matcher matcher = p.matcher(sc.next());
			if (matcher.find()) {
				System.out.println("满足条件");
			} else {
				System.err.println("不满足条件");
			}
		}
		sc.next();
	}
}
