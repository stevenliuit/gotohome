package org.jamie.test.md5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Test {

	/**commons-codec-1.3.jar
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String  pass = "weidejiushini";
		String passmd5 = getMD5Str(pass);
		System.out.println("after encryption: " +passmd5);
		
		if(1==1){
			System.out.println("1111");			
		}else if(2==2){
			System.out.println("2222");
		}
		
	}

	 private static String getMD5Str(String str) {      
	        MessageDigest messageDigest = null;      
	      
	        try {      
	            messageDigest = MessageDigest.getInstance("MD5");      
	      
	            messageDigest.reset();      
	      
	            messageDigest.update(str.getBytes("UTF-8"));      
	        } catch (NoSuchAlgorithmException e) {      
	            System.out.println("NoSuchAlgorithmException caught!");      
	            System.exit(-1);      
	        } catch (UnsupportedEncodingException e) {      
	            e.printStackTrace();      
	        }      
	      
	        byte[] byteArray = messageDigest.digest();      
	      System.out.println("byteArray "+ byteArray.toString());
	        StringBuffer md5StrBuff = new StringBuffer();      
	      
	        for (int i = 0; i < byteArray.length; i++) {                  
	            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)      
	                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));      
	            else      
	                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));      
	        }      
	      
	        return md5StrBuff.toString();      
	    }     

}
