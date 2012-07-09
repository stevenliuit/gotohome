package org.jamie.test.convert;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class WordtoHtml {
	/**
	*�ĵ�ת������
	*@param docfile word�ĵ��ľ���·�����ļ���(������չ��)
	*@param htmlfile ת�����html�ļ�����·�����ļ���(������չ��)
	*
	*
	*
	*
	*/
	public static void change(String docfile, String htmlfile) {
	ActiveXComponent app = new ActiveXComponent("Word.Application"); // ����word
		try {
			app.setProperty("Visible", new Variant(false));
			// ����word���ɼ�
			Dispatch docs = app.getProperty("Documents").toDispatch();
			Dispatch doc = Dispatch.invoke(
					docs,
					"Open",
					Dispatch.Method,
					new Object[] { docfile, new Variant(false),
							new Variant(true) }, new int[1]).toDispatch();
			// ��word�ļ�
			Dispatch.invoke( doc, "SaveAs", Dispatch.Method,
					new Object[] { htmlfile, new Variant(8) }, new int[1]);
			// ��Ϊhtml��ʽ���浽��ʱ�ļ�
			Variant f = new Variant(false);
			Dispatch.call(doc, "Close", f);
		} catch (Exception e) {
	e.printStackTrace();
	} finally {
	app.invoke("Quit", new Variant[]{});
	}
	}
	
	
	
	public static void main(String[] strs){
	WordtoHtml.change("c:\\test\\HotelBE�ӿ��ĵ�.doc", "c:\\test\\t");

	}


}
