package com.example.demo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpDelete;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import io.restassured.path.json.JsonPath;
import org.apache.http.client.ClientProtocolException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import javax.net.ssl.HttpsURLConnection; 
import javax.net.ssl.SSLContext; 
import javax.net.ssl.TrustManager; 
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
import java.net.MalformedURLException;
import java.io.OutputStream; 
import com.jayway.jsonpath.Configuration;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;


@SpringBootTest
public class junitTest {
   
   
   @Test
   //Chequea si se puede ingresar un producto
	  public void crearProducto() throws URISyntaxException, MalformedURLException, IOException {
        String message;
        JSONObject item = new JSONObject().put("nombre", "test")
                                          .put("codigo", "1234")
                                          .put("fecha_v", "2021-03-02")
                                          .put("categoria", "Nacional")
                                          .put("precio", 1.200);
        try{
        URL url = new URL("http://localhost:8081/productos/create/" + item.toString());
        URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
        HttpUriRequest request = new HttpPost(uri);
        //System.out.println(request);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        HttpEntity httpEntity = httpResponse.getEntity();
        System.out.println(EntityUtils.toString(httpEntity));
        }catch (IOException e) {
            e.printStackTrace();
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }
   
	}

   @Test
    //Revisa que el producto 999 no se encuentra en la api
    public void getProductoApi() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet( "http://localhost:8081/productos/" + 999 );
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(),equalTo(HttpStatus.SC_NOT_FOUND));
    }
    

	@Test
  //Chequea si se elimina un producto existente
	public void eliminarProducto() throws ClientProtocolException, IOException {
        try{
        HttpUriRequest request = new HttpDelete( "http://localhost:8081/productos/" + 5 );
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        HttpEntity httpEntity = httpResponse.getEntity();
        System.out.println(EntityUtils.toString(httpEntity));
        }catch (IOException e) {
            e.printStackTrace();
        }
	}

}
