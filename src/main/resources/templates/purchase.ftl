<#assign title="Купить билет">
<#include "includes/head.inc.ftl" >
<#include "includes/navhead.inc.js.ftl" >
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
<div class="text-center">
    <h1>${seance.getFilm().getTitle()}</h1>
    <h2>${formatter.format(seance.getStartTime())}
        -${formatter.format(seance.getEndTime())}</h2>
</div>
<form id="places" method="POST">
    <input type="hidden" name="seanceId" value="${seance.getId()}">
    <div class="d-flex justify-content-center">
        <div class="col-6">
            <div class="card m-5 p-3 d-flex">
                <#list displayPlaces as row>
                    <div class="d-flex col-12">
                        <#list row as place>

                            <#if place>
                                <label class="d-flex flex-column align-content-center bg-danger border border-primary rounded p-2 m-auto"
                                       for="place${row?index*10 + place?index}">
                                    <input type="radio" class="mx-auto"
                                           id="place${row?index*10 + place?index}"
                                           name="place" disabled ${row?index*10 + place?index}>
                                    <label for="place${row?index*10 + place?index}"
                                           class="form-check-label rounded">${row?index*10 + place?index}</label>
                                </label>
                            <#else >
                                <label for="place${row?index*10 + place?index}"
                                       class="d-flex flex-column align-content-center bg-info border border-primary rounded p-2 m-auto">
                                    <input type="radio" class="mx-auto" name="place"
                                           id="place${row?index*10 + place?index}"
                                           value="${row?index*10 + place?index}">
                                    <label for="place${row?index*10 + place?index}"
                                           class="form-check-label rounded">${row?index*10 + place?index}</label>
                                </label>
                            </#if>
                        </#list>
                    </div>
                </#list>
                <div class="col-12 border border-primary bg-secondary text-center mt-4">Экран</div>
            </div>
        </div>
        <div class="col-4">
            <div class="my-5">
                <p class="text-danger">${message!}</p>
                <div class="form-group">
                    <div class="input-group">
                        <input type="text" class="form-control" name="payData" placeholder="Данные для оплаты">
                    </div>
                </div>
                <@security.authorize access="isAuthenticated()">
                    <p>Электронный билет будет выслан вам на email</p>
                </@security.authorize>
                <@security.authorize access="! isAuthenticated()">
                    <p class="text-info">Вы не авторизованы и вам не будут начилсены бонусы за покупку</p>
                    <p>Укажите email чтобы мы могли выслать вам ваш билет</p>
                    <div class="form-group">
                        <div class="input-group">
                            <input type="text" class="form-control" name="anonEmail" placeholder="email">
                        </div>
                    </div>
                </@security.authorize>
                <h5>Итого:</h5>
                <p>240р</p>
                <button type="submit" form="places" class="btn btn-primary my-auto w-75">Купить</button>
            </div>
        </div>
    </div>
</form>
</body>
</html>
