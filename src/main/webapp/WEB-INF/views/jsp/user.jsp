<main style="padding: 50px 0;">
    <div class="container">
        <div class="row">
            <c:if test="${msg!=null}">
                <div class="alert alert-success col-xs-3 form-group" role="alert">
                    <p>${msg}</p>
                </div>
            </c:if>
            <c:if test="${error!=null}">
                <div class="alert alert-danger col-xs-3 form-group" role="alert">
                    <p>${error}</p>
                </div>
            </c:if>
        </div>
        <div class="row">
            <div class="col-xs-3 form-group">
                <h2>${user.login}</h2>
                <hr/>
            </div>
        </div>
        <form:form method="POST" action="/user/add" modelAttribute="user">
            <form:hidden path="userId"/>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <div class="row">
                    <div class="form-group col-xs-3">
                        <label>Login</label>
                        <form:input path="login" class="form-control" required="true" maxlength="44"/>
                    </div>
                    <div class="form-group col-xs-3">
                        <label>Password</label>
                        <form:input path="password" type="password" class="form-control" required="true"
                                    maxlength="44"/>
                    </div>

                </div>
                <hr/>

            </c:if>
            <div class="row">
                <div class="form-group col-xs-3">
                    <label>Last name</label>
                    <form:input path="lastName" class="form-control" required="true" maxlength="44"/>
                </div>
                <div class="form-group col-xs-3">
                    <label>First name</label>
                    <form:input path="firstName" class="form-control" required="true" maxlength="44"/>
                </div>
                <div class="form-group col-xs-3">
                    <label>Middle name</label>
                    <form:input path="middleName" class="form-control"/>
                </div>

            </div>
            <hr/>

            <div class="row">
                <div class="col-md-3">
                    <div class="form-group">
                        <label>Birth date</label>
                        <div id="datetimepicker1" class="input-group">
                            <form:input path="birthDate" class="form-control" required="true"/>
                            <span class="input-group-addon">
          <span class="glyphicon glyphicon-calendar" href=""></span>
        </span>
                        </div>
                    </div>
                </div>
                <div class="form-group col-xs-3">
                    <label>Phone</label>
                    <form:input path="phone" class="form-control" required="true" maxlength="44"/>
                </div>
                <div class="form-group col-xs-3">
                    <label>E-mail</label>
                    <form:input path="email" type="email" class="form-control" required="true" maxlength="44"/>
                </div>
            </div>
            <hr/>
            <button type="submit" class="btn btn-primary">Save</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form:form>
    </div>
    </div>
    </div>
</main>

<script type="text/javascript">
    var dtf = 'DD-MM-YYYY';

    var birthDate = moment('${user.birthDate}', dtf);
    $('#datetimepicker1').datetimepicker({format: dtf, defaultDate: birthDate});
</script>
