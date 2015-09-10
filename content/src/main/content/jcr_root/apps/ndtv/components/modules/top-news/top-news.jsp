<%
%>
<%@include file="/apps/ndtv/global/global.jsp"%>
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

    TopStories



  ==============================================================================



--%><%@page session="false" contentType="text/html; charset=utf-8" %><%
%><%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0" %><%
%><cq:defineObjects/><%
%>


This is Top-Stories component..........
<action:action actionClassName="com.ndtv.action.TopStoryAction" bean="topStories" actionName="topStories"  />
<div class="hmpage_llhs" xmlns="http://www.w3.org/1999/xhtml"><div class="widgetheader_red">
<span><a href="#" shape="rect"><span>${topStories.title}</span></a></span></div>
<div class="storylist_nothumb top-stories-68" data-tb-region="top-stories ">


<c:forEach var="obj" items="${topStories.news}">

     <ul>

<li data-tb-region-item="data-tb-region-item">


<div class="description">
    <h2>
        <a class="item-title" href="#" onclick="_gaq.push(['_trackEvent', 'top-stories', 'Clicked', 'Sensex Slumps Over 600 Points ']);" shape="rect"><c:out value="${obj}"></c:out> </a> 
        <!--| <a href="#" shape="rect">hfjh</a> -->
    </h2>
    </div>
      </li>


    </ul>


    </c:forEach>
 </div>
</div>

