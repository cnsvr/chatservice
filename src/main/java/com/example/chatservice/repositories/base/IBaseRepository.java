package com.example.chatservice.repositories.base;

public interface IBaseRepository<T> {
    T save(T entity);
}
