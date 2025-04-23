package org.example.commands;

import org.example.models.Ticket;
import org.example.utils.*;
import java.util.NoSuchElementException;
import static java.lang.Integer.parseInt;

/**
 * Команда для обновления элемента коллекции по заданному ID.
 * Запрашивает новые данные для элемента и заменяет старый элемент новым с сохранением ID.
 */
public class Update extends Command {
    private Console console;
    private CollectionManager collectionManager;

    /**
     * Создает команду обновления элемента.
     *
     * @param console консоль для ввода-вывода данных
     * @param collectionManager менеджер коллекции для выполнения операций
     */
    public Update(Console console, CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Обновляет элемент коллекции по указанному ID.
     *
     * @param arguments строка, содержащая ID элемента для обновления
     * @throws IllegalArgumentException если:
     *         - не указан ID
     *         - введены некорректные данные
     * @throws NumberFormatException если ID не является числом
     * @throws NoSuchElementException если элемент с указанным ID не найден
     */
    @Override
    public void apply(String arguments) {
        try {
            // Проверка наличия аргументов
            if (arguments.isEmpty()) {
                throw new IllegalArgumentException("не указан id и данные элемента");
            }

            // Парсинг ID
            String[] args = arguments.split(" ", 1);
            if (args.length < 1) {
                throw new IllegalArgumentException("ты не ввел id");
            }

            int id = parseInt(args[0]);
            Ticket oldTicket = collectionManager.byId(id);

            // Проверка существования элемента
            if (oldTicket == null) {
                throw new NoSuchElementException("элемента с таким id нет");
            }

            // Запрос новых данных
            console.println("введи новые данные");
            Ticket newTicket = Ask.AskTicket(console);

            // Сохранение даты создания оригинального элемента
            newTicket.getCreationDate();

            // Обновление элемента
            collectionManager.remove(id);
            collectionManager.add(newTicket);

            console.println("элемент с id " + id + " обнoвлен");

        } catch (NumberFormatException e) {
            console.println("id должен быть целым");
        } catch (NoSuchElementException | IllegalArgumentException e) {
            console.println("тут ошибка " + e.getMessage());
        }
    }
}

