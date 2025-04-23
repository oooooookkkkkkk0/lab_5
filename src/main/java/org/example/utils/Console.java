package org.example.utils;

import org.example.commands.Command;
import org.example.commands.ExecuteScript;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Класс для работы с консольным вводом/выводом и выполнения команд.
 * Поддерживает как интерактивный режим, так и выполнение скриптов.
 */
public class Console {
    private final CommandManager commandManager; // Добавляем поле
    private Scanner currentScanner;
    private Scanner defaultScanner;
    private Scanner fileScanner;
    private boolean interactiveMode = true;

    /**
     * Создает новый экземпляр консоли.
     *
     * @param commandManager менеджер команд для выполнения
     */
    public Console(CommandManager commandManager) {
        this.commandManager = commandManager;
        this.defaultScanner = new Scanner(System.in);
        this.currentScanner = defaultScanner;
    }

    /**
     * Читает строку ввода.
     *
     * @return введенная строка
     */
    public String readln() {
        if (fileScanner != null) {
            try {
                if (fileScanner.hasNext()) return fileScanner.nextLine();
            } catch (Exception e) {fileScanner = null;}
            fileScanner = null;
        }
        return defaultScanner.nextLine();
    }

    /**
     * Выводит объект в консоль с переводом строки.
     *
     * @param obj объект для вывода
     */
    public void println(Object obj) {
        System.out.println(obj);
    }

    /**
     * Устанавливает сканер для чтения из файла.
     *
     * @param scanner сканер для файла
     * @throws FileNotFoundException если файл не найден
     */
    public void setScanner(Scanner scanner) throws FileNotFoundException {
        fileScanner = scanner;
    }

    /**
     * Возвращает текущий файловый сканер.
     *
     * @return текущий файловый сканер или null
     */
    public Scanner getScanner() {
        return fileScanner;
    }

    /**
     * Выполняет команду из строки.
     *
     * @param commandLine строка с командой и аргументами
     */
    public void executeCommand(String commandLine) {
        String[] parts = commandLine.split("\\s+", 2);
        String commandName = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";
        Command command = (Command) commandManager.getCommands().get(commandName);
        if (command == null) {
            println("Команда '" + commandName + "' не найдена");
            return;
        }
        if (command instanceof ExecuteScript) {
            handleScriptCommand((ExecuteScript) command, arguments);
            return;
        }
        try {
            command.apply(arguments);
        } catch (Exception e) {
            println("Ошибка выполнения команды");
        }
    }

    /**
     * Обрабатывает команду выполнения скрипта.
     *
     * @param command команда ExecuteScript
     * @param scriptPath путь к скрипту
     */
    private void handleScriptCommand(ExecuteScript command, String scriptPath) {
        boolean isScriptMode = false;
        if (isScriptMode) {
            println("Рекурсивные скрипты запрещены!");
            return;
        }

        command.apply(scriptPath);
    }
}