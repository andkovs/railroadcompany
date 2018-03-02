<main style="padding: 50px 0;">
    <div class="container">
        <div class="row">
            <h2>${user.login}</h2>
            <hr/>
            <form:form method="POST" action="/user/add" modelAttribute="user">
                <form:hidden path="userId"/>
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <div class="row">
                        <div class="form-group col-xs-3">
                            <label>Login</label>
                            <form:input path="login" class="form-control"/>
                        </div>
                        <div class="form-group col-xs-3">
                            <label>Password</label>
                            <form:input path="password" class="form-control"/>
                        </div>
                    </div>
                    <hr/>
                </c:if>
                <div class="row">
                    <div class="form-group col-xs-3">
                        <label>Last name</label>
                        <form:input path="lastName" class="form-control"/>
                    </div>
                    <div class="form-group col-xs-3">
                        <label>First name</label>
                        <form:input path="firstName" class="form-control"/>
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
                                <form:input path="birthDate" type="text" class="form-control"/>
                                <span class="input-group-addon">
          <span class="glyphicon glyphicon-calendar" href=""></span>
        </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-xs-3">
                        <label>Phone</label>
                        <form:input path="phone" class="form-control"/>
                    </div>
                    <div class="form-group col-xs-3">
                        <label>E-mail</label>
                        <form:input path="email" class="form-control"/>
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
