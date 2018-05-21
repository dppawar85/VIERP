<!doctype html>
    <html>
    <head>
    <meta name="layout" content="blank">
    <style>

    </style>

    </head>
    <body>
    <div style="position: fixed; top: 50px; right: 80px" class="bg-warning">
            <a class="btn btn-sm" style="color: black" href="/instructor/instructor"> <i class="fa fa-home" aria-hidden="true"></i> HOME </a>
        </div>
        <br><br>
    <table class="table table-bordered">
    <th>Name of the Learner</th>
    <th>submission Date</th>
    <th>student answer file path</th>
    <th>Assignment Weitage</th>
    <th>Marks</th>



    <g:each var="ass" in="${li}">
    <tr>

    <td><h5>${ass.learner.person.firstName}&nbsp &nbsp ${ass.learner.person.lastName}</h5></td>
    <td><h5>${ass.submission_date}</h5></td>
   <!-- <td><a href="${ass.student_answer_file_path}${ass.student_answer_file_name}" download target="_blank">Access answer file</a>  </td>-->
    <td><g:link action="download" id="${ass.id}"  target="_blank">Download Assignment</g:link>  </td>
    <td><h5>${ass.assignmentoffering.assignment.weightage}  </td>

    <td><input type="text" id="myid" onchange="saveMarks(${ass.id},${ass.assignmentoffering.assignment.weightage})"value="${ass.marks}" placeholder="give marks"/>  </td>



    </tr>


    </g:each>
    </table>

    <script>
    function saveMarks(asid,weightage){

    var x=document.getElementById("myid").value
    if (x>weightage)
    {alert("Marks cannot be more than weightage");
    return;
    }

    //document.getElementById("editGrade").style.display = "none"
    //document.getElementById("updateMe").style.display = "none"
    //var x = document.getElementById("material");
    //x.style.display = "block";
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
    document.getElementById("myid").value = this.responseText;
    }
    };
    xmlhttp.open("GET", "${request.contextPath}/assignmentSubmission/savemarks?asid=" +asid +"&marks="+x, true);
    xmlhttp.send();
    }
    </script>
    </body>

    </html>