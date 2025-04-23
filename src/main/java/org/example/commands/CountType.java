package org.example.commands;


import org.example.models.TicketType;
import org.example.utils.*;

/**
 * Команда для подсчёта элементов коллекции, тип которых больше заданного.
 * Сравнение производится на основе порядка перечисления значений в TicketType.
 */
public class CountType extends Command {
    private Console console;
    private CollectionManager collectionManager;

    /**
     * Создает команду для подсчёта элементов по типу.
     *
     * @param console консоль для ввода-вывода
     * @param collectionManager менеджер коллекции для доступа к данным
     */
    public CountType(Console console, CollectionManager collectionManager) {
        super("count_greater_than_type", "вывести количество элементов, значение поля type которых больше заданного");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет подсчёт элементов, тип которых больше указанного.
     *
     * @param arguments строка с типом билета для сравнения (должна содержать одно из значений TicketType)
     * @throws IllegalArgumentException если не указан тип или коллекция не инициализирована
     */
    @Override
    public void apply(String arguments) {
        try {
            // Проверка наличия аргумента
            if (arguments.isEmpty()) {
                throw new IllegalArgumentException("не указан тип данных для сравнения");
            }

            // Проверка пустой коллекции
            if (collectionManager.getCollection().isEmpty()) {
                console.println("Коллекция пуста");
                return;
            }

            // Парсинг типа билета
            TicketType inputType;
            try {
                inputType = TicketType.valueOf(arguments.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                console.println("такого типа билета нет(");
                return;
            }

            // Подсчёт элементов
            long count = collectionManager.getCollection().stream()
                    .filter(ticket -> ticket.getType() != null)
                    .filter(ticket -> ticket.getType().ordinal() > inputType.ordinal())
                    .count();
            console.println("количество элементов в коллекции " + inputType + ": " + count);

        } catch (IllegalArgumentException e) {
            console.println("тут ошибка " + e.getMessage());
        } catch (Exception e) {
            console.println("тут ошибка ");
        }
    }
}

