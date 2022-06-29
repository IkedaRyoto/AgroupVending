package AllSum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AllSum {
	public static void main(String args[]) {
		//確認用int SUM[] = {10,20,34,40,50,11,22};
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root","");//データベースに接続
			Statement st = con.createStatement();
			String sql = "select * from manage.table;";
			ResultSet result = st.executeQuery(sql);//SQLの実行
			int STOCK, BUTTON;
			int[] SUM = new int[7];
			int count = 0;
			while(result.next()) {
				STOCK = result.getInt("STOCK");
				BUTTON = result.getInt("BUTTON");
				SUM[count] = result.getInt("SUM");
				
				System.out.print("STOCK: " + STOCK + ", ");
				System.out.print("BUTTON: " + BUTTON + ", ");
				System.out.println("SUM: " + SUM[count]);
				count++;
			}
			int allsum = 0;
			for(int i = 0; i < SUM.length; i++) {
				allsum = AllSum(allsum, SUM[i]);
			}
			System.out.println(allsum);
		}	
		catch (SQLException e) {
			System.out.println("接続に失敗しました。");
			e.printStackTrace();
		}
	}
	public static int AllSum(int allsum, int sum) {
		//int allsum = 0;
		allsum = allsum + sum;
		
		return allsum;
	}
}
