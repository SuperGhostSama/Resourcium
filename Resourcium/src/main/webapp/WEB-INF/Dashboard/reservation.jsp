<%@ page import="com.example.resourcium.model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.resourcium.model.Reservation" %>
<%@ page import="com.example.resourcium.model.Equipement" %>
<html>
<%@ include file="../Component/dashboard-head.jsp" %>


<body>

<%@ include file="../Component/dashboard-sidenav.jsp" %>
<style>
    .unstyled{
        border: none;
        margin: 0;
        padding: 0;
        width: auto;
        overflow: visible;
        color: rgba(var(--bs-link-color-rgb),var(--bs-link-opacity,1));
        background: transparent;
    }
</style>
<% if (session.getAttribute("reservationSuccess") != null) { %>
<!-- Display reservationSuccess message in a green alert -->
<div class="alert alert-success" role="alert">
    <%= session.getAttribute("reservationSuccess") %>
</div>
<% } %>

<% if (session.getAttribute("reservationError") != null) { %>
<!-- Display reservationError message in a red alert -->
<div class="alert alert-danger" role="alert">
    <%= session.getAttribute("reservationError") %>
</div>
<% } %>
<div class="details">
    <div class="recentOrders">
        <div class="cardHeader">
            <h2>Reservations List</h2>
            <a href="#modal-reservation-add" data-bs-toggle="modal" class="btns">Add Reservation</a>
        </div>

        <table>
            <thead>
            <tr>
                <td>User</td>
                <td>Equipment</td>
                <td>reservationDate</td>
                <td>returnDate</td>
                <td>Operations</td>
            </tr>
            </thead>
            <tbody>
            <%
                List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
                if (reservations != null) {
                    for (Reservation reservation : reservations) {
            %>
            <tr>
                <td><%= reservation.getUser().getFullName() %></td>
                <td><%= reservation.getEquipement().getName() %></td>
                <td><%= reservation.getReservationDate() %></td>
                <td><%= reservation.getReturnDate() %></td>
                <td>
                    <a id="edit-button"  href="#modal-reservation-edit" data-bs-toggle="modal"><ion-icon name="create-outline"></ion-icon></a>


                    <form class="d-inline" method="POST" action="${pageContext.request.contextPath}/ReservationDeleteServlet">
                        <input type="hidden" name="reservationId" value="<%= reservation.getId() %>">
                        <button class="unstyled" type="submit"><ion-icon name="trash-outline"></ion-icon></button>
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>

    </div>

</div>

<div class="details">
    <div class="recentOrders">
        <div class="cardHeader">
            <h2>Equipement List</h2>
            <a href="" data-bs-toggle="modal" class="btns">Add Equipement</a>
        </div>

        <table>
            <thead>
            <tr>
                <td>Equipment</td>
                <td>Type</td>
                <td>Availability</td>
                <td>Operations</td>
            </tr>
            </thead>
            <tbody>
            <%
                List<Equipement> equipments = (List<Equipement>) request.getAttribute("equipment");
                if (equipments != null) {
                    for (Equipement equipment : equipments) {
            %>
            <tr>
                <td><%= equipment.getName() %></td>
                <td><%= equipment.getType() %></td>
                <td><%= equipment.isAvailability() %></td>
                <td>
                    <a id=""  href="" data-bs-toggle="modal"><ion-icon name="create-outline"></ion-icon></a>


                    <form class="d-inline" method="POST" action="">
                        <input type="hidden" name="equipmentId" value="<%= equipment.getId() %>">
                        <button class="unstyled" type="submit"><ion-icon name="trash-outline"></ion-icon></button>
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>

    </div>

</div>



<%@ include file="../Component/Modals/ReservationModal/add-reservation.jsp" %>
<%@ include file="../Component/Modals/ReservationModal/update-reservation.jsp" %>

</body>

<%@ include file="../Component/dashboard-script.jsp" %>
</html>