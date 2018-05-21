<g:set var="wdth" value="${completevideo*100/total}"/>

Your Course Progress:${Math.round(wdth)}% <div id="prgbar1" class="progress col-sm-6" style="height:30px">
    <div class="progress-bar" style="width:${wdth}%;height:30px"></div>
  </div>