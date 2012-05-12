package com.demo.httpclient;

import java.util.HashMap;

public class CommonConst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static final String HOTELBE_HEADER_USERID = "9091";
	public static final String HOTELBE_HEADER_PASSWORD = "traVL87sky";
	public static final String HOTELBE_HEADER_XMLNS = "Production";

	// HttpStatus
	public static HashMap<String, String> httpStatusCodeMap = new HashMap<String, String>();

	static {
		// 收到请求(PROCESS):请求收到，继续处理
		httpStatusCodeMap.put("100", "收到请求:客户端应该继续发出请求。");
		httpStatusCodeMap.put("101", "收到请求:客户端要求服务器根据请求转换HTTP协议版本。");

		// 请求成功(SUCCESS):操作成功收到，分析、接受
		httpStatusCodeMap.put("200", "请求成功:交易成功。");
		httpStatusCodeMap.put("201", "请求成功:请求已经完成并一个新的返回资源被创建。");
		httpStatusCodeMap.put("202", "请求成功:请求已经被接受和处理,但是处理并没有完成。");
		httpStatusCodeMap.put("203", "请求成功:返回信息不确定或不完整。");
		httpStatusCodeMap.put("204", "请求成功:服务器已经接受请求，但返回信息为空。");
		httpStatusCodeMap.put("205", "请求成功:服务器已经接受请求，并且用户代理应该重新设置文档视图。");
		httpStatusCodeMap.put("206", "请求成功:服务器已经接受请求GET请求资源的部分。");

		// 重新定向(REDIRECTION):完成此请求必须进一步处理
		httpStatusCodeMap.put("300", "重新定向:请求的资源可在多处得到。");
		httpStatusCodeMap.put("301", "重新定向:删除请求数据。");
		httpStatusCodeMap.put("302", "重新定向:在其他地址发现了请求数据。");
		httpStatusCodeMap.put("303", "重新定向:建议客户访问其他URL或访问方式。");
		httpStatusCodeMap.put("304", "重新定向:客服端已经完成一个有条件的请求并且请求是允许的，但是这个文档并没有改变。");
		httpStatusCodeMap.put("305", "重新定向:请求的资源必须通过代理（由Location字段指定）来访问。");
		httpStatusCodeMap.put("306", "重新定向:前一版本HTTP中使用的代码，现行版本中不再使用。");
		httpStatusCodeMap.put("307", "重新定向:申明请求的资源临时性删除。");

		// 客户端错误(Client Error) :请求包含一个错误语法或不能完成
		httpStatusCodeMap.put("400", "客户端错误 :因为错误的语法导致服务器无法处理请求信息。");
		httpStatusCodeMap.put("401", "客户端错误 :请求授权失败，应该包含一个WWW-Authenticate头字段用来指明请求资源的权限。");
		httpStatusCodeMap.put("402", "客户端错误 :保留有效ChargeTo头响应。");
		httpStatusCodeMap.put("403", "客户端错误 :服务器接受请求，但是被拒绝处理。");
		httpStatusCodeMap.put("404", "客户端错误 :服务器没有找到任何匹配Request-URI的资源。");
		httpStatusCodeMap.put("405", "客户端错误 :Request-Line请求的方法不被允许通过指定的URI。");
		httpStatusCodeMap.put("406", "客户端错误 :根据用户发送的Accept拖，请求资源不可访问。");
		httpStatusCodeMap.put("407", "客户端错误 :类似401，用户必须首先在代理服务器上得到授权。");
		httpStatusCodeMap.put("408", "客户端错误 :在服务器等待处理时间内，客服端没有提交任何请求。");
		httpStatusCodeMap.put("409", "客户端错误 :对当前资源状态，请求不能完成。");
		httpStatusCodeMap.put("410", "客户端错误 :服务器上不再有此资源且无进一步的参考地址。");
		httpStatusCodeMap.put("411", "客户端错误 :服务器拒绝接受没有定义Content-Length属性的请求。");
		httpStatusCodeMap.put("412", "客户端错误 :一个或多个请求头字段在当前请求中错误。");
		httpStatusCodeMap.put("413", "客户端错误 :服务器拒绝处理请求因为请求数据超过服务器能够处理的范围。服务器可能关闭当前连接来阻止客服端继续请求。");
		httpStatusCodeMap.put("414", "客户端错误 :服务器拒绝服务当前请求因为URI的长度超过了服务器的解析范围。");
		httpStatusCodeMap.put("415", "客户端错误 :服务器拒绝服务当前请求因为请求数据格式并不被请求的资源支持。");
		httpStatusCodeMap.put("416", "客户端错误 :请求中包含Range请求头字段，在当前请求资源范围内没有range指示值，请求也不包含If-Range请求头字段。");
		httpStatusCodeMap.put("417", "客户端错误 :服务器不满足请求Expect头字段指定的期望值，如果是代理服务器，可能是下一级服务器不能满足请求。");

		// 服务器错误(Server Error):服务器执行一个完全有效请求失败
		httpStatusCodeMap.put("500", "服务器错误:服务器遭遇异常，产生内部错误，并阻止了当前请求的执行。");
		httpStatusCodeMap.put("501", "服务器错误:服务器没有相应的执行动作来完成当前请求。 ");
		httpStatusCodeMap.put("502", "服务器错误:网关错误，服务器暂时不可用，在代理模式下后端服务器出现问题引起的 。");
		httpStatusCodeMap.put("503", "服务器错误:因为临时文件超载导致服务器不能处理当前请求。");
		httpStatusCodeMap.put("504", "服务器错误:网关过载，服务器使用另一个网关或服务来响应用户，等待时间设定值较长。");
		httpStatusCodeMap.put("505", "服务器错误:服务器不支持或拒绝支请求头中指定的HTTP版本。");
	}
}
