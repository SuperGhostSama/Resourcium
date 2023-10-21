<!-- MODAL -->
<div class="modal fade" id="modal-tasks-add" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="" method="POST" id="form" enctype="multipart/form-data" data-parsley-validate>


                <div class="modal-header">
                    <h5 class="modal-title" id="modal-title">Add Task</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label" >Assigned To</label>
                        <select name="assignedTo"  class="form-select" aria-label="Default select example">
                            <option selected disabled>Open this select menu</option>

                            <option value="">Hamid</option>
                            <option value="">Hassan</option>

                        </select>
                    </div>
                    <div class="form-group mb-3">
                        <label for="message-text" class="col-form-label">Description</label>
                        <textarea class="form-control" id="message-text" name="description"></textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Start Date</label>
                        <input type="date" class="form-control" name="startDate">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">End Date</label>
                        <input type="date" class="form-control" name="endDate">
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