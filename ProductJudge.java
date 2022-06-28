class ProductJudgeclass{
	
	
	public boolean []  productjudgeMethod(int STOCK[]){
		
		// boolen配列を宣言
		boolean judge_product [] = new boolean[STOCK.length];
		
		// 各商品の在庫が0ならtrueを格納
		for(int i = 0; i< STOCK.length; i++) {
			if(STOCK[i] == 0) {
				judge_product[i] = true;
			}
			else {
				judge_product[i] = false;
			}
		}
		 return judge_product;
	}
	

	

}