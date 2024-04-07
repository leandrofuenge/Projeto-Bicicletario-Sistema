# AdminApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**usuariosAtualizarCpfPut**](AdminApi.md#usuariosAtualizarCpfPut) | **PUT** /usuarios/atualizar/{cpf} | Atualiza um usuário por CPF |
| [**usuariosCpfCpfGet**](AdminApi.md#usuariosCpfCpfGet) | **GET** /usuarios/cpf/{cpf} | Obtém um usuário por CPF |
| [**usuariosCriarPost**](AdminApi.md#usuariosCriarPost) | **POST** /usuarios/criar | Cria um novo usuário |
| [**usuariosExcluirCpfDelete**](AdminApi.md#usuariosExcluirCpfDelete) | **DELETE** /usuarios/excluir/{cpf} | Exclui um usuário por CPF |
| [**usuariosTodosGet**](AdminApi.md#usuariosTodosGet) | **GET** /usuarios/todos | Obtém todos os usuários |


<a name="usuariosAtualizarCpfPut"></a>
# **usuariosAtualizarCpfPut**
> Usuario usuariosAtualizarCpfPut(cpf, usuario)

Atualiza um usuário por CPF

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AdminApi apiInstance = new AdminApi(defaultClient);
    String cpf = "cpf_example"; // String | CPF do usuário a ser atualizado
    Usuario usuario = new Usuario(); // Usuario | Objeto de usuário atualizado
    try {
      Usuario result = apiInstance.usuariosAtualizarCpfPut(cpf, usuario);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#usuariosAtualizarCpfPut");
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
| **cpf** | **String**| CPF do usuário a ser atualizado | |
| **usuario** | [**Usuario**](Usuario.md)| Objeto de usuário atualizado | [optional] |

### Return type

[**Usuario**](Usuario.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Usuário atualizado com sucesso |  -  |
| **404** | Usuário não encontrado |  -  |
| **400** | Erro ao processar a requisição |  -  |

<a name="usuariosCpfCpfGet"></a>
# **usuariosCpfCpfGet**
> Usuario usuariosCpfCpfGet(cpf)

Obtém um usuário por CPF

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AdminApi apiInstance = new AdminApi(defaultClient);
    String cpf = "cpf_example"; // String | CPF do usuário a ser buscado
    try {
      Usuario result = apiInstance.usuariosCpfCpfGet(cpf);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#usuariosCpfCpfGet");
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
| **cpf** | **String**| CPF do usuário a ser buscado | |

### Return type

[**Usuario**](Usuario.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Usuário encontrado |  -  |
| **404** | Usuário não encontrado |  -  |

<a name="usuariosCriarPost"></a>
# **usuariosCriarPost**
> Usuario usuariosCriarPost(usuario)

Cria um novo usuário

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AdminApi apiInstance = new AdminApi(defaultClient);
    Usuario usuario = new Usuario(); // Usuario | Objeto de usuário a ser criado
    try {
      Usuario result = apiInstance.usuariosCriarPost(usuario);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#usuariosCriarPost");
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
| **usuario** | [**Usuario**](Usuario.md)| Objeto de usuário a ser criado | [optional] |

### Return type

[**Usuario**](Usuario.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Usuário criado com sucesso |  -  |
| **400** | Erro ao processar a requisição |  -  |

<a name="usuariosExcluirCpfDelete"></a>
# **usuariosExcluirCpfDelete**
> usuariosExcluirCpfDelete(cpf)

Exclui um usuário por CPF

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AdminApi apiInstance = new AdminApi(defaultClient);
    String cpf = "cpf_example"; // String | CPF do usuário a ser excluído
    try {
      apiInstance.usuariosExcluirCpfDelete(cpf);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#usuariosExcluirCpfDelete");
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
| **cpf** | **String**| CPF do usuário a ser excluído | |

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
| **200** | Usuário excluído com sucesso |  -  |
| **404** | Usuário não encontrado |  -  |

<a name="usuariosTodosGet"></a>
# **usuariosTodosGet**
> List&lt;Usuario&gt; usuariosTodosGet()

Obtém todos os usuários

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AdminApi apiInstance = new AdminApi(defaultClient);
    try {
      List<Usuario> result = apiInstance.usuariosTodosGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdminApi#usuariosTodosGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Usuario&gt;**](Usuario.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Lista de usuários |  -  |
| **400** | Erro ao processar a requisição |  -  |

