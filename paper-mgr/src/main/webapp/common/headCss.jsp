<%@ page language="java" import="java.net.URLDecoder" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
    String imagePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()+ "/";
%>

  <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/style-index.css"> 
  <link rel="stylesheet" href="<%=basePath %>/js/kindeditor/themes/default/default.css" />	
  <link rel="stylesheet" href="<%=basePath %>/js/pagination.mine.css" />
   <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.6.2/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/style-index.css">
  <link rel="stylesheet" href="<%=basePath %>/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=basePath %>/css/font-awesome.min.css">

  <link rel="stylesheet" href="//cdn.bootcss.com/prism/1.4.1/themes/prism.min.css">