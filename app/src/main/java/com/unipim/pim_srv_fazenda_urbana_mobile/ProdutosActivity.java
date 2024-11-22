package com.unipim.pim_srv_fazenda_urbana_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unipim.pim_srv_fazenda_urbana_mobile.adapters.ProdutoAdapter;
import com.unipim.pim_srv_fazenda_urbana_mobile.models.Produto;
import com.unipim.pim_srv_fazenda_urbana_mobile.services.ProdutoApiService;

import java.util.ArrayList;
import java.util.List;

public class ProdutosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProdutoAdapter adapter;
    private List<Produto> produtoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        // Receber o ID do cliente passado pela LoginActivity
        Intent intent = getIntent();
        int clienteId = intent.getIntExtra("CLIENTE_ID", -1); // Pega o ID ou -1 se não encontrar

        // Exibe o ID do cliente (opcional, caso queira fazer algo com o clienteId)
        if (clienteId != -1) {
            Log.d("ProdutosActivity", "ID do Cliente: " + clienteId);
        } else {
            Log.d("ProdutosActivity", "Cliente não encontrado!");
        }

        recyclerView = findViewById(R.id.recyclerViewProdutos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        produtoList = new ArrayList<>();
        adapter = new ProdutoAdapter(produtoList); // Inicializa o Adapter com a lista
        recyclerView.setAdapter(adapter); // Define o Adapter no RecyclerView

        // Carrega a lista de produtos da API
        carregarProdutos();
    }

    private void carregarProdutos() {
        new Thread(() -> {
            try {
                // Chama o serviço para buscar os produtos
                List<Produto> produtos = ProdutoApiService.getProdutos();

                // Atualiza a lista de produtos na UI
                runOnUiThread(() -> {
                    if (produtos != null && !produtos.isEmpty()) {
                        produtoList.clear();
                        produtoList.addAll(produtos); // Adiciona os produtos retornados da API
                        adapter.notifyDataSetChanged(); // Notifica o adapter sobre as mudanças
                    } else {
                        Toast.makeText(ProdutosActivity.this, "Nenhum produto encontrado", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(ProdutosActivity.this, "Erro ao carregar os produtos", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
