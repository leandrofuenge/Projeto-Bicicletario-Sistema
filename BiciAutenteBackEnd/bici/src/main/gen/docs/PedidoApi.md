# PedidoApi

All URIs are relative to */pedido*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**cancelarPedidoCpfDelete**](PedidoApi.md#cancelarPedidoCpfDelete) | **DELETE** /cancelar-pedido/{cpf} | Cancela um pedido de cartão pelo CPF do usuário |
| [**salvarEstadoPost**](PedidoApi.md#salvarEstadoPost) | **POST** /salvar-estado | Salva o estado de um pedido |
| [**solicitarCartaoCpfGet**](PedidoApi.md#solicitarCartaoCpfGet) | **GET** /solicitar-cartao/{cpf} | Solicita um cartão pelo CPF do usuário |


<a name="cancelarPedidoCpfDelete"></a>
# **cancelarPedidoCpfDelete**
> String cancelarPedidoCpfDelete(cpf)

Cancela um pedido de cartão pelo CPF do usuário

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PedidoApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/pedido");

    PedidoApi apiInstance = new PedidoApi(defaultClient);
    String cpf = "cpf_example"; // String | CPF do usuário
    try {
      String result = apiInstance.cancelarPedidoCpfDelete(cpf);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PedidoApi#cancelarPedidoCpfDelete");
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
| **cpf** | **String**| CPF do usuário | |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Pedido de cartão cancelado com sucesso |  -  |
| **404** | Not Found - O usuário não possui pedido de cartão |  -  |
| **500** | Erro interno do servidor |  -  |

<a name="salvarEstadoPost"></a>
# **salvarEstadoPost**
> String salvarEstadoPost(estadoPedido)

Salva o estado de um pedido

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PedidoApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/pedido");

    PedidoApi apiInstance = new PedidoApi(defaultClient);
    Integer estadoPedido = 56; // Integer | Estado do pedido a ser salvo
    try {
      String result = apiInstance.salvarEstadoPost(estadoPedido);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PedidoApi#salvarEstadoPost");
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
| **estadoPedido** | **Integer**| Estado do pedido a ser salvo | |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Estado do pedido salvo com sucesso |  -  |

<a name="solicitarCartaoCpfGet"></a>
# **solicitarCartaoCpfGet**
> String solicitarCartaoCpfGet(cpf)

Solicita um cartão pelo CPF do usuário

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PedidoApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("/pedido");

    PedidoApi apiInstance = new PedidoApi(defaultClient);
    String cpf = "cpf_example"; // String | CPF do usuário
    try {
      String result = apiInstance.solicitarCartaoCpfGet(cpf);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PedidoApi#solicitarCartaoCpfGet");
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
| **cpf** | **String**| CPF do usuário | |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Cartão solicitado com sucesso |  -  |
| **400** | Bad Request - O usuário não está elegível para solicitar um cartão |  -  |
| **500** | Erro interno do servidor |  -  |

