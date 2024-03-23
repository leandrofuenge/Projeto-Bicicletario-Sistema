$(document).ready(function () {
    // Criar um novo usuário
    $("#criarUsuario").submit(function (event) {
        event.preventDefault();
        const formData = $(this).serialize();
        $.post("/usuarios/criar", formData, function (response) {
            alert("Novo usuário criado com sucesso!");
            console.log(response);
        }).fail(function (error) {
            alert("Erro ao criar novo usuário!");
            console.log(error);
        });
    });

    // Obter todos os usuários
    $("#obterTodosUsuarios").click(function () {
        $.get("/usuarios/todos", function (usuarios) {
            console.log(usuarios);
            // Manipular os usuários recebidos conforme necessário
        }).fail(function (error) {
            console.log(error);
            alert("Erro ao obter todos os usuários!");
        });
    });

    // Obter um usuário pelo ID
    $("#obterUsuarioPorId").click(function () {
        const userId = $("#userId").val();
        $.get("/usuarios/" + userId, function (usuario) {
            console.log(usuario);
            // Manipular o usuário recebido conforme necessário
        }).fail(function (error) {
            console.log(error);
            alert("Erro ao obter usuário!");
        });
    });

    // Atualizar um usuário existente
    $("#atualizarUsuario").submit(function (event) {
        event.preventDefault();
        const formData = $(this).serialize();
        const userId = $("#userId").val();
        $.ajax({
            url: "/usuarios/" + userId,
            type: 'PUT',
            data: formData,
            success: function (response) {
                alert("Usuário atualizado com sucesso!");
                console.log(response);
            },
            error: function (error) {
                alert("Erro ao atualizar usuário!");
                console.log(error);
            }
        });
    });

    // Excluir um usuário pelo ID
    $("#excluirUsuario").click(function () {
        const userId = $("#userId").val();
        $.ajax({
            url: "/usuarios/" + userId,
            type: 'DELETE',
            success: function () {
                alert("Usuário excluído com sucesso!");
            },
            error: function (error) {
                alert("Erro ao excluir usuário!");
                console.log(error);
            }
        });
    });

    // Consumir crédito
    $("#consumirCredito").click(function () {
        const userId = $("#userId").val();
        $.post("/usuarios/post?userId=" + userId, function () {
            alert("Crédito consumido com sucesso!");
        }).fail(function (error) {
            alert("Erro ao consumir crédito!");
            console.log(error);
        });
    });
});
