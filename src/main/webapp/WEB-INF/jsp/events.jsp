<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<b>All Events:</b>
<table>
	<tr>
		<td><b>Id</b></td>
		<td><b>Name</b></td>
		<td><b>Location</b></td>
		<td><b>Description</b></td>
	</tr>
	<c:forEach var="event" items="${events}">
    <tr>
      <td>${event.id}</td>
      <td>${event.name}</td>
      <td>${event.location}</td>
      <td>${event.description}</td>
    </tr>
  	</c:forEach>
</table>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>

