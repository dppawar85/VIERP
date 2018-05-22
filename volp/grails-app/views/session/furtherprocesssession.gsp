<html>
<head>
 <style>
 .error{
 color:red;
 }
 </style>
</head>
<body>
<g:if test="${msg!=null}">
  <div class="alert alert-error" style="display: block">${msg}</div>
</g:if>
<table class="table table-striped">
<th> Course</th>
<th> Description</th>
<th>Start Date</th>
<th>End Date</th>
<tr>
<td><input class="form-control" name="coursecodecoursename" type="text" value="${coff.course.course_code}&nbsp;${coff.course.course_name}" readonly></td>
<td><input class="form-control" name="batch" type="text" value="${coff.batch}" readonly></td>
 <td> <g:formatDate date="${coff.start_date}" name="start_date" format="dd-MM-yyyy"/></td>
 <td> <g:formatDate date="${coff.end_date}" name="end_date" format="dd-MM-yyyy"/></td>
  </tr>

</table>
<br>
<h6>Add Sessions</h6>

<g:form url="[action:'addsavesessions']">
<g:set var="x" value="${row-1}"/>
<table class="table table-striped">

<g:each in="${(0..x).toList()}" var="i" status="d">
    <tr>
    <g:each in="${listOfLists[i]}" var="e" status="j">
         <g:if test="${i==0}">
            <td><b>${listOfLists[i][j]}</b></td>
        </g:if>
        <g:else>
            <td>${listOfLists[i][j]}</td>
        </g:else>
    </g:each>
    <g:if test="${i!=0}">
        <g:if test="${previus_session_number_list[i]=='1000'}">
            <td><span class="error">*</span>&nbsp;<g:select name="session_number" from="${session_number_list}" value="${i}" /></td>
        </g:if>
        <g:else>
            <td><span class="error">*</span>&nbsp;<g:select name="session_number" from="${session_number_list}" value="${previus_session_number_list[i]}" /></td>
        </g:else>
        <g:if test="${session.courseofferingtype=='Weekwise' || session.courseofferingtype=='Self-Pace'}">
             <g:if test="${previus_week_number_list[i]=='1000'}">
                <td><span class="error">*</span>&nbsp;<g:select name="week_number" id="${i},${j}" from="${week_number_list}" value="${i}" /></td>
             </g:if>
             <g:else>
                <td><span class="error">*</span>&nbsp;<g:select name="week_number" id="${i},${j}" from="${week_number_list}" value="${previus_week_number_list[i]}" /></td>
             </g:else>
        </g:if>
        <g:if test="${session.courseofferingtype=='Daywise'}">
            <td><span class="error">*</span>&nbsp;<g:datePicker name="start_date"  id="${i},${j}" precision="day" value="${previus_date_number_list[i]}"/></td>
        </g:if>
    </g:if>
    </tr>
</g:each>


</table>
<center><g:submitToRemote class="btn btn-primary" url="[action: 'addsavesessions']" update="edit" value="Save Session"/>
<!-- <g:submitButton name="Add Schedule" value="Add Schedule"></g:submitButton> -->
</center>
</g:form>
<br><br><br><br>
</body>
</html>