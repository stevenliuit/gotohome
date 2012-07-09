package org.jamie.test.swing;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Test extends JFrame {
	 private TrayIcon trayIcon;//����ͼ��
	    private SystemTray systemTray;//ϵͳ����
	         
	    public Test()
	    {
	        super("ϵͳ����ͼ��");
	        systemTray = SystemTray.getSystemTray();//���ϵͳ���̵�ʵ�� 
	        setSize(150,150); 
	                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                this.setVisible(true);    
	        try {
	            trayIcon = new TrayIcon(ImageIO.read(new File("0.gif")));
	            systemTray.add(trayIcon);//�������̵�ͼ�꣬0.gif������ļ�ͬһĿ¼
	        }
	        catch (IOException e1) {e1.printStackTrace();}
	        catch (AWTException e2) {e2.printStackTrace();}
	        
	        this.addWindowListener(
	                new WindowAdapter(){   
	                    public void windowIconified(WindowEvent e){   
	                        dispose();//������С��ʱdispose�ô��� 
	                    }   
	                });
	        
	        trayIcon.addMouseListener(
	                new MouseAdapter(){
	                    public void mouseClicked(MouseEvent e){
	                        if(e.getClickCount() == 2)//˫�����̴�������
	                            setExtendedState(Frame.NORMAL);
	                            setVisible(true);
	                    }
	                });        
	    }   

	    public static void main(String args[])
	    {
	        new Test();
	    }

}
