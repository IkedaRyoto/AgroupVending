class CalcStockclass{

	public static void main(String[] args){
		//仮条件設定
		int STOCK [] = {1,3,4,2};
		int button = 1;

		//表示
		STOCK = CalcStockMethod(button, STOCK);
		for (int i = 0; i < STOCK.length; i++){
			System.out.println(STOCK[i]);
		}
	}

	public static int[]  CalcStockMethod(int button, int STOCK[]){
		STOCK[button - 1] --;
		return STOCK;
	}
}
