<#assign title = "Личный кабинет">
<#include "includes/head.inc.ftl">
<#include "includes/navhead.inc.js.ftl">
<h2 class="col-10 mx-auto my-2">Личный кабинет</h2>
<div class="d-flex col-10 mx-auto p-0">
    <div class="col-4">
        <img class="rounded w-75 my-4" src="${springMacroRequestContext.contextPath}/static/userprofile.png">
        <p>Email: ${userObject.getEmail()}</p>
        <p>Бонусы: ${userObject.getBonuses()!}</p>
        <a href="${springMacroRequestContext.contextPath}/logout">Выйти</a>
    </div>
    <div class="col-5">
        <h3>Ваши последние заказы: </h3>

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Дата и время покупки</th>
                <th scope="col">Фильм</th>
                <th scope="col">Время</th>
                <th scope="col">Место</th>
            </tr>
            </thead>
            <tbody>
            <#list orders as order>
                <tr>
                    <td><p>${formatter.format(order.getDatetime())}</p></td>
                    <td><p>${order.getSeance().getFilm().getTitle()}</p></td>
                    <td><p>${formatter.format(order.getSeance().getStartTime())}</p></td>
                    <td><p>${order.getPlace()}</p></td>
                </tr>
            </#list>
            </tbody>
        </table>

    </div>
</div>
</body>
</html>
