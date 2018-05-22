               <center>
               <b>Add/Edit Division</b><br><br>
            <g:if test="${flash.message}">
                <b><div class="message" style:"display: block">${flash.message}</div></b>
            </g:if>

              <g:form action="savedivision">
                <table class="table table-striped">
               <tbody>
                <tr>
                    <td><lable>Division:</lable></td>
                    <td><input class="form-control"  name="division" type="name" required></td>
                </tr>
                <tr>
                    <td><lable>Year:</lable></td>
                    <td><g:select class="form-control" name="year" from="${yearlist.year}" /></td>
                </tr>
                <tr>
                    <td><lable>Program:</lable></td>
                    <td><g:select class="form-control" name="program" from="${programlist.name}" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"> <g:submitToRemote class="btn btn-primary" url="[controller:'Division', action:'savedivision']" update="mainorgranizationajax" value="Add Division" /></td>
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
                            <th>Division</th>
                            <th>Year</th>
                            <th>Program</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody >
                        <g:each in="${divisionlist}" status="i" var="d">
                            <tr class="${(i % 2) == 0 ? 'success' : 'warning'}" >

                                <td>${i+1}</td>
                                <td>${d.name}</td>
                                <td>${d.year.year}</td>
                                <td>${d.program.name}</td>
                                <td> <a href="#" class="btn btn-primary" onclick="editdivision(${d.id})">edit</a></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
             </div>
               </center>
