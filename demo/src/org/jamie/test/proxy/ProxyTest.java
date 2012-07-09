package org.jamie.test.proxy;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class ProxyTest {
	public static void main(String[] args) {      
	    Properties prop = System.getProperties();   
	    prop.put( " proxySet " ,  " true " );

	    // ����http����Ҫʹ�õĴ���������ĵ�ַ      
	    prop.setProperty("http.proxyHost", "172.17.17.80");      
	    // ����http����Ҫʹ�õĴ���������Ķ˿�      
	    prop.setProperty("http.proxyPort", "8080");      
	    // ���ò���Ҫͨ��������������ʵ�����������ʹ��*ͨ����������ַ��|�ָ�      
	   // prop.setProperty("http.nonProxyHosts", "localhost|192.168.0.*");      
	    // ���ð�ȫ����ʹ�õĴ����������ַ��˿�      
	    // ��û��https.nonProxyHosts���ԣ�������http.nonProxyHosts �����õĹ������      
	  //  prop.setProperty("https.proxyHost", "192.168.0.254");      
	  //  prop.setProperty("https.proxyPort", "443");      
	    // ʹ��ftp������������������˿��Լ�����Ҫʹ��ftp���������������      
	 /*   prop.setProperty("ftp.proxyHost", "192.168.0.254");      
	    prop.setProperty("ftp.proxyPort", "2121");      
	    prop.setProperty("ftp.nonProxyHosts", "localhost|192.168.0.*");      
	    // socks����������ĵ�ַ��˿�      
	  */
	    prop.setProperty("socksProxyHost", "172.17.17.80");      
	    prop.setProperty("socksProxyPort", "8000");      
	    // ���õ�½��������������û���������      
	    Authenticator.setDefault(new MyAuthenticator("", ""));   
	    
	    
	}      
	static class MyAuthenticator extends Authenticator {      
	    private String user = "";      
	    private String password = "";      
	    public MyAuthenticator(String user, String password) {      
	        this.user = user;      
	        this.password = password;      
	    }      
	    protected PasswordAuthentication getPasswordAuthentication() {      
	        return new PasswordAuthentication(user, password.toCharArray());      
	    }      
	}    

}
