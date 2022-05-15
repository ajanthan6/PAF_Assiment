<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Unit"%>
<%
//Save---------------------------------
if (request.getParameter("AccountNo") != null)
{
	Unit unitsObj = new Unit();
String stsMsg = "";
//Insert--------------------------
if (request.getParameter("hidIDSave") == "")
{
	 stsMsg = unitsObj.insertUnit(request.getParameter("AccountNo"),
			 request.getParameter("CusName"),
			 request.getParameter("Unit"),
			 request.getParameter("Month"),
			 request.getParameter("Amount"));
}
else//Update----------------------
{
stsMsg = unitsObj.updateUnit(request.getParameter("hidIDSave"),
		request.getParameter("AccountNo"),
		request.getParameter("CusName"),
		 request.getParameter("Unit"),
		 request.getParameter("Month"),
		 request.getParameter("Amount"));

}
session.setAttribute("statusMsg", stsMsg);
}
//Delete-----------------------------
if (request.getParameter("hidIDDelete") != null)
{
	Unit unitsObj = new Unit();
String stsMsg =
unitsObj.deleteUnit(request.getParameter("hidIDDelete"));
session.setAttribute("statusMsg", stsMsg);
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="Views/unit.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Unit.js"></script>
<title>Power Unit Management</title>
</head>
<body>
<h1>Power Unit Management</h1>

<div>

<form id="formUnit" name="formUnit" method="post" action="unit.jsp">
Account Number:
<input id="AccountNo" name="AccountNo" type="text"
 class="form-control form-control-sm">
<br> Customer Name:
<input id="CusName" name="CusName" type="text"
 class="form-control form-control-sm">
<br> Power Usage:
<input id="Unit" name="Unit" type="text"
 class="form-control form-control-sm">
<br> Usage Month:
<input id="Month" name="Month" type="text"
 class="form-control form-control-sm">
<br>
<br> Payment Amount:
<input id="Amount" name="Amount" type="text"
 class="form-control form-control-sm">

<br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidIDSave" name="hidIDSave" value="">
</form>
</div>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<%
 out.print(session.getAttribute("statusMsg"));
%>
<br>
<div id="divPaymentGrid">
<%
Unit unitsObj = new Unit();
out.print(unitsObj.readunit());
%>
</div>
</body>
</html>