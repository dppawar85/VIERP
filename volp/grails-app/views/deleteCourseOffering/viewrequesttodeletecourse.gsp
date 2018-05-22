<g:each var="delst" in="${listcourse}">
<table class="table  table-bordered col-sm-12 table-strip">
    <th>Course Name</th>
    <th>Batch Name</th>
    <th>Start Date</th>
    <th>End Date</th>
    <th>Course Instructor</th>
    <th>Reason</th>
    <th>Deleted Status</th>
    <th>Delete</th>
    <tr>
        <td>${delst.courseoffering.course.course_name}</td>
        <td>${delst.courseoffering.batch}</td>
        <td>${delst.courseoffering.start_date}</td>
        <td>${delst.courseoffering.end_date}</td>
        <td>${delst.instructor.person.firstName}</td>
        <td>${delst.reason}</td>
        <td>${delst.courseoffering.isDeleted}</td>
        <td>
            <a href="#" onclick="deleteCourseOffering(${delst.courseoffering.id})">DELETE</a>
        </td>
    </tr>

</table>
</g:each>
