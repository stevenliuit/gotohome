package org.jamie.test.readFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadDomain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "";
		
		 try {
	            String encoding = "GBK"; // 字符编码(可解决中文乱码问题 )

	            File file = new File("D:\\TDDOWNLOAD\\domain\\每日下载待删除域名\\2011-10-20.txt");

	            if (file.isFile() && file.exists()) {
	                InputStreamReader read = new InputStreamReader(
	                        new FileInputStream(file), encoding);

	                BufferedReader bufferedReader = new BufferedReader(read);
	                String lineTXT = null;
	                while ((lineTXT = bufferedReader.readLine()) != null) {
	                	
	                    String domainName = lineTXT.substring(0, lineTXT.indexOf("."));
	                    //System.out.println(domainName);
	                    if(domainName!=null &&domainName.length()==4 ){
	                    	// System.out.println(lineTXT );
	                    }
	                    
	                    String lastDomain = lineTXT.substring( lineTXT.indexOf("."),lineTXT.length());
	                    //System.out.println(lastDomain);
	                    if(".com".equalsIgnoreCase(lastDomain) ){
	                    	 System.out.println(lineTXT );
	                    }
	                    
//	                    //将文本读取出来的字符去掉","  因为读取出来的字符是用","来分隔的
//	                    String text = lineTXT.replaceAll(",", ""); 
//	                    
//	                    //用字符分隔成数组
//	                    String[] version_1 = text.split("-"); 
//
//	                    for (int i = 0; i < version_1.length; i++) {
//	                        
//	                        String[] version_2 = version_1[i].split(":");
//
//	                        for (int j = 0; j < version_2.length; j++) {
//	                            System.out.println(version_2[j]);
//	                        }
//	                        System.out.println("-------");
//	                    }
	                }
	                read.close();
	            }else{
	                System.out.println("找不到指定的文件！");
	            }
	        } catch (Exception e) {
	            System.out.println("读取文件内容操作出错");
	            e.printStackTrace();
	        }

		
	}

}
