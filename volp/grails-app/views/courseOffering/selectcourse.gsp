
<h5>Offer Course</h5>

<g:form url="[action:'processcourseoffering']">
<table class="table table-bordered">

<tr>
<th>Select Course&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Select the course you want to offer </span> </i></th><td><g:select class="form-control" name="coursecodecoursename" from="${courselist}"/></td>
</tr>

</table>
<center><g:submitButton class="btn btn-primary" name="Proceed" value="Proceed"></g:submitButton></center>
</g:form>

<br>
