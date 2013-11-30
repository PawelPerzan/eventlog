<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2 style="background-color:#e0e0ff;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px"><fmt:message key="home"/></h2>
<div id="menuContent" style="border: 1px solid; width=500px;">
<table width="500" border="0">
<tr style="background-color:#e0e0ff;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
<td>Menu</td></tr>
    <tr style="background-color:#eeeeee;width:100px;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px"">
      <td><a href="<c:url value="/addEvent"/>">Add Event</a></td>
    </tr>
        <tr style="background-color:#eeeeee;width:100px;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px"">
      <td><a href="<c:url value="/findEvents"/>">Find events</a></td>

    </tr>
        <tr style="background-color:#eeeeee;width:100px;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px"">
      <td><a href="<c:url value="/events"/>">Display all events</a></td>
    </tr>
        <tr style="background-color:#eeeeee;width:100px;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px"">
      <td><a href="<c:url value="/logs"/>">Audit Logs</a></td>
    </tr>
</table>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
