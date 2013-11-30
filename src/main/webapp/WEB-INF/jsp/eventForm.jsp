<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<form:form modelAttribute="event" method="post">
	<table><tr><td style="color: red;"><form:errors path="*" element="div" /></td></tr></table>
	<table>
		<tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
			<td align="right">Event Name:</td>
			<td><form:input path="name" size="30" maxlength="30" /></td>
			<td style="color: red;">*</td>
		</tr>
		<tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
			<td align="right">Last location:</td>
			<td><form:input path="location" size="30" maxlength="30" /></td>
			<td style="color: red;">*</td>
		</tr>
		<tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
			<td align="right">Event date:</td>
			<td style="color: red;"><form:input path="eventDate" size="10" maxlength="10" />*</td>
		</tr>
		<tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
			<td align="right">Description:</td>
			<td><form:input path="description" size="30" maxlength="30" /></td>
			<td style="color: red;">*</td>
		</tr>
		<tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
			<td>
				<p>
					<input type="submit" value="Update Event" />
				</p>
			</td>
		</tr>
	</table>
</form:form>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
