package org.example.commands;


import org.example.utils.*;

/**
 * Команда для очистки коллекции.
 * Удаляет все элементы из текущей коллекции, оставляя её пустой.
 * Перед очисткой проверяет состояние коллекции.
 */
public class Clear extends Command {
    private Console console;
    private CollectionManager collectionManager;

    /**
     * Создает команду очистки с указанными консолью и менеджером коллекции.
     *
     * @param console консоль для вывода информации и ошибок
     * @param collectionManager менеджер коллекции, который управляет операциями с коллекцией
     */
    public Clear (Console console, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет очистку коллекции.
     * Проверяет:
     * - отсутствие аргументов команды
     * - инициализацию коллекции
     * - наличие элементов в коллекции
     *
     * @param arguments аргументы команды (должны быть пустыми)
     * @throws IllegalArgumentException если:
     *         - переданы аргументы команды
     *         - коллекция не инициализирована
     * @throws IllegalStateException если коллекция уже пуста
     * @throws NullPointerException если коллекция не существует
     */
    @Override
    public void apply(String arguments) {
        try {
            if (!arguments.isEmpty()) {
                throw new IllegalArgumentException("не принимает аргументы");
            }

            if (collectionManager.getCollection() == null) {
                throw new IllegalArgumentException("коллекция не инициализирована");
            }
            if (collectionManager.getCollection().isEmpty()) {
                throw new IllegalStateException("коллекция пуста(");
            }

            collectionManager.clear();
            console.println("коллекция очищена");

        } catch (IllegalArgumentException | IllegalStateException e) {
            console.println("тут ошибка " + e.getMessage());
        } catch (NullPointerException e) {
            console.println("коллекции не существует");
        }
    }
}
