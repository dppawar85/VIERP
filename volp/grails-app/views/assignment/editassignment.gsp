<!doctype html>
<html>
    <head>
        <style>
              table {
                  border-collapse: collapse;

              }

              table, th, td {
                  padding: 2px;
                  border: 1px solid black;
                  text-align: center;
              }
        </style>
    </head>
    <body>
        <g:if test="${type == 'NonMCQ'}">
            <g:form  name="updateassignment" controller="assignment" action="updateassignment" enctype="multipart/form-data">
                <table class="table table-bordered">
                    <tr>
                        <g:hiddenField name="assid" value="${ass.id}" />
                    </tr>
                    <tr>
                        <td><th>Assignment Text</th></td>
                    </tr>
                    <tr>
                        <td><g:textArea name="assignment_text" value="${ass.assignment_text}" rows="5" cols="40"/></td>
                    </tr>
                    <tr>
                        <td><th>Assignment Type</th></td>
                        <td><g:select name="assignment_type" id="${at.id}" from="${at.type}" value="${it}" optionKey="${id}"/></td>
                            <!--<g:textField value="${ass.assignmenttype.type}" name="assignment_type"/>--></td>
                    </tr>
                    <tr>
                        <td><th> Assignment weightage </th></td>
                        <td><g:textField value="${ass.weightage}" name="assignment_weightage"/></td>
                    </tr>
                    <tr>
                        <td><th> Problem Statement </th></td>
                        <td><g:textField value="${ass.description}" name="assignment_description"/></td>
                    </tr>
                    <tr>
                        <td><th>Assignment Answer File</th> </td>
                        <td>Select a file: <input type="file" value="${ass.model_answer_path}" name="assignment_answer">
                            <a href="${ass.model_answer_path}">Existing Assignment Answer File</a>
                        </td>
                    </tr>
                    <tr>
                        <td><th>Assignment File</th> </td>
                        <td>Select a file: <input type="file"  name="assignment_file" >
                            <a href="${ass.assignment_path}">Existing Assignment</a></td>
                    </tr>
                    <tr>
                        <td><th> Assignment Schedule date </th></td>
                        <td><g:datePicker name="myDate" value="${ao.schedule_date}" noSelection="['':'-Choose-']"/></td>
                    </tr>
                    <tr>
                        <td><th> Assignment Due_date </th></td>
                        <td><g:datePicker name="myDate1" value="${ao.due_date}" noSelection="['':'-Choose-']"/></td>
                    </tr>
                    <tr>
                        <center>
                            <td colspan="3"><input name="update" class="btn btn-primary"type="submit" value="Update"/> </td>
                        </center>
                    </tr>
                </table>
            </g:form>
            <br><br>
        </g:if>
        <g:else>
            <g:form name="updatemcq" controller="assignment" action="updatemcq" enctype="multipart/form-data">
                <table class="table table-bordered">
                    <tr>
                        <g:hiddenField name="assid" value="${ass.id}" />
                    </tr>
                    <tr><td colspan=2><h4>Assignment Question Details</h4></td></tr>
                    <tr>
                        <td><b>Assignment Text</b></td>
                        <td><g:textArea value="${ass.assignment_text}" name="assignment_text" rows="2" cols="40"/></td>
                    </tr>
                    <tr>
                        <td><b> Assignment Schedule date </b></td>
                        <td><g:datePicker name="myDate" value="${ao.schedule_date}" noSelection="['':'-Choose-']"/></td>
                    </tr>
                    <tr>
                        <td><b> Assignment Due_date </b></td>
                        <td><g:datePicker name="myDate1" value="${ao.due_date}" noSelection="['':'-Choose-']"/></td>
                    </tr>
                    <tr>
                        <td colspan=2><h4>Assignment Option Details</h4></td>
                    </tr>
                    <tr>
                        <ol type="A">
                        <g:each var="mcqoption" in="${mo}">
                            <g:hiddenField name="mcqoption" value="${mcqoption.id}" />
                            <table class="table table-bordered">
                                <tr>
                                  <li><td>Mcq option value</td></li>

                                    <td><g:textArea value="${mcqoption.option_value}" name="option_value" rows="2" cols="40"/></td>
                                </tr>
                                <tr>
                                    <td>Mcq option isCorrect</td>
                                    <td><g:checkBox id='iscorrect${mcqoption.id}' name="myCheckbox" value="${mcqoption.isCorrect}" onclick="check(${mcqoption.id})"/>
                                        <g:if test="${mcqoption.isCorrect}">
                                            <g:hiddenField id='iscorrect1Hidden${mcqoption.id}' name="iscorrect1" value="${mcqoption.id}" />
                                        </g:if>
                                        <g:else>
                                            <g:hiddenField id='iscorrect1Hidden${mcqoption.id}' name="iscorrect1" value="0" />
                                        </g:else>
                                    </td>
                                </tr>
                                <tr>
                                    <td>option File
                                        Select a file: <input type="file"  name="option_file[]" multiple/ >
                                        <a href="${mcqoption.mcq_option_file_name}">${mcqoption.mcq_option_file_name}</a>
                                    </td>
                                </tr>
                                <!--
                                    <tr>
                                        <td><label>Mcq option file name</labbel></td><td><g:textField value="${mcqoption.mcq_option_file_name}" name="mcq_option_file_name"/></td>
                                    </tr>
                                    <tr>
                                        <td><label>Mcq option file path</labbel></td><td><g:textField value="${mcqoption.mcq_option_file_path}" name="mcq_option_file_path"/></td>
                                    </tr>
                                    <tr>
                                        <td><label>Mcq mcq_option_file_link</labbel></td><td><g:textField value="${mcqoption.mcq_option_file_link}" name="mcq_option_file_link"/></td>
                                    </tr>
                                -->
                            </tr>
                            </table>
                    </g:each>

                    <tr>
                        <center><td colspan="3"><input name="update" class="btn btn-primary"type="submit" value="Update"/> </td></center>
                    </tr>
                </table>
                <br>
            </g:form>
        </g:else>
    </body>
</html>