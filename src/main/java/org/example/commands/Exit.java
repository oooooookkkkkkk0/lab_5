package org.example.commands;


import org.example.utils.*;

/**
 * Команда для завершения работы программы.
 * Перед выходом запрашивает подтверждение у пользователя.
 * Завершает работу без сохранения данных.
 */
public class Exit extends Command {
    private Console console;
    private CommandManager commandManager;

    /**
     * Создает команду выхода из программы.
     *
     * @param console консоль для взаимодействия с пользователем
     * @param commandManager менеджер команд для управления выполнением программы
     */
    public Exit (Console console, CommandManager commandManager) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.console = console;
        this.commandManager = commandManager;
    }

    /**
     * Выполняет команду выхода с подтверждением.
     *
     * @param arguments аргументы команды (должны быть пустыми)
     * @throws IllegalArgumentException если указаны аргументы команды
     */
    @Override
    public void apply(String arguments) {
        try {
            if (!arguments.isEmpty()) {
                throw new Exception();
            }
            console.println("ты точно хочешь закончить?");
            String confirmation = console.readln().trim().toLowerCase();

            if (confirmation.equals("да") || confirmation.equals("yes")) {
                console.println("завершение программы");
                commandManager.stopExecution();
                System.exit(0);
            } else {
                console.println("продолжаем");
            }
        } catch (Exception e) {
            console.println("тут ошибка");
            System.exit(1);
        }
    }
}

