 <center>
               <b>Add/Edit Department</b><br><br>
            <g:if test="${flash.message}">
                <b><div class="message" style:"display: block">${flash.message}</div></b>
            </g:if>

              <g:form action="savedepartment">
                <table class="table table-striped">
               <tbody>
                <tr>
                    <td><lable>Department:</lable></td>
                    <td><input class="form-control"  name="name" type="name" placeholder="Enter Department Name" required="true"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"> <g:submitToRemote class="btn btn-primary" url="[controller:'department', action:'savedepartment']" update="mainorgranizationajax" value="Add Department" /></td>
                </tr>
               </tbody>
               </table>
               </g:form>
               <br>
                <div class="container">
                <table class="table table-striped table-responsive table-bordered"  id="data1" style="width:auto">
                    <thead>
                        <tr class="danger" >
                            <th>S.No.</th>
                            <th>Department</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody >
                        <g:each in="${deptlist}" status="i" var="d">
                            <tr class="${(i % 2) == 0 ? 'success' : 'warning'}" >

                                <td>${i+1}</td>
                                <td>${d.name}</td>
                                <td> <a href="#" class="btn btn-primary" onclick="editdepartment(${d.id})">edit</a></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
             </div>

 </center>
