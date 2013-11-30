<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<h3 style="background-color:#e0e0ff;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">All Events:</h3>
<div id="eventContent" style="border: 1px solid; width=500px;">
<table>
	<tr style="background-color:#e0e0e0;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
		<td><b>Id</b></td>
		<td><b>Name</b></td>
		<td><b>Date</b></td>
		<td><b>Location</b></td>
		<td><b>Description</b></td>
	</tr>
	<c:forEach var="event" items="${events}">
    <tr style="background-color:#e0e0e0;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
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
  	<tr style="background-color:#e0e0e0;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px"><td><a href="<c:url value="/addEvent"/>">Add Event</a></td></tr>
</table>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
