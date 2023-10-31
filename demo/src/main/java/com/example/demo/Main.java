package com.example.demo;

import java.sql.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.101:4567/madang",
					"root", "1234");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Book");

			// scanner 선언
			Scanner scanner = new Scanner(System.in);

			System.out.println("원하는 기능을 선택하시오(1: 삽입, 2: 삭제, 3: 검색, 4: 프로그램 종료): ");

			int catalog = scanner.nextInt(); // 원하는 기능 번호 변수
			scanner.nextLine();

			while(catalog != 4) {

				if(catalog == 1) {
					System.out.println("삽입할 아이디, 이름, 출판사, 가격을 입력하시오: ");
					int bookidv = scanner.nextInt();
					scanner.nextLine();
					String booknamev = scanner.nextLine();
					String publisherv = scanner.nextLine();
					int pricev = scanner.nextInt();
					scanner.nextLine();
					String sqlI = "INSERT INTO Book(bookid, bookname, publisher, price) VALUES (" + bookidv + ", '" + booknamev + "', '" + publisherv + "', " + pricev + ");";
					stmt.executeUpdate(sqlI);
				}

				if(catalog == 2) {
					System.out.println("삭제할 아이디를 입력하시오: ");
					int deleteId = scanner.nextInt(); // 삭제할 번호에 대한 변수
					scanner.nextLine();
					String sqlD = "DELETE FROM Book WHERE bookid = '" + deleteId + "'";
					stmt.executeUpdate(sqlD);
				}

				if(catalog == 3) {
					System.out.println("검색 번호: ");
					int search = scanner.nextInt(); // 검색시 검색할 번호에 대한 변수
					while(rs.next()) {
						if(search == rs.getInt(1)) {
							System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
						}
					}
					scanner.nextLine();
				}

				con.close();

				System.out.println("원하는 기능을 선택하시오(1: 삽입, 2: 삭제, 3: 검색, 4: 프로그램 종료): ");
				con = DriverManager.getConnection("jdbc:mysql://192.168.56.101:4567/madang",
						"root", "1234");
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT * FROM Book");

				catalog = scanner.nextInt(); // 원하는 기능 번호 변수
				scanner.nextLine();
			}

			System.out.println("시스템이 종료되었습니다.");
		} catch(Exception e) { System.out.println(e);}
	}
}
