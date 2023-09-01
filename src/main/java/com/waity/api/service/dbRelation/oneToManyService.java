package com.waity.api.service.dbRelation;

import java.util.List;

public interface oneToManyService<F, S> {
    F selectFirstBySecond(int secondId) throws Exception;
    List<S> selectSecondByFirst(int firstId) throws Exception;
    void insertRelation(int firstId, int secondId) throws Exception;
    void deleteRelation(int firstId, int secondId) throws Exception;

    void insertRelations(int firstId, List<Integer> secondIds) throws Exception;
    void deleteRelations(int firstId, List<Integer> secondIds) throws Exception;
    void updateRelations(int firstId, List<Integer> secondIds) throws Exception;
}