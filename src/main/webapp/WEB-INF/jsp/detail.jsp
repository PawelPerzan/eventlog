<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<b>Event Detail page:<b/><br/><br/>



<!-- Event Name:[todo]<br/>
Event Location:[todo]<br/>
Etc... -->

  <table>
    <tr>
      <th>Event Name:</th>
      <td><b>${event.name}</b></td>
    </tr>
    <tr>
      <th>Date</th>
      <td>${event.eventDate}</td>
    </tr>
    <tr>
      <th>Event Location</th>
      <td>${event.location}</td>
    </tr>
    <tr>
      <th>Description </th>
      <td>${event.description}</td>
    </tr>
  </table>
  <table>
    <tr>
      <td>
        <spring:url value="{eventId}/edit" var="editUrl">
        	<spring:param name="eventId" value="${event.id}" />
        </spring:url>
        <a href="${fn:escapeXml(editUrl)}">Edit Event</a>
      </td>

    </tr>
  </table>