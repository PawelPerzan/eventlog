<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2><fmt:message key="home"/></h2>

<ul>
  <li><a href="<c:url value="/addEvent.do"/>">Add Event</a></li>
  <li><a href="<c:url value="/findEvents.do"/>">Find events</a></li>
  <li><a href="<c:url value="/events.do"/>">Display all events</a></li>
  <li><a href="<c:url value="/sendSns.do"/>">Send Notification</a></li>
  
</ul>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
