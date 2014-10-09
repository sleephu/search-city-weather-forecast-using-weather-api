<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/screen.css">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>weather forecast </title>
</head>
<body>
<form action="ProcessReq" method="get">
<label>Search By City:</label> <input type="text" name="cityName"/>
<input type="submit"  value="Search"/> 
</form>
<%-- <% String loc= request.getParameter("weatherLocation"); %> --%>
</body>
</html>