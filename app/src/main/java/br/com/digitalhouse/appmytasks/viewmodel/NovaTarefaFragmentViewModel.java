package br.com.digitalhouse.appmytasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import br.com.digitalhouse.appmytasks.model.data.Database;
import br.com.digitalhouse.appmytasks.model.data.TarefaDao;
import br.com.digitalhouse.appmytasks.model.pojos.Tarefa;

public class NovaTarefaFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<Tarefa> tarefa = new MutableLiveData<>();
    private TarefaDao tarefaDao = Database.getDatabase(getApplication()).tarefaDao();

    public NovaTarefaFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Tarefa> retornaTarefa() {
        return this.tarefa;
    }

    public void insereTarefaBancoDados(Tarefa tarefa) {

        new Thread(() -> {
            if (tarefa != null) {
                tarefaDao.inserirTarefa(tarefa);
            }
        }).start();

        this.tarefa.setValue(tarefa);
    }
}
