<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<h3 style="background-color:#e0e0ff;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">Event Detail page:</h3><br/><br/>
<div id="detailContent" style="border: 1px solid; width=500px;">
  <table>
    <tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
      <th>Event Name:</th>
      <td><b>${event.name}</b></td>
    </tr>
    <tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
      <th>Date</th>
      <td>${event.eventDate}</td>
    </tr>
    <tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
      <th>Event Location</th>
      <td>${event.location}</td>
    </tr>
    <tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
      <th>Description </th>
      <td>${event.description}</td>
    </tr>
  </table>
  <table>
    <tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
      <td>
        <spring:url value="{eventId}/edit" var="editUrl">
        	<spring:param name="eventId" value="${event.id}" />
        </spring:url>
        <a href="${fn:escapeXml(editUrl)}">Edit Event</a>
      </td>

    </tr>
  </table>
</div>