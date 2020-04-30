<#assign title="Расписание сеансов" >
<#include "includes/head.inc.ftl" >
<#include "includes/navhead.inc.js.ftl" >
<form class="d-flex mt-4">
    <div class="d-flex col-6">
        <input type="date" class="input-group-text" name="date">
        <input type="submit" class="btn btn-info" value="Посмотреть билеты на дату">
    </div>
</form>
<form class="d-flex mt-4">
    <div class="d-flex col-6">
        <input type="date" class="input-group-text" name="fromDate" placeholder="От">
        <input type="date" class="input-group-text" name="toDate" placeholder="До">
        <input type="submit" class="btn btn-info" value="Посмотреть билеты в промежутке">
    </div>
</form>
<div class="d-flex flex-row">
<#--    <div class="col-3">-->
<#--        <div class="card">-->
<#--            <div class="card-body">-->
<#--                <h5 class="text-center">Популярные фильмы по версии <a href="https://www.kinopoisk.ru/">кинопоиска</a> </h5>-->
<#--                <ol>-->
<#--                    <li>1</li>-->
<#--                    <li>2</li>-->
<#--                </ol>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->
    <div class="col-12">
    <table class="table">
            <thead>
            <tr>
                <th scope="col">Фильм</th>
                <th scope="col">Начало</th>
                <th scope="col">Окончание</th>
                <th scope="col">Цена</th>
            </tr>
            </thead>
            <tbody>
            <#list seances as seance>
                <tr>
                    <td>
                        <a href="${springMacroRequestContext.contextPath}/film?id=${seance.getFilm().getId()}">${seance.getFilm().getTitle()}</a>
                    </td>
                    <td><p>${formatter.format(seance.getStartTime())}</p></td>
                    <td><p>${formatter.format(seance.getEndTime())}</p></td>
                    <td><p>${seance.getPrice()}</p></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
<#include "includes/footer.inc.ftl">
