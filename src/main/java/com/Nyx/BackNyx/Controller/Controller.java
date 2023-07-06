/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Nyx.BackNyx.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author guilh
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/despesasTotaisPorMes")
    public String despesasTotaisPorMes() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String urlExterna = "http://dados.recife.pe.gov.br/api/3/action/datastore_search_sql?sql=SELECT%20mes_movimentacao,%20SUM(CAST(REPLACE(valor_pago,%20%27,%27,%20%27.%27)%20AS%20numeric))%20AS%20despesas_totais%20FROM%20%22d4d8a7f0-d4be-4397-b950-f0c991438111%22%20GROUP%20BY%20mes_movimentacao";
            HttpGet httpGet = new HttpGet(urlExterna);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String responseBody = EntityUtils.toString(entity);

                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(responseBody);

                    if (jsonNode.has("result") && jsonNode.get("result").has("records")) {
                        String records = jsonNode.get("result").get("records").toString();
                        return records;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "{\"mensagem\": \"Falha na obtenção dos dados.\"}";
    }

    @GetMapping("/despesasTotaisPorCategorias")
    public String despesasTotaisPorCategorias() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String urlExterna = "http://dados.recife.pe.gov.br/api/3/action/datastore_search_sql?sql=SELECT%20categoria_economica_nome,%20SUM(CAST(REPLACE(valor_pago,%20%27,%27,%20%27.%27)%20AS%20numeric))%20AS%20despesas_totais%20FROM%20%22d4d8a7f0-d4be-4397-b950-f0c991438111%22%20GROUP%20BY%20categoria_economica_nome";
            HttpGet httpGet = new HttpGet(urlExterna);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String responseBody = EntityUtils.toString(entity);

                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(responseBody);

                    if (jsonNode.has("result") && jsonNode.get("result").has("records")) {
                        String records = jsonNode.get("result").get("records").toString();
                        return records;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "{\"mensagem\": \"Falha na obtenção dos dados.\"}";
    }

    @GetMapping("/agrupamentoPorFonte")
    public String agrupamentoPorFonte() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String urlExterna = "http://dados.recife.pe.gov.br/api/3/action/datastore_search_sql?sql=SELECT%20fonte_recurso_nome,%20SUM(CAST(REPLACE(valor_pago,%20%27,%27,%20%27.%27)%20AS%20numeric))%20AS%20despesas_totais%20FROM%20%22d4d8a7f0-d4be-4397-b950-f0c991438111%22%20GROUP%20BY%20fonte_recurso_nome;";
            HttpGet httpGet = new HttpGet(urlExterna);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String responseBody = EntityUtils.toString(entity);

                    // Converter a resposta JSON em um objeto JsonNode usando o ObjectMapper
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(responseBody);

                    // Verificar se a chave "records" existe no objeto JsonNode
                    if (jsonNode.has("result") && jsonNode.get("result").has("records")) {
                        // Obter o valor da chave "records" como uma string JSON
                        String records = jsonNode.get("result").get("records").toString();
                        return records;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "{\"mensagem\": \"Falha na obtenção dos dados.\"}";
    }

}
