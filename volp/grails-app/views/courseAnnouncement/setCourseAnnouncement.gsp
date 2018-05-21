<style>
.error{
color:red;
}
</style>

<div class="container">
<g:form id="myForm">
    <table class="table table-stripped table-bordered ">
        <tr>
        <th><span class="error">*</span>&nbsp; Notice&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Enter the messege you want to convey to the students</span> </i></th>
        <td><!-- <g:field class="form-control" type="text" name="notice" placeholder="Enter the Notice here..." required="" /> -->
        <textArea class="form-control" name="notice" id="notice" value="" rows="5" cols="40" required ></textArea>
        </td>
        <tr>
        <tr>
        <th><span class="error">*</span>&nbsp;Scheduled Date&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Specify the Date on which, the notice is to be displayed to the students </span> </i></th>
        <td><g:datePicker class="form-control" name="myDate" precision="day" value="${new Date()}" /> </td>
        <tr>
     <g:hiddenField name="coffrid" value="${coffr.id}" />
    <g:hiddenField name="caid" value="${ca.id}" />
    <tr> <td colspan="2"><center><g:submitToRemote class="btn btn-primary" url="[action: 'processCourseAnnouncement']" update="material" value="Save" before="if(!validateGradeForm1()) return false;"/></center></td></tr>
    </table>

</g:form>

<table class="table table-stripped table-bordered ">
<tr>
<th>Notice</th>
<th>Date</th>
<th> Edit</th>
</tr>
<g:each var="c" in="${ca}">
<tr>
<td>${c.notice}</td><td>${c.schedule_date}</td><td><a href="#" onclick="editAnnouncement(${c.id},${coffr.id})"><i class="fa fa-edit fa-2x"></i></a>
</td>
</tr>
</g:each>
</table>
</div>
<br>
<br>
