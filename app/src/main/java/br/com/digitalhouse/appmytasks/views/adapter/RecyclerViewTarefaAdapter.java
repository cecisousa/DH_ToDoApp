package br.com.digitalhouse.appmytasks.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.digitalhouse.appmytasks.R;
import br.com.digitalhouse.appmytasks.model.pojos.Tarefa;

public class RecyclerViewTarefaAdapter extends RecyclerView.Adapter<RecyclerViewTarefaAdapter.ViewHolder> {

    private List<Tarefa> tarefaList;

    public RecyclerViewTarefaAdapter(List<Tarefa> tarefaList) {
        this.tarefaList = tarefaList;
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
