



        <!-- container-fluid-->
          <div class="container" >
           <g:form name="changeFe" controller="courseFees" action="saveUpdatedFees">

          <table class="table table-striped table-bordered" >
  <!--
          <tr>
          <th>Course Name </th> <td>${changeFee.courseoffering.course.course_name}</td>
          </tr>
          <tr>
          <th>Certificate</th> <td> ${changeFee.coursecertificatetype.type}</td>
          </tr>
          <tr>
          <th>FeeType</th> <td>${changeFee.coursefeestype.type[0]}</td>
          </tr>-->
          <tr >
          <th>Course Fees &nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Depending on the policy, Enter the the amount of fees in appropriate currency</span> </i></th><td><g:textField class="form-control" name="chfees" value ="${changeFee.fees}" optionValue="${changeFee.fees}" optionKey="id"/>Course fees in Dollar</td>


          <g:hiddenField name="changeFeeId" value="${changeFee.id}" />
          <td colspan="2"><center>
       <g:submitToRemote class="btn btn-sm btn-primary" url="[action: 'saveUpdatedFees']" update="mainContentDiv" value="Update Fees"/></center></td>

          </tr>
          </g:form>
          </table>
          </div>
          <script>

</script>




