<!-- MODAL -->
<div class="modal fade" id="modal-equipment-add" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/EquipmentServlet" method="POST" id="form" >


                <div class="modal-header">
                    <h5 class="modal-title" id="modal-title">Add Equipment</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label" >Name</label>
                        <input type="text" class="form-control" id="equipmentName" name="equipmentName">
                    </div>
                    <div class="mb-3">
                        <label class="form-label" >Type</label>
                        <input type="text" class="form-control" id="equipmentType" name="equipmentType">
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