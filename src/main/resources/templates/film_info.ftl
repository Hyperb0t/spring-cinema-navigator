<#assign title=film.getTitle()! >
<#include "includes/head.inc.ftl" >
<#include "includes/navhead.inc.js.ftl" >
<div class="col-10 m-auto">
<h1>${film.getTitle()!}</h1>
<div class="container d-flex">
    <div class="col-4">

        <img class="w-50" src="${film.getImageLink()!}">
    </div>
    <div class="col-6">
        <h3 class="">Описание</h3>
        <p>${film.getDescription()!}</p>
        <h3>Продолжительность</h3>
        <p>${film.getDuration().toMinutes()!} мин</p>
        <h3>Тэги</h3>
        <div>
            <#list film.getTags() as tag>
                <p><span class="badge badge-secondary">${tag.getName()}</span></p>
            </#list>
        </div>
    </div>
</div>
</div>
</body>
</html>
