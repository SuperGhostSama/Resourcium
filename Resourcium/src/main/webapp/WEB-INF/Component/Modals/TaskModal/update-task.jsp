<!-- MODAL -->
<div class="modal fade" id="modal-tasks-edit" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/TasksUpdateServlet" method="POST" id="form" >

                <input type="hidden" name="taskIdUpdate" id="taskIdUpdate" value="">

                <div class="modal-header">
                    <h5 class="modal-title" id="modal-title">Update Task</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label" >Assigned To</label>
                        <select name="assignedToUpdate"  class="form-select" aria-label="Default select example">

                            <%
                                // Iterate through users
                                List<User> usersUpdate = (List<User>) request.getAttribute("users");

                                for (User user : usersUpdate) {
                            %>
                            <option value="<%= user.getId() %>"><%= user.getFullName() %></option>
                            <%
                                }
                            %>

                        </select>
                    </div>
                    <div class="form-group mb-3">
                        <label for="message-text" class="col-form-label">Description</label>
                        <textarea class="form-control" id="message-text" name="descriptionUpdate"></textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Start Date</label>
                        <input type="date" class="form-control" name="startDateUpdate">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">End Date</label>
                        <input type="date" class="form-control" name="endDateUpdate">
                    </div>


                </div>

                <div class="modal-footer">
                    <button type="button" data-bs-dismiss="modal" class="btn btn-secondary" >Cancel</button>
                    <button type="submit" name="save" class="btn btn-warning task-action-btn" id="save">Update</button>
                </div>
            </form>
        </div>
    </div>
</div>