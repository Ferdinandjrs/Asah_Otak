package asah.otak;

import java.sql.*;
import java.util.Scanner;

public class AsahOtakGame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/database_asah_otak";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "HSDgrc55##";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            playGame(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void playGame(Connection conn) throws SQLException {

        String query = "SELECT id, kata, clue FROM master_kata ORDER BY RAND() LIMIT 1";
        String kata = "";
        String clue = "";
        int kataId = 0;

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                kataId = rs.getInt("id");
                kata = rs.getString("kata");
                clue = rs.getString("clue");
                System.out.println("Clue: " + clue);
            }
        }


        int kataLength = kata.length();
        char[] userInput = new char[kataLength];

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < kataLength; i++) {
            if (i == 2 || i == 6) {
                userInput[i] = kata.charAt(i);
                System.out.println("Huruf ke-" + (i + 1) + " adalah: " + kata.charAt(i));
            } else {
                System.out.print("Huruf ke-" + (i + 1) + ": ");
                userInput[i] = scanner.next().charAt(0);
            }
        }


        int totalScore = 0;
        for (int i = 0; i < kataLength; i++) {
            if (i == 2 || i == 6) {
                continue; // Skip clue letters
            }

            if (userInput[i] == kata.charAt(i)) {
                totalScore += 10;
            } else {
                totalScore -= 2;
            }
        }

        System.out.println("Poin yang Anda dapat adalah: " + totalScore);


        System.out.println("Pilih aksi: ");
        System.out.println("1. Simpan Poin");
        System.out.println("2. Ulangi");
        int pilihan = scanner.nextInt();

        if (pilihan == 1) {
            saveScore(conn, totalScore);
        } else {
            playGame(conn);
        }
    }

    public static void saveScore(Connection conn, int totalScore) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama Anda: ");
        String namaUser = scanner.nextLine();

        String insertQuery = "INSERT INTO point_game (nama_user, total_point) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            pstmt.setString(1, namaUser);
            pstmt.setInt(2, totalScore);
            pstmt.executeUpdate();
        }

        System.out.println("Poin Anda telah disimpan. Permainan dimulai lagi!");
        playGame(conn);
    }
}

