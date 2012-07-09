package org.jamie.test.conf;

import java.io.File;
import java.io.IOException;

public class ConnectionPool {
	
	 static java.io.InputStream in;
    //��̬��ʼ����������ConnectionPools����ʱ�Զ�ִ��
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
        //����dbProp��Ϊ��Ӧ������Դ����������ص����ԣ�����C3P0........
     }

    public static void main(String[] args){
    	try {
    		 //�õ������װ����
            ClassLoader loader=ConnectionPool.class.getClassLoader();
           Package pac = ConnectionPool.class.getPackage();
            //�ȴӵ�ǰ������·���ĸ�Ŀ¼��Ѱ�������ļ�
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
