<html>
<head>
<meta name="layout" content="blank">
</head>
<body>
  <div class="pull-right bg-warning" style="position: relative; top: 10px; right: 80px">
         <a class="btn btn-sm" style="color: black" href="${request.contextPath}/instructor/instructor"> <i class="fa fa-home" aria-hidden="true"></i> HOME </a>
  </div>

<div class="container">
<br>
  <g:if test="${flash.message}">
  <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
</g:if>

<b>Course:${session.course.course_name}</b>
 <g:form action="savecoursevideo" controller="courseVideos">

<table class="table table-bordered" >
<tbody>

<tr>

<td><label></label> <span class="error">*</span>&nbsp;<g:select class="form-control" name="uploadcategory" id="uploadcategory" from="${uploadcategorylist}" onChange="return callme();"/></td>
</tr>
</tbody>
</table>

<div id="material1"></div>
<div id="material2"></div>

 <div id="material3"></div>

 <div id="material4"></div>

</g:form>
</div>
</div>
<div id="addeditvdo">
<g:set var="x" value="${row-1}"/>
<table class="table table-striped table-responsive table-bordered" style="margin-left: 10%">
<tbody>
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
    <g:if test="${i!=0}">
        <td> <g:link action="editVideo" id="${i}"><i class="fa fa-2x  fa-pencil-square-o" aria-hidden="true" data-toggle="tooltip" title="Edit"></i></g:link></td>
        <td> <g:link action="deletecoursevideo" id="${i}"><i class="fa fa-2x fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i></g:link></td>
    </g:if>
    </tr>
</g:each>
</tbody>
</table>
<script>
    function callme()
    {
     // var coursecodecoursename = document.getElementById("coursecodecoursename").value;
      var uploadcategory = document.getElementById("uploadcategory").value;
      //alert(uploadcategory)
      var xmlhttp = new XMLHttpRequest();
      xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          document.getElementById("material1").innerHTML = this.responseText;
        }
      };
      //xmlhttp.open("GET", "${request.contextPath}/courseVideos/processcoursevideo?coursecodecoursename=" + coursecodecoursename +"&uploadcategory="+uploadcategory, true);
      xmlhttp.open("GET", "${request.contextPath}/courseVideos/processcoursevideo?uploadcategory=" + uploadcategory, true);
      xmlhttp.send();
      return(false)
    }
    function fetchvideo()
    {
     var coursecodecoursename = document.getElementById("coursecodecoursename").value;
     var xmlhttp = new XMLHttpRequest();
     xmlhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
         document.getElementById("material4").innerHTML = this.responseText;
       }
     };
     xmlhttp.open("GET", "${request.contextPath}/courseVideos/addeditcoursevideo?coursecodecoursename=" + coursecodecoursename, true);
     xmlhttp.send();
     return(false)
    }
    function callmetoo()
    {
      var courseoutline = document.getElementById("courseoutline").value;
      //alert(courseoutline)
      var xmlhttp = new XMLHttpRequest();
      xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          document.getElementById("material2").innerHTML = this.responseText;
        }
      };
      xmlhttp.open("GET", "${request.contextPath}/courseVideos/furtherprocesscoursevideo?courseoutline=" + courseoutline, true);
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
                 //   xmlhttp.open("GET", "${request.contextPath}/courseVideos/savecoursevideo?coursetopic=" + coursetopic, true);
                 //   xmlhttp.send();
                 return(false)
               }


</script>
</body>

</html>