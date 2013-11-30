<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h3 style="background-color:#e0e0ff;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">Find Events:</h3>
<br/>
<div id="findContent" style="border: 1px solid; width=500px;">
<form:form modelAttribute="event">
  <table>
    <tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px"><td><form:errors path="*" cssClass="errors"/></td></tr>
    <tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
    	<td>Event Name:<form:input path="name" size="35" maxlength="100"/></td>
    </tr>
    <br/>
    <tr font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 12px">
      <td><p class="submit"><input type="submit" value="Find Events"/></p></td>
    </tr>
  </table>
</form:form>
<br/><br/>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</div>