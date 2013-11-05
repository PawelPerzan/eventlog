<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2><fmt:message key="home"/></h2>

<ul>
  <li><a href="<c:url value="/addEvent"/>">Add Event</a></li>
  <li><a href="<c:url value="/findEvents"/>">Find events</a></li>
  <li><a href="<c:url value="/events"/>">Display all events</a></li>
<%--   <li><a href="<c:url value="/sendSns"/>">Send Notification</a></li> --%>
  
</ul>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
