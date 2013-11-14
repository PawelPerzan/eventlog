<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<b>Add Event:</b>
<br />
<form:form modelAttribute="event">
	<table><tr><td style="color: red;"><form:errors path="*" element="div" /></td></tr></table>
	<table>
		<tr>
			<td align="right">Event Name:</td>
			<td><form:input path="name" type="text" size="30" maxlength="30" /></td>
			<td style="color: red;">*</td>
		</tr>
		<tr style="width: 342px;">
			<td align="right">Event Location:</td>
			<td><form:input path="location" type="text" size="30"
					maxlength="30" /></td>
			<td style="color: red;">*</td>
		</tr>
		<tr>
			<td align="right">Event Date:</td>
			<td style="color: red;"><form:input path="eventDate" type="text" size="10"
					maxlength="10"/>*</td>

		</tr>
		<tr>
			<td align="right" valign="top">Description:</td>
			<td><form:input path="description" size="30" maxlength="30" /></td>
			<td style="color: red;" valign="top">*</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Submit" /></td>
		</tr>
		<tr>
			<td align="center" colspan="4" height="20"></td>
		</tr>
	</table>
</form:form>
<br />
<br />
<%@ include file="/WEB-INF/jsp/footer.jsp"%>