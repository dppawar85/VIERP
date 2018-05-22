<html>
<head>
    <title>listCourseOutline</title>
    <meta charset="utf-8">

    <meta name="layout" content="blank">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <asset:stylesheet src="bootstrap.min.css"/>
    <asset:stylesheet src="font-awesome.min.css"/>
    <asset:stylesheet src="sb-admin.css"/>
    <style>

    </style>
    <script>
        function updatecourse1(myupdateid)
        {
           if (myupdateid.length == 0) {
            document.getElementById("topic").innerHTML = "";
            return;
        } else {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementById("topic").innerHTML = this.responseText;
                }
            };
            xmlhttp.open("GET", "${request.contextPath}/courseTopic/updatecoursetopic?id=" + myupdateid, true);
            xmlhttp.send();
        }
    }
    function saveudateddata()
    {
       // alert("Hi saveudateddata1:"+topic);
        var ono = document.getElementById("update_outline_number").value;
        var tno = document.getElementById("update_topic_number").value;
        var tp =document.getElementById("update_topic").value;
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("topic").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET", "${request.contextPath}/courseTopic/saveupdatecoursetopic?ono=" + ono +"&tno="+tno +"&tp="+tp, true);
        xmlhttp.send();
        return(false)
    }
    function deletecourse1(mydeleteid)
    {
       if (mydeleteid.length == 0) {
        document.getElementById("topic").innerHTML = "";
        return;
    } else {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("topic").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET", "${request.contextPath}/courseTopic/deletecoursetopic?id=" + mydeleteid, true);
        xmlhttp.send();
    }
}
function callme(){
    var ono = document.getElementById("outline_number").value;
    var tp = document.getElementById("topic1").value;
    var tno =document.getElementById("topic_number").value;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("topic").innerHTML = this.responseText;
        }
    };
    xmlhttp.open("GET", "${request.contextPath}/courseTopic/savecoursetopic?ono=" + ono +"&tp="+tp +"&tno="+tno, true);
    xmlhttp.send();
    return(false)
}
function getTopic(topic) {
//document.getElementById('topic').style.display="block";
    if (topic.length == 0) {
        document.getElementById("topic").innerHTML = "";
        return;
    } else {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("topic").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET", "${request.contextPath}/courseTopic/addeditdeletecoursetopics?id=" + topic, true);
        xmlhttp.send();
    }
}
</script>
</head>
<body>
<div class="pull-right bg-warning" style="border-radius:5px; position: absolute; top: 50px; right: 80px;">
             <a class="btn btn-sm" style="color: black" href="${request.contextPath}/instructor/instructor"> <i class="fa fa-home" aria-hidden="true"></i> HOME </a>
      </div>
    <br>
    <br>


    <div class="container" >
        <center>
            <b>Add/Edit Topics</b>
        </center>
        <center>
            <Label class="form-control">
                Course Name
                <input class="form-control col-sm-12" name="coursename" type="text" value="${course.course_name}" readonly>
            </Label>
        </center>
        <div class="col-sm-12">
            <div class="row" >
                <div class="col-sm-6">
                    <table class=" table table-striped table-bordered"  id="data1" >
                        <thead>
                            <tr class="danger" >
                                <th>Unit No.</th>
                                <th>Unit</th>
                                <th>Add Course Topic</th>
                            </tr>
                        </thead>
                        <g:each in="${courseoutlinelist}" status="i" var="d">
                        <tr class="${(i % 2) == 0 ? 'success' : 'warning'}" >
                            <td>${d.outline_number}</td>
                            <td>${d.outline}</td>
                            <td> <a href="#" onclick="getTopic(${d.id});"><i class="fa fa-plus-circle fa-2x" aria-hidden="true"></i></a></td>
                            <!--<td> <div id="topic"></div> </td>-->
                        </tr>
                        </g:each>
                    </table>
                </div>
<div  class="col-sm-6" id="topic"></div>
            </div>

        </div>

    </div>

<asset:javascript src="jquery.min.js"/>
<asset:javascript src="bootstrap.bundle.min.js"/>
<asset:javascript src="jquery.easing.min.js"/>
<asset:javascript src="sb-admin.min.js"/>
</body>
</html>