package br.com.digitalhouse.appmytasks.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.com.digitalhouse.appmytasks.model.Tarefa;
import io.reactivex.Observable;

@Dao
public interface TarefaDao {

    @Insert
    void inserirTarefa (Tarefa tarefa);

    @Insert
    void deletarTarefa (Tarefa tarefa);

    @Insert
    void atualizarTarefa (Tarefa tarefa);

    @Query("SELECT * FROM tarefas")
    Observable<List<Tarefa>> getAlltarefas();

    @Query("SELECT * FROM tarefas WHERE id = :id")
    Tarefa getById(long id);

    @Query("SELECT * FROM tarefas WHERE tarefa = :nomeTarefa")
    Tarefa getByNome(String nomeTarefa);

    @Query("SELECT * FROM tarefas ORDER BY id DESC LIMIT 5")
    Observable<List<Tarefa>> tarefasRecentes();

}
