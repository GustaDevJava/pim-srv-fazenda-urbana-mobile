package com.unipim.pim_srv_fazenda_urbana_mobile.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.unipim.pim_srv_fazenda_urbana_mobile.InformacoesProdutoActivity;
import com.unipim.pim_srv_fazenda_urbana_mobile.R;
import com.unipim.pim_srv_fazenda_urbana_mobile.models.Produto;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder> {
    private final List<Produto> produtoList;

    public ProdutoAdapter(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produto produto = produtoList.get(position);

        // Configura o nome e preço do produto
        holder.nomeTextView.setText(produto.getNome());
        holder.precoTextView.setText(String.format("R$ %.2f", produto.getPreco()));

        // Configura a imagem do produto usando Glide
        Glide.with(holder.itemView.getContext())
                .load(produto.getImagem()) // URL da imagem
                .apply(new RequestOptions()
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.error_image)
                        .centerCrop())
                .into(holder.produtoImageView);

        // Define um OnClickListener para o item
        holder.itemView.setOnClickListener(v -> {
            // Cria uma Intent para abrir InformacoesProdutoActivity
            Intent intent = new Intent(holder.itemView.getContext(), InformacoesProdutoActivity.class);

            // Passa os dados do produto como extras
            intent.putExtra("produtoId", produto.getId());
            intent.putExtra("nome", produto.getNome());
            intent.putExtra("preco", produto.getPreco());
            intent.putExtra("imagem", produto.getImagem());
            intent.putExtra("categoria", produto.getCategoria());
            intent.putExtra("quantidade", produto.getQuantidade());
            intent.putExtra("desconto", produto.getDesconto());

            // Inicia a nova atividade
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView produtoImageView;
        TextView nomeTextView, precoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            produtoImageView = itemView.findViewById(R.id.produtoImageView);
            nomeTextView = itemView.findViewById(R.id.nomeTextView);
            precoTextView = itemView.findViewById(R.id.precoTextView);
        }
    }
}
