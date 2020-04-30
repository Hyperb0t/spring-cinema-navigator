<#ftl encoding='UTF-8'>
<#assign title = "Регистрация">
<#include "includes/head.inc.ftl">
<#include "includes/navhead.inc.js.ftl">
<div class="container">
    <div class="row justify-content-center">
        <aside class="col-lg-4 col-md-5 col-7">
            <div class="card">
                <article class="card-body">
                    <h4 class="card-title text-center mb-4 mt-1">Регистрация</h4>
                    <hr>
                    <div class="text-danger text-center" id="errors"><#if message??>${message}<#else></#if></div>
                    <form method="POST">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                </div>
                                <input name="email" class="form-control" placeholder="Email" type="email"
                                       value="${emailValue!}" id="email">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                </div>
                                <input name="password" class="form-control" placeholder="Пароль" type="password"
                                       value="${passwordValue!}" id="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                </div>
                                <input name="repassword" class="form-control" placeholder="Повторите пароль"
                                       type="password" value="${repasswordValue!}" id="repassword">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-check justify-content-center">
                                <input name="accept" class="form-check-input" type="checkbox" id="accept">
                                <label class="form-check-label text-center"
                                       for="accept">Принимаю условия использования</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block" id="submitButton">
                                Зарегистрироваться
                            </button>
                        </div>
                    </form>
                    <div class="text-center">
                        <p class="my-0">Уже зарегестрированы?</p>
                        <a class="text-center" href="${springMacroRequestContext.contextPath}/login">Войти</a>
                    </div>
                </article>
            </div>
        </aside>
    </div>
</div>
<script type="application/javascript" src="${springMacroRequestContext.contextPath}/static/js/checkRegFrom.js"></script>
</body>
</html>
