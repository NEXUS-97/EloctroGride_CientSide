package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Billing;

/**
 * Servlet implementation class DoctorAPI
 */
@WebServlet("/EmployeeApi")
public class EmployeeApi extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	Billing employeeObj = new Billing();

	public EmployeeApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String result = employeeObj.insertEmployee(request.getParameter("eName"),
				request.getParameter("eAddress"), request.getParameter("eEmail"),request.getParameter("eDate"),
				request.getParameter("pno"));

		response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */

	private Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}

		} catch (Exception e) {

		}
		return map;
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> param = getParasMap(request);

		String result = employeeObj.updateEmployee(param.get("hideIDSave").toString(),
				param.get("eName").toString().toString().replace("+", " "),
				param.get("eAddress").toString().toString().replace("+", " "),
				param.get("eEmail").toString().toString().replace("%40", "@"),
				param.get("eDate").toString().toString().replace("+", " "),
				param.get("pno").toString());

		response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> param = getParasMap(request);

		String result = employeeObj.deleteEmployee(param.get("eID").toString());

		response.getWriter().write(result);
	}

}