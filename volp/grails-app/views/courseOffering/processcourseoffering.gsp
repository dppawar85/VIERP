<html>
<head>
<meta name="layout" content="blank">
<style>
.error{
color: red;

}
table #{
     display: none;
    }
</style>
<script>

function changedate()
{

    if (document.getElementById("report").value === "Self-Pace")
    {
        document.getElementById('fdate').style.display = 'none';
        document.getElementById('tdate').style.display = 'none';
    }
    if (document.getElementById("report").value === "Weekwise")
    {
        document.getElementById('fdate').style.display = 'block';
        document.getElementById('tdate').style.display = 'block';
    }
}
</script>
</head>
<body>
<br>
<h5 class="well well-sm">Offer Course</h5>
<g:form url="[action:'savecourseoffering']">
<table>
<tbody>
<tr><td>Course:</td><td><input class="form-control" name="course" type="text" value="${course.course_name}" readonly></td></tr>
<tr><td><span class="error">*</span> Course Pattern:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Pattern indicates whether student has to complete the course within the Schedule or self spaced Mode</span> </i></td><td><g:select class="form-control" name="courseofferingtype" from="${courseofferingtypelist.name}" required="true" onChange="changedate();" id="report" /></td></tr>
<tr id="fdate"><td><label><span class="error">*</span> Start Date:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Course commencement date </span> </i></label></td><td><g:datePicker class="form-control" name="startdate"  precision="day" value="${new Date()}" required="true"/></td></tr>
<tr id="tdate"><td><label><span class="error">*</span> End Date:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Last schedule date of the course </span> </i></label></td><td><g:datePicker class="form-control" name="enddate"  precision="day" value="${new Date()}" required="true"/></td></tr>
<tr><td><span class="error">*</span> Batch Description:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Specify batch name E.g.Summer-2018/19 or Winter-2018/19 </span> </i></td><td><input class="form-control" name="batch" type="text" required></td></tr>

<g:if test="${session.isuserbelongstoorganization==true}">
    <tr><td><span class="error">*</span> Year:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">First Year/Second Year/Third Year etc.. </span> </i></td><td><g:select class="form-control" name="year" from="${yearlist.year}" required="true"/></td></tr>
    <tr><td><span class="error">*</span> Academic Year:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Academic year in which you want to offer course  </span> </i></td><td><g:select class="form-control" name="ay" from="${aylist.ay}" required="true"/></td></tr>
    <tr><td><span class="error">*</span> Semester:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Semester for academic year in which you want to offer course </span> </i></td><td><g:select class="form-control" name="sem" from="${semlist.sem}" required="true"/></td></tr>
    <tr><td><span class="error">*</span> Course Type:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Course Type indicates course with Laboratory only or Theory only or Theory and Lab or Theory and Project or tutorial</span> </i></td><td><g:select class="form-control" name="coursetype" from="${coursetypelist.type}" required="true"/></td></tr>
</g:if>

<tr><td colspan="2" align="right"><g:submitButton class="btn btn-primary" name="offer course" value="Offer Course"></g:submitButton></td></tr>
</tbody>
</table>
</g:form>
<br>
                <table class="table table-striped table-responsive table-bordered"  id="data1" style="width:auto">
                    <thead>
                        <tr class="danger" >
                            <th>Course</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Batch</th>
                            <th>isLive</th>
                            <th>Course Pattern</th>
                            <g:if test="${session.isuserbelongstoorganization==true}">
                                <th>Year</th>
                                <th>Academic Year</th>
                                <th>Semester</th>
                                <th>Course Type</th>
                            </g:if>
                        </tr>
                    </thead>
                    <tbody >
                        <g:each in="${courseofferinglist}" status="i" var="d">
                            <tr class="${(i % 2) == 0 ? 'warning' : 'warning'}" >


                                <td>${d.course.course_name}</td>
                                <g:if test="${d.courseofferingtype.name=='Self-Pace'}">
                                   <td>--</td>
                                   <td>--</td>
                                </g:if>
                                <g:else>
                                    <td> <g:formatDate class="form-control" date="${d.start_date}" format="dd-MM-yyyy"/></td>
                                    <td> <g:formatDate class="form-control" date="${d.end_date}" format="dd-MM-yyyy"/></td>
                                </g:else>
                                <td>${d.batch}</td>
                                <td>${d.isActive}</td>
                                <td>${d.courseofferingtype.name}</td>
                                 <g:if test="${session.isuserbelongstoorganization==true}">
                                    <g:if test="${d.year!=null}">
                                        <td>${d.year.year}</td>
                                    </g:if>
                                    <g:else>
                                        <td>--</td>
                                     </g:else>
                                    <g:if test="${d.academicyear!=null}">
                                        <td>${d.academicyear}</td>
                                    </g:if>
                                    <g:else>
                                        <td>--</td>
                                     </g:else>
                                    <g:if test="${d.semester!=null}">
                                        <td>${d.semester.sem}</td>
                                    </g:if>
                                    <g:else>
                                     <td>--</td>
                                    </g:else>
                                    <g:if test="${d.coursetype!=null}">
                                        <td>${d.coursetype.type}</td>
                                    </g:if>
                                    <g:else>
                                     <td>--</td>
                                    </g:else>
                                 </g:if>
                                 <g:else>
                                 </g:else>
                                 <td> <g:link class="btn btn-primary" action="updatecourseoffering" id="${d.id}">edit</g:link></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>


</body>
</html>