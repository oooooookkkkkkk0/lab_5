package org.example.commands;

import org.example.utils.*;

/**
 * Команда для удаления первого элемента из коллекции.
 * Удаляет элемент с индексом 0, предварительно проверяя состояние коллекции.
 */
public class RemoveFirst extends Command {
    private Console console;
    private CollectionManager collectionManager;

    /**
     * Создает команду удаления первого элемента.
     *
     * @param console консоль для взаимодействия с пользователем
     * @param collectionManager менеджер коллекции для выполнения операции
     */
    public RemoveFirst(Console console, CollectionManager collectionManager) {
        super("remove_first", "удалить первый элемент из коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет первый элемент коллекции.
     *
     * @param arguments аргументы команды (должны быть пустыми)
     * @throws IllegalArgumentException если указаны аргументы команды
     * @throws IllegalStateException если:
     *         - коллекция не инициализирована
     *         - коллекция пуста
     * @throws IndexOutOfBoundsException если не удалось удалить элемент
     */
    @Override
    public void apply(String arguments) {
        try {
            if (!arguments.isEmpty()) {
                throw new IllegalArgumentException("коллекция не принимает аргументы");
            }

            if (collectionManager.getCollection() == null) {
                throw new IllegalStateException("коллекция не инициализирована");
            }

            if (collectionManager.getCollection().isEmpty()) {
                throw new IllegalStateException("коллекция пуста");
            }

            collectionManager.getCollection().remove(0);
            console.println("Ticket успешно удален");
        } catch (IllegalArgumentException | IllegalStateException e) {
            console.println("тут ошибка " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            console.println("не удалось удалить первый элемент");
        }
    }
}