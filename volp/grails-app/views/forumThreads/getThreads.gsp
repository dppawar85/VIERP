<html>
<head>
<meta name="layout" content="blank">
 <style>
 /* CSS Design For Thread Forum */
    .forumBox{
     margin-top: 18px;
     border: 1px solid grey;
     position: relative;

    }
    .postedBy{
     color:black;
     position: absolute;
     top:-18px;
     left: 5px;

    }
   .postedDate{
     color:black;
     position: absolute;
     top: 2px;

     right: 20px;

   }
   .scrollableArea{
    position: relative;
     overflow-y: scroll;
     height: 20%;
     padding: 10px;
     overflow-wrap: break-word;
     word-wrap: break-word;
     padding: 5px;
   }
   .imageBox{
     position: absolute;
     bottom: 2px;
     left: 40%;

   }
   .replyForm{

    margin-top: 10px;

   }
 </style>
</head>
<body><br><br>
<div class="container-fluid">
<div id="topic row">
    <h4 class="badge alert alert-warning" >${ft.title}</h4> <br>
    <g:textArea  class="form-control" name="fdesc" value="${ft.description}"   rows="5" cols="150" readonly="true"/><br>
    <img width="120" height="100" class="img-rounded" src="${createLinkTo(dir:ft.imagepath, file: ft.imagename )}" />


</div>

<div id="threads">
<g:if test="${fthr.size()==0}">
<span class="badge alert-sm alert-danger">No Threads</span>

</g:if>
<g:else>
   <g:each var="thread" in="${fthr}" >
      <div class="forumBox">

       <div class="postedBy badge">
            <g:include action="getName" controller="forumThreads" params="[opid:thread.opid ,type:thread.type.type ]" />
       </div>

       <div class="postedDate badge alert-sm alert-info">
             ${thread.updation_date}
       </div>

       <div class="scrollableArea">
          <div class="textBox">
              ${thread.comment}
          </div>
          <div class="imageBox">
            <g:if test="${thread.imagename!=null}">
            <img width="120" height="100" src="${createLinkTo(dir:thread.imagepath, file: thread.imagename )}" />
            </g:if>
          </div>
        </div>

      </div>
   </g:each>
</g:else>
</div>

<div class="replyForm">
<g:if test="${ft.isClosed}==false">
<g:form controller ="ForumThreads" action="postThread" id="myForm" method="post" name="myForm" enctype="multipart/form-data">
<g:textArea class="form-control" name="comment" value="" placeholder="Reply..."  rows="5" cols="40"/><br>
<g:hiddenField name="topicid" value="${topicid}" />
<g:hiddenField name="typeid" value="${typeid}" />
Select Picture: <input type="file" id="file"  name="coursePic"/>
<input type="submit" class="btn btn-sm btn-danger" value="Reply" >
 </g:form>
 </g:if>

 <g:else>
 <h4 class="badge alert-sm alert-danger"> Topic Closed</h4>
 </g:else>
 <br><br>
</div>


</div>
<script>
$(document).ready(function(){

       $('.imageBox img').width(120);
       $('.imageBox img').mouseover(function()
       {
          $(this).css("cursor","pointer");
          $(this).animate({width: "500px",height:"500px"}, 'slow');
       });

    $('.imageBox img').mouseout(function()
      {
          $(this).animate({width: "120px",height:"100px"}, 'slow');
       });
   });
   </script>
 </body>
 </html>