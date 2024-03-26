/*
 * API de Administração de Usuários
 * Esta API permite a administração de usuários
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.Usuario;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for AdminApi
 */
@Disabled
public class AdminApiTest {

    private final AdminApi api = new AdminApi();

    /**
     * Atualiza um usuário por CPF
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void usuariosAtualizarCpfPutTest() throws ApiException {
        String cpf = null;
        Usuario usuario = null;
        Usuario response = api.usuariosAtualizarCpfPut(cpf, usuario);
        // TODO: test validations
    }

    /**
     * Obtém um usuário por CPF
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void usuariosCpfCpfGetTest() throws ApiException {
        String cpf = null;
        Usuario response = api.usuariosCpfCpfGet(cpf);
        // TODO: test validations
    }

    /**
     * Cria um novo usuário
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void usuariosCriarPostTest() throws ApiException {
        Usuario usuario = null;
        Usuario response = api.usuariosCriarPost(usuario);
        // TODO: test validations
    }

    /**
     * Exclui um usuário por CPF
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void usuariosExcluirCpfDeleteTest() throws ApiException {
        String cpf = null;
        api.usuariosExcluirCpfDelete(cpf);
        // TODO: test validations
    }

    /**
     * Obtém todos os usuários
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void usuariosTodosGetTest() throws ApiException {
        List<Usuario> response = api.usuariosTodosGet();
        // TODO: test validations
    }

}
