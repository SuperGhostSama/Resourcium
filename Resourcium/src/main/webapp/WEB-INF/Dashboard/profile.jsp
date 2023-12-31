<html>
<%@ include file="../Component/dashboard-head.jsp" %>


<body>

<%@ include file="../Component/dashboard-sidenav.jsp" %>

<div class="container rounded bg-white mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">${sessionScope.fullName}</span><span class="text-black-50">${sessionScope.role}</span><span> </span></div>
        </div>
        <div class="col-md-5 border-right">

            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Profile Settings</h4>
                </div>
                <form id="updateForm" action="${pageContext.request.contextPath}/profile" method="POST" >

                    <div class="row mt-2">
                        <div class="col-md-12"><label class="labels">Name</label>
                            <input form="updateForm" type="text" name="fullName" class="form-control" placeholder="Full Name" required value="${sessionScope.fullName}">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-12"><label class="labels">Email</label>
                            <input form="updateForm" type="email" name="email" class="form-control" placeholder="enter email" required value="${sessionScope.email}">
                        </div>
                    </div>

                    <div class="mt-5 text-center">
                        <button form="updateForm" class="btn btn-primary profile-button" type="submit">Save Profile</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


</body>

<%@ include file="../Component/dashboard-script.jsp" %>
</html>
