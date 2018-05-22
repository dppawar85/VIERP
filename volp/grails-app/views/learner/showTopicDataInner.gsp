<g:if test="${coursevideos.size()!=0}">
	<div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="form-control" data-toggle="collapse" href="#collapse_Course_Videos">Course Videos(s)&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
                </h4>
            </div>
            <div id="collapse_Course_Videos" class="panel-collapse collapse">
            <table class=" col-sm-12 table table-striped table-bordered">

            				 <g:each var="vdo" in="${allVideo}">


            						<tr>
            							<td> <center> <iframe class="embed-responsive-item" width="${vp.width}" height="${vp.height}" src="https://www.youtube.com/embed/${cv.video_link}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe> </center> </td>
            						</tr>
                                    <tr>
                                        <td> <center> ${vdo.coursevideos.video_name}&nbsp;${vdo.coursevideos.video_link}&nbsp;${vdo.coursevideos.description}</center> </td>
                                    </tr>
                                    <tr >
                                        <g:if test="${vdo.isViewed==true}">
                                        <td>Mark as Complete<input type="checkbox" id="viewCheck" onclick="videoCompleted(${regCrs},${vdo.coursevideos.id})" checked="true" disabled = "true"></td>
                                        </g:if>
                                        <g:else>
                                        <td>Mark as Complete<input type="checkbox" id="viewCheck" onclick="videoCompleted(${regCrs},${vdo.coursevideos.id})">
                                        </g:else>
                                    </tr>
                                    <!-- <iframe width="480" height="270" src="https://www.youtube.com/embed/${cv.video_link}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe> -->

            					 </g:each>
            				</table>

				<!-- <table class=" col-sm-12 table table-striped table-bordered">
					<g:each var="cv" in="${coursevideos}">
						<tr>
							<td> <center> <iframe class="embed-responsive-item" width="${vp.width}" height="${vp.height}" src="https://www.youtube.com/embed/${cv.video_link}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe> </center> </td>
						</tr>
                        <tr>
                            <td> <center> ${cv.video_name}&nbsp;${cv.video_link}&nbsp;${cv.description}  </center> </td>
                        </tr>
                         <iframe width="480" height="270" src="https://www.youtube.com/embed/${cv.video_link}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
					</g:each>
				</table>-->
			</div>
		</div>
	</div>
</g:if>
<g:if test="${coursematerial.size()!=0}">
	<div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="form-control" data-toggle="collapse" href="#collapse_Course_Material">Course Material(s)&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
                </h4>
            </div>
                <div id="collapse_Course_Material" class="panel-collapse collapse">
                    <g:each var="cm" in="${coursematerial}">
	                    <div class="panel-body">
	                        <table class=" col-sm-12 table table-striped table-bordered">
	                        <tr>
	                            <td>${cm.material_name}&nbsp;&nbsp;${cm.description}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td><a href="${cm.material_link}" target="_blank"><i class="fa fa-download fa-2x"></i></a></td>
                            </tr>
                            </table>
                      </div>
  				</g:each>
  			</div>
  		</div>
  	</div>
</g:if>

<g:set var="mcq" value="${0}"/>
<g:set var="asssub" value="${0}"/>
<g:if test="${assignmentlist.size()!=0}">
<div class="panel-group">
	<div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a class="form-control" data-toggle="collapse" href="#collapse_Course_Assessment">Course Assessment(s)&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
            </h4>
        </div>
        <div id="collapse_Course_Assessment" class="panel-collapse collapse">
            <div class="panel-body">
                <g:each var="list" in= "${assignmentlist}">
					<g:if test="${list.assignment.assignmenttype.type!='MCQ'}">
						<b>Assignment/Project Title :</b>${list.assignment.title}&nbsp;&nbsp;
						<b>Description :</b>${list.assignment.description}&nbsp;&nbsp;
						<b>Weightage :</b>${list.assignment.weightage}&nbsp;&nbsp;
						<g:if test="${list.assignment.assignment_link!=''}">
							<b>Assignment Link:</b>
							<a href="${list.assignment.assignment_link}" target="_blank">${list.assignment.assignment_link}</a>
						</g:if>
						<g:if test="${assignmentsubmissionlist[asssub]!=null}">
							<br> Your File :<a href="${assignmentsubmissionlist[asssub].student_answer_file_path}/${assignmentsubmissionlist[asssub].student_answer_file_name}" target="_blank">${assignmentsubmissionlist[asssub].student_answer_file_name}</a><br>
	                        <br>Your Marks:${assignmentsubmissionlist[asssub].marks}/${assignmentsubmissionlist[asssub].assignmentoffering.assignment.weightage}</br>
						</g:if>
						<g:if test="${session.courseofferingtypelearner=='Self-Pace'}">
							<g:form url="[controller:'Learner',action:'submitcourseassignment']" enctype="multipart/form-data">
								<br>Upload Your Work:
								<input class="btn btn-info form-control" type="file" id ="assignment" name="assignment" >
								<br>
								<g:submitButton class="btn btn-primary" name="upload" value="upload"></g:submitButton>
								<br>
								<input class="form-control" type="hidden" name="ao" value="${list.id}">
							</g:form>
						</g:if>
						<g:else>
							<b>Due Date :</b>
							<g:formatDate date="${list.due_date}" name="due_date" format="dd-MM-yyyy"/>
							<g:if test="${date<=list.due_date}">
								<g:form url="[controller:'Learner',action:'submitcourseassignment']" enctype="multipart/form-data">
									<br>Upload Your Work:
									<input class="btn btn-info form-control" type="file" id ="assignment" name="assignment" >
									<br>
									<g:submitButton class="btn btn-primary" name="upload" value="upload"></g:submitButton>
									<br>
									<input class="form-control" type="hidden" name="ao" value="${list.id}">
								</g:form>
							</g:if>
							<g:else>
								<g:form url="[controller:'Learner',action:'submitcourseassignment']" enctype="multipart/form-data">
									<br>Upload Your Work:
									<input class="btn btn-info form-control" type="file" id ="assignment" name="assignment" disabled="true">
									<br>
									<g:submitButton class="btn btn-primary" name="upload" value="upload" disabled="true"></g:submitButton>
									<br>
									<input class="form-control" type="hidden" name="ao" value="${list.id}">
								</g:form>
							</g:else>
						</g:else>
						<g:set var="asssub" value="${asssub+1}"/>
					</g:if>
					<g:else>
						<div class="card card-body">
							<div class="row"><b>MCQ Title :</b>${list.assignment.title}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<b>Description :</b>${list.assignment.description}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

								<b>Weightage :</b>${list.assignment.weightage}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
							<label><b>Problem Statement :</b>${list.assignment.assignment_text}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></label>
							<g:if test="${session.courseofferingtypelearner=='Self-Pace'}">
								<g:form url="[controller:'Learner',action:'submitcoursemcq']">
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
								<label class="btn-warning" style="background-color: #f18973" ><b>Due Date :</b><g:formatDate date="${list.due_date}" name="due_date" format="dd-MM-yyyy"/></label>
								<g:if test="${date<=list.due_date}">
					                    <g:form url="[controller:'Learner',action:'submitcoursemcq']">
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
					                    <g:form url="[controller:'Learner',action:'submitcoursemcq']">
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
					                        <g:submitButton class="btn btn-primary" name="Save Answer" value="Save Answer" disabled="true" ></g:submitButton>
					                    </g:form>
								</g:else>
							</g:else>
							<g:set var="mcq" value="${mcq+1}"/>
                            <g:set var="asssub" value="${asssub+1}"/>
						</div>
					</g:else>
				</g:each>
			</div>
        </div>
	</div>
</div>
</g:if>
