
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

 </div>