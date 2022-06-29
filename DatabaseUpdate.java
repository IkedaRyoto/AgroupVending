//package Vending_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseUpdate {
	public static void main(final String[] args){
		 
		//manageテーブル
        Connection con = null;
        PreparedStatement pstmt = null;
 
        // JDBCの各設定
        // ※jdbc:mysql://localhost/データベース名
        // ※?以降はMySQLの設定により必要ないです。SSLを無効、タイムゾーンをUTCにしています。
        String url  = "jdbc:mysql://localhost:3306/manage";
        String user = "root";        // ユーザー名
        String pass = "";  // パスワード
 
        try {
            // データベースに接続
            con = DriverManager.getConnection(url,user,pass);
            
            int button = 4;
            int sumbottle = 2;
            int sum = 200;
            int stock = 18;
            // プリペアドステートメント(パラメータ付きSQL)を作成
            // ※プリペアドステートメントは「SQLインジェクション」の対策です。
            String sql = String.format("UPDATE manage SET SUMBOTTLE = ?, SUM = ?, STOCK = ? WHERE BUTTON = %d",button);
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, sumbottle);
            pstmt.setInt(2, sum);
            pstmt.setInt(3, stock);
 
            // トランザクションの開始
            con.setAutoCommit(false);
            try {
                // SQLの実行(INSERT文、UPDATE文、DELETE文でも可能)
                int count = pstmt.executeUpdate();
 
                System.out.println(count + "件のデータを更新しました。");
 
                // コミット
                con.commit();
            }catch (Exception e) {
 
                // ロールバック
                con.rollback();
 
                System.out.println("データの更新に失敗しました。");
            }
          } catch (Exception e) {
              System.out.println(e.getMessage());
          }finally {
              // 各オブジェクトを解放する
              if(pstmt != null) {
                  try {
                      pstmt.close();
                  }catch (Exception e) {
                      System.out.println(e.getMessage());
                }
              }
 
              if(con != null) {
                  try {
                      con.close();
                  }catch (Exception e) {
                      System.out.println(e.getMessage());
                }
              }
          }
        
        	//moneyテーブル
      		Connection con2 = null;
      	    PreparedStatement pstmt2 = null;
      	
      	    // JDBCの各設定
      	    // ※jdbc:mysql://localhost/データベース名
      	    // ※?以降はMySQLの設定により必要ないです。SSLを無効、タイムゾーンをUTCにしています。
      	    String url2  = "jdbc:mysql://localhost:3306/money";
      	    String user2 = "root";        // ユーザー名
      	    String pass2 = "";  // パスワード
      	
      	    try {
      	        // データベースに接続
      	        con2 = DriverManager.getConnection(url2,user2,pass2);
      	        
      	        int money = 1000;
      	        int num = 20;
      	        // プリペアドステートメント(パラメータ付きSQL)を作成
      	        // ※プリペアドステートメントは「SQLインジェクション」の対策です。
      	        String sql2 = String.format("UPDATE money SET NUM = ? WHERE MONEY = %d",money);
      	        pstmt2 = con2.prepareStatement(sql2);
      	        pstmt2.setInt(1, num);
      	
      	        // トランザクションの開始
      	        con2.setAutoCommit(false);
      	        try {
      	            // SQLの実行(INSERT文、UPDATE文、DELETE文でも可能)
      	            int count = pstmt2.executeUpdate();
      	
      	            System.out.println(count + "件のデータを更新しました。");
      	
      	            // コミット
      	            con2.commit();
      	        }catch (Exception e) {
      	
      	            // ロールバック
      	            con2.rollback();
      	
      	            System.out.println("データの更新に失敗しました。");
      	        }
      	      } catch (Exception e) {
      	          System.out.println(e.getMessage());
      	      }finally {
      	          // 各オブジェクトを解放する
      	          if(pstmt2 != null) {
      	              try {
      	                  pstmt2.close();
      	              }catch (Exception e) {
      	                  System.out.println(e.getMessage());
      	            }
      	          }
      	
      	          if(con2 != null) {
      	              try {
      	                  con2.close();
      	              }catch (Exception e) {
      	                  System.out.println(e.getMessage());
      	            }
      	          }
      	      }
	}
}