<%@ page import="com.example.resourcium.model.Task" %>
<%@ page import="java.util.List" %>
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

<div class="details">
    <div class="recentOrders">
        <div class="cardHeader">
            <h2>Tasks List</h2>
            <a href="#modal-tasks-add" data-bs-toggle="modal" class="btns">Add Task</a>
        </div>

        <table>
            <thead>
            <tr>
                <td>Assigned To</td>
                <td>Description</td>
                <td>Start Date</td>
                <td>End Date</td>
                <td>Operations</td>
            </tr>
            </thead>
            <tbody>
            <%
                List<Task> tasks = (List<Task>) request.getAttribute("tasks");
                if (tasks != null) {
                    for (Task task : tasks) {
            %>
            <tr>
                <td><%= task.getUser().getFullName() %></td>
                <td style="max-width: 200px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                    <%= task.getDescription() %>
                </td>
                <td><%= task.getStartDate() %></td>
                <td><%= task.getEndDate() %></td>
                <td>
                    <a id="view-button" href="#modal-tasks-view" data-bs-toggle="modal"><ion-icon name="eye-outline"></ion-icon></a>
                    <a id="edit-button" href="#modal-tasks-edit" data-bs-toggle="modal"><ion-icon name="create-outline"></ion-icon></a>
                    <form class="d-inline" method="POST" action="">
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

<%@ include file="../Component/Modals/TaskModal/add-task.jsp" %>
<%@ include file="../Component/Modals/TaskModal/update-task.jsp" %>
<%@ include file="../Component/Modals/TaskModal/view-task.jsp" %>

</body>

<%@ include file="../Component/dashboard-script.jsp" %>
</html>