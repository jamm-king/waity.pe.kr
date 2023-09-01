package com.waity.api.domain.dataStruct;

import com.waity.api.global.error.exception.DuplicateKeyException;
import com.waity.api.global.error.exception.ErrorCode;
import com.waity.api.global.error.exception.customRuntimeException;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;

public class EntityList<T>{

    private List<T> entityList;
    private Set<Integer> entityIds;

    public EntityList() {
        entityList = new ArrayList<>();
        entityIds = new HashSet<>();
    }
    public EntityList(List<T> entities) throws Exception {
        for(T entity : entities) {
            add(entity);
        }
    }

    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for(T entity : entityList) {
            action.accept(entity);
        }
    }
    public void add(T entity) throws Exception {
        if(entity == null) throw new NullPointerException();
        Method method = entity.getClass().getMethod("getId");
        Integer id = (Integer)method.invoke(entity, null);
        if(id == null) {
            throw new customRuntimeException(ErrorCode.INVALID_OPERATION,
                    String.format("id is missing for class %s", entity.getClass().getSimpleName()));
        } else if(entityIds.contains(id)) {
            throw new DuplicateKeyException(ErrorCode.DUPLICATE_KEY,
                    String.format("Key for %s is duplicated on %d", entity.getClass().getSimpleName(), id));
        } else {
            entityList.add(entity);
        }
    }
    public void addAll(List<T> entities) throws Exception {
        for(T entity : entities) {
            add(entity);
        }
    }
    public void remove(T entity) {
        if(entity == null) return;
        else entityList.remove(entity);
    }
    public void removeAll(List<T> entities) throws Exception {
        for(T entity : entities) {
            remove(entity);
        }
    }
    public List<T> getList() {
        return entityList;
    }
    public void setList(List<T> list) {
        entityList = list;
    }
}
