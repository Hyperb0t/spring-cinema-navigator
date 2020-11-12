<#ftl encoding='UTF-8'>
<#assign title = "Вход">
<#include "includes/head.inc.ftl" />
<#include "includes/navhead.inc.js.ftl" />
<div class="container justify-content-center col-lg-3 col-md-5 col-7 my-4">
    <div class="card">
        <div class="card-body">
            <h4 class="text-center">Войти</h4>
            <hr>
            <p class="text-center text-danger"><#if message??>${message}<#else></#if></p>
            <form method="POST">
                <div class="form-group">
                    <div class="input-group ">
                        <input name="email" class="form-control" placeholder="Email" type="email"
                               value="${emailValue!}" id="email">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group ">
                        <input name="password" class="form-control" placeholder="Пароль" type="password"
                               value="${passwordValue!}" id="password">
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="form-group">
                    <div class="form-check justify-content-center">
                        <input name="remember" class="form-check-input" type="checkbox" id="remember">
                        <label class="form-check-label text-center" for="remember">
                            Запомнить меня
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Войти</button>
                </div>
            </form>
            <div class="text-center">
            <p class="my-0">Нет аккаунта?</p>
            <a class="text-center" href="${springMacroRequestContext.contextPath}/register">Зарегистроваться</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>