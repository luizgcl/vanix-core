package com.vanix.core.application.useCases;

public interface UseCase<T> {

    boolean execute(T data);

}
