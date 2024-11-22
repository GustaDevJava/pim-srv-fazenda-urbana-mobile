package com.unipim.pim_srv_fazenda_urbana_mobile;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unipim.pim_srv_fazenda_urbana_mobile.models.Cliente;
import com.unipim.pim_srv_fazenda_urbana_mobile.services.ClienteApiService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroActivity extends AppCompatActivity {

    private EditText nomeEditText, emailEditText, telefoneEditText, dataNascimentoEditText, cpfEditText, senhaEditText, confirmarSenhaEditText;
    private Button cadastrarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro); // Define o layout da atividade

        // Inicializando os campos de entrada e botão
        nomeEditText = findViewById(R.id.nomeEditText);
        emailEditText = findViewById(R.id.emailEditText);
        telefoneEditText = findViewById(R.id.telefoneEditText);
        dataNascimentoEditText = findViewById(R.id.dataNascimentoEditText);
        cpfEditText = findViewById(R.id.cpfEditText);
        senhaEditText = findViewById(R.id.senhaEditText);
        confirmarSenhaEditText = findViewById(R.id.confirmarSenhaEditText);
        cadastrarButton = findViewById(R.id.cadastrarButton);

        // Ação do botão de cadastro
        cadastrarButton.setOnClickListener(v -> cadastrar());
    }

    private void cadastrar() {
        // Obtém os textos dos campos de entrada
        String nome = nomeEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String telefone = telefoneEditText.getText().toString().trim();
        String dataNascimento = dataNascimentoEditText.getText().toString().trim();
        String cpf = cpfEditText.getText().toString().trim();
        String senha = senhaEditText.getText().toString().trim();
        String confirmarSenha = confirmarSenhaEditText.getText().toString().trim();

        // Valida os campos
        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || dataNascimento.isEmpty() || cpf.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            Toast.makeText(CadastroActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verifica se as senhas coincidem
        if (!senha.equals(confirmarSenha)) {
            Toast.makeText(CadastroActivity.this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Envia os dados para a API
        new Thread(() -> {
            try {
                // Cria o objeto Cliente com os dados do formulário
                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setEmail(email);
                cliente.setCelular(telefone);
                cliente.setDataNascimento(dataNascimento);
                cliente.setCpf(cpf);
                cliente.setSenha(senha);

                // Chama o método de cadastro na API
                boolean sucesso = ClienteApiService.cadastrarCliente(cliente);

                runOnUiThread(() -> {
                    if (sucesso) {
                        Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish(); // Fecha a tela de cadastro e retorna
                    } else {
                        Toast.makeText(CadastroActivity.this, "Erro ao realizar cadastro", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(CadastroActivity.this, "Erro ao realizar cadastro", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
