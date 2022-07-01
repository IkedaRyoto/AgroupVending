import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class main {

	public static void main(String[] args) throws IOException{
		// TODO 自動生成されたメソッド・スタブ
		Purchaser pur = new Purchaser();
		Vendingmachine ven = new Vendingmachine();
		//Databasesyutoku data = new Databasesyutoku();	
		//ドライバー、URL、ユーザー名、パスワードの入力
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String URL = "jdbc:mysql://localhost:3306/vendingmachaine";
		String USER = "root";
		String PASS = "";
		// 変数の準備
		int allsum = 0;
		//各テーブルを配列に入れるための初期化
		//productテーブル
		String ID[] = new String[7];
		String NAME[] = new String[7];
		int VALUE[] = new int[7];
		int BUTTON[] = new int[7];
		//manageテーブル
		//int manage_buttonSQL[] = new int[50];
		int SUMBOTTLE[] = new int[7];
		int SUM[] = new int[7];
		int STOCK[] = new int[7];
		//moneyテーブル
		int MONEY[] = new int[5];
		int NUM[] = new int[5];
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null,rs2 = null,rs3 = null,rs4 = null;
        
        int i = 0;
        try {
        	//データベースの参照
        	Class.forName(DRIVER);
            con = DriverManager.getConnection(URL,USER,PASS);
            // SQLを実行1//SQL分の実行1(productテーブルを呼び出す)
            String sql1 = "SELECT * from product";
            stmt = con.prepareStatement(sql1);
            rs1 = stmt.executeQuery();   
            // 結果行をループ
            i = 0;
            while(rs1.next()){
                // レコードの値
            	ID[i] = rs1.getString("id");
            	NAME[i] = rs1.getString("name");
            	VALUE[i]= rs1.getInt("value");
            	BUTTON[i]= rs1.getInt("button");
                i++;
            }
            //SQL分の実行2(manageテーブルを呼び出す)
            String sql2 = "SELECT * from manage";
            stmt = con.prepareStatement(sql2);
            rs2 = stmt.executeQuery();
            i  = 0;
            // 結果行をループ
            while(rs2.next()){
                // レコードの値
            	BUTTON[i] = rs2.getInt("button");
            	SUMBOTTLE[i] = rs2.getInt("sumbottle");
            	SUM[i]= rs2.getInt("sum");
            	STOCK[i]= rs2.getInt("stock");
                i++;
            }
            //SQL分の実行3(moneyテーブルを呼び出す)
            String sql3 = "SELECT * from money";
            stmt = con.prepareStatement(sql3);
            rs3 = stmt.executeQuery();
            i  = 0;
            // 結果行をループ
            while(rs3.next()){
                // レコードの値
            	MONEY[i] = rs3.getInt("money");
            	NUM[i]= rs3.getInt("num");
                i++;
            }
            //入力される値の初期化
            int money = 0;
    		int button = 0;
    		boolean use_check = false;
    		//投入金額合計の初期化
    		int sum = 0;
    		//自販機内総売り上げ金額
    		boolean summax;
    		boolean judge_product[],nochange[];
    		boolean canchange[] = new boolean[VALUE.length];		
    		
    		//購入可能商品判定
    		judge_product = ven.productjudgeMethod(STOCK);
    		//お釣り切れの場合
    		nochange = ven.NoChangeMethod(NUM,MONEY);
    		
    		//金額入力フェーズ
    		do {
    			//金額入力メソッド
    			money = pur.InputMethod();
    			//金額入力メソッド
    			use_check = ven.checkuseMethod(money);
    			//管理者モード判定
    			if(money == -1) {//-1なら管理者モードへ移行
    				//自販機内総売り上げ計算メソッド
        			allsum = ven.AllSumMethod(allsum,SUM);
    				System.out.println("管理者モードです。");
    				//商品、売上本数、売上金額、商品在庫在庫を表示
    				System.out.format("%9s: %s: %5s: %5s","商品"," 売上本数" ,"売上金額", "商品在庫");
    				System.out.println();
    				for( i=0;i<STOCK.length;i++) {
    	    			System.out.format("%11s: %9d: %9d: %9d",NAME[i],SUMBOTTLE[i],SUM[i],STOCK[i]);
    	    			System.out.println();
    	    		}
    				System.out.println();
    				//自販機内の総売上金額を表示
    				System.out.println("総売上金額："+allsum+"円");
    				System.out.println();
    				//金額、金額在庫を表示
    				System.out.format("%3s: %s","金額","金額在庫");
    				System.out.println();
    				for( i=0;i<MONEY.length;i++) {
    					System.out.format("%5d: %8d",MONEY[i],NUM[i]);
    					System.out.println();
    	    		}
    				//管理者モードの場合、全ての投入された金額を返金する
    				sum = 0;
    				System.out.println("投入されたお金は返金しました。");
    				break;
    			
    			}else if(use_check == true){//入力された金額が使用可能なら
    				//投入金額の枚数カウントメソッド
    				NUM = ven.CalcNumMethod(NUM,money);	
    				//投入金額合計の計算
    				sum += money;
    				//投入合計金額が上限を超えていないかの判定メソッド
    				summax = ven.SumMaxMethod(sum);
    				if(summax == false) {//上限金額を超えていなければ購入可能商品を表示する
    					//投入金額に対して商品購入後、お釣りが返金できるかの判定
    					for (int m = 0; m < BUTTON.length; m++) {
    						canchange[m] = ven.CanChangeMethod(sum, NUM, VALUE, m);
    					}
    					//購入可能商品表示
    					ven.CanBuyMethod(sum,NAME,VALUE,STOCK,BUTTON, canchange);
    				}
    				else {//現状の在庫金額でお釣りが返せない場合
    					NUM = ven.ChangeMethod(sum,NUM,VALUE,-1);
    					sum = 0;
    					System.out.println("全額返金しました。");
    					continue;
    				}
    			}
    		
    		}while(money != 0);
    		//ボタン選択フェーズに行く前に管理者モードに入っていたかのチェック
    		if(money == -1) {//-1ならばボタン選択フェーズは行わない
    			
    		}else {
    			//ボタン選択フェーズ
    			//ボタン入力メソッド
    			button = pur.InputButtonMethod(judge_product , canchange);
    			
        		if(button == -1) {
        			System.out.println("返却レバーが押されました。返却金額は"+sum+"です。");
        			sum = 0;

        			System.out.println("全額返金しました。");
        		}else if(button >= 1 && button <= 7){
        			System.out.println(NAME[button - 1]+"を購入しました。");
        			//お釣りの枚数計算
        			NUM = ven.ChangeMethod(sum,NUM,VALUE,button);
        			//商品在庫減算
        			STOCK = ven.CalcStockMethod(button,STOCK);
        			//合計売り上げ本数の加算
        			SUMBOTTLE = ven.SumBottle(button,STOCK,SUMBOTTLE);
        			//各商品の売り上げ
        			SUM = ven.Sum(button,SUM,VALUE);
        			// SQLを実行4
        			//SQL分の実行4(manageテーブルをアップデート)
        			String sql4 = "update manage set sumbottle = ?,sum = ?,stock = ? where button = ?";
            		for( i=0;i<BUTTON.length;i++) {
        				stmt = con.prepareStatement(sql4);
        				stmt.setInt(1,SUMBOTTLE[i]);
        				stmt.setInt(2,SUM[i]);
        				stmt.setInt(3,STOCK[i]);
        				stmt.setInt(4,BUTTON[i]);
        				stmt.executeUpdate();
            		}
            		// SQLを実行5
        			//SQL分の実行5(moneyテーブルをアップデート)
            		String sql5 = "update money set num = ? where  money = ?";
            		for( i=0;i<NUM.length;i++) {
        				stmt = con.prepareStatement(sql5);
        				stmt.setInt(1,NUM[i]);
        				stmt.setInt(2,MONEY[i]);
        				stmt.executeUpdate();
            		}
        		}
        		stmt = con.prepareStatement(sql2);      
                rs2 = stmt.executeQuery();
                i  = 0;
                // 結果行をループ
                while(rs2.next()){//全ての項目に対してアップデートを入れる
                    // レコードの値
                	BUTTON[i] = rs2.getInt("button");
                	SUMBOTTLE[i] = rs2.getInt("sumbottle");
                	SUM[i]= rs2.getInt("sum");
                	STOCK[i]= rs2.getInt("stock");
                    i++;
                }
                stmt = con.prepareStatement(sql3);  
                rs3 = stmt.executeQuery();
                i  = 0;
                // 結果行をループ
                while(rs3.next()){//全ての項目に対してアップデートを入れる
                    // レコードの値
                	MONEY[i] = rs3.getInt("money");
                	NUM[i]= rs3.getInt("num");
                    i++;
                }
    		}
            con.close();
	    }catch (Exception e) {
	    	System.out.println("JDBCデータベース接続エラー");
	    }
        
	
	}

}
