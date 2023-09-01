package com.waity.api.service.db;

import java.util.List;

public interface dbService<T> {
    T selectEntityById(int id) throws Exception;
    List<T> selectEntitiesByIds(List<Integer> ids) throws Exception;
    List<T> selectEntityAll() throws Exception;
    void updateEntity(T t) throws Exception;
    void updateEntities(List<T> t) throws Exception;
    void insertEntity(T t) throws Exception;
    void insertEntities(List<T> t) throws Exception;
    void deleteEntity(int id) throws Exception;
    void deleteEntities(List<Integer> ids) throws Exception;
}
