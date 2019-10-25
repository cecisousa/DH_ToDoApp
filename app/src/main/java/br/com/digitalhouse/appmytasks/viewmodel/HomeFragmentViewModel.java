package br.com.digitalhouse.appmytasks.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.appmytasks.model.data.Database;
import br.com.digitalhouse.appmytasks.model.data.TarefaDao;
import br.com.digitalhouse.appmytasks.model.pojos.Tarefa;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<List<Tarefa>> listaTarefas = new MutableLiveData<>();
    private TarefaDao tarefaDao = Database.getDatabase(getApplication()).tarefaDao();
    private CompositeDisposable disposable = new CompositeDisposable();

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Tarefa>> retornaListaTarefa () {
        return listaTarefas;
    }

    public void buscaTarefasRecentes() {
        disposable.add(
                tarefaDao.tarefasRecentes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tarefas -> {
                    listaTarefas.setValue(tarefas);
                }, throwable -> {
                    Log.i("LOG", "Busca tarefas recentes" + throwable.getMessage());
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
