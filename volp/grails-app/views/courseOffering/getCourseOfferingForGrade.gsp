<table>
<tr>
<th>Course</th>
<th>Start Date</th>
<th>End Date</th>
<th>Batch</th>
<th>Status</th>
<th></th>
</tr>
<g:each in="${coffr}">
        <tr>
        <td>${it.course.course_name}</td>
        <td>${it.start_date}</td>
        <td>${it.end_date}</td>
        <td>${it.batch}</td>
        <td>${it.isActive}</td>
        <td><a href="#" onclick="setGrade(${it.id})">Set Grade</a></td>
        </tr>
        </g:each>
        </table>