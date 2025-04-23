package org.example.commands;

import org.example.utils.*;
import java.util.Map;

/**
 * Команда для вывода справки о доступных командах.
 * Отображает список всех зарегистрированных команд с их описанием.
 */
public class Help extends Command {
    private Console console;
    private CommandManager commandManager;

    /**
     * Создает команду вывода справки.
     *
     * @param console консоль для вывода информации
     * @param commandManager менеджер команд, содержащий список доступных команд
     */
    public Help (Console console, CommandManager commandManager) {
        super("help", "выводит справку по достпуным командам");
        this.console = console;
        this.commandManager = commandManager;
    }

    /**
     * Выводит список всех доступных команд с их описанием.
     * Формат вывода: "имя_команды - описание_команды"
     *
     * @param arguments аргументы команды (игнорируются)
     */
    @Override
    public void apply(String arguments) {
        Map<String, Command> map = commandManager.getCommands();
        for (Command command: map.values()) {
            console.println(command.getName() + "-" + command.getDescription());
        }
    }
}
