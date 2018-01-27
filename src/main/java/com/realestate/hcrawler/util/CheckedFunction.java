package com.real_estates.util;

/** https://stackoverflow.com/questions/18198176/java-8-lambda-function-that-throws-exception **/

@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws Exception;
}
