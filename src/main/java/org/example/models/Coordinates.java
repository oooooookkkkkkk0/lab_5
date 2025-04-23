package org.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий координаты объекта.
 * Содержит координаты x (может быть null) и y.
 */
@Data
@NoArgsConstructor
public class Coordinates {
    /** Координата X (может быть null) */
    private Float x;

    /** Координата Y */
    private double y;

    /**
     * Создает новый объект Coordinates с заданными значениями.
     *
     * @param x координата X (может быть null)
     * @param y координата Y
     */
    public Coordinates (Float x, double y) {
        this.x = x;
        this.y = y;
    }
}