package com.waity.api.service.dbRelation;

public interface oneToOneService<F, S> {
    F selectFirstBySecond(int secondId) throws Exception;
    S selectSecondByFirst(int firstId) throws Exception;
    void insertRelation(int firstId, int secondId) throws Exception;
    void deleteRelation(int firstId, int secondId) throws Exception;
}
