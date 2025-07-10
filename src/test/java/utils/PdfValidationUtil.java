package utils;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * Classe utilitária para validação de conteúdo em arquivos PDF gerados pela aplicação.
 * Utiliza a biblioteca Apache PDFBox para extrair o texto e validar se os dados do pedido estão corretos.
 */
public class PdfValidationUtil {

    /**
     * Valida os dados de um pedido dentro de um arquivo PDF.
     *
     * @param filePath           Caminho completo do arquivo PDF (geralmente na pasta Downloads).
     * @param orderIdEsperado    Número do pedido que deve estar presente no PDF (ex: "2013329").
     * @param valorTotalEsperado Valor total do pedido esperado no PDF (ex: "1637.00").
     * @param taxEsperado        Valor da taxa esperada no PDF (ex: "0.00").
     */
    public static void validarPedido(String filePath, String orderIdEsperado, String valorTotalEsperado, String taxEsperado) {
        // Cria uma instância de arquivo apontando para o caminho informado
        File pdfFile = new File(filePath);

        // Verifica se o arquivo existe; caso contrário, lança exceção
        if (!pdfFile.exists()) {
            throw new IllegalArgumentException("Arquivo PDF não encontrado: " + filePath);
        }

        // Bloco try-with-resources: garante que o arquivo será fechado após o uso
        try (PDDocument document = PDDocument.load(pdfFile)) {
            // Classe do PDFBox usada para extrair o texto do PDF
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String conteudo = pdfStripper.getText(document); // Extrai o texto do PDF como String

            // Imprime o conteúdo extraído (útil para depuração)
            System.out.println("Conteúdo do PDF:\n" + conteudo);

            // Verifica se o número do pedido está presente no texto
            if (!conteudo.contains("Order# " + orderIdEsperado)) {
                throw new IllegalArgumentException("Número do pedido não encontrado ou incorreto: " + orderIdEsperado);
            }

            // Verifica se o valor total do pedido está presente corretamente
            if (!conteudo.contains("Order total: " + valorTotalEsperado)) {
                throw new IllegalArgumentException("Valor total do pedido não encontrado ou incorreto: " + valorTotalEsperado);
            }

            // Verifica se a taxa informada está presente corretamente
            if (!conteudo.contains("Tax: " + taxEsperado)) {
                throw new IllegalArgumentException("Taxa (Tax) não encontrada ou incorreta: " + taxEsperado);
            }

        } catch (IOException e) {
            // Trata exceções de leitura de arquivo PDF e reencapsula em RuntimeException
            throw new RuntimeException("Erro ao ler o PDF: " + e.getMessage(), e);
        }
    }
}
