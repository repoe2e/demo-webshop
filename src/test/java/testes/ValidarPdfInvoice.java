package testes;

import org.junit.jupiter.api.Test;

import utils.PdfValidationUtil;

public class ValidarPdfInvoice {

	
	@Test
	public void testInvoicePdf() {
		String caminhoPdf = System.getProperty("user.home") + "/Downloads/order_2013329.pdf";
		String orderId = "2013329";
		String valorTotal = "1637.00";
		String tax = "0.00";

		PdfValidationUtil.validarPedido(caminhoPdf, orderId, valorTotal, tax);

	}

}
