public class main {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Purchaser pur = new Purchaser();
		vendingmachine ven = new vendingmachine();
		//変数宣言
		//商品名・値段
		String NAME[] = {"Cola", "Cola", "GreenTea", "Water", "Coffee", "EnergyDrink", "Yagult1000"};
		int VALUE[] = {130, 130, 110, 100, 110, 120, 150};
		//使用可の硬貨・紙幣
		int MONEY[] = {1000, 500, 100, 50, 10};
		//お釣り使用硬貨枚数
		int NUM[] = {0, 5, 0, 0, 0};
		//商品在庫
		int STOCK[] = {20,20,20,20,20,20,0};
		int BUTTON[] = { 1, 2, 3, 4, 5, 6, 7};
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
		
		
		
		int i =0;
		//金額入力フェーズ
		do {
			
			money = pur.Input();
			use_check = ven.checkuseMethod(money);
			
			
			//管理者モード判定
			if(money == -1) {
				allsum = ven.AllSum(allsum,sum);
				System.out.println("自販機内売上合計表示");
				break;
			
			}else if(use_check == true){
				
				//紙幣・硬貨ごとの投入枚数合計
				NUM = ven.CalcNum(NUM,money);	
				
				sum += money;
				summax = ven.SumMaxMethod(sum);
				nochange = ven.NoChangeMethod(NUM,MONEY);
				
				if(summax == false) {
					//購入可能商品表示
					//turi = ven.Change(sum,NUM,VALUE,button);
					for (int m = 0; m < VALUE.length; m++) {
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
		
		for( i=0;i<NUM.length;i++) {
			System.out.println(NUM[i]);
		}
		
		//ボタン選択フェーズ
		button = pur.InputButton(judge_product , canchange);
			
		if(button == -1) {
			sum = 0;
			System.out.println("全額返金しました");
		}else if(button >= 1 && button <= 7){
			
			NUM = ven.Change(sum,NUM,VALUE,button);
			STOCK = ven.CalcStockMethod(button,STOCK);
			
		}
		
//		for(int i=0;i<STOCK.length;i++) {
//			System.out.println(STOCK[i]);
//		}
		
		//for(int i=0;i<NUM.length;i++) {
			//System.out.println(NUM[i]);
		//}
		
		//System.out.print("うれしい");
		
		//int button = pur.InputButton();
		
		
	}

}