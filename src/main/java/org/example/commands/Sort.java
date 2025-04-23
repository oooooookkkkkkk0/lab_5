package org.example.commands;

import org.example.models.Ticket;
import org.example.utils.*;
import java.util.Comparator;

/**
 * Команда для сортировки коллекции в естественном порядке (по возрастанию id).
 * Сортирует элементы коллекции по их уникальному идентификатору.
 */
public class Sort extends Command {
    private Console console;
    private CollectionManager collectionManager;

    /**
     * Создает команду сортировки коллекции.
     *
     * @param console консоль для вывода результатов и ошибок
     * @param collectionManager менеджер коллекции, содержащий данные для сортировки
     */
    public Sort(Console console, CollectionManager collectionManager) {
        super("sort", "отсортировать коллекцию в естественном порядке");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет сортировку коллекции по id в порядке возрастания.
     *
     * @param arguments аргументы команды (должны быть пустыми)
     * @throws IllegalArgumentException если указаны аргументы
     * @throws IllegalStateException если коллекция не инициализирована
     */
    @Override
    public void apply(String arguments) {
        try {
            // Проверка наличия аргументов
            if (!arguments.isEmpty()) {
                throw new IllegalArgumentException("команда не принимает аргументы");
            }

            // Проверка пустой коллекции
            if (collectionManager.getCollection().isEmpty()) {
                console.println("Коллекция пуста");
            }

            // Выполнение сортировки
            collectionManager.getCollection().sort(Comparator.comparing(Ticket::getId));
        } catch (IllegalStateException e) {
            console.println("тут ошибка");
        } catch (IllegalArgumentException e) {
            console.println("тут ошибка " + e.getMessage());
        }
    }
}

