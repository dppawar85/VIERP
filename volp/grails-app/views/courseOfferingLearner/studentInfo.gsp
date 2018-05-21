<div class="container">

        <table class="table table-striped">
            <th> Name of the Student</th>
            <th> Registration Number</th>
            <th> Organization </th>
            <th> Year</th>
            <th> Program</th>
            <th> Delete Student</th>
            <g:each var="learner" in="${coffrleraner}">
                <tr>
                    <td>
                        <g:if test="${learner.learner.person!=null}">
                            ${learner.learner.person.firstName} ${learner.learner.person.lastName}
                        </g:if>
                    </td>
                    <td>
                        ${learner.learner.registration_number}
                    </td>
                    <td>
                             <g:if test="${learner.learner.organization!=null}">
                                 ${learner.learner.organization.organization_name}
                             </g:if>
                         </td>
                         <td>
                             <g:if test="${learner.learner.current_year!=null}">
                                 ${learner.learner.current_year.year}
                             </g:if>
                         </td>
                         <td>
                             <g:if test="${learner.learner.program!=null}">
                                 ${learner.learner.program.name}
                             </g:if>
                         </td>
                         <td>
                             <a href="#" onclick="studentDelete(${learner.id})"><i class="fa fa-trash-o" aria-hidden="true" data-toggle="tooltip" title="delete"></i></a>
                             <!-- <g:submitToRemote url="[action: 'studentDelete($learner.id)']" update="showDeleted" value="Delete"/> -->
                         </td>
                     </tr>
                 </g:each>
             </table>
     </div>
    </table>
</div>
