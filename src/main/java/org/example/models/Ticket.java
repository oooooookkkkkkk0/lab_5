package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий билет.
 * Содержит информацию о билете с различными ограничениями на поля.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int price; //Значение поля должно быть больше 0
    private boolean refundable;
    private TicketType type; //Поле не может быть null
    private Venue venue;//Поле может быть null
}