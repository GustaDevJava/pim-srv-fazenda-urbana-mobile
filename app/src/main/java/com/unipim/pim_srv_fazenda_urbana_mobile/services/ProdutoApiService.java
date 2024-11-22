package com.unipim.pim_srv_fazenda_urbana_mobile.services;

import com.unipim.pim_srv_fazenda_urbana_mobile.models.Produto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProdutoApiService {

    private static final String BASE_URL = "http://192.168.0.116:8080/api/v1"; // Endereço da API

    // Método para buscar os produtos
    public static List<Produto> getProdutos() throws Exception {
        URL url = new URL(BASE_URL + "/produtos"); // Endpoint para a lista de produtos
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            // Abre a conexão HTTP
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // Método GET para obter dados
            conn.setRequestProperty("Content-Type", "application/json"); // Define o tipo de conteúdo como JSON

            // Verifica o código de resposta HTTP
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("Erro na requisição: Código de resposta " + responseCode);
            }

            // Lê a resposta da API
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line); // Concatena as linhas da resposta
            }

            // Converte a resposta em um objeto JSON
            JSONArray jsonArray = new JSONArray(result.toString());

            // Percorre o array de produtos e cria objetos Produto
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonProduto = jsonArray.getJSONObject(i);

                Produto produto = new Produto();
                produto.setId(jsonProduto.getInt("id"));
                produto.setNome(jsonProduto.getString("nome"));
                produto.setPreco(jsonProduto.getDouble("preco"));
                produto.setCategoria(jsonProduto.optString("categoria")); // Categoria pode ser nula
                produto.setDesconto(jsonProduto.optDouble("desconto", 0.0)); // Desconto com valor default de 0.0
                produto.setImagem(jsonProduto.optString("image")); // imagem pode ser nula
                produto.setQuantidade(jsonProduto.optInt("quantidade", 0)); // Quantidade com valor default de 0

                produtos.add(produto); // Adiciona o produto à lista
            }

            return produtos; // Retorna a lista de produtos

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao carregar os produtos: " + e.getMessage());
        } finally {
            // Fechando recursos
            if (reader != null) {
                try {
                    reader.close(); // Fecha o BufferedReader
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect(); // Fecha a conexão HTTP
            }
        }
    }
}
