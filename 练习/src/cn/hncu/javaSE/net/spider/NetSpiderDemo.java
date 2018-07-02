package cn.hncu.javaSE.net.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2018年5月12日 下午5:55:26
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	网络爬虫原理
 *	      演示 获取网络上的邮箱。
 */
public class NetSpiderDemo {
	//为了代码简洁 这里就直接抛异常了
	public static void main(String[] args) throws IOException {
		//获取统一资源定位
		//这里 就 爬我们学校官网的 邮箱
		URL url = new URL( "http://www.hncu.net" );
		//获得输入流
		BufferedReader br = new BufferedReader( 
								new InputStreamReader( url.openStream() ) );
		//定义一个正则表达式，爬邮箱---使用最简单的邮箱规则
		Pattern p = Pattern.compile( "\\w+@\\w+(\\.\\w+)+" );
		String str = br.readLine();
		while ( str != null ) {
			//进行匹配
			Matcher matcher = p.matcher( str );
			//如果有找到就把邮箱打印在控制台
			while ( matcher.find() ){
				System.out.println( matcher.group() );
			}
			str = br.readLine();
		}
		br.close();
	}
}
