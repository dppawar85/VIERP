<head>
 <meta name="layout" content="blanklearner">
 <script>
     function goBack() {
         window.history.back();
     }
     </script>
</head>
<body>
<div class="pull-right" style="position: relative; right: 10px; top: 10px;display: inline-block">
            <a class="btn btn-sm bg-warning" style="color: black" href="${request.contextPath}/learner/learner"> <i class="fa fa-home" aria-hidden="true"></i>HOME </a>
            &nbsp;&nbsp;&nbsp;<button  class="backBtn btn pull-right" onclick="goBack()"><i class="fa fa-angle-left"></i>Go Back</button>
   </div>
<br>
Course Name:${coffr.course.course_code}:${coffr.course.course_name}
<br>
Course Batch:${coffr.batch}
<br>

<table class="table table-bordered">
<th>
Assignment No.
</th>
<th>
Assignment/Problem Statement
</th>
<th>
Weightage
</th>
<th>
Marks
</th>
<g:each var="list" in="${assignmentsubmissionlist}" status="i" >
<tr>

<g:include controller="learner" action="assigninfo" params="[id:list.assignmentoffering.assignment.id]" />
<td>${list.marks}</td>
</tr>
</g:each>
</table>
</body>