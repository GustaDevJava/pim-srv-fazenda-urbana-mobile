package com.unipim.pim_srv_fazenda_urbana_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PesquisarSelecaoActivity extends AppCompatActivity {

    private TextView clienteIdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pesquisar_selecao);

        clienteIdTextView = findViewById(R.id.clienteIdTextView); // Id do TextView onde exibirá o ID

//        // Receber o ID do cliente passado pela LoginActivity
//        Intent intent = getIntent();
//        int clienteId = intent.getIntExtra("CLIENTE_ID", -1); // Pega o ID ou -1 se não encontrar
//
//        // Exibe o ID do cliente na tela
//        if (clienteId != -1) {
//            clienteIdTextView.setText("ID do Cliente: " + clienteId);
//        } else {
//            clienteIdTextView.setText("Cliente não encontrado!");
//        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}