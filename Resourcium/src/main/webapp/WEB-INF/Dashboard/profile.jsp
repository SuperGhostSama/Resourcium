<html>
<%@ include file="../Component/dashboard-head.jsp" %>


<body>

<%@ include file="../Component/dashboard-sidenav.jsp" %>

<div class="container rounded bg-white mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">${sessionScope.fullName}</span><span class="text-black-50">Not Showing Role Yet</span><span> </span></div>
        </div>
        <div class="col-md-5 border-right">

            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Profile Settings</h4>
                </div>
                <form id="updateForm" action="" method="POST" data-parsley-validate>

                    <div class="row mt-2">
                        <div class="col-md-12"><label class="labels">Name</label>
                            <input form="updateForm" type="text" name="name" class="form-control" placeholder="first name" data-parsley-pattern="/^[a-zA-Z ]*$/" required value="${sessionScope.fullName}">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-12"><label class="labels">Email</label>
                            <input form="updateForm" type="email" name="email" class="form-control" placeholder="enter email" data-parsley-pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" required value="${sessionScope.email}">
                        </div>
                        <div class="col-md-12"><label class="labels">Password</label>
                            <input form="updateForm" type="password" name="password" class="form-control" placeholder="" value="********">
                        </div>
                    </div>

                    <div class="mt-5 text-center">
                        <button form="updateForm" class="btn btn-primary profile-button" type="submit">Save Profile</button>
                        <button class="btn btn-danger profile-button" type="submit" form="deleteForm">Delete Profile</button>
                    </div>
                </form>
                <form id="deleteForm" class="d-inline" method="POST" action="">

                    <input type="hidden" name="_method" value="DELETE" form="deleteForm">
                </form>
            </div>
        </div>
    </div>


</body>

<%@ include file="../Component/dashboard-script.jsp" %>
</html>
