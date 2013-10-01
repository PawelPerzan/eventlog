<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<b/>All Events:</b>
<table border="1">
<c:forEach var="event" items="${events}">
	<tr><td>Id</td><td>Name</td>
    <tr>
      <td>${event.id}</td>
      <td>${event.name}</td>
<%--       <td>${event.snsArn}</td> --%>
    </tr>
  </c:forEach>
</table>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>

