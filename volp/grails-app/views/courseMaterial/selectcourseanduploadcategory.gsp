<html>
<head>
<meta name="layout" content="blank">
</head>
<body>
  <div class="pull-right bg-warning" style="border-radius:5px; position: absolute; top: 50px; right: 80px;">
         <a class="btn btn-sm" style="color: black" href="${request.contextPath}/instructor/instructor"> <i class="fa fa-home" aria-hidden="true"></i> HOME </a>
  </div>

<div class="container">

    <g:form url="[action:'savecoursematerial',controller:'courseMaterial']">
    <br><b>Course:${session.course.course_name}</b>

        <table class="table  table-bordered ">


            <tr>
                <td colspan="2"> <g:select class="form-control"  required="true" name="uploadcategory" id="uploadcategory" from="${uploadcategorylist}" onChange="return callme();"/>&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Short Description of material </span> </i></td>
            &nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Category indicates whether you want to upload Material for course or Unit or Topic    </span> </i>
            </tr>

            </table>
            <table class="table  table-bordered ">
                        <tr>
                            <div id="material1"></div>
                        </tr>
                        <tr><div id="material2"></div></tr>
                        <tr><div id="material3"></div></tr>
                        <tr><div id="material4"></div></tr>
                        <tr><td><center><g:submitButton class="btn btn-sm btn-primary" name="save" value="Save" /></center></td></tr>
                    </table>
    </g:form>

    <div id="showprev">
        <g:set var="x" value="${row-1}"/>
        <table class="table  table-bordered col-sm-12 table-strip">
            <g:each in="${(0..x).toList()}" var="i" status="d">
                <tr>
                    <g:each in="${listOfLists[i]}" var="e" status="j">
                        <g:if test="${i==0}">
                            <td><b>${listOfLists[i][j]}</b></td>
                        </g:if>
                        <g:else>
                            <td>${listOfLists[i][j]}</td>
                        </g:else>
                    </g:each>
                     <g:if test="${i==0}">
                        <td><b>Edit</b></td>
                        <td><b>Delete</b></td>
                     </g:if>
                        <g:if test="${i!=0}">
                             <td><g:link action="editCourseMaterial" id="${i}"><i class="fa fa-2x fa-pencil-square-o"> </i></g:link></td>
                             <td><g:link action="deletecoursematerial" id="${i}"><i class="fa fa-2x fa-trash"> </i></g:link></td>
                        </g:if>
                </tr>
            </g:each>
        </table>
    </div>
<script>
function fetchmaterial()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("showprev").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "${request.contextPath}/courseMaterial/addeditcoursematerial?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}
function callme()
{
        //var coursecodecoursename = document.getElementById("coursecodecoursename").value;
        var uploadcategory = document.getElementById("uploadcategory").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material1").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/courseMaterial/processcoursematerial?uploadcategory="+uploadcategory, true);
                xmlhttp.send();
        return(false)
}
function callmetoo()
{
        var courseoutline = document.getElementById("courseoutline").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material2").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/courseMaterial/furtherprocesscoursematerial?courseoutline=" + courseoutline, true);
                xmlhttp.send();
        return(false)
  }
  function callmetooagain()
  {
        var coursetopic = document.getElementById("coursetopic").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material13").innerHTML = this.responseText;
                    }
                };
             //   xmlhttp.open("GET", "${request.contextPath}/courseMaterial/savecoursematerial?coursetopic=" + coursetopic, true);
             //   xmlhttp.send();
        return(false)
  }
</script>
</div>
</body>
</html>