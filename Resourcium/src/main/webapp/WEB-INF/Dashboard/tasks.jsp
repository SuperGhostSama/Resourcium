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
            <a href="#modal-brands" data-bs-toggle="modal" class="btns">Add Task</a>
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

            <tr>
                <td>Hamid</td>
                <td
                    style="max-width: 200px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                    This task is about to start bla bla blaHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
                </td>
                <td>21-10-2023</td>
                <td>30-10-23</td>
                <td>
                    <a id="view-button" onclick="" href="#modal-tasks-view" data-bs-toggle="modal"><ion-icon name="eye-outline"></ion-icon></a>
                    <a id="edit-button" onclick="" href="#modal-tasks-edit" data-bs-toggle="modal"><ion-icon name="create-outline"></ion-icon></a>
                    <form class="d-inline" method="POST" action="">

                        <button class="unstyled" type="submit"><ion-icon name="trash-outline"></ion-icon></button>
                    </form>
                </td>
            </tr>

            </tbody>
        </table>
    </div>

</div>


</body>

<%@ include file="../Component/dashboard-script.jsp" %>
</html>