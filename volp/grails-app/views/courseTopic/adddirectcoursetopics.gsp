<head>
<meta name="layout" content="blank">
<style>
 .error{
 color:red;
 }
 </style>
</head>
<div class="container">

  <g:if test="${flash.message}">
  <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
</g:if>
<div class="card-body">
<center><b>Add Course Topics</b></center><br>
<g:form url="[controller:'CourseTopic',action:'savedirectcoursetopics']">


<label><span class="error"> * </span>&nbsp;Course</label>
<input  class="form-control" name="coursecodecoursename" type="text" value="${course.course_code}:${course.course_name}" readonly>
<br/>
<label><span class="error"> * </span>&nbsp;Course Topic Number</label>
<input  class="form-control" name="topic_number" value="${coursetopiclist.size()+1}" type="number" min="1" placeholder="ex 1,2,3" pattern="[0-9]{1,4}" required>
<br/>
<label><span class="error"> * </span>&nbsp;Course Topic Statement</label>
<input  class="form-control" name="topic_statement" type="text"  placeholder="Enter Course Topic" required>

<br/>
<center><g:submitButton class="btn btn-primary" name="Save" value="Save"></g:submitButton></center>
</g:form>
<br/>
                <table class=" col-sm-12 table table-striped table-bordered">
                    <thead>
                        <tr class="danger" >

                            <th>Topic Number.</th>
                            <th>Topic Statement</th>
                            <th>Update/Edit</th>
                        </tr>
                    </thead>
                    <tbody >
                        <g:each in="${coursetopiclist}" status="i" var="d">
                            <tr class="${(i % 2) == 0 ? 'success' : 'warning'}" >
                                <td>${d.topic_number}</td>
                                <td>${d.topic}</td>
                                <td> <a href="#edittopic" onclick="callMeAddDirectCourseTopics(${d.id});"><i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-edit"/></td>
                          <!--      <td> <g:link action="updatecourseoutcome" id="${d.id}"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></g:link>&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-edit"/></td>  -->
                            </tr>
                        </g:each>
                    </tbody>
                </table>
</div>
<div id="edit"></div>
<script>
    function callMeAddDirectCourseTopics(id){
        //alert("callme");
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("edit").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/CourseTopic/updatedirectcoursetopic?id=" + id, true);
                xmlhttp.send();
    }
    </script>