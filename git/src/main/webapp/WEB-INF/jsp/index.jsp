<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html lang="en">
<body>
    <div>
        <div>
        
        	<h2>${message}</h2>
        
        	<form:form action="searchUser" method="get">
            	<b>Enter User ID: </b>
            	<input type="text" name="userId" />
            	
            	<input type="submit" />
             </form:form>
             
             <font color="red"><b>${err_message}</b></font>
        </div>
    </div>
</body>
</html>