import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class DBqueries {

    public static void Export1() {
        String csvFilePath = "result1.csv";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library",
                "user", "password");) {
            String sql = "SELECT a.login AS reader_name, c.name AS book_name, \n" +
                    "b.returndate AS return_date \n" +
                    "FROM users AS a JOIN orders AS b ON a.id = b.reader_id\n" +
                    "JOIN books AS c ON b.book_id = c.id ";

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

            // write header line containing column names
            fileWriter.write("\"reader_name\",\"book_name\",\"return_date\"");

            while (result.next()) {
                String reader_name = result.getString("reader_name");
                String book_name = result.getString("book_name");
                Date return_date = result.getDate("return_date");


                String line = String.format("\"%s\",\"%s\",\"%s\"",
                        reader_name, book_name, return_date);

                fileWriter.newLine();
                fileWriter.write(line);
            }

            statement.close();
            fileWriter.close();
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
        ComparatorCSVs.compareCSVs("result1.csv", "expected_result1.csv", 1);
    }

    public static void Export2() {
        String csvFilePath = "result2.csv";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library",
                "user", "password")) {
            String sql = "SELECT reader_name AS name, COUNT(f.reader_name) AS orders FROM \n" +
                    "(SELECT a.login AS reader_name, c.name AS book_name, \n" +
                    "b.returndate AS return_date \n" +
                    "FROM users AS a JOIN orders AS b ON a.id = b.reader_id\n" +
                    "JOIN books AS c ON b.book_id = c.id) AS f \n" +
                    "GROUP BY reader_name\n" +
                    "ORDER BY name";

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

            // write header line containing column names
            fileWriter.write("name,orders");

            while (result.next()) {
                String name=result.getString("name");
                int orders = result.getInt("orders");

                String line = String.format("%s,%d",
                        name, orders);

                fileWriter.newLine();
                fileWriter.write(line);
            }

            statement.close();
            fileWriter.close();

        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
        ComparatorCSVs.compareCSVs("result2.csv", "expected_result2.csv", 2);
    }

}
