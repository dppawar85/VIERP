<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'courseCategory.label', default: 'CourseCategory')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <g:link controller="CourseCategory" action="searchCatagory">Course List</g:link>
    </body>
</html>