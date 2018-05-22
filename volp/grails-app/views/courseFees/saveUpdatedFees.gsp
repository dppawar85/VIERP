
<g:form controller="courseFees"  >
<div class="container">
<table class="table table-striped table-bordered" >

<g:if test="${flash.message}">
   <h6 style="color:red"> <g:message code="${flash.message}" /><h6>
</g:if>
<tr>
	<th>Choose Course&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Select course for which you want to update the Fees</span> </i></th><td><g:select class="form-control" name="insCourses" from="${insCourses}" optionValue="${{it.course_code+":"+it.course_name}}" optionKey="id" noSelection="['':'-Choose your Course-']"/></td>
</tr>
	<tr>
	<th>Choose course fees type&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">From below list select the fees amout you want to update </span> </i></th><td><g:select class="form-control" name="courseFeeType" from="${courseFeeType}" optionValue="${{it.type}}" optionKey="id" noSelection="['':'-Choose your Course FeeType-']"/></td>
	</tr>
<tr>
	<th>choose certificate type&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">With this option you can set different fees for the within and outside college students </span> </i></th><td><g:select class="form-control" name="CertificateType" from="${CertificateType}" optionValue="${{it.type}}" optionKey="id" noSelection="['':'-Choose your Course certificate Type-']"/></td>
   </tr>
   <tr>
        <th>*Fees Amount &nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Depending on the policy, Enter the the amount of fees in appropriate currency</span> </i></th><td><g:textField name="Doller" required=""/></td>
   </tr>
    <td colspan="2"><center><g:submitToRemote class="btn btn-primary " url="[action: 'saveCourseFee']" name="submitFees" value="SetFees" update="data2" /></center></td>
</g:form>
</table>
<div id="data3">
<table class="table table-striped table-bordered"  >
<thead>
<tr class="danger" >
                           <th>Course Name</th>
                            <th>Course Fees Type</th>
                             <th>Course Certificate Type</th>
                             <th>Fees</th>
                             <th>Edit Course Fees</th>
                        </tr>
                    </thead>
                    <tbody >
                        <g:each in="${savedCourseFees}" status="i" var="d">
                            <tr class="${(i % 2) == 0 ? 'success' : 'warning'}" >


                                <td>${d.courseoffering.course.course_name}</td>
                                 <td>${d.coursecertificatetype.type}</td>
                                     <td>${d.coursefeestype.type[0]}</td>
                                        <td id="test2">${d.fees}</td>
                                        <td> <center> <a href ="#" onclick="changeFee(${d.id})" id="${d.id}" ><i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i></a>
                                                    <!--<g:submitToRemote class="btn btn-primary " url="[action: 'changeFees']" name="submitFees" value="EditFees" update="data2" />--></center></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
                <div id="test">
                </div>
                <div>
 </div>


