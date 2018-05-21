<html>
<head>
         <meta name="layout" content="blank">

             <style>
                 .backBtn{
                 display:none;
                 }
                 .cur{
                 cursor: pointer;
                 }
                 #diplayForm{
                   display:none;

                 }
             </style>


</head>
<body>


    <div style="border-radius:5px; position: absolute; top: 50px; right: 10px;" class="bg-warning">
        <a class="btn btn-sm" style="color: black" href="${request.contextPath}/instructor/instructor"> <i class="fa fa-home" aria-hidden="true"></i> HOME </a>
    </div>
    <br>

<div id="dfpage" class="container">


<div>
<h5 class="alert alert-info cur"  onclick="displyForm()">Start New Topic <i class="fa fa-plus-circle"> </i></h5>
<div id="diplayForm">
<g:form  name="myForm" controller ="ForumTopic" action="addTopic" enctype="multipart/form-data">
<input class="form-control" type="text" name="ftopic" placeholder="enter topic" />
<br>

<g:textArea class="form-control" name="fdesc" value="" placeholder="enter description"  rows="5" cols="40"/><br>

Select Picture: <input class="btn btn-sm" type="file" id="file"  name="coursePic"/>

<g:hiddenField name="type" value="${ut.type}" />
<g:hiddenField name="coffrid" value="${coffrid}" />
<g:hiddenField name="opid" value="${opid}" />
<input type="submit" value="post" class="btn btn-sm btn-danger">
<!-- <g:submitToRemote url="[controller: 'ForumTopic', action: 'addTopic']" update="dfpage" value="Post" /> -->

</g:form>
</div>
<br>
<h5 class="alert alert-warning">My Posts</h5>
<div class="row">


    <div class="col-sm-3 "><b>Title</b></div>

    <div class="col-sm-3"><b>By</b></div>

    <div class="col-sm-3"><b>Replies</b></div>

    <div class="col-sm-3"><b>Date</b></div>
</div>

<g:each var="topic" in="${forumTopics}" >
<div class="row">

    <div class="col-sm-3">
    <g:link controller="forumThreads" action="getThreads" params="[ftopics: topic.id,typeid:ut.id]" >
        ${topic.title}
        <g:if test="${topic.imagepath==null}"></g:if>
        <g:else><i class="fa fa-file-image-o" aria-hidden="true"></i></g:else>
    </g:link>
     </div>
     <div class="col-sm-3">
          <g:include action="getName" controller="forumThreads" params="[opid:topic.opid]" />
     </div>
    <div class="col-sm-3">
        <i class="fa fa-comments-o"></i>&nbsp;${topic.forumthreads.size()}
    </div>
    <div class="col-sm-3">
        ${topic.updation_date}
    </div>
    </div>
    <br>
</g:each>
<br><br>


</div>
<script>
function displyForm(){
document.getElementById("diplayForm").style.display="block";

}
function getThreads(topicid,typeid,divid){ //ForumTopic
    alert("t:"+divid)
    document.getElementById(divid).style.display = "block"
     var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
       // alert("1")
        if (this.readyState == 4 && this.status == 200) {
             // alert("2")
              document.getElementById(divid).innerHTML = this.responseText;
         }
         };
         xmlhttp.open("GET", "${request.contextPath}/forumThreads/getThreads?ftopics=" +topicid+"&typeid="+typeid, true);
         xmlhttp.send();
}

</script>
</body>
</html>