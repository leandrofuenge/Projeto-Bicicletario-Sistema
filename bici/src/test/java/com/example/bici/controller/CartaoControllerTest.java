import com.example.bici.controller.CartaoController;
import com.example.bici.service.CartaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartaoControllerTest {

    private CartaoController cartaoController;
    private CartaoService cartaoService;

    @BeforeEach
    public void setUp() {
        cartaoService = mock(CartaoService.class);
        cartaoController = new CartaoController(cartaoService);
    }

    @Test
    public void autenticarUsuario_UsuarioAutenticado_Success() {
        when(cartaoService.autenticarUsuario(anyString())).thenReturn(true);

        ResponseEntity<Object> response = cartaoController.autenticarUsuario("numeroDoCartao");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário autenticado com sucesso.", response.getBody());
    }

    @Test
    public void autenticarUsuario_UsuarioNaoAutenticado_Unauthorized() {
        when(cartaoService.autenticarUsuario(anyString())).thenReturn(false);

        ResponseEntity<Object> response = cartaoController.autenticarUsuario("CREDITOS_RESTANTES");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Usuário não autenticado.", response.getBody());
    }

    @Test
    public void verificarCreditos_UsuarioEncontrado_Success() throws SQLException {
        when(cartaoService.verificarCreditos(anyString())).thenReturn(ResponseEntity.ok().body("Créditos restantes do usuário: 10"));

        ResponseEntity<Object> response = cartaoController.verificarCreditos("CREDITOS_RESTANTES");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Créditos restantes do usuário: 10", response.getBody());
    }

    @Test
    public void verificarCreditos_UsuarioNaoEncontrado_NotFound() throws SQLException {
        when(cartaoService.verificarCreditos(anyString())).thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado."));

        ResponseEntity<Object> response = cartaoController.verificarCreditos("numeroDoCartao");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuário não encontrado.", response.getBody());
    }

    // Você pode escrever mais testes para verificar diferentes cenários em verificarCreditos

    // Testes para utilizarCredito podem seguir um padrão semelhante
}
