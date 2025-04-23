package org.example.commands;

import org.example.models.Ticket;
import org.example.utils.*;
import java.util.Vector;

/**
 * Команда для вставки нового элемента в указанную позицию коллекции.
 * Позиция должна быть в пределах от 0 до размера коллекции.
 */
public class InsertAt extends Command {
    private Console console;
    private CollectionManager collectionManager;

    /**
     * Создает команду для вставки элемента.
     *
     * @param console консоль для ввода-вывода
     * @param collectionManager менеджер коллекции
     */
    public InsertAt(Console console, CollectionManager collectionManager) {
        super("insert_at", "добавить новый элемент в заданную позицию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет вставку элемента в указанную позицию коллекции.
     *
     * @param arguments строка, содержащая индекс позиции (должна быть целым числом)
     * @throws Exception если произошла ошибка при выполнении команды
     */
    @Override
    public void apply(String arguments) {
        try {
            int index = parseIndex(arguments);
            console.println(index);
            if (index == -1) {
                console.println("такого индекса нет");
                return;
            }
            Ticket newTicket = parseTicketFromString();;

            var collection = collectionManager.getCollection();
            Vector<Ticket> newColl = new Vector<>();
            for (int i = 0; i < collection.size(); i ++) {
                if (i == index) {
                    newColl.add(newTicket);
                }
                newColl.add(collection.get(i));
            }
            collectionManager.setCollection(newColl);
            console.println("элемент успешно добавлен на позицию " + index);
        } catch (Exception e) {
            console.println("тут ошибка");
        }
    }

    /**
     * Парсит строковый индекс в числовой и проверяет его валидность.
     *
     * @param indexStr строка с индексом
     * @return числовой индекс или -1 если индекс невалиден
     */
    private int parseIndex(String indexStr) {
        try {
            int index = Integer.parseInt(indexStr);
            if (index < 0 || index > collectionManager.getCollection().size()) {
                throw new IllegalArgumentException("неверный индекс");
            }
            return index;
        } catch (Exception e) {
            console.println("тут ошибка");
        }
        return -1;
    }

    /**
     * Создает новый объект Ticket через интерактивный ввод.
     *
     * @return новый объект Ticket
     */
    private Ticket parseTicketFromString() {
        return Ask.AskTicket(console);
    }
}


