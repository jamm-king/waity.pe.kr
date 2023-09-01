package com.waity.api.service.dbRelation;

import java.util.List;

public interface manyToManyService<F, S> {
    List<F> selectFirstBySecond(int secondId) throws Exception;
    List<S> selectSecondByFirst(int firstId) throws Exception;
    void insertRelation(int firstId, int secondId) throws Exception;
    void deleteRelation(int firstId, int secondId) throws Exception;

    void insertRelations(int firstId, List<Integer> secondIds) throws Exception;
    void deleteRelations(int firstId, List<Integer> secondIds) throws Exception;
    void updateRelations(int firstId, List<Integer> secondIds) throws Exception;

    void insertRelations(List<Integer> firstIds, int secondId) throws Exception;
    void deleteRelations(List<Integer> firstIds, int secondId) throws Exception;
    void updateRelations(List<Integer> firstIds, int secondId) throws Exception;
}
