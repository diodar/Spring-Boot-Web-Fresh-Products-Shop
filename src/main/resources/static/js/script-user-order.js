$(function() {

    $('#orderModalBtnSend').click(function(e) {
		e.preventDefault();
		$("#orderModalBtnClear").prop("disabled", true);
		$("#orderModalBtnSend").prop("disabled", true);
		$('#orderModalSpinner').show();

        let formData = new FormData();
		formData.append('userName', $('input[id=nameInputOrder]').val());
		formData.append('userPhone', $('input[id=phoneInputOrder]').val());
		formData.append('userEmail', $('input[id=emailInputOrder]').val());
		formData.append('userOrderMsg', $('textarea[id=orderMsgTextareaOrder]').val());

        $.ajax({
            type: 'POST',
			dataType: 'json',
			contentType: false,
			processData: false,
			url: './order',
			data: formData
        })
        .done(function(response) {
            if(response.success == false) {
                $('#orderModalResponse').css('background-color', '#ffe1e6');
                output = "<span style='font-size: 14px;'>" + response.message + "</span>";
            }else {
                $('#orderModalResponse').css('background-color', '#baffc9');
                output = "<span style='font-size: 14px;'>" + response.message + "</span>";
            }
            $('#orderModalSpinner').hide();
            $('#orderModalResponse').html(output);
            $("#orderModalBtnClear").prop("disabled", false);
            $("#orderModalBtnSend").prop("disabled", false);

        })
        .fail (function(e) {
            $('#orderModalSpinner').hide();
            $("#orderModalResponse").html(e.responseText);
            $("#orderModalBtnClear").prop("disabled", false);
            $("#orderModalBtnSend").prop("disabled", false);
        });
    });

    $('#orderModalBtnClear').click(function(e) {
        $('#orderModalForm')[0].reset();
        $('#orderModalResponse').html('');
        location.reload();
    });

});
