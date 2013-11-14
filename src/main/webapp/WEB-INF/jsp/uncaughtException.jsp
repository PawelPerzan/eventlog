<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<br/>
<h3/>Attention! Unexpected condition or an error has occurred. Please check your constants or configuration files. 
If the error persists, please contact developers of this system.</h3>
<% 
try {
	Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception"); 

	if (exception != null) {
		if (exception instanceof ServletException) {
			ServletException sex = (ServletException) exception;
			Throwable rootCause = sex.getRootCause();
			if (rootCause == null)
				rootCause = sex;
			out.println("** Root cause is: "+ rootCause.getMessage());
		}
		else {
			out.println("Error type has occurred: " + exception.getMessage());
		}
	} 
	else  {
    	out.println("No error information available");
	} 

} catch (Exception ex) { 
	out.println("Error type has occurred: " + ex.getMessage());
	ex.printStackTrace(new java.io.PrintWriter(out));
}
%>
<br/>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
