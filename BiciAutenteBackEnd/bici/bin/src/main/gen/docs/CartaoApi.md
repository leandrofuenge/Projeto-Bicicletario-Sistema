# CartaoApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**usuariosAutenticarGet**](CartaoApi.md#usuariosAutenticarGet) | **GET** /usuarios/autenticar | Autentica um usuário pelo número do cartão |
| [**usuariosNaoUsoMomentaneoBicicletarioGet**](CartaoApi.md#usuariosNaoUsoMomentaneoBicicletarioGet) | **GET** /usuarios/NaoUsoMomentaneoBicicletario | Verifica se o bicicletário não está em uso no momento |
| [**usuariosUsoMomentaneoBicicletarioGet**](CartaoApi.md#usuariosUsoMomentaneoBicicletarioGet) | **GET** /usuarios/UsoMomentaneoBicicletario | Verifica se o bicicletário está em uso no momento |
| [**usuariosUtilizarCreditoPost**](CartaoApi.md#usuariosUtilizarCreditoPost) | **POST** /usuarios/utilizarCredito | Utiliza crédito do usuário pelo número do cartão |
| [**verificarcreditosGet**](CartaoApi.md#verificarcreditosGet) | **GET** /verificarcreditos | Verifica créditos do usuário pelo número do cartão |


<a name="usuariosAutenticarGet"></a>
# **usuariosAutenticarGet**
> usuariosAutenticarGet(numeroDoCartao)

Autentica um usuário pelo número do cartão

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CartaoApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    CartaoApi apiInstance = new CartaoApi(defaultClient);
    String numeroDoCartao = "numeroDoCartao_example"; // String | Número do cartão do usuário
    try {
      apiInstance.usuariosAutenticarGet(numeroDoCartao);
    } catch (ApiException e) {
      System.err.println("Exception when calling CartaoApi#usuariosAutenticarGet");
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
| **numeroDoCartao** | **String**| Número do cartão do usuário | |

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
| **200** | Usuário autenticado com sucesso |  -  |
| **401** | Usuário não encontrado |  -  |
| **500** | Erro interno do servidor |  -  |

<a name="usuariosNaoUsoMomentaneoBicicletarioGet"></a>
# **usuariosNaoUsoMomentaneoBicicletarioGet**
> usuariosNaoUsoMomentaneoBicicletarioGet()

Verifica se o bicicletário não está em uso no momento

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CartaoApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    CartaoApi apiInstance = new CartaoApi(defaultClient);
    try {
      apiInstance.usuariosNaoUsoMomentaneoBicicletarioGet();
    } catch (ApiException e) {
      System.err.println("Exception when calling CartaoApi#usuariosNaoUsoMomentaneoBicicletarioGet");
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
| **200** | Bicicletário não está em uso no momento |  -  |
| **401** | Bicicletário está em uso no momento |  -  |
| **500** | Erro interno do servidor |  -  |

<a name="usuariosUsoMomentaneoBicicletarioGet"></a>
# **usuariosUsoMomentaneoBicicletarioGet**
> usuariosUsoMomentaneoBicicletarioGet()

Verifica se o bicicletário está em uso no momento

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CartaoApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    CartaoApi apiInstance = new CartaoApi(defaultClient);
    try {
      apiInstance.usuariosUsoMomentaneoBicicletarioGet();
    } catch (ApiException e) {
      System.err.println("Exception when calling CartaoApi#usuariosUsoMomentaneoBicicletarioGet");
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
| **200** | Bicicletário disponível para uso |  -  |
| **500** | Erro interno do servidor |  -  |

<a name="usuariosUtilizarCreditoPost"></a>
# **usuariosUtilizarCreditoPost**
> usuariosUtilizarCreditoPost(numeroDoCartao)

Utiliza crédito do usuário pelo número do cartão

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CartaoApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    CartaoApi apiInstance = new CartaoApi(defaultClient);
    String numeroDoCartao = "numeroDoCartao_example"; // String | Número do cartão do usuário
    try {
      apiInstance.usuariosUtilizarCreditoPost(numeroDoCartao);
    } catch (ApiException e) {
      System.err.println("Exception when calling CartaoApi#usuariosUtilizarCreditoPost");
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
| **numeroDoCartao** | **String**| Número do cartão do usuário | |

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
| **200** | Crédito utilizado com sucesso |  -  |
| **400** | Não há créditos suficientes para utilizar |  -  |
| **404** | Usuário não encontrado |  -  |
| **500** | Erro interno do servidor |  -  |

<a name="verificarcreditosGet"></a>
# **verificarcreditosGet**
> verificarcreditosGet(numeroDoCartao)

Verifica créditos do usuário pelo número do cartão

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CartaoApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    CartaoApi apiInstance = new CartaoApi(defaultClient);
    String numeroDoCartao = "numeroDoCartao_example"; // String | Número do cartão do usuário
    try {
      apiInstance.verificarcreditosGet(numeroDoCartao);
    } catch (ApiException e) {
      System.err.println("Exception when calling CartaoApi#verificarcreditosGet");
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
| **numeroDoCartao** | **String**| Número do cartão do usuário | |

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
| **200** | Créditos do usuário obtidos com sucesso |  -  |
| **404** | Usuário não encontrado |  -  |
| **500** | Erro interno do servidor |  -  |

