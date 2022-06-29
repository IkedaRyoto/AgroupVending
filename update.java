package javamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class update{
	
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
		String sql = "update drink set stock = 10 where id = '001'";
		String sql2 = "select * from drink";
		
		try {
			//ドライバーの読み込み
			Class.forName(DRIVER);
			
			//データベースに接続
			con = DriverManager.getConnection(URL,USER,PASS);
			Statement smt = con.createStatement();
			smt.executeUpdate(sql);

			System.out.print("レコード変更しました\n");
			// SQL実行準備
			stmt = con.prepareStatement(sql2);

			// 実行結果取得
			rs = stmt.executeQuery();

			while (rs.next()) {
				//表示する値を取得
				String id = rs.getString("id");
			    String name = rs.getString("name");
			    String price = rs.getString("price");
			    String stock = rs.getString("stock");
			    //それぞれの値を表示
			    System.out.println(id + ":" + name + "," + price + "\\," + stock);
			    }
			    smt.close();
			    con.close();
			    }catch (Exception e) {
			    	
			    	System.out.println("JDBCデータベース接続エラー");
            }
		}
	}