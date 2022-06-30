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
		
		String ID[] = new String[7];
		String NAME[] = new String[7];
		int VALUE[] = new int[7];
		int BUTTON[] = new int[7];
		//manage
		//int manage_buttonSQL[] = new int[50];
		int SUMBOTTLE[] = new int[7];
		int SUM[] = new int[7];
		int STOCK[] = new int[7];
		//money
		int MONEY[] = new int[5];
		int NUM[] = new int[5];
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null,rs2 = null,rs3 = null;
        
        int i = 0;
        try {
        	Class.forName(DRIVER);
            con = DriverManager.getConnection(URL,USER,PASS);
            // SQLを実行する
            String sql1 = "SELECT * from product";
            stmt = con.prepareStatement(sql1);
         
            rs1 = stmt.executeQuery();
            
            // 結果行をループ
            while(rs1.next()){
                // レコードの値]
            	i = 0;
            	ID[i] = rs1.getString("id");
            	NAME[i] = rs1.getString("name");
            	VALUE[i]= rs1.getInt("value");
            	BUTTON[i]= rs1.getInt("button");
                //結果を表示する
                System.out.format("%s: %15s: %d: %5d",ID[i],NAME[i],
                		VALUE[i],BUTTON[i]);
                System.out.println();
                i++;
            }
            System.out.println();
            
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
                //結果を表示する
                System.out.format("%d: %d: %d: %5d",BUTTON[i],SUMBOTTLE[i],
                		SUM[i],STOCK[i]);
                System.out.println();
                i++;
            }
            System.out.println();
            String sql3 = "SELECT * from money";
            stmt = con.prepareStatement(sql3);
            
            rs3 = stmt.executeQuery();
            i  = 0;
            // 結果行をループ
            while(rs3.next()){
                // レコードの値
            	MONEY[i] = rs3.getInt("money");
            	NUM[i]= rs3.getInt("num");
                //結果を表示する
                System.out.format("%5d: %d",MONEY[i],NUM[i]);
                System.out.println();
                i++;
            }
            con.close();
	    }catch (Exception e) {
	    	System.out.println("JDBCデータベース接続エラー");
	    }
        
		//変数宣言
		//商品名・値段
//		String NAME[] = {"Cola", "Cola", "GreenTea", "Water", "Coffee", "EnergyDrink", "Yagult1000"};
//		int VALUE[] = {130, 130, 110, 100, 110, 120, 150};
//		//使用可の硬貨・紙幣
//		int MONEY[] = {1000, 500, 100, 50, 10};
//		//お釣り使用硬貨枚数
//		int NUM[] = {0, 5, 0, 0, 200};
//		//商品在庫
//		int STOCK[] = {20,20,20,20,20,20,0};
//		int BUTTON[] = { 1, 2, 3, 4, 5, 6, 7};
		//入力する値の初期化
		int money = 0;
		int button = 0;
		boolean use_check = false;
		//投入金額合計
		int sum = 0;
		int turi[] = {0, 0, 0, 0, 0};
		//自販機内総売り上げ金額
		int allsum = 0;
		boolean summax;
		boolean judge_product[],nochange[];
		boolean canchange[] = new boolean[VALUE.length];		
		
		//購入可能商品判定
		judge_product = ven.productjudgeMethod(STOCK);
		
		nochange = ven.NoChangeMethod(NUM,MONEY);
		
		i =0;
		//金額入力フェーズ
		do {
			//金額入力メソッド
			money = pur.Input();
			//金額入力メソッド
			use_check = ven.checkuseMethod(money);
			//管理者モード判定
			if(money == -1) {
				//自販機内総売り上げメソッド
				allsum = ven.AllSum(allsum,sum);
				System.out.println("自販機内売上合計表示");
				break;
			
			}else if(use_check == true){//入力された金額が使用可能なら
				//投入金額の枚数メソッド
				NUM = ven.CalcNum(NUM,money);	
				//投入金額の計算
				sum += money;
				//投入合計金額が上限を超えていないか判定メソッド
				summax = ven.SumMaxMethod(sum);
				
				
				
				if(summax == false) {
					//購入可能商品表示
					//turi = ven.Change(sum,NUM,VALUE,button);
					for (int m = 0; m < BUTTON.length; m++) {
						canchange[m] = ven.Can_Change(sum, NUM, VALUE, m);
					}
					ven.can(sum,NAME,VALUE,STOCK,BUTTON, canchange);
					
				}
				else {
					
					NUM = ven.Change(sum,NUM,VALUE,-1);
					sum = 0;
					System.out.println("全額返金しました");
					continue;
				}
				
			}
				
		
		}while(money != 0);
//		for( i = 0;i<nochange.length; i++) {
//			sum = 0;
//			System.out.println("全額返金しました");
//		}
//		
//		for( i=0;i<NUM.length;i++) {
//			System.out.println(NUM[i]);
//		}
		
		//ボタン選択フェーズ
		button = pur.InputButton(judge_product , canchange);
			
		if(button == -1) {
			System.out.println("返却レバーが押されました。返却金額は"+sum+"です");
			sum = 0;
			System.out.println("全額返金しました");
		}else if(button >= 1 && button <= 7){
			
			NUM = ven.Change(sum,NUM,VALUE,button);
			STOCK = ven.CalcStockMethod(button,STOCK);
			
		}
		
		for( i=0;i<STOCK.length;i++) {
			System.out.println(NAME[i]+":"+STOCK[i]);
		}
		
		//for(int i=0;i<NUM.length;i++) {
			//System.out.println(NUM[i]);
		//}
		
		//System.out.print("うれしい");
		
		//int button = pur.InputButton();
		
		
	}

}
