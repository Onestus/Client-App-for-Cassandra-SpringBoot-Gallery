import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.json.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(
                "Что Вы хотите сделать?\n" +
                        "1.Просмотреть таблицу\n" +
                        "2.Записать в таблицу\n" +
                        "3.Удалить из таблицы\n" +
                        "4.Поиск в таблице\n");

        String point = keyboard.nextLine();
        switch(point) {
            case "1":
                PrintTable();
                break;
            case "2":
                WriteInTable();
                break;
            case "3":
                DeleteFromTable();
                break;
            case "4":
                SearchInTable();
                break;
            default:
                System.out.println("Такого варианта не существует.");
                break;
        }
    }

    public static void PrintTable() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Какую таблицу необходимо просмотреть?");
        System.out.println(
                "\n 1.Painting" +
                        "\n 2.Artist" +
                        "\n 3.Exhibition" +
                        "\n 4.Collection" +
                        "\n 5.Employee\n");

        String table = keyboard.nextLine();
        switch (table) {
            case "1":
                get_table("http://localhost:8080/api/paintings");
                break;
            case "2":
                get_table("http://localhost:8080/api/artists");
                break;
            case "3":
                get_table("http://localhost:8080/api/exhibitions");
                break;
            case "4":
                get_table("http://localhost:8080/api/collections");
                break;
            case "5":
                get_table("http://localhost:8080/api/employees");
                break;
            default:
                System.out.println("Такой таблицы не существует");
        }
    }

    public static void WriteInTable() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("В какую таблицу необходимо произвести запись?");
        System.out.println(
                "\n 1.Painting" +
                        "\n 2.Artist" +
                        "\n 3.Exhibition" +
                        "\n 4.Collection" +
                        "\n 5.Employee\n");

        String table = keyboard.nextLine();
        switch (table) {
            case "1":
                post("http://localhost:8080/api/paintings", uploadPaintings());
                break;
            case "2":
                post("http://localhost:8080/api/artists", uploadArtists());
                break;
            case "3":
                post("http://localhost:8080/api/exhibitions", uploadExhibitions());
                break;
            case "4":
                post("http://localhost:8080/api/collections", uploadCollections());
                break;
            case "5":
                post("http://localhost:8080/api/employees", uploadEmployees());
                break;
            default:
                System.out.println("Такой таблицы не существует");
        }
    }

    public static void DeleteFromTable() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Из какой таблицы необходимо удалить запись?");
        System.out.println(
                "\n 1.Painting" +
                        "\n 2.Artist" +
                        "\n 3.Exhibition" +
                        "\n 4.Collection" +
                        "\n 5.Employee\n");

        String table = keyboard.nextLine();
        switch (table) {
            case "1":
                delete(deletePaintings());
                break;
            case "2":
                delete(deleteArtists());
                break;
            case "3":
                delete(deleteExhibitions());
                break;
            case "4":
                delete(deleteCollections());
                break;
            case "5":
                delete(deleteEmployee());
                break;
            default:
                System.out.println("Такой таблицы не существует");
        }
    }
    public static void SearchInTable() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("В какой таблице вы хотите провести поиск?");
        System.out.println(
                "\n 1.Painting" +
                "\n 2.Artist" +
                "\n 3.Exhibition" +
                "\n 4.Collection" +
                "\n 5.Employee\n");

        String table = keyboard.nextLine();
        switch (table) {
            case "1":
                get_element(searchPaintings());
                break;
            case "2":
                get_element(searchArtists());
                break;
            case "3":
                get_element(searchExhibitions());
                break;
            case "4":
                get_element(searchCollections());
                break;
            case "5":
                get_element(searchEmployees());
                break;
            default:
                System.out.println("Такой таблицы не существует");
        }
    }

    public static String uploadPaintings(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Введите ID картины");
        String id = keyboard.nextLine();
        System.out.println("Введите название картины");
        String name = keyboard.nextLine();
        System.out.println("Введите год написания картины");
        String year = keyboard.nextLine();
        System.out.println("Введите описание кариины");
        String description = keyboard.nextLine();

        String updatedPainting = "{\n   \"id\":"+id+",\n    \"name\": \""+name+"\",\n    \"year\": "+year+",\n    \"description\": \""+description+"\"\n}";
        // System.out.println(updatedPainting);
        return updatedPainting;
    }
    public static String uploadArtists(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Введите ID художника");
        String id = keyboard.nextLine();
        System.out.println("Введите имя художника");
        String name = keyboard.nextLine();
        System.out.println("Введите годы жизни");
        String yearsofliving = keyboard.nextLine();
        System.out.println("Введите названия картин");
        String paintings = keyboard.nextLine();
        System.out.println("Введите биографию художника");
        String biography = keyboard.nextLine();

        String updatedArtist = "{\n \"id\":"+id+",\n    \"name\": \""+name+"\",\n    \"yearsofliving\": \""+yearsofliving+"\",\n    \"paintings\": \""+paintings+"\",\n    \"biography\": \""+biography+"\"\n}";
        // System.out.println(updatedArtist);
        return updatedArtist;
    }

    public static String uploadExhibitions(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Введите ID выставки");
        String id = keyboard.nextLine();
        System.out.println("Введите название выставки");
        String name = keyboard.nextLine();
        System.out.println("Введите место проведения");
        String location = keyboard.nextLine();
        System.out.println("Введите даты проведения");
        String dates = keyboard.nextLine();
        System.out.println("Введите тематику выставки");
        String thematics = keyboard.nextLine();

        String updatedExhibition = "{\n    \"id\":"+id+",\n    \"name\": \""+name+"\",\n    \"location\": \""+location+"\",\n    \"dates\": \""+dates+"\",\n    \"thematics\": \""+thematics+"\"\n}" ;
        // System.out.println(updatedExhibition);
        return updatedExhibition;
    }

    public static String uploadCollections(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Введите ID коллекции");
        String id = keyboard.nextLine();
        System.out.println("Введите название коллекции");
        String name = keyboard.nextLine();
        System.out.println("Введите предметы коллекции");
        String items = keyboard.nextLine();
        System.out.println("Введите имена авторов");
        String authors = keyboard.nextLine();

        String updatedCollection = "{\n    \"id\":"+id+",\n    \"name\": \""+name+"\",\n    \"items\": \""+items+"\",\n    \"authors\": \""+authors+"\"\n}";
        System.out.println(updatedCollection);
        return updatedCollection;
    }

    public static String uploadEmployees(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Введите ID работника");
        String id = keyboard.nextLine();
        System.out.println("Введите имя работника");
        String name = keyboard.nextLine();
        System.out.println("Введите фамилию");
        String surname = keyboard.nextLine();
        System.out.println("Введите должность");
        String job = keyboard.nextLine();

        String updatedEmployee = "{\n    \"id\":"+id+",\n    \"name\": \""+name+"\",\n    \"surname\": \""+surname+"\",\n    \"job\": \""+job+"\"\n}";
        // System.out.println(updatedEmployee);
        return updatedEmployee;
    }

    public static String deletePaintings() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Введите ID картины, подлежащей удалению");
        String paintingId = keyboard.nextLine();

        String deletingPainting = String.format("http://localhost:8080/api/paintings/%s", paintingId);
        return deletingPainting;
    }

    public static String deleteArtists() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Введите ID художника, подлежащего удалению");
        String artistId = keyboard.nextLine();

        String deletingArtist=String.format("http://localhost:8080/api/artists/%s", artistId);
        return deletingArtist;
    }

    public static String deleteExhibitions() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Введите ID выставки, подлежащей удалению");
        String exhibitionId = keyboard.nextLine();

        String deletingExhibition = String.format("http://localhost:8080/api/exhibitions/%s", exhibitionId);
        return deletingExhibition;
    }

    public static String deleteCollections() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Введите ID коллекции, подлежащей удалению");
        String collectionId = keyboard.nextLine();

        String deletingCollection = String.format("http://localhost:8080/api/collections/%s", collectionId);
        return deletingCollection;
    }

    public static String deleteEmployee() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Введите ID работника, подлежащего удалению");
        String employeeId = keyboard.nextLine();

        String deletingEmployee = String.format("http://localhost:8080/api/employees/%s", employeeId);
        return deletingEmployee;
    }

    public static String searchPaintings() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Ведите ID картины");
        String paintingId = keyboard.nextLine();
        String url = String.format("http://localhost:8080/api/paintings/%s", paintingId);
        return url;
    }

    public static String searchArtists() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Ведите ID художника");
        String artistId = keyboard.nextLine();
        String url = String.format("http://localhost:8080/api/artists/%s", artistId);
        return url;
    }

    public static String searchExhibitions() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Ведите ID выставки");
        String exhibitionId = keyboard.nextLine();
        String url = String.format("http://localhost:8080/api/exhibitions/%s", exhibitionId);
        return url;
    }

    public static String searchCollections() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Ведите ID коллекции");
        String collectionId = keyboard.nextLine();
        String url = String.format("http://localhost:8080/api/collections/%s", collectionId);
        return url;
    }

    public static String searchEmployees() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Ведите ID работника");
        String employeeId = keyboard.nextLine();
        String url = String.format("http://localhost:8080/api/employees/%s", employeeId);
        return url;
    }

    public static void delete(String StringURL) throws IOException{
        URL url = new URL(StringURL);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("DELETE");
        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
        }
        http.disconnect();
    }

    public static void post(String StringURL, String req) throws IOException{
        URL url = new URL(StringURL);
        String requestBody = req;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        byte[] requestBodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);
        connection.getOutputStream().write(requestBodyBytes);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = in.readLine();
            System.out.println("Response from server: " + response);
            in.close();
        } else {
            System.out.println("Error: " + responseCode);
        }
    }

    public static void get_table(String StringURL) throws IOException {
        URL url = new URL(StringURL);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("GET");
        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            printJsonTable(response.toString());
        }

        http.disconnect();
    }

    public static void get_element(String StringURL) throws IOException {
        URL url = new URL(StringURL);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("GET");
        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            printJsonTable("["+ response.toString() + "]");
        }

        http.disconnect();
    }

    public static void printJsonTable(String jsonString) {
        // Парсим строку JSON
        JSONArray jsonArray = new JSONArray(jsonString);

        // Получаем массив ключей из первого объекта JSON
        JSONObject firstObj = jsonArray.getJSONObject(0);
        String[] keys = firstObj.keySet().toArray(new String[firstObj.length()]);

        // Выводим заголовок таблицы
        for (String key : keys) {
            System.out.print(key + "\t");
        }
        System.out.println();

        // Выводим строки таблицы
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            for (String key : keys) {
                System.out.print(obj.get(key) + "\t");
            }
            System.out.println();
        }
    }
}