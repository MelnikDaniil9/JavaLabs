package org.dstu.db;

import org.dstu.util.CsvReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DbWorker {
    public static void populateFromFile(String fileName) {
        List<String[]> strings = CsvReader.readCsvFile(fileName, ";");
        Connection conn = DbConnection.getConnection();
        try {
            Statement cleaner = conn.createStatement();
            System.out.println(cleaner.executeUpdate("DELETE FROM book"));
            System.out.println(cleaner.executeUpdate("DELETE FROM journal"));
            PreparedStatement studentSt = conn.prepareStatement(
                    "INSERT INTO book (title, author, year_of_creation, genre, isbn, lang) " +
                            "VALUES (?, ?, ?, ?, ?, ?)");
            PreparedStatement teacherSt = conn.prepareStatement(
                    "INSERT INTO journal (title, author, year_of_creation, number, editor_name, topic) " +
                            "VALUES (?, ?, ?, ?, ?, ?)");

            for (String[] line: strings) {
                if (line[0].equals("0")) {
                    studentSt.setString(1, line[1]);
                    studentSt.setString(2, line[2]);
                    studentSt.setInt(3, Integer.parseInt(line[3]));
                    studentSt.setString(4, line[4]);
                    studentSt.setString(5, line[5]);
                    studentSt.setString(6, line[6]);
                    studentSt.addBatch();
                } else {
                    teacherSt.setString(1, line[1]);
                    teacherSt.setString(2, line[2]);
                    teacherSt.setInt(3, Integer.parseInt(line[3]));
                    teacherSt.setInt(4, Integer.parseInt(line[4]));
                    teacherSt.setString(5, line[5]);
                    teacherSt.setString(6, line[6]);
                    teacherSt.addBatch();
                }
            }
            int[] stRes = studentSt.executeBatch();
            int[] teacherRes = teacherSt.executeBatch();
            for (int num: stRes) {
                System.out.println(num);
            }

            for (int num: teacherRes) {
                System.out.println(num);
            }
            cleaner.close();
            studentSt.close();
            teacherSt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void demoQuery() {
        Connection conn = DbConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM book WHERE lang = 'русский'");
            while (rs.next()) {
                System.out.print(rs.getString("title"));
                System.out.print(" ");
                System.out.print(rs.getString("genre"));
                System.out.print(" ");
                System.out.println(rs.getString("author"));
            }
            rs.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void dirtyReadDemo() {
        Runnable first = () -> {
            Connection conn1 = DbConnection.getNewConnection();
            if (conn1 != null) {
                try {
                    conn1.setAutoCommit(false);
                    conn1.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                    Statement upd = conn1.createStatement();
                    upd.executeUpdate("UPDATE journal SET number=2 WHERE number=1");
                    Thread.sleep(2000);
                    conn1.rollback();
                    upd.close();
                    Statement st = conn1.createStatement();
                    System.out.println("In the first thread:");
                    ResultSet rs = st.executeQuery("SELECT * FROM journal");
                    while (rs.next()) {
                        System.out.println(rs.getString("number"));
                    }
                    st.close();
                    rs.close();
                    conn1.close();
                } catch (SQLException | InterruptedException throwables) {
                    throwables.printStackTrace();
                }
            }
        };

        Runnable second = () -> {
            Connection conn2 = DbConnection.getNewConnection();
            if (conn2 != null) {
                try {
                    Thread.sleep(500);
                    conn2.setAutoCommit(false);
                    conn2.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                    Statement st = conn2.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM journal");
                    while (rs.next()) {
                        System.out.println(rs.getString("number"));
                    }
                    rs.close();
                    st.close();
                    conn2.close();
                } catch (SQLException | InterruptedException throwables) {
                    throwables.printStackTrace();
                }
            }
        };
        Thread th1 = new Thread(first);
        Thread th2 = new Thread(second);
        th1.start();
        th2.start();
    }
}
