<h4 class="top-h4">${cc.name}</h4>
<!--<div class="container">
<div style="float:left">
<a href="#" onclick="prevcat4()"><i class="fa fa-chevron-left fa-2x" aria-hidden="true"></i>PREV</a>
</div>
<div style="float:right">
<a  href="#"  onclick="nextcat4()">NEXT<i class="fa fa-chevron-right fa-2x" aria-hidden="true"></i></a>
</div>
</div> -->
<div class="col-sm-12" id="sld4">
<g:set var="cnt" value="${0}"/>
<g:each  var="c" in="${crs}">
<div class="col-sm-3">
 <div class="card" style="display:block;height:300px" >
        <div class="card-header">
        <!--<asset:image class="card-img-top" src="homeImages/images.jpg" alt="Card image" style="width:100%; max-height:100px"/>-->
       <g:if test="${c.imgpath==null}">
                                                                 <img src="${createLinkTo(dir:'assets/images/course', file: 'default.jpg' )}" alt="Card image" style="width:100%; max-height:100px" />
                                                             </g:if>
                                 <g:if test="${c.imgpath!=null}">
                                                                <img src="${createLinkTo(dir:c.imgpath, file: c.imgname )}" alt="Card image" style="width:100%; max-height:100px"/>
                                 </g:if>
        </div>
        <g:if test="${(cnt==0)}">
                                                  <div style="position:absolute;left:-20px;opacity:.5">
                                                   <a href="#sld4" onclick="prevcat4()"><i class="fa fa-chevron-circle-left fa-3x" aria-hidden="true"></i></a>
                                                  </div>
                                                  </g:if>
                     <g:if test="${cnt==3}">
                                                               <div style="position:absolute;right:-20px;opacity:.5">
                                                                <a href="#sld4" onclick="nextcat4()"><i class="fa fa-chevron-circle-right fa-3x" aria-hidden="true"></i></a>
                                                               </div>
                                                               </g:if>
         <div class="card-body" style=" min-height:200px" >

           <p style="text-align:justify;word-wrap: break-word;">
           <h4><g:link action="course" controller="login" params="[cid:c.id]">${c.course_name}</g:link></h4><br>
           <h4> <i class="fa fa-star fa-1x"></i>&nbsp;${c.rating}</h4>
            <h5>${c.courseowner.person.firstName} ${c.courseowner.person.lastName}</h5>
           <!--Features::<br>
                      <g:each var="f" in="${c.features}">
                      ${f.feature}<br>
                        </g:each> -->
           </p>

         </div>
       </div>
       </div>
<g:set var="cnt" value="${cnt+1}"/>
</g:each>
</div>





