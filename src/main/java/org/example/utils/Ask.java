package org.example.utils;

import org.example.models.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

/**
 * Утилитарный класс для интерактивного создания объектов через консоль.
 * Содержит методы для запроса данных у пользователя и создания объектов моделей.
 */
public class Ask {
    /**
     * Создает новый объект Ticket, запрашивая данные у пользователя через консоль.
     *
     * @param console консоль для ввода/вывода
     * @return новый объект Ticket
     */
    public static Ticket AskTicket(Console console) {
        long id = IdGenerator.getNextId();

        String name = AskName(console);

        Coordinates coordinates = AskCoordinates(console);

        Venue venue = AskVenue(console);

        TicketType ticketType = AskTicketType(console);

        int price = AskPrice(console);

        console.println("Введите refundable");
        boolean refundable = parseBoolean(console.readln());

        LocalDateTime creationDate = AskLocalDate(console);

        return new Ticket(parseInt(String.valueOf(id)), name, coordinates, creationDate, price, refundable, ticketType, venue);
    }

    /**
     * Запрашивает координаты у пользователя.
     *
     * @param console консоль для ввода/вывода
     * @return объект Coordinates
     */
    private static Coordinates AskCoordinates(Console console) {
        while (true) {
            try {
                console.println("Введите координаты");

                console.println("Введите x:");
                Float x = parseFloat(console.readln());
                if (x >= 839){
                    throw new IllegalArgumentException("x должно быть <= 839");
                }

                console.println("Введите y:");
                double y = parseDouble(console.readln());

                return new Coordinates(x, y);
            } catch (NumberFormatException e) {
                console.println("тут ошибка");
            } catch (IllegalArgumentException e){
                console.println("тут ошибка " + e.getMessage());
            }
        }
    }

    /**
     * Запрашивает название места проведения.
     *
     * @param console консоль для ввода/вывода
     * @return название места
     */
    private static String AskVenueName(Console console) {
        while (true) {
            console.println("введите имя");
            try {
                String name = console.readln();
                if (name.isEmpty()) {
                    throw new Exception();
                }
                if (!name.matches("^[a-zA-Zа-яА-Я\\s]+$")){
                    throw new IllegalArgumentException("у name тип string");
                }
                return name;
            } catch (Exception e) {
                console.println("тут ошибка");
            }
        }
    }

    /**
     * Запрашивает вместимость места проведения.
     *
     * @param console консоль для ввода/вывода
     * @return строка с числом вместимости
     */
    private static String AskVenueCapacity(Console console) {
        while (true) {
            console.println("введите capacity");
            try {
                String capacity = console.readln();
                if (capacity.isEmpty()) {
                    throw new Exception();
                }

                long capacityNew = Long.parseLong(capacity);
                if (capacityNew < 0) {
                    throw new IllegalArgumentException("тут должно быть положительное число");
                }
                return capacity;
            } catch (Exception e) {
                console.println("тут ошибка");
            }
        }
    }

    /**
     * Запрашивает тип места проведения.
     *
     * @param console консоль для ввода/вывода
     * @return строковое представление типа
     */
    private static String AskVenueType(Console console) {
        while (true) {
            try {
                console.println("введите тип: BAR, LOFT, THEATRE, MALL, STADIUM");
                String input = console.readln().trim().toUpperCase();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("тут не должно быть пусто(");
                }

                for (VenueType type : VenueType.values()) {
                    if (type.name().equals(input)) {
                        return input;
                    }
                }

                throw new IllegalArgumentException("такого типа быть не может(");

            } catch (IllegalArgumentException e) {
                String validTypes = Arrays.stream(VenueType.values())
                        .map(Enum::name)
                        .collect(Collectors.joining(", "));
                console.println("тут ошибка( Допустимые значения: " + validTypes);
            }
        }
    }

    /**
     * Создает объект Venue, запрашивая данные у пользователя.
     *
     * @param console консоль для ввода/вывода
     * @return объект Venue
     */
    private static Venue AskVenue(Console console) {
        while (true) {
            console.println("Введите venue");
            long id = IdGenerator.getNextId();
            String name = AskVenueName(console);
            String capacity = AskVenueCapacity(console);
            String type = AskVenueType(console);
            return new Venue(parseInt(String.valueOf(id)), name, parseLong(capacity), VenueType.valueOf(type));
        }
    }

    /**
     * Запрашивает название билета.
     *
     * @param console консоль для ввода/вывода
     * @return название билета
     */
    private static String AskName(Console console) {
        while (true) {
            console.println("Введите name");
            try {
                String name = console.readln();
                if (name.isEmpty()) {
                    throw new Exception();
                }
                return name;
            } catch (Exception e) {
                console.println("введи строку");
            }
        }
    }

    /**
     * Запрашивает тип билета.
     *
     * @param console консоль для ввода/вывода
     * @return тип билета
     */
    private static TicketType AskTicketType(Console console) {
        while (true) {
            console.println("Введите ticketType: VIP, USUAL, BUDGETARY, CHEAP");
            try {
                TicketType ticketType = TicketType.valueOf(console.readln().trim().toUpperCase());
                if (ticketType.equals(null)) {
                    throw new Exception();
                }
                return ticketType;
            } catch (Exception e) {
                console.println("введи нужный тип");
            }
        }
    }

    /**
     * Запрашивает цену билета.
     *
     * @param console консоль для ввода/вывода
     * @return цена билета
     */
    private static int AskPrice(Console console) {
        while (true) {
            console.println("Введите price");
            try {
                int price = parseInt(console.readln());
                if (price <= 0) {
                    throw new Exception();
                }
                return price;
            } catch (Exception e) {
                console.println("введи число");
            }
        }
    }

    /**
     * Создает текущую дату и время.
     *
     * @param console консоль для ввода/вывода
     * @return текущая дата и время
     */
    private static LocalDateTime AskLocalDate (Console console) {
        while (true) {
            LocalDateTime creationDate = LocalDateTime.now();
            try {
                if (creationDate.equals(null)) {
                    throw new Exception();
                }
                return creationDate;
            } catch (Exception e) {
                console.println("тут должна быть дата");
            }
        }
    }
}
