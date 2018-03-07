<main style="padding: 100px 0;">
    <div class="container">
        <form name='loginForm'
              action="<c:url value='/j_spring_security_check' />" method='POST'>
            <div class="container">
                <div class="row">
                    <div class="col-xs-6 col-xs-offset-4">
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger col-xs-8 form-group" role="alert">${error}</div>
                        </c:if>
                        <c:if test="${not empty msg}">
                            <div class="alert alert-success col-xs-8 form-group" role="alert">${msg}</div>
                        </c:if>
                    </div>
                    <div class="row">
                        <div class="col-xs-4 col-xs-offset-4">
                            <div class="form-group">
                                <label>Login</label>
                                <input class="form-control" type="text" name="username" required="true"/>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input class="form-control" type="password" name="password" required="true"/>
                            </div>
                            <div class="form-group">
                                <button name="submit" type="submit" class="btn btn-primary">Enter</button>
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </form>
    </div>
</main>
