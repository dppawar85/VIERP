                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>

<g:form url="[controller:'Organization',action: 'saveinstructor']" enctype="multipart/form-data">
    <br>
    <b>Upload Excelsheet having columns: 1.Employee Code 2. First Name 3. Middle Name 4. Last Name   5. Designation 6. Program  7. official_email_id</b>
    <br><br>
    <input class="btn btn-warning" type="file" id ="sheet" name="sheet" required="true">
    &nbsp;&nbsp;&nbsp;&nbsp;
    <g:submitButton class="btn btn-primary" name="upload" value="upload" update="mainorgranizationajax" />
    <br>
</g:form>
