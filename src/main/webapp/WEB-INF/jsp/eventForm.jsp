<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>



<form:form modelAttribute="event" method="post">
  <table>
  <form:errors path="*" element="div" />
    <tr>
		Event name:
        <br/>
        <form:input path="name" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Last location: 
        <br/>
        <form:input path="location" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
        Event date:
        <br/>
        <form:input path="eventDate" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Description:
        <br/>
        <form:input path="description" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <td>
		<p><input type="submit" value="Update Evnt"/></p>
      </td>
    </tr>
  </table>
</form:form>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
