<style>
.error{
color:red;
}
</style>

<div class="container" id="editAnn">


<g:form>
<table class="table table-stripped table-bordered ">
    <tr>
    <th><span class="error">*</span>&nbsp; Notice&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Enter the messege you want to convey to the students</span> </i></th>
    <td><!-- <g:field class="form-control" type="text" name="notice" value="${ca.notice}" required="true" /> -->
    <textArea class="form-control" name="notice" value="" id="notice"  rows="5" cols="40" required >${ca.notice}</textArea>
    </td>
    <tr>
    <tr>
    <th><span class="error">*</span>&nbsp; Scheduled Date&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Specify the Date on which, the notice is to be displayed to the students </span> </i></th>
    <td>Prev:${ca.schedule_date}::<g:datePicker precision="day" name="myDate" value="${new Date()}" /></td>
    <tr>
    <td colspan="2">
    <center>
    <g:hiddenField name="coffrid" value="${coffr.id}" />
     <g:hiddenField name="caid" value="${ca.id}" />
     <g:submitToRemote class="btn btn-primary" url="[action: 'saveEditAnnouncement']" update="material" value="Edit" />
    </center>
    </td>
    </tr>
</table>

</g:form>
</div>
<br>
<br>