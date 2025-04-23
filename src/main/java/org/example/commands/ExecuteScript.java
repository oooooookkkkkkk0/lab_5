package org.example.commands;


import org.example.utils.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Команда для выполнения скрипта из файла.
 * Читает команды из указанного файла и выполняет их последовательно.
 * Обеспечивает защиту от рекурсивного вызова скриптов.
 */
public class ExecuteScript extends Command {
    private Console console;
    private static int MAX_SCRIPT_DEPTH = 3;
    private static int recursionLevel = 0;

    /**
     * Создает команду выполнения скрипта.
     *
     * @param console консоль для ввода-вывода и выполнения команд
     */
    public ExecuteScript(Console console) {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.console = console;
    }

    /**
     * Выполняет скрипт из указанного файла.
     *
     * @param arguments путь к файлу скрипта
     * @throws IllegalArgumentException если:
     *         - не указан путь к файлу
     *         - превышена максимальная глубина рекурсии
     * @throws SecurityException если нет прав доступа к файлу
     */
    @Override
    public void apply(String arguments) {
        try {
            // Проверка наличия аргумента
            if (arguments == null || arguments.trim().isEmpty()) {
                throw new IllegalArgumentException("тут пусто(");
            }

            // Проверка глубины рекурсии
            if (recursionLevel >= MAX_SCRIPT_DEPTH) {
                throw new IllegalArgumentException("слишком большая глубина");
            }
            File scriptFile = new File(arguments.trim());
            if (!validateFile(scriptFile)) return;

            recursionLevel++;

            try (Scanner fileScanner = new Scanner(scriptFile)) {
                console.setScanner(fileScanner);
                processScriptCommands(fileScanner);
            } catch (FileNotFoundException e) {
                console.println("файл не найден" + e.getMessage());
            }
        } finally {
            // Гарантированно уменьшаем уровень рекурсии
            recursionLevel--;
        }
    }

    /**
     * Проверяет валидность файла скрипта.
     *
     * @param file файл для проверки
     * @return true если файл валиден, false в противном случае
     */
    private boolean validateFile(File file) {
        if (!file.exists()) {
            console.println("файл не существует" + file.getAbsolutePath());
            return false;
        }
        if (file.isDirectory()) {
            console.println("это директория" + file.getAbsolutePath());
            return false;
        }
        if (!file.canRead()) {
            console.println("нет прав" + file.getAbsolutePath());
            return false;
        }
        return true;
    }

    /**
     * Обрабатывает команды из скрипта.
     *
     * @param scanner сканер для чтения файла
     */
    private void processScriptCommands(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String commandLine = scanner.nextLine().trim();
            if (commandLine.startsWith("execute_script")) {
                console.println("рекурсия запрещена");
                continue;
            }
            console.executeCommand(commandLine);
        }
    }

}

