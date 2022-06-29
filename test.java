package javamysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test{
	
	public static void main(String[] args) {
		
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String URL = "jdbc:mysql://localhost:3306/jihanki";
		String USER = "root";
		String PASS = "";
		
		//変数の準備
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//SQL文の作成
		String sql = "select * from drink";
		
		try {
			//JDBCドライバのロード
			Class.forName(DRIVER);
			
			//データベース接続
			con = DriverManager.getConnection(URL, USER, PASS);
			
			// SQL実行準備
            stmt = con.prepareStatement(sql);
            
            // 実行結果取得
            rs = stmt.executeQuery();

      // データがなくなるまで(rs.next()がfalseになるまで)繰り返す
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String price = rs.getString("price");
                String stock = rs.getString("stock");

                System.out.println(id + ":" + name + "," + price + "\\," + stock);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBCドライバのロードでエラーが発生しました");
        } catch (SQLException e) {
            System.out.println("データベースへのアクセスでエラーが発生しました。");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("データベースへのアクセスでエラーが発生しました。");
            }
            
		}
		
	}
}