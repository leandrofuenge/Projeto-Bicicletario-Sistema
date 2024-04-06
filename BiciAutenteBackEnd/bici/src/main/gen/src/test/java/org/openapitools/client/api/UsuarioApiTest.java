/*
 * API de Usuário de Bicicletas
 * Esta API permite operações relacionadas aos usuários de bicicletas
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
 * API tests for UsuarioApi
 */
@Disabled
public class UsuarioApiTest {

    private final UsuarioApi api = new UsuarioApi();

    /**
     * Modificar os dados de um usuário pelo ID
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void usuarioAlterarMeusDadosIdPutTest() throws ApiException {
        Integer id = null;
        Usuario usuario = null;
        api.usuarioAlterarMeusDadosIdPut(id, usuario);
        // TODO: test validations
    }

    /**
     * Excluir os dados de um usuário pelo ID
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void usuarioExcluirMeusDadosIdDeleteTest() throws ApiException {
        Integer id = null;
        api.usuarioExcluirMeusDadosIdDelete(id);
        // TODO: test validations
    }

    /**
     * Obter os dados de um usuário pelo ID
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void usuarioMeusDadosIdGetTest() throws ApiException {
        Integer id = null;
        api.usuarioMeusDadosIdGet(id);
        // TODO: test validations
    }

}