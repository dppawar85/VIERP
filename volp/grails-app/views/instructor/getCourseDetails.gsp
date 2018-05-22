   <style>
        .error{
         color: red;
         font-style: oblique;
        }
   </style>
    <div class="col-sm-11">

    <g:form name="myForm" url="[action:'processEditCourse',controller:'instructor']" enctype="multipart/form-data">
     <div class="col-md-8 "><b>Select Logo Image for Course:(JPEG format)</b><input class="form-control" type="file" onchange="checkImageSize(this)" name="coursePic"/></div>
    <div class="col-md-4 pull-right">Course Logo:<img width="120" height="100" src="${createLinkTo(dir:crs.imgpath, file: crs.imgname )}" /></div>
    <g:hiddenField name="cid" value="${cid}" />
    Category:<g:select class="form-control" name="cc"
              from="${cc}"
              value="${crs?.coursecategory.id}"
              optionValue="${{it.name}}"
              optionKey="id" />

    Code:<input class="form-control" type="text" value="${crs.course_code}" name="code" /><br>
    Name:<input class="form-control" type="text" value="${crs.course_name}" name="name" required/><br>
    Credit:<input class="form-control" type="number" value="${crs.credit}"  name="credit" /><br>

    Visibilty:<g:select class="form-control" name="cv"
              from="${cv}"
              value="${crs?.coursevisibility.id}"
              optionValue="${{it.name}}"
              optionKey="id" />


    collaborative Instructor(s)<g:select class="form-control" name="ins"
              from="${newins}"
              value="${crs?.courseinstructor.id}"
              optionValue="${{it.uid}}"
              optionKey="id" />


    Previous features:<span class="error">(Select to delete)</span><g:select class="form-control" optionKey="id" from="${features}" optionValue="${{it.feature_number+":"+it.feature}}" name="featuresdel" multiple="true" /><br>
    Add features if any <div id="newlink">
                <div class="feed"></div>
            </div>
            <p id="addnew">                 <a href="javascript:add_feed()"><i class="fa fa-plus-circle fa-2x" aria-hidden="true"></i>  </a>                </p>
    <!-- Previous Description:<span class="error">(Select to delete)</span><g:select class="form-control" optionKey="id" from="${desp}" optionValue="${{it.number+":"+it.description}}" name="despdel" multiple="true" /><br>
    Add Desciption if any:<div id="newlinkdes">
                        <div class="feeddes">

                        </div>
               </div>
    <p id="addnewdes">          <a href="javascript:add_feeddes()"><i class="fa fa-plus-circle fa-2x" aria-hidden="true"></i>  </a>        </p>
-->
    Previous URL:<span class="error">(Select to delete)</span><g:select class="form-control" optionKey="id" from="${url}" optionValue="${{it.url}}" name="urldel" multiple="true" /><br>
    Add URL if any:<div id="newlinkurl">
                <div class="feedurl">
                </div>
            </div>
    <p id="addnewurl">
                <a href="javascript:add_feedurl()"><i class="fa fa-plus-circle fa-2x" aria-hidden="true"></i>  </a>
            </p>

    Previous Prerequisite:<span class="error">(Select to delete)</span><g:select class="form-control" optionKey="id" from="${pre}" optionValue="${{it.number+":"+it.prerequisite}}" name="predel" multiple="true" /><br>
    Add Prerequisite id any<div id="newlinkpre">
                <div class="feedpre">
                </div>
            </div>
    <p id="addnewpre">
                <a href="javascript:add_feedpre()"><i class="fa fa-plus-circle fa-2x" aria-hidden="true"></i>  </a>
            </p>

    <input class="btn btn-primary" type="submit" value="Update" name="submit">
    </g:form>
    <div id="newlinktpl" style="display:none">
        <div class="feed row">
         <input class="form-control col-sm-3" type="number" name="featureno" placeholder="Enter No" size="50" min="1" required>
         <input class="form-control col-sm-8" type="text" name="feature" placeholder="Enter feature" size="50" required>
         <input class="btn btn-sm btn-danger col-sm-1" value="remove" type="button" onclick="javascript:this.parentNode.parentNode.removeChild(this.parentNode)">
        </div> <br>
    </div>

    <div id="newlinktpldes" style="display:none">
        <div class="feeddes row">
         <input class="form-control col-sm-3" type="number" name="descno" placeholder="Enter No." size="50" min="1" required>
         <input class="form-control col-sm-8" type="text" name="description" placeholder="Enter description" size="50" required>
         <input class="btn btn-sm btn-danger col-sm-1" value="remove" type="button" onclick="javascript:this.parentNode.parentNode.removeChild(this.parentNode)">
        </div><br>
    </div>

    <div id="newlinktplurl" style="display:none">
        <div class="feedurl row">

         <input class="form-control col-sm-11" type="text" name="url" placeholder="Enter url" size="50" required>
         <input class="btn btn-sm btn-danger col-sm-1" value="remove" type="button" onclick="javascript:this.parentNode.parentNode.removeChild(this.parentNode)">
        </div><br>
    </div>

    <div id="newlinktplpre" style="display:none">
        <div class="feedpre row">
         <input class="form-control col-sm-3" type="number" name="preno" placeholder="Enter No." size="50" required>
         <input class="form-control col-sm-8" type="text" name="prerequisite" placeholder="Enter Prerequisite" size="50" required>
         <input class="btn btn-sm btn-danger col-sm-1 " value="remove" type="button" onclick="javascript:this.parentNode.parentNode.removeChild(this.parentNode)">
        </div><br>
    </div>
    </div>
