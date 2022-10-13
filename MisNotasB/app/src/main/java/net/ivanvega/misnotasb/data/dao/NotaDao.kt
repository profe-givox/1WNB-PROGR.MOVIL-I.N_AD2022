package net.ivanvega.misnotasb.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import net.ivanvega.misnotasb.data.model.Nota

@Dao
interface NotaDao {
    @Insert
    fun insertar(nota: Nota)
    @Update
    fun actualizar(nota: Nota)
    @Delete
    fun eliminar(nota: Nota)
    @Query("select * from Nota")
    fun getAll():List<Nota>
    @Query("select * from Nota where uid=:id")
    fun getOneByID(id: Int): Nota
}