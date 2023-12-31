<!DOCTYPE html>
<html>
<%@ include file="../Component/head.jsp" %>
<%@ include file="../Component/navbar.jsp" %>
<body>

<% String loginFailed = (String) session.getAttribute("loginFailed"); %>
<% if (loginFailed != null && !loginFailed.isEmpty()) { %>
<div class="alert alert-danger alert-dismissible fade show" role="alert">
    <strong><%= loginFailed %></strong>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<% } %>

<% String registrationSuccess = (String) session.getAttribute("registrationSuccess"); %>
<% if (registrationSuccess != null && !registrationSuccess.isEmpty()) { %>
<div class="alert alert-success alert-dismissible fade show" role="alert">
    <strong><%= registrationSuccess %></strong>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<% } %>

<section id="loginSection">
    <div class="d-flex justify-content-center align-items-center " style="height: 100vh;">
        <div class="col-sm-12 col-md-9 col-lg-5 text-white border border-4 border-secondary rounded p-5 ">
            <h3 class="text-center mb-4 fw-bold">Log In</h3>
            <form action="${pageContext.request.contextPath}/login" method="POST">
                <div class="mb-3">
                    <label for="email_address" class="form-label ">Email address</label>
                    <input name="email" type="text" class="form-control opacity-50" id="email_address" aria-describedby="emailHelp" placeholder="email@mail.com" required >

                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input name="password" type="password" class="form-control opacity-50" id="password">

                </div>

                <a href="" class="mb-3">Forgot Password?</a>
                <button type="submit"class="btn-prim col-10 col-sm-2 text-white w-100 p-1 mb-5">Login</button>
            </form>

        </div>
    </div>
</section>

<%@ include file="../Component/script.jsp" %>

</body>
</html>
