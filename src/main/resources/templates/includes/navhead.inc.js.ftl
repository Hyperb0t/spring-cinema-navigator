<#ftl encoding='UTF-8'>
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
<body>
<nav class="navbar navbar-light bg-light d-flex">
    <div class="nav-link col-1 mx-1" href="#"></div>
    <a class="nav-link col-2 <#if springMacroRequestContext.requestUri=='/search' >active</#if>"
       href="${springMacroRequestContext.contextPath}/search">Быстрый поиск</a>
    <a class="navbar-brand d-flex p-0 mx-1 justify-content-center"
       href="${springMacroRequestContext.contextPath}/search"><span class="h-75 my-auto">Cinema</span>
        <img src="static/alogo.png" width="45" height="45">
        <span class="h-75 my-auto">navigator</span></a>
    <a class="nav-link col-2 <#if springMacroRequestContext.requestUri=='/timetable' >active</#if>"
    href="${springMacroRequestContext.contextPath}/timetable">Полное расписание</a>
    <a class="nav-link col-1 mx-1"
        <@security.authorize access="isAuthenticated()">
            href="${springMacroRequestContext.contextPath}/cabinet"><@security.authentication property="principal.username" />
        </@security.authorize>
        <@security.authorize access="! isAuthenticated()">
            href="${springMacroRequestContext.contextPath}/login">Войти
        </@security.authorize>
    </a>
</nav>
