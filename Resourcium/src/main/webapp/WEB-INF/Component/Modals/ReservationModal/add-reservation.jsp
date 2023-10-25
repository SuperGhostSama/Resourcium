<%@ page import="com.example.resourcium.model.User" %>
<!-- MODAL -->
<div class="modal fade" id="modal-reservation-add" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/reservations" method="POST" id="form" >


                <div class="modal-header">
                    <h5 class="modal-title" id="modal-title">Make Reservation</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">

                    <input type="hidden" name="user" value="<%= session.getAttribute("userId") %>">

                    <div class="mb-3">
                        <label class="form-label" >Equipement</label>
                        <select name="assignedTo"  class="form-select" aria-label="Default select example">
                            <option selected disabled>Open this select menu</option>

                            <%
                                for (Equipement equipment : equipments) {
                            %>
                            <option value="<%= equipment.getId() %>"><%= equipment.getName()  %></option>
                            <%
                                }
                            %>

                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Reservation Date</label>
                        <input type="date" class="form-control" name="reservationDate">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Return Date</label>
                        <input type="date" class="form-control" name="returnDate">
                    </div>


                </div>

                <div class="modal-footer">
                    <button type="button" data-bs-dismiss="modal" class="btn btn-secondary" >Cancel</button>
                    <button type="submit" name="save" class="btn btn-primary task-action-btn" id="save">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>