<g:set var="h" value="${1}"/>

<head>
    <meta charset="UTF-8">
    <title>Add Course</title>

    <style>
     .error{
      color: red;
      font-style: oblique;
     }

</style>

</head>

<body >
 <div class="container">
  <g:if test="${flash.message}">
  <div class="alert alert-error" style="display: block;color:red">${flash.message}
  </div>
</g:if>
<div class="card-body">
    <g:form name="myForm" url="[action:'processCourse',controller:'instructor']" enctype="multipart/form-data">

    <h3>Add Course </h3>


       <div class="row col-sm-8">
        <b>Select Logo Image for Course:(JPEG format)</b> &nbsp;&nbsp;<input type="file" id="file"  name="coursePic"/>
        </div>
        <br>
        <span class="error">* &nbsp;Mandetory fileds</span>
        <div class="row col-sm-12">
        <div class="form-control col-sm-6" > Course Code:
         <i class="fa fa-info-circle info"><span class="infotext">Choose approrpiate course code defined by Institute/Organization/Industry</span> </i>
            <input class="form-control" type="text" name="crs_code" placeholder="Eg. CS3039" size="50">
        </div>
        <div class="card col-sm-6"><span class="error">* </span><span>Course Name: <i class="fa fa-info-circle info"><span class="infotext">give suitable course name </span> </i> </span>
            <input  class="form-control" type="text" name="crs_name" placeholder="Eg. Computer Networks" required size="50">
        </div>
        </div>


    <div class="row col-sm-12">
        <div class="form-control col-sm-6" > Credits:
        <i class="fa fa-info-circle info"><span class="infotext">set the course credit ex. 1/2/3/4</span> </i>
            <input class="form-control" type="number" name="credit" placeholder="Eg. 4" min="1" size="50"  />
        </div>
        <div class="card col-sm-6"><span class="error">* </span>Course Category:
            <g:select class="form-control" optionKey="id" from="${cc}" optionValue="${{it.name}}" name="cat" noSelection="${['null':'Please Select One...']}" onchange="addCat(this.value)"  required="true" /></>
            <div id="scat"></div>
        </div>
    </div>

    <div class="row col-sm-12">
       <!-- <div class="form-control col-sm-6" >Dept:
            <g:select class="form-control" optionKey="id" from="${dept}" optionValue="${{it.name}}" name="dept" noSelection="${['null':'Please Select One...']}" />
        </div> -->
        <div class="col-sm-12">
        <span class="error">* </span>
          Program: <i class="fa fa-info-circle info"><span class="infotext">Choose approrpiate program of your course defined by Institute/Organization/Industry</span> </i>
            <g:select class="form-control" optionKey="id" from="${prog}" optionValue="${{it.name}}" name="prog" noSelection="${['null':'Not Applicable']}" />

        </div>
    </div>
    <br>
    <span class="error">* </span>Visibility :<span class="error">(Public: Course Offered to within and outside Institute  / Private: Course Offered within Institute)</span>
<div class="col-sm-12">
    <g:select class="form-control " optionKey="id" from="${cv}" optionValue="${{it.name}}" name="cv"  required="true"    />
</div>

<br>
    Do you want to allow the course for collaborative Instructor:
    <span >
    Yes <input type="radio" name="cInstructor" value="yes" onclick="hidecolValue(this)"/>
    NO <input type="radio" name="cInstructor" value="no" onclick="hidecolValue(this)" checked/>
    </span>
    <div class="col-sm-12" id="collaborativeInstructor" style="display:none">
    <g:select class="form-control col-sm-12" optionKey="id" from="${ints}" optionValue="${{it.uid}}" name="ins" multiple="true"/>
    </div>
    <br><br>
    <span class="error"> </span>Course Highlights
    <div id="newlink">
        <div class=" row col-sm-12">
          <!--  <div class="feed col-sm-3">
                <input class="form-control" type="number" name="featureno" placeholder="Enter No." size="50" min="1" required>
            </div>
            <div class="col-sm-6">
                <input class="form-control" type="text" name="feature" placeholder="Enter Highlight" size="50" required>
            </div>  -->
            <div class="col-sm-3" id="addnew">
                <a class="btn btn-primary circlebtn" href="javascript:add_feed()" title="Add Course Highlight">+</a>
                 <i class="fa fa-info-circle info"><span class="infotext">Click here to add course highlights</span> </i>
            </div>
        </div>
    </div>
<br>

<!-- <span class="error">* </span>Course Description
<div id="newlinkdes">
    <div class=" row feeddes col-sm-12">
        <div class="col-sm-3">
            <input class="form-control" type="number" name="descno" placeholder="Enter No." size="50" min="1" required>
        </div>
        <div class="col-sm-6">
            <input class="form-control" type="text" name="description" placeholder="Enter description" size="50" required>
        </div>
        <div class="col-sm-3" id="addnewdes">
            <a class="btn btn-primary circlebtn" href="javascript:add_feeddes() " title="Add more">+</a>
          <i class="fa fa-info-circle info"><span class="infotext">Click here to add course Description</span> </i>
        </div>
    </div>
</div> -->
<br>

Course Prerequisite
<div id="newlinkpre">
    <div class=" row feedpre col-sm-12">
    <div class="col-sm-3" id="addnewpre">
                <a class="btn btn-primary circlebtn" href="javascript:add_feedpre()" title="Add Course Prerequisite">+</a>
                 <i class="fa fa-info-circle info"><span class="infotext">Click here to add course prerequisite</span> </i>
            </div>
    </div>
</div>
<br>
Reference URL<span class="error"> (Any Software/Tools/Usefull links are required for course then specify URL link)</span>
<div id="newlinkurl">
    <div class="row feedurl col-sm-12">
<div class="col-sm-3" id="addnewurl">
            <a class="btn btn-primary circlebtn" href="javascript:add_feedurl()" title="Add URL" >+</a>
                 <i class="fa fa-info-circle info"><span class="infotext">Click here to add course URL</span> </i>
        </div>
 </div>
</div>
<p>
    <br>
    <input class="btn btn-warning" type="reset" name="reset1">&nbsp&nbsp&nbsp&nbsp&nbsp
    <input class="btn btn-primary" type="submit" value="Save" name="submit" id="submitCrs" title="Please select all mandotory fields!!!" disabled="true">
</p>

</g:form>
<!-- Template. This whole data will be added directly to working form above -->

<div class="card-body"  id="newlinktpl" style="display:none">
    <div class="feed row">
      <!-- <input class="form-control col-sm-3" type="number" name="featureno" placeholder="Enter No." size="50" min="1" value="${h}" /> -->
       <g:set var="h" value="${h+1}"/>
       <input class="form-control col-sm-8" type="text" name="feature" placeholder="Enter Highlight" size="50" required />
       <input class="col-sm-1 btn btn-sm btn-danger" value="remove" type="button" onclick="javascript:this.parentNode.parentNode.removeChild(this.parentNode)">
   </div><br>
</div>
<div class="card-body" id="newlinktpldes" style="display:none">
    <div class="feeddes row">
       <input class="form-control col-sm-3" type="number" name="descno" placeholder="Enter No." size="50" min="1" />
       <input class="form-control col-sm-8" type="text" name="description" placeholder="Enter description" size="50" />
       <input class="col-sm-1 btn btn-sm btn-danger" value="remove" type="button" onclick="javascript:this.parentNode.parentNode.removeChild(this.parentNode)">
   </div> <br>
</div>
<div class="card-body" id="newlinktplpre" style="display:none">
    <div class="feedpre row">
     <!--  <input class="form-control col-sm-3" type="number" name="preno" placeholder="Enter No." size="50" min="1" required>  -->
       <input class="form-control col-sm-8" type="text" name="prerequisite" placeholder="Enter Prerequisite" size="50" required>
      <input class="col-sm-1 btn btn-sm btn-danger" value="remove" type="button" onclick="javascript:this.parentNode.parentNode.removeChild(this.parentNode)">
   </div><br>
</div>
<div class="card-body" id="newlinktplurl" style="display:none">
    <div class="feedurl row">

       <input class="form-control col-sm-11" type="text" name="url" placeholder="Enter url" size="50" required>
       <input class="col-sm-1 btn btn-sm btn-danger" value="remove" type="button" onclick="javascript:this.parentNode.parentNode.removeChild(this.parentNode)">
   </div><br>
</div>
</div>
<br>
</div>
<script>
$(document).on('change', ':file', functigraon() {
    var input = $(this),
        numFiles = input.get(0).files ? input.get(0).files.length : 1,
        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect', [numFiles, label]);
});

</script>
</body>
</html>