package org.example.commands;


import org.example.utils.*;

/**
 * Команда для добавления нового элемента в коллекцию.
 * Создает новый объект Ticket, запрашивая данные у пользователя в интерактивном режиме,
 * и добавляет его в управляемую коллекцию.
 */
public class Add extends Command {
    private Console console;
    private CollectionManager collectionManager;

    /**
     * Создает команду добавления с указанными консолью и менеджером коллекции.
     *
     * @param console консоль для взаимодействия с пользователем
     * @param collectionManager менеджер коллекции, который управляет операциями с коллекцией
     */
    public Add(Console console, CollectionManager collectionManager) {
        super("add", "добавляет новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду добавления, запрашивая данные билета у пользователя
     * и добавляя новый билет в коллекцию.
     *
     * @param arguments аргументы команды (для этой команды должны быть пустыми)
     * @throws IllegalArgumentException если указаны аргументы (команда не принимает аргументы)
     * @throws NullPointerException если указаны некорректные или пустые данные билета
     */
    @Override
    public void apply(String arguments) {
        try {
            if (!arguments.isEmpty()) {
                throw new IllegalArgumentException("не указаны данные элемента");
            }
            collectionManager.add(Ask.AskTicket(console));
            console.println("Ticket успешно добавлен");
        } catch(IllegalArgumentException e){
            console.println(e.getMessage());
        } catch (NullPointerException e) {
            console.println("неправильные данные элемента");
        }
    }
}
