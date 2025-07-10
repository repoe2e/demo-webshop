package professor_demoshop;

public class FormatarTexto {

	public static void main(String[] args) {
		
		
		String order =  "#2013329";
		System.out.println(order);
		String orderFormatada = order.replace("#", "").trim();
		System.out.println(orderFormatada);

		
		String orderTotal = "      R$ 1637.00";
		System.out.println(orderTotal);
		String orderTotalFOrmatada = orderTotal.replace("R$", "").trim();
		System.out.println(orderTotalFOrmatada);

		
	}

}
