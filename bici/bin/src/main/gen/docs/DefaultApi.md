# DefaultApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**usuariosGet**](DefaultApi.md#usuariosGet) | **GET** /usuarios | Obter todos os usuários |
| [**usuariosPost**](DefaultApi.md#usuariosPost) | **POST** /usuarios | Criar novo usuário |


<a name="usuariosGet"></a>
# **usuariosGet**
> usuariosGet()

Obter todos os usuários

Endpoint para recuperar todos os usuários cadastrados.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      apiInstance.usuariosGet();
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#usuariosGet");
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

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Lista de usuários obtida com sucesso |  -  |
| **500** | Erro interno do servidor |  -  |

<a name="usuariosPost"></a>
# **usuariosPost**
> usuariosPost()

Criar novo usuário

Endpoint para criar um novo usuário.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      apiInstance.usuariosPost();
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#usuariosPost");
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

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Usuário criado com sucesso |  -  |
| **400** | Requisição inválida |  -  |
| **500** | Erro interno do servidor |  -  |

