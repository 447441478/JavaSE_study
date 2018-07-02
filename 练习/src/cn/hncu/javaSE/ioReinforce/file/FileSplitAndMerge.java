package cn.hncu.javaSE.ioReinforce.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.Test;

/**
 * 2018年5月6日 上午10:30:14
 * @author <a href="mailto:447441478@qq.com">宋进宇</a>
 *	演示文件切割和合并
 */
public class FileSplitAndMerge {
	//文件切割
	@Test
	public void split(){
		//创建文件选择窗口
		JFileChooser jfc = new JFileChooser();
		//显示出来
		int sel = jfc.showOpenDialog(null);
		if ( sel == JFileChooser.APPROVE_OPTION ) {
			//获取要被切割的文件
			File srcFile = jfc.getSelectedFile();
			//获取要存放的目标文件夹
			//这里直接在源文件的 文件夹下创建一个子文件。
			File targetDir = new File( srcFile.getParentFile(), "splitFiles" );
			try {
				//1：切割成指定大小
				splitFileBySize( srcFile, targetDir , 128*1024 );//128KB
				//2:切割成指定个数
				//splitFileByNum( srcFile, targetDir, 10 );
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "切割失败！");
			}
		}
		
	}
	/**
	 * 切割成指定大小 的碎片文件
	 * @param srcFile 被切割的文件
	 * @param targetDir 目标文件夹
	 * @param size 指定大小(必须大于等于1KB)
	 * @throws IOException 出错异常
	 */
	private void splitFileBySize(File srcFile, File targetDir, long size ) throws IOException {
		//被切割的文件若不存在就返回
		if ( srcFile == null || !srcFile.exists() ) {
			return;
		}
		//判断存放的文件夹是否存在，如果不存在就创建
		if ( !targetDir.exists() ) {
			targetDir.mkdirs();
		}
		if ( size <1024 ){
			return;
		}
		//创建文件字节输入流
		BufferedInputStream bis = new BufferedInputStream(
									  new FileInputStream( srcFile ) );
		//把源文件分割成 每个（除了最后一个）大小都为1MB 的碎片文件
		byte[] buf = new byte[1024];//一次读1KB;
		int len = -1;
		long curSize = 0L;//表示当前碎片文件的大小
		int count = 1;//表示第 count 个碎片文件
		BufferedOutputStream bos = new BufferedOutputStream( 
									   new FileOutputStream( 
										   new File( targetDir,srcFile.getName() + count + ".frag" ) ) );
		while ( ( len = bis.read( buf ) ) != -1 ) {
			curSize += len ;
			if ( curSize >= size ) {
				//能到这里说明 当前 碎片文件 如果全部写入就会超出
				//所以要分割 
				curSize -= size; //下一个碎片文件中字节的大小
				//把当前碎片文件写满
				bos.write( buf, 0, (int)(len-curSize) );
				bos.close();
				
				//更新当前 碎片文件
				count++;
				bos = new BufferedOutputStream( 
						   new FileOutputStream( 
							   new File( targetDir,srcFile.getName() + count + ".frag" ) ) );
				bos.write( buf, (int)(len-curSize), (int)curSize );
			}else{
				bos.write( buf, 0, len );
			}
		}
		
		bis.close();
		bos.close();
	}
	/**
	 * 通过指定个数切割文件
	 * @param srcFile 被切割的文件
	 * @param targetDir 存放的目标文件夹
	 * @param i 指定个数
	 * @throws IOException 出错异常
	 */
	public void splitFileByNum(File srcFile, File targetDir, int i) throws IOException {
		//被切割的文件若不存在就返回
		if ( srcFile == null || !srcFile.exists() ) {
			return;
		}
		//判断存放的文件夹是否存在，如果不存在就创建
		if ( !targetDir.exists() ) {
			targetDir.mkdirs();
		}

		long fileSize = srcFile.length();
		//如果文件大小小于切割的个数 则无法切割
		if ( fileSize < i ) {
			return ;
		}
		long fragSize = fileSize/i;
		//创建文件字节输入流
		BufferedInputStream bis = new BufferedInputStream(
									  new FileInputStream( srcFile ) );
		byte[] buf = new byte[1024];
		int len = -1;
		long curSize = 0L; //表示当前文件的容量
		int count = 1;//表示 第 count 个文件碎片
		BufferedOutputStream bos = new BufferedOutputStream( 
									   new FileOutputStream( 
										   new File( targetDir, srcFile.getName() + count + ".frag" ) ) );
		while ( ( len = bis.read( buf ) ) != -1 ) {
			curSize += len;
			if ( curSize >= fragSize ) {
				count++;
				if (count <= i) {
					//能进入到这里说明 不是最后一个碎片文件
					curSize = (int) (curSize-fragSize);
					bos.write( buf, 0, (int)( len - curSize ) );
					bos.flush();
					bos.close();
					bos = new BufferedOutputStream( 
							   new FileOutputStream( 
								   new File( targetDir, srcFile.getName() + count + ".frag" ) ) );
					
					bos.write( buf, (int)( len - curSize ), (int) curSize );
				} else {
					bos.write( buf, 0, len );
				}
				
			}else{
				bos.write( buf, 0, len );
			}
		}
		bis.close();
		bos.close();
	}
	
	//文件合并
	@Test
	public void merge(){
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		int sel = jfc.showOpenDialog( null );
		if ( sel == JFileChooser.APPROVE_OPTION ) {
			//选择存放  要合并的 文件碎片 的文件夹
			File srcDir = jfc.getSelectedFile();
			//这里的文件名 需要 自己控制
			String targetFileName = "四大名著【精校合集】（红+西+水+三）.rar";//这里可以指定哪个文件
			try {
				mergeFile( srcDir, targetFileName );
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "文件合并失败！");
			}
		}
	}
	private void mergeFile(File srcDir, String targetFileName) throws IOException {
		//预防一下
		if( srcDir == null || !srcDir.exists() ){
			return ;
		}
		if ( targetFileName == null || "".equals(targetFileName) ) {
			return ;
		}
		File[] files = srcDir.listFiles();
		//这里需注意 要  排序  如果碎片文件个数 大于 等于 10个 合并就会出问题！！！
		Arrays.sort( files, new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				if ( o1.getName().length() > o2.getName().length() ) {
					return 1;
				} else if ( o1.getName().length() == o2.getName().length() ){
					return o1.getName().compareTo( o2.getName() );
				} else {
					return -1;
				}
			}
		} );
		ArrayList<FileInputStream> list = new ArrayList<FileInputStream>();
		//遍历该文件夹下所有文件
		int k = 1;
		for (int i = 0; i < files.length; i++) {
			//把符合的文件 加入 list
			if ( files[i].getName().contains( targetFileName + k + ".frag" ) ) {
				list.add( new FileInputStream( files[i] ) );
				k++;
			}
		}
		//如果没有碎片文件则退出
		if ( k == 1 ) {
			return ;
		}
		//通过 集合的工具类 Collections 获取 Enumeration 对象
		Enumeration<FileInputStream> en = Collections.enumeration( list );
		//通过 Enumeration 对象 构造 序列流 就是把多个输入流的出口合并成一个出口
		SequenceInputStream sis = new SequenceInputStream( en );
		//加Buffered 加快读取速度
		BufferedInputStream bis = new BufferedInputStream( sis );
		//加Buffered 加快写的速度
		BufferedOutputStream bos = new BufferedOutputStream( 
									   new FileOutputStream( 
										   new File( srcDir, targetFileName ) ) );
		byte[] buf = new byte[1024];
		int len = -1;
		while ( ( len = bis.read( buf ) ) != -1 ) {
			bos.write( buf, 0, len );
		}
		sis.close();
		bis.close();
		bos.close();
	}
}
