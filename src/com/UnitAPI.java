package com;

import java.io.IOException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Unit;

/**
 * Servlet implementation class UnitAPI
 */
@WebServlet("/UnitAPI")
public class UnitAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Unit unitsObj = new Unit();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnitAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Output= unitsObj.readunit();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().write(Output);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output= unitsObj.insertUnit(request.getParameter("AccountNo"),
				 request.getParameter("CusName"),
				 request.getParameter("Unit"),
				 request.getParameter("Month"),
				 request.getParameter("Amount"));
		         response.getWriter().write(output);
	}
	

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			String output= unitsObj.updateUnit(request.getParameter("hidIDSave"),
					request.getParameter("AccountNo"),
					request.getParameter("CusName"),
					 request.getParameter("Unit"),
					 request.getParameter("Month"),
					 request.getParameter("Amount"));
			         response.getWriter().write(output);

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		 String output = unitsObj.deleteUnit(paras.get("UnitID").toString());
		response.getWriter().write(output);
	}

	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
		 String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}

}
