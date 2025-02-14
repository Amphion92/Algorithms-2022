package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {  //O(N^3)
        File file = new File(inputName);
        try {
            Scanner scanner = new Scanner(file);
            List<String> list = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(line);
            }
            List<Integer> secondsList = new ArrayList<>();
            for (String str : list) {
                if (str.contains("AM")) {
                    str = str.replace(" AM", "");
                    String[] params = str.split(":");
                    int hours = Integer.parseInt(params[0]);
                    if (hours == 12) {
                        hours = 0;
                    }
                    int seconds = hours * 3600 + Integer.parseInt(params[1]) * 60 +
                            Integer.parseInt(params[2]);
                    secondsList.add(seconds);
                }
                if (str.contains("PM")) {
                    str = str.replace(" PM", "");
                    String[] params = str.split(":");
                    int hours = Integer.parseInt(params[0]);
                    if (hours == 12) {
                        int seconds = (Integer.parseInt(params[0])) * 3600 + Integer.parseInt(params[1]) * 60 +
                                Integer.parseInt(params[2]);
                        secondsList.add(seconds);
                    } else {
                        int seconds = (Integer.parseInt(params[0]) + 12) * 3600 + Integer.parseInt(params[1]) * 60 +
                                Integer.parseInt(params[2]);
                        secondsList.add(seconds);
                    }
                }
            }
            Collections.sort(secondsList);
            List<String> result = new ArrayList<>();
            for (Integer second : secondsList) {
                int hour = second / 3600;
                int min = second / 60 % 60;
                int sec = second % 60;
                if (hour >= 12){
                    if (hour > 12) {
                        result.add(prepareTime(hour-12) + ":" + prepareTime(min) + ":" + prepareTime(sec) + " PM" );
                    } else {
                        result.add(prepareTime(hour) + ":" + prepareTime(min) + ":" + prepareTime(sec) + " PM" );
                    }
                }
                else if (hour == 0){
                    result.add(prepareTime(hour + 12) + ":" + prepareTime(min) + ":" + prepareTime(sec) + " AM" );
                } else {
                    result.add(prepareTime(hour) + ":" + prepareTime(min) + ":" + prepareTime(sec) + " AM" );
                }
            }
            FileWriter fileWriter = new FileWriter(outputName);
            for(String str: result){
                fileWriter.write(str + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String prepareTime(int time) {
        if (time < 10) {
            return "0" + time;
        }
        else return String.valueOf(time);
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) { //O(N)
        File file = new File(inputName);
        try {
            Scanner scanner = new Scanner(file);
            List<Float> list = new ArrayList<>();
            while (scanner.hasNextLine()) {
                list.add(Float.valueOf(scanner.nextLine()));
            }
            Collections.sort(list);
            FileWriter fileWriter = new FileWriter(outputName);
            for(Float number: list){
                fileWriter.write(number + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
