function initializeEditableFields() {
    $('.editable').each(function () {
        $(this).editable({
            success: function (response) {
                if (response.status === 'error') {
                    return response.message;
                }
            },
            error: function (response, newValue) {
                return "Error: " + response.status;
            }
        });
    });

    $(".editable").on('shown.bs.popover', function () {
        window.dispatchEvent(new Event('resize'));
    });
}

function sortTables() {
    $.bootstrapSortable(true);
}

function dangerConfirm(message, callback, checkEnvironment = false) {
    dangerConfirmWithTitle("Attention!", message, callback, checkEnvironment);
}

function dangerConfirmWithTitle(title, body, callback, checkEnvironment = false) {
    customConfirm(title, body, "danger", callback, checkEnvironment)
}

function defaultConfirm(message, callback) {
    defaultConfirmWithTitle("Attention!", message, callback);
}

function defaultConfirmWithTitle(title, body, callback) {
    customConfirm(title, body, null, callback, false)
}

function customConfirm(title, body, style, callback, promptProductionField) {
    const headerClass = style == "danger" ? "bg-danger" : "bg-primary";
    const buttonClass = style == "danger" ? "btn-danger" : "btn-primary";

    const prodSecurity = '<div class="modal-security">' +
        '<div class="form-group mb-0">' +
        '<label for="prod-prompt">To confirm, type <em>production</em> in the text input field.</label>' +
        '<input type="text" class="form-control" id="prod-prompt" placeholder="production"/>' +
        '</div>' +
        '</div>';

    let modalContent = '<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header modal-colored-header ' + headerClass + '">' +
        '<h4 class="modal-title">' + title + '</h4>' +
        '<button type="button" class="btn-close btn-close-white" data-bs-dismiss="alert" id="buttonClose" aria-label="Close"></button>' +
        '</div>' +
        '<div class="modal-body">' + body + '</div>';

    let actionAttribute = "";

    modalContent += '<div class="modal-footer">' +
        '<button type="button" class="btn btn-light" id="closeButton">Cancel</button>' +
        '<button type="button" class="btn ' + buttonClass + '" id="customAlertModalAction"' + actionAttribute + '>Continue</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';

    let confirmModal = $(modalContent);

    confirmModal.find('#customAlertModalAction').click(function () {
        callback();
        confirmModal.modal('hide');
    });

    confirmModal.modal('show');

    confirmModal.find('#closeButton').click(function () {
        console.log("Close button clicked");
        confirmModal.modal('hide');
    });

    confirmModal.find('#buttonClose').click(function () {
        console.log("Close button clicked");
        confirmModal.modal('hide');
    });
}
