<html>
<title>furtherprocessweek</title>
<body>
<div class="container">
<b>Further processweeks</b>
<table class="col-sm-8 table table-striped  table-bordered" id="data1">
<tbody>
<tr><td><label>Course</label></td><td><center> <input class="form-control"  name="coursecodecoursename" type="text" value="${coff.course.course_code}&nbsp;${coff.course.course_name}" readonly></td></tr>
<tr><td><label>Description</label></td><td><center> <input class="form-control"  name="batch" type="text" value="${coff.batch}" readonly></td></tr>
<tr><td><label>Start Date</label></td> <td><center>  <g:formatDate date="${coff.start_date}" name="start_date" format="dd-MM-yyyy"/></td></tr>
<tr><td><label>End Date</label></td> <td><center>  <g:formatDate date="${coff.end_date}" name="end_date" format="dd-MM-yyyy"/></td></tr>
</tbody>
</table>
<br><br>
<b>Add Weeks</b>
<g:form url="[action:'addsaveweeks']">
<table class="col-sm-8 table table-striped  table-bordered" id="data1">
<tbody>

<tr><td><lable>Week Number:</lable></td><td><center> <g:select class="form-control"  name="week_number" from="${week_number_list}"/></td></tr>
<tr><td><label>Start Date:</label></td><td><center> <g:datePicker name="start_date"  precision="day" value="${new Date()}" /></td></tr>
<tr><td><label>End Date:</label></td><td><center> <g:datePicker name="end_date"  precision="day" value="${new Date()}" /></td></tr>

<tr><td colspan="2"><center>
<g:submitToRemote class="btn btn-primary " url="[action: 'addsaveweeks']" update="edit" value="Save"/>
<!--<g:submitButton name="Add" value="Add"></g:submitButton> --></center></td></tr>
</tbody>
</table>
</g:form>

                <table class="col-sm-8 table table-striped  table-bordered" id="data1">
                    <thead>
                        <tr class="danger" >
                            <th><center>Week No.</center></th>
                            <th><center>Start Date</center></th>
                            <th><center>End Date</center></th>
                            <th><center>Edit</center></th>
                        </tr>
                    </thead>
                    <tbody >
                        <g:each in="${week_list}" status="i" var="d">
                            <tr class="${(i % 2) == 0 ? 'success' : 'warning'}" >

                                <!-- <g:link action="updateweeks" id="${d.id}">edit</g:link>  -->
                                 &nbsp;&nbsp;&nbsp;
                                <!-- <g:link action="deleteweeks" id="${d.id}">delete</g:link>  -->
                                <span class="glyphicon glyphicon-edit"/></center></td>
                                <td><center> ${d.week_number}</center></td>
                                <td><center>  <g:formatDate date="${d.start_date}" name="start_date" format="dd-MM-yyyy"/></center></td>
                                <td><center>  <g:formatDate date="${d.end_date}" name="end_date" format="dd-MM-yyyy"/></center></td>
                                 <td><center> <a href="#" onclick="updateweeks(${d.id})" > <i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i></a>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
                <br><br>
                </div>
</body>
</html>