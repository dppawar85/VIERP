<!doctype html>
    <html>
    <head>
    <meta name="layout" content="blank">
    <style>
      table {
          border-collapse: collapse;

      }

      table, th, td {
          padding: 2px;
          border: 1px solid black;
          text-align: center;
      }
    </style>

    </head>
    <body>
    <div style="border-radius:5px; position: absolute; top: 50px; right: 80px;" class="bg-warning">
        <a class="btn btn-sm" style="color: black" href="/instructor/instructor"> <i class="fa fa-home" aria-hidden="true"></i> HOME </a>
    </div>
    <br><br>
    <table>
    <th>Assignment Number</th>
    <th>Assignment Title</th>
    <th>Assignment Description</th>
    <th>Assignment Weightage</th>
    <th>Assignment Type</th>
    <th>Assignment path</th>
    <th>Assignment Text</th>
    <th>Evaluate Assignment </th>
    <tr>
<g:each var="ass" in="${li}">
    <g:if test="${ass.assignment.assignmenttype.type != 'MCQ'}">
  <td>  ${ass.assignment_offering_number}</td>
    <td>${ass.assignment.title}</td>
    <td>${ass.assignment.description}</td>
    <td>${ass.assignment.weightage}</td>
    <td>${ass.assignment.assignmenttype.type}</td>
    <g:if test="${ass.assignment.assignment_path==''}">
        <td><a href=${ass.assignment.assignment_path}></a></td>
    </g:if>
    <g:else>
        <td><a href=${ass.assignment.assignment_path}>Link</a></td>
    </g:else>
    <td>${ass.assignment.assignment_text}</td>
    <td><a href="${request.contextPath}/assignmentSubmission/listallstudents?asoid=${ass.id}">Evaluate</a></td>

    </g:if>
</tr>


    </g:each>
    </table>
    <br><br>
    </body>
</html>