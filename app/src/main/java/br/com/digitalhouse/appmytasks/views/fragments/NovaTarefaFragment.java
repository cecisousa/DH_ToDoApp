package br.com.digitalhouse.appmytasks.views.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import br.com.digitalhouse.appmytasks.R;
import br.com.digitalhouse.appmytasks.model.data.Database;
import br.com.digitalhouse.appmytasks.model.data.TarefaDao;
import br.com.digitalhouse.appmytasks.model.pojos.Tarefa;
import br.com.digitalhouse.appmytasks.viewmodel.NovaTarefaFragmentViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class NovaTarefaFragment extends Fragment {
    private TextInputLayout nome;
    private TextInputLayout descricao;
    private FloatingActionButton btnAdd;
    private NovaTarefaFragmentViewModel viewModel;

    public NovaTarefaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nova_tarefa, container, false);

        initViews(view);

        btnAdd.setOnClickListener(v -> {
            String nomeTarefa = nome.getEditText().getText().toString();
            String descricaoTarefa = descricao.getEditText().getText().toString();

            if (!nomeTarefa.isEmpty() && !descricaoTarefa.isEmpty()) {

                    Tarefa tarefa = new Tarefa(nomeTarefa, descricaoTarefa);

                    viewModel.insereTarefaBancoDados(tarefa);

                    viewModel.retornaTarefa().observe(this, tarefaRetornada -> {
                        Log.i("LOG", "Tarefa inserida no banco" + tarefaRetornada.toString());
                    });

//                    o método acima serve apenas para ver a aplicação funcionando

                nome.getEditText().setText("");
                descricao.getEditText().setText("");

                Snackbar.make(nome, "Tarefa salva com sucesso!", Snackbar.LENGTH_LONG).show();
            } else {
                nome.setError("Preencha o nome da tarefa!");
                descricao.setError("Preencha a descrição da tarefa!");
            }
        });

        return view;
    }

    private void initViews(View view) {
        nome = view.findViewById(R.id.textInpuLayoutNome);
        descricao = view.findViewById(R.id.textInputLayoutDescricao);
        btnAdd = view.findViewById(R.id.floatingActionButtonSalvar);
        viewModel = ViewModelProviders.of(this).get(NovaTarefaFragmentViewModel.class);

    }


}
