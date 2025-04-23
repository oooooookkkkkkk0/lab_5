package org.example.commands;

import org.example.models.Ticket;
import org.example.utils.*;
import java.util.NoSuchElementException;
import static java.lang.Integer.parseInt;

/**
 * Команда для удаления элемента коллекции по указанному идентификатору.
 * Проверяет валидность ID и существование элемента перед удалением.
 */
public class Remove extends Command {
    private Console console;
    private CollectionManager collectionManager;

    /**
     * Создает команду для удаления элемента по ID.
     *
     * @param console консоль для ввода-вывода сообщений
     * @param collectionManager менеджер коллекции, содержащий данные
     */
    public Remove(Console console, CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет элемент коллекции с указанным ID.
     *
     * @param arguments строка, содержащая ID элемента для удаления
     * @throws IllegalArgumentException если:
     *         - не указан ID
     *         - ID не является положительным числом
     * @throws NumberFormatException если ID не является числом
     * @throws NoSuchElementException если элемент с указанным ID не найден
     * @throws RuntimeException если произошла ошибка при удалении элемента
     */
    @Override
    public void apply(String arguments) {
        try {
            if (arguments.isEmpty()) {
                throw new IllegalArgumentException("не указан id");
            }
            int id = parseInt(arguments);
            if (id <= 0) {
                throw new IllegalArgumentException("у билетов не может быть отрицательного id(((");

            }

            Ticket ticket = collectionManager.byId(id);
            if (ticket == null) {
                throw new NoSuchElementException("нет билета с id " + id);
            }

            collectionManager.remove(id);
            if (collectionManager.byId(id) != null) {
                throw new RuntimeException("ошибка при удалении");
            }
            console.println("Ticket успешно удален");
        } catch (NumberFormatException e) {
            console.println("id должен быть целым числом");
        } catch (NoSuchElementException e) {
            console.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            console.println("тут ошибка " + e.getMessage());
        } catch (Exception e) {
            console.println("тут ошибка");
        }
    }
}
