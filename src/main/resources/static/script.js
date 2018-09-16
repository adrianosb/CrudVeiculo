var bxCor = $('#cor');
$.getJSON("/cores", function (data) {
    bxCor.append($('<option disabled selected value></option>'));
    $.each(data, function (key, entry) {
        bxCor.append($('<option></option>').attr('value', entry.idCor).text(entry.descricao));
    });
    $('.selectpicker').selectpicker('refresh');
});

$(document).ready(function () {
    $('#placa').mask('SSS0000');
    $('#anoFabricacao').mask('0000');
    $('#anoModelo').mask('0000');

    $("form").on('submit', function (e) {
        return false;
    });

    $('#limpar').click(function () {
        limpar();
        $('.alert').alert('close');
    });
    $('#grava').click(function () {
        gravar();
    });
    $('#remover').click(function () {
        remover();
    });
});

function gravar() {
    var item = {
        "placa": $('#placa').val().toUpperCase(),
        "anoModelo": $('#anoModelo').val(),
        "anoFabricacao": $('#anoFabricacao').val(),
        "ativo": true,
        "cor": {
            "idCor": $('#cor').val()
        }
    };
    $.ajax({
        url: '/veiculos',
        type: $("#placa").prop("disabled") ? 'PUT' : 'POST',
        contentType: 'application/json',
        data: JSON.stringify(item),
        dataType: "json",
        success: function (e) {
            limpar();
            console.log('Sucesso!');
            showAlertSuccess('Sucesso!');
            $("form")[0].reset();
            $('.selectpicker').selectpicker('refresh');
            atualizarTabela();
        },
        error: function (e) {
            console.log('Error: ' + e.responseJSON.message);
            showAlertError("Falha: " + e.responseJSON.message);
            atualizarTabela();
        }
    });
}

function remover() {
    $.ajax({
        url: '/veiculos/'+$('#placa').val().toUpperCase(),
        type: 'DELETE',
        success: function (e) {
            limpar();
            console.log('Sucesso!');
            showAlertSuccess('Sucesso!');
            $("form")[0].reset();
            $('.selectpicker').selectpicker('refresh');
            atualizarTabela();
        },
        error: function (e) {
            console.log('Error: ' + e.responseJSON.message);
            showAlertError("Falha: " + e.responseJSON.message);
            atualizarTabela();
        }
    });
}

function editar(placa) {
    $.getJSON("/veiculos/" + placa, function (data) {

        $('#placa').val(data.placa);
        $("#placa").prop("disabled", true);

        $('#anoModelo').val(data.anoModelo);
        $('#anoFabricacao').val(data.anoFabricacao);
        $('.selectpicker').selectpicker('val', data.cor.idCor);
        $("#remover").prop("disabled", false);
    });
}

function limpar() {
    $("#placa").prop("disabled", false);
    $('.selectpicker').selectpicker('val', "");
    $("#remover").prop("disabled", true);
}

function atualizarTabela() {
    $('#veiculos > tr').remove();

    var bxCor = $('#cor');
    $.getJSON("/veiculos", function (data) {

        $.each(data, function (key, entry) {
            $("#veiculos").prepend(
                    "<tr><td>" + entry.placa +
                    "</td><td>" + entry.cor.descricao +
                    "</td><td>" + entry.anoModelo +
                    "</td><td>" + entry.anoFabricacao +
                    "</td><td><button type='button' class='btn btn-outline-secondary' onclick='editar(\"" + entry.placa + "\")'>Editar</button></td></tr>"
                    );
        });
    });
}

function showAlertSuccess(message) {
    $('#alerta').html('<div class="alert alert-success alert-dismissible fade show" role="alert">' +
            message +
            '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>');
    $('.alert').show();
}

function showAlertError(message) {
    $('#alerta').html('<div class="alert alert-warning alert-dismissible fade show" role="alert">' +
            message +
            '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>');
    $('.alert').show();
}

atualizarTabela();


