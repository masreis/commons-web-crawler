package net.marcoreis.commons.webcrawler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExtratorConteudoTest {
	private static List<DumpNutchVO> listaVos;
	private static final Logger logger =
			Logger.getLogger(ExtratorConteudoTest.class);

	@BeforeClass
	public static void iniciarlizar() throws IOException {
		DumpNutchParser parser = new DumpNutchParser();
		File arquivoDump = new File(
				"/home/marco/software/apache-nutch-1.13/crawl/dump-conteudo/dump");
		listaVos = parser.parse(arquivoDump);
	}

	// @Test
	public void testExtraiConteudoPrincipal() {
		for (DumpNutchVO vo : listaVos) {
			ExtratorConteudo extrator = new ExtratorConteudo();
			extrator.carregarConteudoHtml(vo.getContent());
			extrator.extraiConteudoPrincipal();
		}
	}

	// @Test
	public void testExtraiCategorias() {
		ExtratorConteudo extrator = new ExtratorConteudo();
		Set<String> categorias =
				extrator.extraiCategorias(listaVos);
		for (String categoria : categorias) {
			logger.info(categoria);
		}
	}

	@Test
	public void testExtraiConteudoHead() {
		for (DumpNutchVO vo : listaVos) {
			ExtratorConteudo extrator = new ExtratorConteudo();
			extrator.carregarConteudoHtml(vo.getContent());
			extrator.extraiConteudoHead();
		}
	}
}
