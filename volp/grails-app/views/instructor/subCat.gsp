
<g:if test="${check == 0}">
     Sub-Category(if any):
      <g:select class="form-control" optionKey="id" from="${scat}" optionValue="${{it.name}}" name="sscat" onchange="addCats(this.value)" noSelection="${['null':'Please Select Sub-Category']}" />
      Category:- ${msg}
</g:if>
<g:else>
     Sud-Category:<g:select class="form-control" optionKey="id" from="${scat}" optionValue="${{it.name}}" name="sscat" onchange="addCat(this.value)" />Category:- ${msg}
</g:else>