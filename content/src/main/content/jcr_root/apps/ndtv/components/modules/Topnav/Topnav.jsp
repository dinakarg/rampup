<%--
  Copyright 1997-2009 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================

    Topanv

    

  ==============================================================================

--%>
      <%@page session="false" contentType="text/html; charset=utf-8" %><%
%><%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0" %>
<%
%>
<!-- <cq:defineObjects/><%
      %><div class="nglobalnav_wrap" xmlns="http://www.w3.org/1999/xhtml">
<div class="nglobalnav">
    <a class="select" href='<%=properties.get("url1","")%>.html' shape="rect"><%=properties.get("title1","hai")%></a>
    <a href='<%=properties.get("url2","")%>.html' shape="rect"><%=properties.get("title2","hello")%></a>
    <a href='<%=properties.get("url3","")%>.html' shape="rect"><%=properties.get("title3","bye")%></a>
</div></div> -->
<%@include file="/apps/training/components/page/global.jsp"%>

<action:action actionClassName="com.ndtv.action.TopnavAction" bean="topnavBean" actionName="topnav"  />

<a href="${topnavBean.url1}.html">${topnavBean.title1}</a>
<a href="${topnavBean.url2}.html">${topnavBean.title2}</a> 
<a href="${topnavBean.url3}.html">${topnavBean.title3}</a> 






abccd

