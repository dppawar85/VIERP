<html>
<head>
<meta name="layout" content="blank">
<style>
.error{
color:red;
}
</style>

</head>
<script>
function callme()
{
        alert("Parth Cartoon");
        var uploadcategory1 = document.getElementById("uploadcategory").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material1").innerHTML = this.responseText;
                    }
                };
       //         xmlhttp.open("GET", "xyz", true);
                xmlhttp.open("GET", "${request.contextPath}/assignment/processaddassignment?uploadcategory1=" + uploadcategory1, true);
                xmlhttp.send();
        return(false)
  }
</script>
<body class="row">
<div style="position: fixed; top: 50px; right: 80px" class="bg-warning">
    <a class="btn btn-sm" style="color: black" href="/instructor/instructor"> <i class="fa fa-home" aria-hidden="true"></i> HOME </a>
</div>
<br><br>
<center><h5>Add Assignment</h5></center>
<table class="table table-bordered" style="width: 60%; position: relative; margin: auto auto">
<tbody>
<tr><td><label>Course</label></td><td><input class="form-control" name="coursecodecoursename" type="text" value="${coff.course.course_code}&nbsp;${coff.course.course_name}" readonly></td></tr>
<tr><td><label>Description</label></td><td><input class="form-control" name="batch" type="text" value="${coff.batch}" readonly></td></tr>
<!--<tr><td><label><span class="error">*</span>&nbsp;Start Date</label></td> <td> <g:datePicker date="${coff.start_date}" name="start_date" precision="day"/></td></tr>
<tr><td><label><span class="error">*</span>&nbsp;End Date</label></td> <td> <g:datePicker date="${coff.end_date}" name="end_date" precision="day"/></td></tr> -->
</tbody>
</table>

<g:form url="[action:'processaddassignment']" onSubmit="return selectType()">
<g:hiddenField name="coffrid" value="${coffrid}" />

<table class="table table-bordered" style="width: 60%; position: relative; margin: auto auto">
<tbody>
<tr>
<td><label><span class="error">*</span>&nbsp;Select Upload Category&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Category indicates that the assignment is related with Either a Course or Unit or Topic</span> </i></label></td>
<td><g:select class="form-control" name="uploadcategory" id="uploadcategory" from="${uploadcategorylist}" /></td>
</tr>
<tr><td><label><span class="error">*</span>&nbsp;Assignment Type&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Type indicates Nature of an assignment E.g Long answer/Short Answer etc. </span> </i></label></td><td><g:select class="form-control" name="assignmenttype" id="assignmenttype" from="${assignmenttypelist}"/></td></tr>

<tr><td></td><td><g:submitButton name="Proceed" class="btn btn-primary" value="Proceed"></g:submitButton></td></tr>
</tbody>
</table>
<div id="material2"></div>
</g:form>
<script>
function selectType(){
var v1=document.getElementById("uploadcategory").value;
var v2=document.getElementById("assignmenttype").value;
   if((v1 !== null && v1==='') || (v2!== null && v2===''))
   {
      if((v1 !== null && v1===''))
           document.getElementById("uploadcategory").focus();
      if((v2 !== null && v2==='')){
                 document.getElementById("assignmenttype").focus();

                 }
      alert("Please Select");
      return false;
    }
    else{
    return true;
    }
   }
</script>

</body>
</html>