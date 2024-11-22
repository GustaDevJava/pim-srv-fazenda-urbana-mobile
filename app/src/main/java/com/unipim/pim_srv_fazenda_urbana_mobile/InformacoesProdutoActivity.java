package com.unipim.pim_srv_fazenda_urbana_mobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class InformacoesProdutoActivity extends AppCompatActivity {

    private ImageView produtoImageView;
    private TextView nomeTextView, precoTextView, categoriaTextView, quantidadeTextView, descontoTextView;
    private Button adicionarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_informacoes_produto);

        // Inicializa as views
        produtoImageView = findViewById(R.id.produtoImageViewDetalhes);
        nomeTextView = findViewById(R.id.nomeTextViewDetalhes);
        precoTextView = findViewById(R.id.precoTextViewDetalhes);
        quantidadeTextView = findViewById(R.id.quantidadeTextViewDetalhes);
        categoriaTextView = findViewById(R.id.categoriaTextViewDetalhes);
        adicionarButton = findViewById(R.id.adicionarButton);

        // Recupera os dados do produto passados pela Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nome = extras.getString("nome");
            double preco = extras.getDouble("preco");
            String imagem = extras.getString("imagem");
            String categoria = extras.getString("categoria");
            int quantidade = extras.getInt("quantidade");

            // Define os valores nas views
            nomeTextView.setText(nome);
            precoTextView.setText(String.format("%.2f R$", preco));
            quantidadeTextView.setText(String.format("Quantidade: %d", quantidade));
            categoriaTextView.setText(String.format("Categoria: %s", categoria));

            // Carrega a imagem com Glide
            Glide.with(this)
                    .load(imagem)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .into(produtoImageView);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configura o botão para realizar uma ação
        adicionarButton.setOnClickListener(v -> {

        });
    }


}