package org.jamie.test.conf;

import java.io.File;
import java.io.IOException;

public class ConnectionPool {
	
	 static java.io.InputStream in;
    //静态初始化器，将在ConnectionPools加载时自动执行
    static{
        java.util.Properties dbProp=new java.util.Properties();
        in=PropHelper.guessPropFile(org.jamie.test.conf.ConnectionPool.class,"database.properties");
        if(in!=null)
			try {
				dbProp.load(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        //利用dbProp，为相应的数据源对象设置相关的属性，比如C3P0........
     }

    public static void main(String[] args){
    	try {
    		 //得到类的类装载器
            ClassLoader loader=ConnectionPool.class.getClassLoader();
           Package pac = ConnectionPool.class.getPackage();
            //先从当前类所处路径的根目录中寻找属性文件
           // java.io.InputStream in=loader.getResourceAsStream("database.properties");
           String packPath = pac.getName();
			System.out.println(packPath);
			System.out.println(File.separator+"file");
			System.out.println(packPath.replace(".", "/"));
			System.out.println(packPath.replaceAll("\\.", "/"));
			   String curDir=System.getProperty("user.dir");
			System.out.println(curDir);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
