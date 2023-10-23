<!-- MODAL -->
<div class="modal fade" id="modal-tasks-view" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="" method="POST" id="form" enctype="multipart/form-data" data-parsley-validate>


                <div class="modal-header">
                    <h5 class="modal-title" id="modal-title">View Task</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label" >Assigned To</label>
                        <input disabled type="text" class="form-control" id="assignedToView" name="startDate">

                    </div>
                    <div class="form-group mb-3">
                        <label for="descriptionView" class="col-form-label">Description</label>
                        <textarea disabled class="form-control" id="descriptionView" name="description"></textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Start Date</label>
                        <input disabled type="text" class="form-control" id="startDateView" name="startDate">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">End Date</label>
                        <input disabled type="text" class="form-control" id="endDateView" name="endDate">
                    </div>


                </div>

                <div class="modal-footer">
                    <button type="button" data-bs-dismiss="modal" class="btn btn-secondary" >Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>