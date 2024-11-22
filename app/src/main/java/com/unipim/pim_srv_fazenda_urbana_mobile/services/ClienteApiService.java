package com.unipim.pim_srv_fazenda_urbana_mobile.services;

import com.unipim.pim_srv_fazenda_urbana_mobile.models.Cliente;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ClienteApiService {

    private static final String BASE_URL = "http://192.168.0.116:8080/api/v1"; // Endereço da API

    public static Cliente getLogin(String email, String senha) throws Exception {
        URL url = new URL(BASE_URL + "/login"); // Cria a URL para o endpoint de login
        HttpURLConnection conn = null;
        BufferedReader reader = null;

        try {
            // Criação do objeto JSON para o corpo da requisição (login)
            JSONObject jsonLogin = new JSONObject();
            jsonLogin.put("email", email);  // Adiciona o email
            jsonLogin.put("senha", senha);  // Adiciona a senha

            // Abre a conexão HTTP
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json"); // Define o tipo de conteúdo como JSON
//            conn.setConnectTimeout(15000); // Define o tempo limite de conexão (15 segundos)
//            conn.setReadTimeout(15000);    // Define o tempo limite de leitura (15 segundos)

            // Ativa o envio de dados (POST)
            conn.setDoOutput(true);

            // Envia o corpo da requisição (o JSON)
            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.writeBytes(jsonLogin.toString());
                wr.flush();
            }

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
            JSONObject jsonObject = new JSONObject(result.toString());

            // Cria um objeto Cliente e popula os dados
            Cliente cliente = new Cliente();
            cliente.setId(jsonObject.getInt("id"));
            cliente.setNome(jsonObject.getString("nome"));
            cliente.setCpf(jsonObject.getString("cpf"));
            cliente.setEmail(jsonObject.getString("email"));
            cliente.setCnpj(jsonObject.optString("cnpj"));
            cliente.setDataNascimento(jsonObject.getString("dataNascimento"));
            cliente.setCelular(jsonObject.getString("celular"));
            cliente.setSenha(jsonObject.getString("senha"));
            cliente.setPremium(jsonObject.getBoolean("premium"));
            cliente.setImagem(jsonObject.optString("imagem"));

            return cliente; // Retorna o objeto Cliente com os dados

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao fazer login: " + e.getMessage());
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

    public static boolean cadastrarCliente(Cliente cliente) {
        try {
            URL url = new URL(BASE_URL + "/clientes/cadastro");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Cria o JSON com os dados do cliente
            String jsonInputString = String.format(
                    "{\"nome\": \"%s\", \"email\": \"%s\", \"telefone\": \"%s\", \"dataNascimento\": \"%s\", \"cpf\": \"%s\", \"senha\": \"%s\"}",
                    cliente.getNome(), cliente.getEmail(), cliente.getCelular(), cliente.getDataNascimento(), cliente.getCpf(), cliente.getSenha()
            );

            // Envia os dados no corpo da requisição
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            // Verifica a resposta da API
            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_CREATED; // Se a resposta for 200 (OK), o cadastro foi bem-sucedido

        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna falso se ocorrer algum erro
        }
    }
}
