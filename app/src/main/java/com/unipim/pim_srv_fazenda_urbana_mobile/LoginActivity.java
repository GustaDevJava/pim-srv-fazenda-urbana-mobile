package com.unipim.pim_srv_fazenda_urbana_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unipim.pim_srv_fazenda_urbana_mobile.models.Cliente;
import com.unipim.pim_srv_fazenda_urbana_mobile.services.ClienteApiService;

import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "NetworkCheck"; // Tag para logs de diagnóstico de rede.
    private EditText emailEditText, passwordEditText; // Campos de entrada para ID, Nome e Email do cliente.
    private Button loginButton; // Botões para ações CRUD.
    private Cliente cliente = new Cliente(); // Lista de objetos Cliente para armazenar dados carregados.
    private TextView createAccountText; // TextView para o "Cadastre-se"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Define o layout da atividade.

        // Inicializando os campos de entrada e botão
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        createAccountText = findViewById(R.id.createAccountText); // Inicializando o TextView de cadastro

        // Ação do botão de login
        loginButton.setOnClickListener(v -> logar());

        // Ação do TextView de "Cadastre-se"
        createAccountText.setOnClickListener(v -> {
            // Inicia a tela de CadastroActivity
            Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
            startActivity(intent); // Inicia a tela de cadastro
        });
    }

    private void logar() {
        String email = emailEditText.getText().toString().trim(); // Obtém o texto do campo de email
        String senha = passwordEditText.getText().toString().trim(); // Obtém o texto do campo de senha

        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Por favor, preencha ambos os campos.", Toast.LENGTH_SHORT).show();
            return; // Verifica se os campos estão vazios
        }

        new Thread(() -> {
            try {
                // Passa os dados de login (email e senha) para o método getLogin no ClienteApiService
                cliente = ClienteApiService.getLogin(email, senha);

                runOnUiThread(() -> {
                    Toast.makeText(LoginActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show(); // Mensagem de sucesso

                    // Criando a Intent para navegar para a tela ProdutosActivity
                    Intent intent = new Intent(LoginActivity.this, PesquisarProdutosActivity.class);

                    // Passando o ID do cliente para a nova tela
                    intent.putExtra("CLIENTE_ID", cliente.getId());
                    startActivity(intent); // Inicia a nova tela

                    finish(); // Fecha a tela de login
                });
            } catch (Exception e) {
                e.printStackTrace(); // Exibe erro no console
                runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Erro ao realizar login", Toast.LENGTH_SHORT).show()); // Mensagem de erro
            }
        }).start();
    }
}

