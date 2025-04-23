package org.example.commands;

import org.example.utils.*;

/**
 * Команда для вывода информации о текущей коллекции.
 * Отображает тип коллекции, количество элементов и даты последних операций.
 */
public class Info extends Command{
    private final Console console;
    private final CollectionManager collectionManager;

    /**
     * Создает команду для вывода информации о коллекции.
     *
     * @param console консоль для вывода информации
     * @param collectionManager менеджер коллекции, содержащий данные
     */
    public Info(Console console, CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции.");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит информацию о коллекции.
     * Формат вывода:
     * - Тип коллекции
     * - Количество элементов
     * - Дата последнего сохранения
     * - Дата последней инициализации
     *
     * @param arguments аргументы команды (не должны содержать значений)
     * @throws IllegalArgumentException если указаны аргументы
     */
    @Override
    public void apply(String arguments) {
        try {
            if (!arguments.isEmpty()) throw new Exception();

            String lastSaveDate;
            String lastInitTime;
            if (collectionManager.getLastInitTime() == null) { lastInitTime = "в данной сессии инициализации еще не происходило";}
            else { lastInitTime = collectionManager.getLastInitTime().toString();}
            if (collectionManager.getLastSaveTime() == null) { lastSaveDate = "в данной сессии сохранения еще не происходило";}
            else { lastSaveDate = collectionManager.getLastSaveTime().toString();}

            String s="Сведения о коллекции:\n";
            s+=" Тип: " + collectionManager.getCollection().getClass()+"\n";
            s+=" Количество элементов: " + collectionManager.getCollection().size()+"\n";
            s+=" Дата последнего сохранения: " + lastSaveDate+"\n";
            s+=" Дата последней инициализации: " + lastInitTime;
            console.println(s);
        } catch (Exception e) {console.println("тут ошибка");}
    }
}
