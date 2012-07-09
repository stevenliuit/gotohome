package org.jamie.test.swing;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class TrayDemo extends javax.swing.JFrame {
	private TrayIcon trayIcon = null; // ����ͼ��

	private SystemTray tray = null; // ������ϵͳ���̵�ʵ��

	public static TrayDemo trayDemo = new TrayDemo();

	private TrayDemo() {
		init();
	}

	public void init() {
		if (SystemTray.isSupported()) { // �������ϵͳ֧������
			tray = SystemTray.getSystemTray(); // ��ñ�����ϵͳ���̵�ʵ��
			this.tray();
		}
		this.setSize(300, 200);
		this.setResizable(false);
		// ���ڹر�ʱ�����¼�
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				tray.remove(trayIcon);
				System.exit(0);
			}

			public void windowIconified(WindowEvent e) {
				try {
					 setVisible(false); // ʹ���ڲ�����
					//dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		this.setVisible(true);
	}

	private void tray() {

		ImageIcon icon = new ImageIcon("0.gif"); // ��Ҫ��ʾ�������е�ͼ��

		PopupMenu pop = new PopupMenu(); // ����һ���Ҽ�����ʽ�˵�
		MenuItem show = new MenuItem("�򿪳���(s)");
		MenuItem exit = new MenuItem("�˳�����(x)");
		pop.add(show);
		pop.add(exit);
		trayIcon = new TrayIcon(icon.getImage(), "��������ϵͳ", pop);

		/**
		 * ������������������������ͼ����˫��ʱ��Ĭ����ʾ����
		 */
		trayIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // ���˫��
					//tray.remove(trayIcon); // ��ϵͳ������ʵ�����Ƴ�����ͼ��
					setExtendedState(JFrame.NORMAL);
					setVisible(true); // ��ʾ����
					toFront();
				}
			}
		});
		show.addActionListener(new ActionListener() { // �������ʾ���ڡ��˵��󽫴�����ʾ����
					public void actionPerformed(ActionEvent e) {
						//tray.remove(trayIcon); // ��ϵͳ������ʵ�����Ƴ�����ͼ��
						setExtendedState(JFrame.NORMAL);
						setVisible(true); // ��ʾ����
						toFront();
					}
				});
		exit.addActionListener(new ActionListener() { // ������˳���ʾ���˵����˳�����
					public void actionPerformed(ActionEvent e) {
						tray.remove(trayIcon);
						System.exit(0); // �˳�����
					}
				});
		
		try {
			tray.add(trayIcon);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // ������ͼ����ӵ�ϵͳ������ʵ����
		// setVisible(true);
	}

	/**
	 * ��ʾ��Ϣ
	 */
	// ���ص�һ��ʵ������
	public static TrayDemo getInstanceTrayDemo() {
		return trayDemo;
	}

	// �������¼�

	public static void main(String[] args) {
		TrayDemo.getInstanceTrayDemo();
	}

}
