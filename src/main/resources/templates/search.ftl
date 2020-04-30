<#assign title = "Поиск">
<#include "includes/head.inc.ftl" >
<#include "includes/navhead.inc.js.ftl" >
<form>
    <div class="form-inline justify-content-center my-4">
        <div class="col-lg-5 col-md-8 col-sm-10 d-flex justify-content-around">
            <input name="filmTitle" type="text" class="form-control col-10" placeholder="Введите название фильма"
                   value="${searchFilmTitle!}">
            <input type="submit" class="btn btn-info mx-1" value="Найти"></input>
        </div>
    </div>


    <#--    <div class="search-button-group d-flex justify-content-center my-4">--%>-->
    <#--        <div class="col-lg-5 col-md-8 col-sm-10 d-flex justify-content-center btn-group">-->
    <#--            <label class="btn btn-secondary"><input type="checkbox">Сегодня вечером</label>-->
    <#--            <label class="btn btn-secondary d-flex flex-column">В эти выходные<input type="checkbox">-->
    <#--                                                                                     class="mt-2 mx-auto custom-checkbox"></label>-->
    <#--            <label class="btn btn-secondary"><input type="checkbox">Дешевле 150р</label>-->
    <#--            <label class="btn btn-secondary"><input type="checkbox">Только новые фильмы</label>-->
    <#--        </div>-->
    <#--    </div>-->
</form>
<div class="d-flex flex-row justify-content-between">
    <div class="col-2">
        <#if recommendations??>
            <#if recommendations?size != 0>
                <div class="card">
                    <article class="card-body">
                        <h5>Рекоммендации с <a href="http://www.omdbapi.com/">Open Movie Database</a> по вашему запросу
                        </h5>
                        <ol>
                            <#list recommendations! as rec>
                                <li> ${rec.Title!}</li>
                            </#list>
                        </ol>
                    </article>
                </div>
            </#if>
        </#if>
    </div>
    <div id="seancelist" class="col-8">
        <#list seances as seance>
            <div class="search-result my-4 col-lg-11 col-md-11 col-sm-11 mx-auto d-flex bg-light border flex-lg-row flex-column
                justify-content-center">
                <div class="img col-lg-3 col-md-10 col-sm-10 my-auto mx-auto d-flex justify-content-center">
                    <img src="${seance.getFilm().getImageLink()}" class="rounded my-2 w-75" alt="Picture">
                </div>
                <div class="text-column col-lg-3 col-md-10 col-sm-10 my-auto mx-auto">
                    <h5>
                        <a href="${springMacroRequestContext.contextPath}/film?id=${seance.getFilm().getId()}">${seance.getFilm().getTitle()}</a>
                    </h5>
                    <p>${formatter.format(seance.getStartTime())} - ${formatter.format(seance.getEndTime())}
                    </p>
                    <p>${seance.getPrice()}₽</p>
                </div>
                <div class="text-column col-lg-3 col-md-10 col-sm-10 my-auto mx-auto">
                    <p>${seance.getFilm().getDescription()}</p>
                </div>
                <div class="col-lg-3 col-md-10 col-sm-10 d-flex justify-content-center mx-auto">
                    <form class="col-11 px-0 my-auto" action="${springMacroRequestContext.contextPath}/purchase">
                        <input type="hidden" value="${seance.getId()}" name="seanceId">
                        <input type="submit" class="btn btn-primary col-12 my-auto" value="Купить билет">
                    </form>
                </div>
            </div>
        </#list>
    </div>
    <div class="col-2">

    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
<script src="${springMacroRequestContext.contextPath}/static/js/searchFilmAjaxLoader.js"></script>
</body>
</html>
