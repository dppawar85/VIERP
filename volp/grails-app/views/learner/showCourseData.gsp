 <g:set var="asssub" value="${0}"/>
 <g:set var="i" value="${0}"/>
 <g:set var="mcq" value="${0}"/>
 <g:each var="listoflist" in= "${listOflistOfLists}">
 <g:set var="y" value="${3}"/>
 <g:each var="j" in="${0..y}">
 <g:if test="${i==0 && j==0 && unitwisetopicwise!='topic'}">
 <b>Unit:${listOflistOfLists[i][j][0].courseoutline.outline_number}::${listOflistOfLists[i][j][0].courseoutline.outline}<br></b>
</g:if>

<g:if test="${i==0 && j==0 && unitwisetopicwise=='topic'}">
<br>
<b>Topic:${listOflistOfLists[i][j][0].topic_number}::${listOflistOfLists[i][j][0].topic}<br></b>
</g:if>
<g:if test="${i!=0 && j==0}">
<hr>
<br>
<b>Topic:${listOflistOfLists[i][j][0].topic_number}::${listOflistOfLists[i][j][0].topic}<br></b>
</g:if>
<g:else>
<g:each var="list" in= "${listOflistOfLists[i][j]}">
<g:if test="${j==1}">
<div class="panel-group">
 <div class="panel panel-default">
   <div class="panel-heading">
     <h4 class="panel-title">
            <a class="form-control" data-toggle="collapse" href="#v${list.id}">Video on ${list.video_name}&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
     </h4>
   </div>
   <div id="v${list.id}" class="panel-collapse collapse">
     <div class="panel-body">
       <table class="col-sm-12 table table-striped table-bordered">
         <tr><td><center><iframe class="embed-responsive-item" width="${vp.width}" height="${vp.height}" src="https://www.youtube.com/embed/${list.video_link}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe></center></td></tr>
         <tr><td><center>${list.video_name}&nbsp;${list.description}</center></td></tr>
       </table>
     </div>
   </div>
 </div>
</div>
</g:if>
<g:if test="${j==2}">
<div class="panel-group">
 <div class="panel panel-default">
   <div class="panel-heading">
     <h4 class="panel-title">
       <a class="form-control" data-toggle="collapse" href="#m${list.id}">Topic Material on ${list.material_name}&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
     </h4>
   </div>
   <div id="m${list.id}" class="panel-collapse collapse">
    <div class="panel-body">
      <table class=" col-sm-12 table table-striped table-bordered">
       <tr>
         <td>${list.material_name}&nbsp;&nbsp;${list.description}</td>
         <td><a href="${list.material_link}" target="_blank"><i class="fa fa-download fa-2x"></i></a></td>
       </tr>
     </table>
   </div>
 </div>
</div>
</div>

</g:if>

<g:if test="${j==3}">
<div class="panel-group">
  <div class="panel panel-default">
   <div class="panel-heading">
     <h4 class="panel-title">
       <a class="form-control" data-toggle="collapse" href="#a${list.id}"><b>Assesment:</b>&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
     </h4>
   </div>
   <div id="a${list.id}" class="panel-collapse collapse">
    <div class="panel-body">

      <g:if test="${list.assignment.assignmenttype.type!='MCQ'}">
      <b>Assignment/Project Title:</b>${list.assignment.title}&nbsp;&nbsp;
      <b>Description:</b>${list.assignment.description}&nbsp;&nbsp;
      <b>Weightage:</b>${list.assignment.weightage}&nbsp;&nbsp;
      <g:if test="${list.assignment.assignment_link!=''}">
      <b>Assignment Link:</b>
      <a href="${list.assignment.assignment_link}" target="_blank">${list.assignment.assignment_link}</a>
    </g:if>
    <g:if test="${assignmentsubmissionlist[asssub]!=null}">
    <br> Your File:<a href="${assignmentsubmissionlist[asssub].student_answer_file_path}/${assignmentsubmissionlist[asssub].student_answer_file_name}" target="_blank">${assignmentsubmissionlist[asssub].student_answer_file_name}</a><br>
	<br>Your Marks:${assignmentsubmissionlist[asssub].marks}/${assignmentsubmissionlist[asssub].assignmentoffering.assignment.weightage}</br>
  </g:if>
  <g:if test="${session.courseofferingtypelearner=='Self-Pace'}">
  <g:form url="[controller:'Learner',action:'submitassignment']" enctype="multipart/form-data">
  Upload Your Work:   <input type="file" id ="assignment" name="assignment" >
  <g:submitButton class="btn btn-primary" name="upload" value="upload"></g:submitButton>
  <input type="hidden" name="ao" value="${list.id}">
</g:form>
</g:if>
<g:else>
<label class="btn-warning" style="background-color: #f18973" ><b>Due Date:</b><g:formatDate date="${list.due_date}" name="due_date" format="dd-MM-yyyy"/></label>
<g:if test="${date<=list.due_date}">
<g:form url="[controller:'Learner',action:'submitassignment']" enctype="multipart/form-data">
Upload Your Work:   <input type="file" id ="assignment" name="assignment" >
<g:submitButton class="btn btn-primary" name="upload" value="upload"></g:submitButton>
<input type="hidden" name="ao" value="${list.id}">
</g:form>
</g:if>
<g:else>
<g:form url="[controller:'Learner',action:'submitassignment']" enctype="multipart/form-data">
Upload Your Work:   <input type="file" id ="assignment" name="assignment" >
<g:submitButton class="btn btn-primary" name="upload" value="upload"></g:submitButton>
<input type="hidden" name="ao" value="${list.id}">
</g:form>
</g:else>
</g:else>
<g:set var="asssub" value="${asssub+1}"/>
</g:if>
<g:else>
<div class="card card-body">
  <div class="row">
    <b>MCQ Title:</b>${list.assignment.title}&nbsp;&nbsp;
    <b>Description:</b>${list.assignment.description}&nbsp;&nbsp;
    <b>Weightage:</b>${list.assignment.weightage}&nbsp;&nbsp;

  </div>
  <label><b>Problem Statement :</b>${list.assignment.assignment_text}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
  <g:if test="${session.courseofferingtypelearner=='Self-Pace'}">
  <g:form url="[controller:'Learner',action:'submitmcq']">
  <g:each var="options" in= "${MCQOptionslistoflist[mcq]}">
  <g:if test="${assignmentsubmissionlist[asssub]!=null}">
  <g:if test="${assignmentsubmissionlist[asssub].mcqoptions.option_name==options.option_name}">
  <div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" checked="true"/> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
</g:if>
<g:else>
<div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" /> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
</g:else>
</g:if>
<g:else>
<div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" /> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
</g:else>
</g:each>
<input type="hidden" name="ao" value="${list.id}">
<g:submitButton class="btn btn-primary" name="Save Answer" value="Save Answer"></g:submitButton>
</g:form>
</g:if>
<g:else>
<label class="btn-warning" style="background-color: #f18973" ><b>Due Date:</b><g:formatDate date="${list.due_date}" name="due_date" format="dd-MM-yyyy"/></label>
<g:if test="${date<=list.due_date}">
<br>
<g:form url="[controller:'Learner',action:'submitmcq']">
<g:each var="options" in= "${MCQOptionslistoflist[mcq]}">
<g:if test="${assignmentsubmissionlist[asssub]!=null}">
<g:if test="${assignmentsubmissionlist[asssub].mcqoptions.option_name==options.option_name}">
<div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" checked="true"/> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
</g:if>
<g:else>
<div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" /> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
</g:else>
</g:if>
<g:else>
<div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" /> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
</g:else>
</g:each>
<input type="hidden" name="ao" value="${list.id}">
<g:submitButton class="btn btn-primary" name="Save Answer" value="Save Answer"></g:submitButton>
</g:form>
</g:if>
<g:else>
<br>
<g:form url="[controller:'Learner',action:'submitmcq']">
<g:each var="options" in= "${MCQOptionslistoflist[mcq]}">
<g:if test="${assignmentsubmissionlist[asssub]!=null}">
<g:if test="${assignmentsubmissionlist[asssub].mcqoptions.option_name==options.option_name}">
<div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" checked="true" disabled="true"/> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
</g:if>
<g:else>
<div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" disabled="true"/> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
</g:else>
</g:if>
<g:else>
<div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" disabled="true"/> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
</g:else>
</g:each>
<input type="hidden" name="ao" value="${list.id}">
<g:submitButton class="btn btn-primary" name="Save Answer" value="Save Answer" disabled="true"></g:submitButton>
</g:form>
</g:else>
</g:else>
<g:set var="mcq" value="${mcq+1}"/>
<g:set var="asssub" value="${asssub+1}"/>
</div>
</g:else>
</div>
</div>
</div>
</div>
</g:if>
<br>
</g:each>
</g:else>
</g:each>
<g:set var="i" value="${i+1}"/>
<br>
</g:each>