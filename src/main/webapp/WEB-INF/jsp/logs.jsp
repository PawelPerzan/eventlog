<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h3 style="background-color:#e0e0ff;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">Audit Logs</td></h3>
<div id="logsContent" style="border: 1px solid; width=500px;">
<table width="500" border="0">
<tr style="background-color:#e0e0ff;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
<tr colspan="2" style="background-color:#e0e0e0;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
		<td><b>Object Name</b></td>
		<td><b>User</b></td>
		<td><b>Date</b></td>
		<td><b>Audit Message</b></td>
	</tr>
	<c:forEach var="log" items="${logs}">
    <tr style="background-color:#eeeeee;width:100px;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
      <td>${log.objectId}</td>
      <td>${log.userId}</td>
      <td>${log.auditDate}</td>
      <td>${log.auditMessage}</td>
    </tr>
  	</c:forEach>
</table>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>

