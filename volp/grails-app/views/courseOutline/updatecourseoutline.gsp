<style>
 .popup{
         position: fixed; /* Sit on top of the page content */
         display: block; /* Hidden by default */
         color: red;
         height: 70%;
         top: 80px;
         left: 0;
         right: 0;
         bottom: 0;
         background-color: rgba(192,192,192,1); /* Black background with opacity */
         z-index: 2; /* Specify a stack order in case you're using a different order for other elements */
         cursor: pointer; /* Add a pointer on hover */
 }
</style>
<div class="container popup" id="closefrm">
<g:form url="[action:'saveupdatecourseoutline']">
<table class="table col-sm-12 table-striped  table-bordered"  id="data1" >
<tbody>
<tr>
<td ><b>Course</b></td>
<td ><input class="form-control" name="coursecodecoursename" type="text" value="${coursecodecoursename}" readonly> </td>
</tr>
<tr>
<td><b>Course Outline Number</b></td>
<td><input class="form-control" name="outline_number" type="text"  value="${outline_number}"></td>
</tr>
<tr>
<td><b>Course Outline Text</b></td>
<td><input class="form-control" name="outline" type="text" value="${outline}" ></td>
</tr>
<tr>

<!-- <td><input class="flow-control" name="email" type="hidden" value="${email}" ></td>
<td><input class="flow-control" name="coursecode" type="hidden" value="${coursecode}" ></td>
<td><input class="flow-control" name="coursename" type="hidden" value="${coursename}" ></td> -->

<td colspan="2" align="right"><g:submitButton class="btn btn-primary" name="Update" value="Update"></g:submitButton>
 &nbsp;&nbsp;<input type="button" class="btn btn-danger" value="close" onclick="javascript:document.getElementById('closefrm').style.display='none'">
</tr>
</tbody>
</table>
</g:form>
</div>