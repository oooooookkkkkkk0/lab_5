package org.example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.models.Ticket;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

/**
 * Класс для работы с сериализацией и десериализацией коллекции билетов в JSON.
 * Обеспечивает сохранение и загрузку данных из файла.
 */
public class DumpManager {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // Регистрируем модуль для поддержки LocalDateTime и других классов Java 8 даты/времени
        mapper.registerModule(new JavaTimeModule());
        // Форматирование вывода для красоты
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }

//     /**
//     * Сохраняет коллекцию билетов в JSON-файл.
//     *
//     * @param tickets коллекция билетов для сохранения
//     * @param filePath путь к файлу для сохранения
//     * @throws IOException если произошла ошибка ввода-вывода
//     * @throws IllegalArgumentException если filePath некорректен или tickets равен null
//     */
//    public static void saveTicket(Collection<Ticket> tickets, String filePath) throws IOException {
//        Objects.requireNonNull(tickets, "тут пусто");
//        Objects.requireNonNull(filePath, "тут пусто");
//
//        // Валидация входных параметров
//        if (filePath.trim().isEmpty()) {
//            throw new IllegalArgumentException("тут пусто");
//        }
//
//        Path path = Paths.get(filePath);
//        if (Files.isDirectory(path)) {
//            throw new IllegalArgumentException("нужен путь к файлу");
//        }
//
//        // Проверка прав на запись
//        if (Files.exists(path) && !Files.isWritable(path)) {
//            throw new IOException("нет прав");
//        }
//
//        Path parentDir = path.getParent();
//        if (parentDir != null && !Files.exists(parentDir)) {
//            throw new IOException("такого не существует");
//        }
//        if (parentDir != null && !Files.isWritable(parentDir)) {
//            throw new IOException("нет прав");
//        }
//
//        mapper.writeValue(path.toFile(), tickets);
//    }
//
//    /**
//     * Загружает коллекцию билетов из JSON-файла.
//     *
//     * @param filePath путь к файлу для загрузки
//     * @return коллекция билетов (пустая, если файл пустой)
//     * @throws IOException если произошла ошибка ввода-вывода
//     * @throws IllegalArgumentException если filePath некорректен
//     */
//    public static List<Ticket> loadTicket(String filePath) throws IOException {
//        Objects.requireNonNull(filePath);
//
//        // Валидация входных параметров
//        if (filePath.trim().isEmpty()) {
//            throw new IllegalArgumentException("тут пусто");
//        }
//
//        Path path = Paths.get(filePath);
//
//        if(!Files.exists(path)) {
//            throw new IOException("нет прав");
//        }
//
//        File file = path.toFile();
//        if (file.length() > 0) {
//            return mapper.readValue(file, new TypeReference<Vector<Ticket>>() {});
//        }
//        return new Vector<>();
//    }

    /**
     * Сохраняет коллекцию билетов в JSON-файл.
     *
     * @param tickets коллекция билетов для сохранения
     * @param filePath путь к файлу для сохранения
     * @throws IOException если произошла ошибка ввода-вывода
     * @throws IllegalArgumentException если filePath некорректен
     */
    public static void saveTicket(Collection<Ticket> tickets, String filePath) throws IOException {
        mapper.writeValue(new File(filePath), tickets);
    }

    /**
     * Загружает коллекцию билетов из JSON-файла.
     *
     * @param filePath путь к файлу для загрузки
     * @return коллекция билетов (пустая, если файл пустой)
     * @throws IOException если произошла ошибка ввода-вывода
     * @throws IllegalArgumentException если filePath некорректен
     */
    public static List<Ticket> loadTicket(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.length() > 0)
            return mapper.readValue(file, new TypeReference<Vector<Ticket>>() {
            });
        return new Vector<>();
    }


}
