<%@page import="model.Unit"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("AccountNo") != null)
 {
 Unit unitsObj = new Unit();
 String stsMsg = unitsObj.insertUnit(request.getParameter("AccountNo"),
 request.getParameter("CusName"),
 request.getParameter("Unit"),
 request.getParameter("Month"),
 request.getParameter("Amount"));
 
 session.setAttribute("statusMsg", stsMsg);
 }




//Delete item----------------------------------
if (request.getParameter("UnitID") != null)
{
Unit unitsObj = new  Unit();
String stsMsg = unitsObj.deleteUnit(request.getParameter("UnitID"));
session.setAttribute("statusMsg", stsMsg);
}


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Units Management</title>
</head>
<body>
<h1>Units Management</h1>
<form method="post" action="units.jsp">
  Account NO: <input name="AccountNo" type="text"><br>
   User Name: <input name="CusName" type="text"><br>
  Usage Unit: <input name="Unit" type="text"><br>
 Usage Month: <input name="Month" type="text"><br>
Usage Amount: <input name="Amount" type="text"><br>
 <input name="btnSubmit" type="submit" value="Save">
</form>
<%
 out.print(session.getAttribute("statusMsg"));
%>

<br>
<%
Unit unitsObj = new  Unit();
 out.print(unitsObj.readunit());
%>


</body>
</html>