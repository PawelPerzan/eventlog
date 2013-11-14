<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<b>All Events:</b><br/>
<table>
	<tr>
		<td><b>Id</b></td>
		<td><b>Name</b></td>
		<td><b>Date</b></td>
		<td><b>Location</b></td>
		<td><b>Description</b></td>
	</tr>
	<c:forEach var="event" items="${events}">
    <tr>
      <td>${event.id}</td>
      <td>
	      <spring:url value="events/{eventId}" var="eventUrl">
	     	<spring:param name="eventId" value="${event.id}"/>
	      </spring:url>
	      <a href="${fn:escapeXml(eventUrl)}">${event.name}</a>
      </td>
      <td>${event.eventDate}</td>
      <td>${event.location}</td>
      <td>${event.description}</td>
    </tr>
  	</c:forEach>
  	<tr><td><a href="<c:url value="/addEvent"/>">Add Event</a></td></tr>
</table>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
