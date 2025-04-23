package org.example.commands;

import org.example.utils.*;

/**
 * Команда для сохранения текущего состояния коллекции в файл.
 * Вызывает соответствующий метод менеджера коллекции для выполнения сохранения.
 */
public class Save extends Command {
    private Console console;
    private CollectionManager collectionManager;

    /**
     * Создает команду сохранения коллекции.
     *
     * @param console консоль для вывода результатов операции
     * @param collectionManager менеджер коллекции, содержащий логику сохранения
     */
    public Save(Console console, CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет сохранение коллекции в файл.
     *
     * @param arguments аргументы команды (не используются)
     * @throws Exception если произошла ошибка при сохранении
     */
    @Override
    public void apply(String arguments) {
        try {
            collectionManager.save();
            console.println("коллекция сохранена в файл");
        } catch (Exception e) {
            console.println("тут ошибка");
        }
    }
}

