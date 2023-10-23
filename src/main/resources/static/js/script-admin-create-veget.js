$(function() {

    $('#addModalBtnSave').click(function(e) {
        e.preventDefault();
        $("#addModalBtnClear").prop("disabled", true);
        $("#addModalBtnSave").prop("disabled", true);
        $('#addModalSpinner').show();

        let selectedFile = $('input[id=formFileAdd]')[0].files[0];
        if (selectedFile) {
            let formData = new FormData();
            formData.append('vegetName', $('input[id=nameInputAdd]').val());
            formData.append('vegetArt', $('input[id=artInputAdd]').val());
            formData.append('vegetDescr', $('textarea[id=descrTextareaAdd]').val());
            formData.append('vegetPrice', $('input[id=priceInputAdd]').val());
            formData.append('vegetFile', selectedFile);

            $.ajax({
                type: 'POST',
                enctype: 'multipart/form-data',
                dataType: 'json',
                contentType: false,
                processData: false,
                url: '/admin/create-veget',
                data: formData
            })
            .done(function(response) {
                if (response.success == false) {
                    output = "<span style='color: #f02d1f; font-size: 16px;'>" + response.message + "</span>";
                } else {
                    output = "<span style='color: #22a131; font-size: 16px;'>" + response.message + "</span>";
                }
                $('#addModalSpinner').hide();
                $('#addModalResponse').html(output);
                $("#addModalBtnClear").prop("disabled", false);
                $("#addModalBtnSave").prop("disabled", false);

                $('#addModal').modal('hide');
                location.reload();

            })
            .fail(function(e) {
                $('#addModalSpinner').hide();
                $("#addModalResponse").html(e.responseText);
                $("#addModalBtnClear").prop("disabled", false);
                $("#addModalBtnSave").prop("disabled", false);
            });
        } else {
            alert("Please select a file");
            $("#addModalBtnClear").prop("disabled", false);
            $("#addModalBtnSave").prop("disabled", false);
            $('#addModalSpinner').hide();
        }
    });

    $('#addModalBtnClear').click(function(e) {
        $('#modalFormCreateFruit')[0].reset();
        $('#addModalResponse').html('');
        location.reload();
    });
});
