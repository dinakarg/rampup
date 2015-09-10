<%--

  hello_test component.

  hello_test

--%><%
%><%@include file="/libs/foundation/global.jsp"%><%
%><%@page session="false" %><%
%><%
	// TODO add you code here
%>
<%
%>

<%
%><%@page session="false" %><%
%>

<%@include file="/apps/training/components/page/global.jsp"%>
<%@ page import="com.day.cq.commons.Doctype,
    com.day.cq.wcm.api.components.DropTarget,
    com.day.cq.wcm.foundation.Image, com.day.cq.wcm.foundation.Placeholder" %><%
%>
<%
%><cq:defineObjects/><%
%>
<%@page import="com.day.text.Text,com.day.cq.wcm.foundation.Image,com.day.cq.commons.Doctype" %>
<%@page import="com.day.cq.wcm.api.WCMMode,com.day.cq.wcm.foundation.Placeholder" %>


<action:action actionClassName="com.virtusa.training.action.HeroAction" bean="heroBean" actionName="hero"  />

<div class="ndtv_leadstory" data-tb-region="Latest Story Big"
					data-tb-region-item="data-tb-region-item">
					<div class="ndtv_leadst_mainpic">
						<div class="ndtv_lead_st_headline">
							<h1>
                                Headlines:<a class="item-title"  shape="rect">${heroBean.title} </a>
							</h1>
						</div>


                        <img style="'width:300px','height:300px'"src="${heroBean.path}">
