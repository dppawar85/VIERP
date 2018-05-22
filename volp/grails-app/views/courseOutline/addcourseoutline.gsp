<head>
<meta name="layout" content="blank">
<style>
 .error{
 color:red;
 }
 </style>
</head>
<body>
<div  style="border-radius:5px; position: absolute; top: 50px; right: 80px;" class="bg-warning">
      <a class="btn btn-sm" style="color: black" href="${request.contextPath}/instructor/instructor"> <i class="fa fa-home" aria-hidden="true"></i> HOME </a>
    </div> <br>
<div>

  <g:if test="${flash.message}">
  <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
</g:if>
<div class="card-body">

<g:form url="[action:'savecourseoutline']">


<label>Course</label>
<input class="form-control" name="coursecodecoursename" type="text" value="${coursecodecoursename}" readonly >
<br/>
<label><span class="error"> * </span>&nbsp; Course Unit Number  </label>
<input class="form-control" name="outline_number" type="number" value="${courseOutlinelist.size()+1}" min="1" placeholder="ex 1,2,3" pattern="[0-9]{1,4}" required>

<label><span class="error"> * </span>&nbsp; Course Unit Title</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input class="form-control" name="outline" type="text"  placeholder="Enter Course Unit Title" required>
<!-- <td><input name="email" type="hidden" value="${email}" ></td>
<input class="form-control" name="coursecode" type="hidden" value="${coursecode}" >
<input class="form-control" name="coursename" type="hidden" value="${coursename}" >
<input class="form-control" name="coursecodecoursename" type="hidden" value="${coursecodecoursename}" > </td> -->

<center><g:submitButton class="btn btn-primary" name="Save" value="Save"></g:submitButton> </center>
</g:form>
</div>

<br/>

                <table class="table col-sm-12 table-striped  table-bordered"  id="data1" >
                    <thead>
                        <tr class="danger" >

                            <th>Unit No.</th>
                            <th>Unit</th>
                            <th>Edit</th>
                            <th>Delete</th>

                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${courseOutlinelist}" status="i" var="d">
                            <tr class="${(i % 2) == 0 ? 'success' : 'warning'}" >


                                <td>${d.outline_number}</td>
                                <td>${d.outline}</td>
                                <td>  <a href="#" onclick="callMe(${d.id});"><i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;<!-- <g:link action="deletecourseoutline" id="${d.id}"><i class="fa fa-trash-o" aria-hidden="true"></i></g:link> --><span class="glyphicon glyphicon-edit"/></td>
                                <td><g:link action="deletecourseoutline" id="${d.id}"><i class="fa fa-2x fa-trash"> </i></g:link></td>
                            <!--    <td> <g:link action="updatecourseoutline" id="${d.id}"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></g:link>&nbsp;&nbsp;&nbsp;<g:link action="deletecourseoutline" id="${d.id}"><i class="fa fa-trash-o" aria-hidden="true"></i></g:link><span class="glyphicon glyphicon-edit"/></td>  -->
                            </tr>
                        </g:each>
                    </tbody>
                </table>
</div>

</div>
<div id="edit"></div>
<script>
    function callMe(id){
       // alert("callme");
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("edit").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/courseOutline/updatecourseoutline?id=" + id, true);
                xmlhttp.send();
    }
</script>
</body>
</html>