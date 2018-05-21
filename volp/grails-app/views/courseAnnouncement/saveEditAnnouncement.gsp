 <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
<g:form action="processCourseAnnouncement">
    <table class="table table-stripped table-bordered ">
        <tr><th>Notice&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Enter the messege you want to convey to the students</span> </i></th>
        <td><!-- <input class="form-control"type="text" name="notice" placeholder="Enter the Notice here..." required> -->
        <textArea class="form-control" name="notice" id="notice" value="" rows="5" cols="40" required ></textArea>

        </td></tr>
        <tr><th>Scheduled Date&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Specify the Date on which, the notice is to be displayed to the students </span> </i></th>
                <td><g:datePicker name="myDate" value="${new Date()}" /></td></tr>
     <tr> <td colspan="2"><center><g:submitToRemote class="btn btn-primary"url="[action: 'processCourseAnnouncement']" update="material" value="Save" before="if(!validateGradeForm1()) return false;"/><center></td></tr>
    </table>
    <g:hiddenField name="coffrid" value="${coffr.id}" />
    <g:hiddenField name="caid" value="${ca.id}" />

</g:form>

<table class="table table-stripped table-bordered ">
<th>Notice</th><th>Date</th><th>Edit</th>
<g:each var="c" in="${ca}">
<tr><td>${c.notice}</td><td>${c.schedule_date}</td><td><a href="#" onclick="editAnnouncement(${c.id},${coffr.id})">Edit</a></td></tr>
</g:each>
</table>
<br>
<br>