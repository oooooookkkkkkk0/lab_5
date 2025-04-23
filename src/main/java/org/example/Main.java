package org.example;

import org.example.commands.*;
import org.example.utils.*;
// TODO unkown command
// TODO add floatparse возращает java error
// TODO sort молчит
public class Main {
    public static void main(String[] args) {
        CollectionManager collectionManager = CollectionManager.getInstance();
        CommandManager commandManager = new CommandManager();
        Console console = new Console(commandManager);
        commandManager.add(new Add(console, collectionManager));
        commandManager.add(new Show(console, collectionManager));
        commandManager.add(new Help(console, commandManager));
        commandManager.add(new Remove(console, collectionManager));
        commandManager.add(new Clear(console, collectionManager));
        commandManager.add(new Save(console, collectionManager));
        commandManager.add(new RemoveFirst(console, collectionManager));
        commandManager.add(new Sort(console, collectionManager));
        commandManager.add(new CountType(console, collectionManager));
        commandManager.add(new PrintVenue(console, collectionManager));
        commandManager.add(new PrintPrice(console, collectionManager));
        commandManager.add(new Exit(console, commandManager));
        commandManager.add(new InsertAt(console, collectionManager));
        commandManager.add(new Update(console, collectionManager));
        commandManager.add(new ExecuteScript(console));
        commandManager.add(new Info(console, collectionManager));


        try {
            String[] userCommand;
            console.println("Введите команду --> ");
            while (true) {
                try {
                    userCommand = (console.readln().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                    Command command = commandManager.getCommands().get(userCommand[0]);
                    command.apply(userCommand[1]);
                } catch (ClassCastException | NullPointerException e) {
                    console.println(e);
                    console.println("такой команды нет(");
                }
            }
        } catch (Exception e) {
            console.println("Что-то пошло не так( пока-пока");
        }
    }
}