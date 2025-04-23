package org.example.commands;

/**
 * Абстрактный базовый класс для всех команд.
 * Определяет общую структуру и поведение команд, включая имя,
 * описание и метод выполнения.
 */
public abstract class Command {
    private final String name;
    private final String description;

    /**
     * Создает новую команду с указанным именем и описанием.
     *
     * @param name название команды
     * @param description краткое описание назначения команды
     */
    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Возвращает название команды.
     *
     * @return название команды
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает описание команды.
     *
     * @return описание назначения команды
     */
    public String getDescription() {
        return description;
    }

    /**
     * Абстрактный метод выполнения команды.
     * Должен быть реализован в конкретных классах-наследниках.
     *
     * @param arguments аргументы команды (могут быть пустыми)
     */
    public abstract void apply(String arguments);
}
