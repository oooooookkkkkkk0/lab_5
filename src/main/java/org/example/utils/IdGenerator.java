package org.example.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Генератор уникальных идентификаторов.
 * Обеспечивает потокобезопасную генерацию последовательных ID.
 */
public class IdGenerator {
    private static AtomicLong idCounter = new AtomicLong(0);

    /**
     * Генерирует и возвращает следующий уникальный ID.
     *
     * @return следующий уникальный идентификатор (на 1 больше предыдущего)
     */
    public static long getNextId() {
        return idCounter.incrementAndGet();
    }

    /**
     * Устанавливает начальное значение для генератора ID.
     *
     * @param id начальное значение, с которого будет продолжена генерация
     * @throws IllegalArgumentException если переданное значение отрицательное
     */
    public static void setIdInitial(long id){
        idCounter = new AtomicLong(id);
    }
}