
<%@include file="/libs/foundation/global.jsp"%><%@page session="false" %>




<%@include file="/apps/training/components/page/global.jsp"%>

<%@taglib prefix="action" uri="http://com.virtusa.training/taglibs/action/1.0" %><%
%><%@taglib prefix="func" uri="http://com.virtusa.training/taglibs/func/1.0" %><%
%>
<action:action actionClassName="com.virtusa.training.action.ImageGalleryAction" bean="ib" actionName="imageGallery"  />
hello123


<a href="#"><img src="${ib.image}" width="20%" height="20%"/></a>

<a href="#"><img src="${ib.image1}" width="20%" height="20%"/></a>
<a href="#"><img src="${ib.image2}" width="20%" height="20%"/></a>






