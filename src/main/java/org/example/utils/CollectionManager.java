package org.example.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.Getter;
import org.example.models.*;

/**
 * Менеджер коллекции, реализующий паттерн Singleton.
 * Обеспечивает загрузку, хранение и управление коллекцией билетов.
 */
public class CollectionManager {
    private static CollectionManager instance;
    private CollectionManager(){}

    /**
     * Возвращает экземпляр менеджера коллекции (реализация Singleton).
     * При первом вызове инициализирует коллекцию из файла.
     *
     * @return единственный экземпляр CollectionManager
     * @throws IllegalStateException если не удалось загрузить коллекцию
     */
    public static CollectionManager getInstance() {
        if (instance == null){
            try {
                instance = new CollectionManager();
                String filePath = System.getenv("lab5");
                if (filePath == null || filePath.isEmpty()) {
                    System.err.println("укажите переменную окружения lab5");
                    System.exit(1);
                }
                instance.filePath = filePath;
                instance.lastInitTime = LocalDateTime.now();
                instance.collection = (Vector<Ticket>) DumpManager.loadTicket(filePath);
                long maxid = 0;
                for (Ticket t : instance.collection){
                    if (t.getId() > maxid) {maxid = t.getId();}
                }
                IdGenerator.setIdInitial(maxid);

            } catch (UnrecognizedPropertyException e) {
                System.err.println("Дурачёк, в твоём файле написано что-то невалидное");
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Анлак");
                System.exit(1);
            }
        }
        return instance;
    }

    private String filePath;
    @Getter
    private LocalDateTime lastInitTime;
    @Getter
    private LocalDateTime lastSaveTime;
    @Getter
    private Vector<Ticket> collection = new Vector<>();
    private Map<Integer, Ticket> tickets = new HashMap<>();

    /**
     * Добавляет билет в коллекцию.
     *
     * @param ticket билет для добавления
     */

    public void add(Ticket ticket) {
        collection.add(ticket);
        tickets.put(ticket.getId(), ticket);
    }

    /**
     * Возвращает билет по его ID.
     *
     * @param id идентификатор билета
     * @return найденный билет или null если не найден
     */
    public Ticket byId (Integer id) {
        return tickets.get(id);
    }

    /**
     * Удаляет билет из коллекции по ID.
     *
     * @param id идентификатор билета для удаления
     */
    public void remove(Integer id) {
        collection.remove(byId(id));
        tickets.remove(id);
    }

    /**
     * Очищает коллекцию.
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Заменяет текущую коллекцию новой.
     *
     * @param newCol новая коллекция билетов
     */
    public void setCollection(Vector<Ticket> newCol){
        collection.clear();
        collection = newCol;
    }

    /**
     * Сохраняет коллекцию в файл.
     *
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public void save() throws IOException {
        Vector<Ticket> tempCollection = new Vector<>(collection);
        DumpManager.saveTicket(tempCollection, filePath);
        lastSaveTime = LocalDateTime.now();
    }
}