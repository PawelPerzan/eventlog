<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<b>AddEvent:</b>
<br/>
<form:form modelAttribute="event">
  <table>
    <tr>
      <th>
        <br/>
         Event Name: <form:errors path="*" cssClass="errors"/>
        <form:input path="name" size="35" maxlength="100"/>
        <br/>
         Event Location: <form:errors path="*" cssClass="errors"/>
        <form:input path="location" size="35" maxlength="100"/>
        <br/>
         Event Description: <form:errors path="*" cssClass="errors"/>
        <form:textarea path="description" size="55" maxlength="100"/>
      </th>
    </tr>
    <tr>
      <td><p class="submit"><input type="submit" value="Add Event"/></p></td>
    </tr>
  </table>
</form:form>
<br/><br/>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
