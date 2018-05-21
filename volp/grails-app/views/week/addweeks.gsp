<html>
<body>
<b>Add Weeks...</b>
<table>
<tbody>
<tr><td><label>Course</label></td><td><input name="coursecodecoursename" type="text" value="${coff.course.course_code}&nbsp;${coff.course.course_name}" readonly></td></tr>
<tr><td><label>Description</label></td><td><input name="batch" type="text" value="${coff.batch}" readonly></td></tr>
<tr><td><label>Start Date</label></td><td><input name="start_date" type="text" value="${coff.start_date}" readonly></td></tr>
<tr><td><label>End Date</label></td><td><input name="end_date" type="text" value="${coff.end_date}" readonly></td></tr>
</tbody>
</table>
<br><br>
<g:form url="[action:'addsaveweeks']">
<table>
<tbody>
<tr><td><lable>Week Number:</lable></td><td><g:select name="week_number" from="${week_number_list}"/></td></tr>
<tr><td><label>Start Date:</label></td><td><g:datePicker name="start_date"  precision="day" value="${new Date()}" /></td></tr>
<tr><td><label>End Date:</label></td><td><g:datePicker name="end_date"  precision="day" value="${new Date()}" /></td></tr>
<tr><td></td><td><g:submitButton name="Save" value="Save"></g:submitButton></td></tr>
</tbody>
</table>
</g:form>
                <table class="table table-striped table-responsive table-bordered"  id="data1" style="width:auto">
                    <thead>
                        <tr class="danger" >
                            <th>Week No.</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                        </tr>
                    </thead>
                    <tbody >
                        <g:each in="${week_list}" status="i" var="d">
                            <tr class="${(i % 2) == 0 ? 'success' : 'warning'}" >
                                <td> <g:link action="updateweeks" id="${d.id}">edit</g:link>&nbsp;&nbsp;&nbsp;<g:link action="deleteweeks" id="${d.id}">delete</g:link><span class="glyphicon glyphicon-edit"/></td>
                                <td>${d.week_number}</td>
                                <td> <g:formatDate date="${d.start_date}" name="start_date" format="dd-MM-yyyy"/></td>
                                <td> <g:formatDate date="${d.end_date}" name="end_date" format="dd-MM-yyyy"/></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
</body>
</html>