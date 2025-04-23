package org.example.commands;

import org.example.models.Ticket;
import org.example.utils.*;

/**
 * Команда для вывода всех элементов коллекции в строковом представлении.
 * Отображает каждый элемент коллекции с помощью его метода toString().
 */
public class Show extends Command {
    private Console console;
    private CollectionManager collectionManager;

    /**
     * Создает команду для отображения коллекции.
     *
     * @param console консоль для вывода элементов коллекции
     * @param collectionManager менеджер коллекции, содержащий данные для отображения
     */
    public Show (Console console, CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит все элементы коллекции.
     *
     * @param arguments аргументы команды (должны быть пустыми)
     * @throws IllegalArgumentException если указаны аргументы
     * @throws IllegalStateException если коллекция пуста
     */
    @Override
    public void apply(String arguments) {
        try {
            if (!arguments.isEmpty()) {
                throw new Exception();
            }

            if (collectionManager.getCollection().isEmpty()) {
                throw new Exception();
            }
            for (Ticket ticket: collectionManager.getCollection()) {
                console.println(ticket.toString());
            }

        } catch (Exception e) {
            console.println("Коллекция пуста");
        }
    }
}

