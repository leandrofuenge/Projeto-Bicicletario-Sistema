package com.example.bici.service;

import com.example.bici.entity.Usuario;
import com.example.bici.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaoService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CartaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Deduz um crédito do saldo do usuário correspondente ao número do cartão fornecido.
     *
     * @param numeroDoCartao número do cartão do usuário
     */
    public void deduzirCredito(String numeroDoCartao) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByNumeroDoCartao(numeroDoCartao);

        optionalUsuario.ifPresentOrElse(usuario -> {
            int creditos = usuario.getCreditos();
            if (creditos > 0) {
                usuario.setCreditos(creditos - 1);
                usuarioRepository.save(usuario);
                System.out.println("Um crédito deduzido para o usuário com número do cartão: " + numeroDoCartao);
            } else {
                System.out.println("O usuário com número do cartão " + numeroDoCartao + " não tem créditos disponíveis.");
            }
        }, () -> System.out.println("Usuário com número do cartão " + numeroDoCartao + " não encontrado."));
    }

    /**
     * Obtém o usuário correspondente ao número do cartão fornecido.
     *
     * @param numeroDoCartao número do cartão do usuário
     * @return usuário encontrado ou null se não encontrado
     */
    public Usuario obterUsuarioPorNumeroDoCartao(String numeroDoCartao) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByNumeroDoCartao(numeroDoCartao);
        return optionalUsuario.orElse(null);
    }

    /**
     * Simula a autenticação do usuário e verifica a disponibilidade de bicicleta.
     *
     * @param numeroDoCartao número do cartão do usuário
     * @return mensagem indicando o resultado da autenticação e disponibilidade de bicicleta
     */
    public String autenticarUsuarioEVerificarBicicleta(String numeroDoCartao) {
        // Aqui você pode implementar a lógica para autenticar o usuário e verificar a disponibilidade de bicicleta
        // Por enquanto, retornamos apenas o número do cartão
        return numeroDoCartao;
    }

    /**
     * Verifica se o usuário tem créditos suficientes para o plano especificado.
     *
     * @param numeroDoCartao número do cartão do usuário
     * @param valorDoPlano   valor do plano a ser verificado
     * @return mensagem indicando se os créditos são suficientes ou não
     */
    public boolean verificarCreditosSuficientes(String numeroDoCartao, int valorDoPlano) {
        // Aqui você pode implementar a lógica para verificar se o usuário tem créditos suficientes para o plano especificado
        // Por enquanto, retornamos apenas o número do cartão
        return Boolean.parseBoolean(numeroDoCartao);
    }
    /**
     * Obtém a quantidade restante de créditos no cartão do usuário.
     *
     * @param numeroDoCartao número do cartão do usuário
     * @return quantidade restante de créditos
     */
    public int obterCreditosRestantes(String numeroDoCartao) {
        // Aqui você pode implementar a lógica para obter a quantidade restante de créditos no cartão do usuário
        // Por enquanto, retornamos 0
        return 0;
    }

    /**
     * Verifica a disponibilidade de bicicleta no bicicletário.
     *
     * @param numeroDoCartao número do cartão do usuário
     * @return mensagem indicando se a bicicleta está disponível ou não
     */
    public String verificarDisponibilidadeBicicleta(String numeroDoCartao) {
        // Aqui você pode implementar a lógica para verificar a disponibilidade de bicicleta no bicicletário
        // Por enquanto, retornamos apenas o número do cartão
        return numeroDoCartao;
    }






    public String validarComBancoDeDados(String numeroDoCartao) {
        // Verifica se o cartão e o plano são válidos no banco de dados
        boolean cartaoValido = verificarCartaoNoBancoDeDados(numeroDoCartao);
        int valorDoPlano = 0;
        boolean planoValido = verificarPlanoNoBancoDeDados(numeroDoCartao, valorDoPlano);

        if (cartaoValido && planoValido) {
            return "OK"; // Cartão e plano válidos
        } else {
            return "Cartão ou plano inválidos"; // Cartão ou plano inválidos
        }
    }













    private boolean verificarPlanoNoBancoDeDados(String numeroDoCartao, int valorDoPlano) {
        return false;
    }




    private boolean verificarCartaoNoBancoDeDados(String numeroDoCartao) {
        return false;
    }

    // Método para consumir crédito de todos os usuários
    public void consumirCreditoDeTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario : usuarios) {
            consumirCredito(usuario.getNumeroDoCartao());
        }
    }

    // Método privado para consumir crédito de um usuário
    private void consumirCredito(String numeroDoCartao) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByNumeroDoCartao(numeroDoCartao);
        optionalUsuario.ifPresent(usuario -> {
            int creditos = usuario.getCreditos();
            if (creditos > 0) {
                usuario.setCreditos(creditos - 1);
                usuarioRepository.save(usuario);
                System.out.println("Um crédito foi deduzido para o usuário com número do cartão: " + numeroDoCartao);
            } else {
                System.out.println("O usuário com número do cartão " + numeroDoCartao + " não tem créditos disponíveis.");
            }
        });
    }

    public void salvarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void deduzirCreditos(String numeroDoCartao, int creditosNecessarios) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByNumeroDoCartao(numeroDoCartao);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            int creditosDisponiveis = usuario.getCreditos();
            if (creditosDisponiveis >= creditosNecessarios) {
                usuario.setCreditos(creditosDisponiveis - creditosNecessarios);
                usuarioRepository.save(usuario);
                System.out.println("Créditos deduzidos com sucesso para o usuário com número do cartão: " + numeroDoCartao);
            } else {
                System.out.println("O usuário com número do cartão " + numeroDoCartao + " não tem créditos suficientes.");
            }
        } else {
            System.out.println("Usuário com número do cartão " + numeroDoCartao + " não encontrado.");
        }
    }

    public boolean autenticarUsuarioPorNumeroDoCartao(String numeroDoCartao) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByNumeroDoCartao(numeroDoCartao);
        if (optionalUsuario.isPresent()) {
            // Aqui você pode implementar a lógica de autenticação, por exemplo, verificando se o usuário está ativo, etc.
            // Neste exemplo, retornamos true se o usuário for encontrado pelo número do cartão
            System.out.println("Usuário autenticado com sucesso com número do cartão: " + numeroDoCartao);
            return true;
        } else {
            System.out.println("Usuário com número do cartão " + numeroDoCartao + " não encontrado.");
            return false;
        }
    }
}
