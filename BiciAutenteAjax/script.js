$(document).ready(function () {


// Função para realizar login
    function fazerLogin() {
        // Intercepta o envio do formulário
        $('#loginForm').submit(function(event) {
            event.preventDefault(); // Impede o envio padrão do formulário
            const formData = $(this).serialize(); // Serializa os dados do formulário
            // Envia a solicitação POST para o endpoint /login
            $.post("/login", formData)
                .done(function(response) {
                    // Se o login for bem-sucedido
                    alert("Login bem-sucedido!");
                    console.log(response);
                    // Redirecionar para outra página, se necessário
                    window.location.href = '/dashboard'; // Redireciona para a página de dashboard
                })
                .fail(function(xhr, status, error) {
                    // Se ocorrer um erro, exibe a mensagem de erro
                    alert("Erro ao fazer login!");
                    console.error('Erro de login:', error);
                });
        });
    }

// Chama a função fazerLogin quando o documento estiver pronto
    $(document).ready(function() {
        fazerLogin();
    });










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
    })




})


