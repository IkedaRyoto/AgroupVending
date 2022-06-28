
public class main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Purchaser pur = new Purchaser();
		vendingmachine ven = new vendingmachine();
		//変数宣言
		String NAME[] = {"Cola", "Cola", "GreenTea", "Water", "Coffee", "EnergyDrink", "Yagult1000"};
		int VALUE[] = {130, 130, 110, 100, 110, 120, 150};
		int MONEY[] = {1000, 500, 100, 50, 10};
		//お釣り使用硬貨枚数
		int NUM[] = {30, 30, 30, 30, 5};
		//商品在庫
		int STOCK[] = {20,20,20,20,20,20,20};
		int BUTTON[] = { 1, 2, 3, 4, 5, 6,7};
		int money = 0;
		boolean use_check = false;
		//投入金額合計
		int sum = 0;
		//自販機内総売り上げ金額
		int allsum = 0;
		boolean summax = false;
		boolean judge_product[];		
		
		//購入可能商品判定
		judge_product = ven.productjudgeMethod(STOCK);
		
		
		//金額入力フェーズ
		do {
			
			money = pur.Input();
			use_check = ven.checkuseMethod(money);
			
			
			//管理者モード判定
			if(money == -1) {
				allsum = ven.AllSum(allsum,sum);
				System.out.print("自販機内売上合計表示");
				break;
			
			}else if(use_check == true){
				
				//紙幣・硬貨ごとの投入枚数合計
				NUM = ven.CalcNum(NUM,MONEY);	
				
				sum += money;
				summax = ven.SumMaxMethod(sum);
				if(summax == false) {
					//購入可能商品表示
					ven.can(sum,NAME,VALUE,STOCK,BUTTON);
				}
				else {
					sum = 0;
					System.out.print("全額返金しました");
					continue;
				}
			}
				
		
		}while(money != 0);
		
		
		
		System.out.print("うれしい");
		
		//int button = pur.InputButton();
		
		
	}
	 
}
