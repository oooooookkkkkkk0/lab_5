package org.example.commands;

import org.example.models.Ticket;
import org.example.utils.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Команда для вывода значений поля price всех элементов коллекции в порядке убывания.
 * Позволяет просмотреть все цены билетов из коллекции в отсортированном виде.
 */
public class PrintPrice extends Command {
    private Console console;
    private CollectionManager collectionManager;

    /**
     * Конструктор команды PrintPrice.
     *
     * @param console консоль для вывода результатов
     * @param collectionManager менеджер коллекции, содержащий данные
     */
    public PrintPrice (Console console, CollectionManager collectionManager) {
        super("print_field_descending_price", "вывести значения поля price всех элементов в порядке убывания");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду, выводя цены билетов в порядке убывания.
     *
     * @param arguments аргументы команды (должны быть пустыми)
     * @throws Exception если произошла ошибка при выполнении команды
     */
    @Override
    public void apply(String arguments) {
        try {
            if (!arguments.isEmpty()){
                throw new Exception();
            }

            if (collectionManager.getCollection().isEmpty()) {
                console.println("коллекция пуста");
                return;
            }
            List<Integer> prices = new ArrayList<>();
            for (Ticket ticket: collectionManager.getCollection()) {
                prices.add(ticket.getPrice());
            }

            prices.sort(Collections.reverseOrder());

            console.println("цены в порядке убывания");
            for (int price : prices) {
                console.println(price);
            }
        } catch (Exception e) {
            console.println("тут ошибка");
        }
    }
}

