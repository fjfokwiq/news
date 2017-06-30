package com.okw.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.okw.db.DBUtil;
import com.okw.db.Userinfo;

public class LoginData extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public LoginData() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String userinfo = request.getParameter("userInfo");

		Gson gson = new Gson();
		Userinfo user = gson.fromJson(userinfo, Userinfo.class);
		Userinfo info = DBUtil.getInstance().DBQuery(user.getUsername());
		if (info.getUsername()==null) {
			out.print(buildJson(user.getUsername(), "error"));

		} else {

			if (info.getUsername().equals(user.getUsername())
					&& info.getPassword().equals(user.getPassword())) {

				out.print(buildJson(info.getUsername(), "ok"));

			} else {
				out.print(buildJson(user.getUsername(), "error"));
			}
		}
		out.flush();
		out.close();
	}

	public static StringBuilder buildJson(String user, String result) {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("\"user\":\"");
		builder.append(user);
		builder.append("\",");
		builder.append("\"result\":\"");
		builder.append(result);
		builder.append("\"");
		builder.append("}");
		return builder;
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
