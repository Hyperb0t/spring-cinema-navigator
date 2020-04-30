<#assign title="Успешная покупка">
<#include "includes/head.inc.ftl" >
<#include "includes/navhead.inc.js.ftl" >
<div class="d-flex justify-content-center">
    <div class="col-5 card border border-success my-4">
    <h1 class="text-success">Покупка выполнена</h1>
    <p>Билет отправлен вам на email</p>
    <p>Фильм: ${order.getSeance().getFilm().getTitle()}</p>
    <p>Время: ${formatter.format(order.getSeance().getStartTime())}</p>
    <p>Место: ${order.getPlace()}</p>
    <p class="text-primary">Начислено бонусов: ${addBonuses}</p>
    </div>
</div>
<#include "includes/footer.inc.ftl">