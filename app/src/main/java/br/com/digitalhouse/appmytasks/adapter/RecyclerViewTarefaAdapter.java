package br.com.digitalhouse.appmytasks.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.digitalhouse.appmytasks.R;
import br.com.digitalhouse.appmytasks.interfaces.OnClick;
import br.com.digitalhouse.appmytasks.model.Tarefa;

public class RecyclerViewTarefaAdapter extends RecyclerView.Adapter<RecyclerViewTarefaAdapter.ViewHolder> {

    private List<Tarefa> tarefaList;
    private OnClick listener;

    public RecyclerViewTarefaAdapter(List<Tarefa> tarefaList, OnClick listener) {
        this.tarefaList = tarefaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_tarefas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tarefa tarefa = tarefaList.get(position);
        holder.onBind(tarefa);

        holder.itemView.setOnClickListener(view -> listener.OnClick(tarefa));
    }

    @Override
    public int getItemCount() {
        return tarefaList.size();
    }

    public void atualizaListaTarefas(List<Tarefa> listaTarefas){
        this.tarefaList.clear();
        this.tarefaList = listaTarefas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textViewNomeTarefa);
        }

        public void onBind(Tarefa tarefa) {
            nome.setText(tarefa.getNomeTarefa());
        }
    }
}
