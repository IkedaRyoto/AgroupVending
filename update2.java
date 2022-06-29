package javamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class update2{
	
	public static void main(String[] args) {
		
		
		//ドライバー、URL、ユーザー名、パスワードの入力
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String URL = "jdbc:mysql://localhost:3306/jihanki";
		String USER = "root";
		String PASS = "";
		
		//変数の準備
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//SQL文の作成
		String sql = "update drink set stock = stock - 1 where button = ''";
		String sql2 = "select * from drink";
		try {
			//ドライバーの読み込み
			Class.forName(DRIVER);
			
			//データベースに接続
			con = DriverManager.getConnection(URL,USER,PASS);

			// SQL実行準備
			stmt = con.prepareStatement(sql2);

			// 実行結果取得
			rs = stmt.executeQuery();

			//商品リストを表示
			System.out.println("    商品名 価格 在庫");
			while (rs.next()) {
				//表示する値を取得
				String button = rs.getString("button");
			    String name = rs.getString("name");
			    String price = rs.getString("price");
			    String stock = rs.getString("stock");
			    //それぞれの値を表示
			    System.out.println(String.format("%s : %6s %s\\ %s", button, name, price, stock));
			    }
			    con.close();
			    }catch (Exception e) {
			    	
			    	System.out.println("JDBCデータベース接続エラー");
            }
		//購入する商品のボタン番号を入力
		System.out.println("ボタン番号を入力してください");
		Scanner scanner = new Scanner(System.in);
		String botan = scanner.next();
		//System.out.println(botan);
		StringBuilder sql3 = new StringBuilder(sql);
		sql3.insert(51,botan);
		scanner.close();
		
		try {
			//ドライバーの読み込み
			Class.forName(DRIVER);
			
			//データベースに接続
			con = DriverManager.getConnection(URL,USER,PASS);
			Statement smt = con.createStatement();
			smt.executeUpdate(sql3.toString());

			//Sysyem.out.println("レコードを更新しました。");
			System.out.println("\n--------------------");
			System.out.print("商品を購入します。\n");
			// SQL実行準備
			stmt = con.prepareStatement(sql2);

			// 実行結果取得
			rs = stmt.executeQuery();

			//商品リストを表示
			System.out.println("    商品名 価格 在庫");
			while (rs.next()) {
				//表示する値を取得
				String button = rs.getString("button");
			    String name = rs.getString("name");
			    String price = rs.getString("price");
			    String stock = rs.getString("stock");
			    //商品リストを表示
			    System.out.println(String.format("%s : %6s %s\\ %s", button, name, price, stock));
			    }
			    smt.close();
			    con.close();
			    }catch (Exception e) {
			    	
			    	System.out.println("JDBCデータベース接続エラー");
            }
		}
	}