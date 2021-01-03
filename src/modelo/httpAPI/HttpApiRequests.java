package modelo.httpAPI;

import java.io.IOException;

import com.squareup.okhttp.*;

public class HttpApiRequests {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String url;
    OkHttpClient client;

    public HttpApiRequests(String url){
    	this.url = url;
        client = new OkHttpClient();
    }
    
    public String getRequest() throws IOException {
        Request request= new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    
    public String postRequest(String json) throws IOException{
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }
    
    public String putRequest(String json) throws IOException{
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }
    
    public String deleteRequest(String json) throws IOException{
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .delete(body)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}
