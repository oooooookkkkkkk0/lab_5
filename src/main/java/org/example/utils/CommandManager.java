package org.example.utils;

import lombok.Getter;
import org.example.commands.Command;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Менеджер команд для управления и хранения доступных команд.
 * Реализует механизм регистрации команд и управления их выполнением.
 */
public class CommandManager {
    /**
     * Коллекция зарегистрированных команд, где ключ - имя команды,
     * значение - объект команды.
     */
    @Getter
    private Map<String, Command> commands = new LinkedHashMap<>();

    /**
     * Добавляет новую команду в менеджер.
     *
     * @param command команда для добавления
     */
    public void add(Command command) {
        commands.put(command.getName(), command);
    }

    private boolean isRunning = true;

    /**
     * Останавливает выполнение команд.
     */
    public void stopExecution() {
        this.isRunning = false;
    }
}
