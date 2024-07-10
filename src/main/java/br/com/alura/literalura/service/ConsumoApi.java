package br.com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    private String enderecoApi;

    public ConsumoApi(String endereco) {
        this.enderecoApi = endereco;
    }

    public String buscarApi(String parametros) {

        String url = enderecoApi + parametros.trim().replaceAll(" ", "%20");

        System.out.println("==========");
        System.out.println("url: " + url);
        System.out.println("==========");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();

        return json;
    }
}
