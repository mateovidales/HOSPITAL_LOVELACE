package model;

import java.util.List;

public interface CRUD {

    //crear
    public Object create(Object obj);
    //buscar
    public List<Object> findAll();
    //actualizar
    public boolean update(Object obj);
    //eliminar
    public boolean delete(Object obj);
}
