
<g:select name="subCourses" from="${subCourses.toList()}" optionKey="id" optionValue="name" noSelection="['':'-Choose your course-']" onchange="showCourse(this.value)"/>
