package vend;

public class CalcSum {

	public static void main(String[] args) {
		//投入金額
		int money[] = {10, 10, 100, 100, 50,500,1000};
		int num[] = {10, 10, 10, 10, 10};
		int sum = 0;
		
		//投入金額合計計算
		sum = Calc(num, money, sum);

		System.out.println("投入金額合計:" +sum+ "円");

	}

	private static int Calc(int num[], int money[], int sum) {
		for(int i = 0; i <= money.length-1; i++) {
			sum += money[i];
			
			switch(money[i]) {
			case 10:
				num[0] += 1;
				break;
				
			case 50:
				num[1] += 1;
				break;
				
			case 100:
				num[2] += 1;
				break;
				
			case 500:
				num[3] += 1;
				
			default :
				num[4] += 1;
			}
		}
		System.out.printf("%4d円 %4d円 %4d円 %4d円 %4d円\n",10,50,100,500,1000);
		System.out.printf("%4d枚 %4d枚 %4d枚 %4d枚 %4d枚\n",num[0],num[1],num[2],num[3],num[4]);

		return sum;
		}
}
