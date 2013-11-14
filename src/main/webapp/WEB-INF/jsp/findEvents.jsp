<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<b>Find Events:</b>
<br/>
<form:form modelAttribute="event">
  <table>
    <tr><td><form:errors path="*" cssClass="errors"/></td></tr>
    <tr>
    	<td>Event Name:<form:input path="name" size="35" maxlength="100"/></td>
    </tr>
    <br/>
    <tr>
      <td><p class="submit"><input type="submit" value="Find Events"/></p></td>
    </tr>
  </table>
</form:form>
<br/><br/>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
