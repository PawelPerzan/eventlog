<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<h3 style="background-color:#e0e0ff;font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">Add Event:</h3>
<br />
<div id="addContent" style="border: 1px solid; width=500px;">
<form:form modelAttribute="event">
	<table><tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px"><td style="color: red;">
	<form:errors path="*" element="div" /></td></tr></table>
	<table>
		<tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
			<td align="right">Event Name:</td>
			<td><form:input path="name" type="text" size="30" maxlength="30" /></td>
			<td style="color: red;">*</td>
		</tr>
		<tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
			<td align="right">Event Location:</td>
			<td><form:input path="location" type="text" size="30"
					maxlength="30" /></td>
			<td style="color: red;">*</td>
		</tr>
		<tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
			<td align="right">Event Date:</td>
			<td style="color: red;"><form:input path="eventDate" type="text" size="10"
					maxlength="10"/>*</td>

		</tr>
		<tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px"> 
			<td align="right" valign="top">Description:</td>
			<td><form:input path="description" size="30" maxlength="30" /></td>
			<td style="color: red;" valign="top">*</td>
		</tr>
		<tr style="font-family: Calibri, Tahoma, Arial, sans-serif;font-size: 14px">
			<td></td>
			<td><input type="submit" value="Submit" /></td>
		</tr>
		<tr>
			<td align="center" colspan="4" height="20"></td>
		</tr>
	</table>
</form:form>
</div>
<br />
<br />
<%@ include file="/WEB-INF/jsp/footer.jsp"%>