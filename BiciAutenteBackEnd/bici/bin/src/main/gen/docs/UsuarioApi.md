# UsuarioApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**usuarioAlterarMeusDadosIdPut**](UsuarioApi.md#usuarioAlterarMeusDadosIdPut) | **PUT** /usuario/Alterar-Meus-Dados/{id} | Modificar os dados de um usuário pelo ID |
| [**usuarioExcluirMeusDadosIdDelete**](UsuarioApi.md#usuarioExcluirMeusDadosIdDelete) | **DELETE** /usuario/Excluir-Meus-Dados/{id} | Excluir os dados de um usuário pelo ID |
| [**usuarioMeusDadosIdGet**](UsuarioApi.md#usuarioMeusDadosIdGet) | **GET** /usuario/Meus-dados/{id} | Obter os dados de um usuário pelo ID |


<a name="usuarioAlterarMeusDadosIdPut"></a>
# **usuarioAlterarMeusDadosIdPut**
> usuarioAlterarMeusDadosIdPut(id, usuario)

Modificar os dados de um usuário pelo ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    UsuarioApi apiInstance = new UsuarioApi(defaultClient);
    Integer id = 56; // Integer | ID do usuário
    Usuario usuario = new Usuario(); // Usuario | Objeto JSON contendo os novos dados do usuário
    try {
      apiInstance.usuarioAlterarMeusDadosIdPut(id, usuario);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApi#usuarioAlterarMeusDadosIdPut");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**| ID do usuário | |
| **usuario** | [**Usuario**](Usuario.md)| Objeto JSON contendo os novos dados do usuário | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Dados do usuário modificados com sucesso |  -  |
| **500** | Erro interno do servidor ao tentar modificar os dados do usuário |  -  |

<a name="usuarioExcluirMeusDadosIdDelete"></a>
# **usuarioExcluirMeusDadosIdDelete**
> usuarioExcluirMeusDadosIdDelete(id)

Excluir os dados de um usuário pelo ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    UsuarioApi apiInstance = new UsuarioApi(defaultClient);
    Integer id = 56; // Integer | ID do usuário
    try {
      apiInstance.usuarioExcluirMeusDadosIdDelete(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApi#usuarioExcluirMeusDadosIdDelete");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**| ID do usuário | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Dados do usuário excluídos com sucesso |  -  |
| **500** | Erro interno do servidor ao tentar excluir os dados do usuário |  -  |

<a name="usuarioMeusDadosIdGet"></a>
# **usuarioMeusDadosIdGet**
> usuarioMeusDadosIdGet(id)

Obter os dados de um usuário pelo ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuarioApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    UsuarioApi apiInstance = new UsuarioApi(defaultClient);
    Integer id = 56; // Integer | ID do usuário
    try {
      apiInstance.usuarioMeusDadosIdGet(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuarioApi#usuarioMeusDadosIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**| ID do usuário | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Dados do usuário recuperados com sucesso |  -  |
| **404** | Usuário não encontrado |  -  |
| **500** | Erro interno do servidor |  -  |

