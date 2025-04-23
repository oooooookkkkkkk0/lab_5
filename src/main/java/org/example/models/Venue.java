package org.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий место проведения мероприятия.
 * Содержит информацию о площадке с различными ограничениями на поля.
 */
@Data
@NoArgsConstructor
public class Venue {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long capacity; //Значение поля должно быть больше 0
    private VenueType type; //Поле не может быть null

    /**
     * Конструктор класса Venue
     * @param id уникальный идентификатор места
     * @param name название места
     * @param capacity вместимость места
     * @param type тип места
     */
    public Venue(Integer id, String name, long capacity, VenueType type) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }
}