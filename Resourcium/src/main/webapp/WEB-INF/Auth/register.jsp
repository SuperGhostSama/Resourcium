<!DOCTYPE html>
<html>
<%@ include file="../Component/head.jsp" %>
<%@ include file="../Component/navbar.jsp" %>
<body>
<section id="signupSection">
    <div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
        <div class="col-sm-12 col-md-9 col-lg-5 text-white border border-4 border-secondary rounded p-5 ">
            <h3 class="text-center mb-4 fw-bold">Sign Up</h3>
            <form action="" method="POST" >

                <div class="mb-3">
                    <label for="name" class="form-label ">Full Name</label>
                    <input name="name" type="text" class="form-control opacity-50" id="name" aria-describedby="fullName" placeholder="Full Name" required >
                    <span id="validateName"></span>

                </div>
                <div class="mb-3">
                    <label for="email_address" class="form-label ">Email address</label>
                    <input name="email" type="email" class="form-control opacity-50" id="email_address" aria-describedby="emailHelp" placeholder="email@mail.com" required >
                    <span id="validateEmail"></span>

                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input name="password" type="password" class="form-control opacity-50" id="password" >
                    <span id="validatePassword"></span>

                </div>


                <button type="submit" class="btn-prim col-10 col-sm-2 text-white w-100 p-1 mb-5">Sign Up</button>
            </form>

        </div>
    </div>
</section>
</body>
</html>
