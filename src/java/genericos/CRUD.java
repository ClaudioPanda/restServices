package genericos;

import java.util.List;

public interface CRUD <T>{
    public Boolean agregar(T objeto);
    public Boolean modificar(T objeto);
    public Boolean eliminar(T objeto);    
    public List<T> consultarTodos();
    public T consultarSegunID(T objeto);
    
}
