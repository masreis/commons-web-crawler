package net.marcoreis.commons.webcrawler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class ExtratorConteudoTest {
	private NutchParser parser = new NutchParser();
	private File arquivoDump = new File(
			"/home/marco/software/apache-nutch-1.13/crawl/dump-conteudo/dump");

	@Test
	public void testExtrator() throws IOException {
		List<DumpNutchVO> lista = parser.parse(arquivoDump);
		for (DumpNutchVO vo : lista) {
			ExtratorConteudo extrator =
					new ExtratorConteudo(vo.getContent());
			extrator.extrairConteudoPrincipal();
		}
	}
}
