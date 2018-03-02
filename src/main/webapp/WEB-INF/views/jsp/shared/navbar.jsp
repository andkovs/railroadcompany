<header class="bs-docs-nav navbar navbar-static-top" id="top">
    <div class="container">
        <div class="navbar-header">
            <a href="../" class="navbar-brand">FranceRR</a>
        </div>
        <nav class="collapse navbar-collapse" id="bs-navbar">
            <ul class="nav navbar-nav">
                <li><a href="/station">Stations</a></li>
                <li><a href="/train">Trains</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:url value="/j_spring_security_logout" var="logoutUrl"/>
                <form action="${logoutUrl}" method="post" id="logoutForm">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                </form>
                <script>
                    function formSubmit() {
                        document.getElementById("logoutForm").submit();
                    }
                </script>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li>Welcome : ${pageContext.request.userPrincipal.name} |</li>
                    <li><p><a href="javascript:formSubmit()">Logout</a></p>

                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</header>