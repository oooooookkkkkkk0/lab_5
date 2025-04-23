package org.example.commands;

import org.example.models.*;
import org.example.utils.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Команда для вывода информации о местах проведения (Venue) всех элементов коллекции
 * в порядке возрастания их идентификаторов (id).
 */
public class PrintVenue extends Command {
    private Console console;
    private CollectionManager collectionManager;

    /**
     * Создает команду для вывода информации о местах проведения.
     *
     * @param console консоль для вывода информации
     * @param collectionManager менеджер коллекции, содержащий данные
     */
    public PrintVenue(Console console, CollectionManager collectionManager) {
        super("print_field_ascending_venue", "вывести значения поля venue всех элементов в порядке возрастания");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду, выводя информацию о местах проведения в порядке возрастания id.
     *
     * @param arguments аргументы команды (должны быть пустыми)
     * @throws IllegalArgumentException если указаны аргументы
     */
    @Override
    public void apply(String arguments) {
        try {
            if (!arguments.isEmpty()) {
                throw new Exception();
            }
            if (collectionManager.getCollection().isEmpty()) {
                console.println("коллекция пуста");
                return;
            }
            List<Venue> venues = new ArrayList<>();
            for (Ticket tickets : collectionManager.getCollection()) {
                Venue venue = tickets.getVenue();
                if (venue != null) {
                    venues.add(venue);
                }
            }

            Collections.sort(venues, new Comparator<Venue>() {
                @Override
                public int compare(Venue v1, Venue v2) {
                    return Integer.compare(v1.getId(), v2.getId());
                }
            });

            console.println("список venue: " + venues.size());
            for (Venue venue : venues) {
                printVenue(venue);
            }
        } catch (Exception e) {
            console.println("тут ошибка");
        }
    }

    /**
     * Выводит детальную информацию о месте проведения.
     *
     * @param venue место проведения для вывода информации
     */
    private void printVenue(Venue venue) {
        console.println("id: " + venue.getId());
        console.println("name: " + (venue.getName() != null ? venue.getName() : "не указан"));
        console.println("capacity: " + venue.getCapacity());
        console.println("type: " + (venue.getType() != null ? venue.getType() : "не указан"));
    }
}

