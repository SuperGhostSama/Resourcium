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

        <%
            String userRole = (String) session.getAttribute("role");

            if ("Administrator".equals(userRole)) {
        %>
        <div class="cardHeader">
            <h2>Tasks List</h2>
            <a href="#modal-tasks-add" data-bs-toggle="modal" class="btns">Add Task</a>
        </div>
        <%
        } else {
        %>

        <div class="cardHeader">
            <h2>Tasks List</h2>
            <a disabled href="" data-bs-toggle="modal" class="btn btn-secondary">Add Task</a>
        </div>
        <%
            }
        %>


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
                    <a id="view-button" onclick="showTask(this)" href="#modal-tasks-view" data-bs-toggle="modal"><ion-icon name="eye-outline"></ion-icon></a>
                    <%
                        if ("Administrator".equals(userRole)) {
                    %>
                    <a id="edit-button" onclick="fillUpdateModal(this)" href="#modal-tasks-edit" data-bs-toggle="modal"><ion-icon name="create-outline"></ion-icon></a>

                    <form class="d-inline" method="POST" action="${pageContext.request.contextPath}/TasksDeleteServlet">
                        <input type="hidden" name="taskId" value="<%= task.getId() %>">
                        <button class="unstyled" type="submit"><ion-icon name="trash-outline"></ion-icon></button>
                    </form>
                    <%
                        }
                    %>

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
<script>
    function showTask(element){
        let myData=element.parentElement.parentElement.getElementsByTagName("td");
        document.getElementById("assignedToView").value=myData[0].innerText;
        document.getElementById("descriptionView").value=myData[1].innerText;
        document.getElementById("startDateView").value=myData[2].innerText;
        document.getElementById("endDateView").value=myData[3].innerText;
    }
    function fillUpdateModal(element) {
        let myData = element.parentElement.parentElement.getElementsByTagName("td");
        console.log(myData[0].innerText); // Log the assignedTo value
        console.log(myData[1].innerText); // Log the description value
        console.log(myData[2].innerText); // Log the startDate value
        console.log(myData[3].innerText); // Log the endDate value
;
        // document.querySelector("[name='assignedToUpdate']").value = myData[0].innerText;
        document.querySelector("[name='descriptionUpdate']").value = myData[1].innerText;
        // document.querySelector("[name='startDateUpdate']").value = myData[2].innerText;
        // document.querySelector("[name='endDateUpdate']").value = myData[3].innerText;
    }


</script>


<%@ include file="../Component/Modals/TaskModal/add-task.jsp" %>
<%@ include file="../Component/Modals/TaskModal/update-task.jsp" %>
<%@ include file="../Component/Modals/TaskModal/view-task.jsp" %>

</body>

<%@ include file="../Component/dashboard-script.jsp" %>
</html>