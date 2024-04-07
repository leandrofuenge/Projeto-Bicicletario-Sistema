# LoginControllerApi

All URIs are relative to *https://api.server.test*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**loginPost**](LoginControllerApi.md#loginPost) | **POST** /login | Realiza login de usuário |


<a name="loginPost"></a>
# **loginPost**
> Usuario loginPost(body)

Realiza login de usuário

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.LoginControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.server.test");

    LoginControllerApi apiInstance = new LoginControllerApi(defaultClient);
    LoginPostRequest body = new LoginPostRequest(); // LoginPostRequest | Credenciais de usuário
    try {
      Usuario result = apiInstance.loginPost(body);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling LoginControllerApi#loginPost");
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
| **body** | [**LoginPostRequest**](LoginPostRequest.md)| Credenciais de usuário | |

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
| **200** | Login bem-sucedido |  -  |
| **400** | Requisição inválida |  -  |
| **401** | Não autorizado |  -  |

