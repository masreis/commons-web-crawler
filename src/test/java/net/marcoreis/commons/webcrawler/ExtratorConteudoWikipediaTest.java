package net.marcoreis.commons.webcrawler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExtratorConteudoWikipediaTest {
	private static List<DumpNutchVO> listaVos;
	private static final Logger logger = Logger
			.getLogger(ExtratorConteudoWikipediaTest.class);

	@BeforeClass
	public static void iniciarlizar() throws IOException {
		DumpNutchParser parser = new DumpNutchParser();
		String pathname =
				"/home/marco/software/apache-nutch-1.13/crawlwiki/dump-conteudo/dump";
		File arquivoDump = new File(pathname);
		listaVos = parser.parse(arquivoDump);
	}

	// @Test
	public void testExtraiConteudoHead() {
		for (DumpNutchVO vo : listaVos) {
			ExtratorConteudo extrator = new ExtratorConteudo();
			extrator.carregaConteudoHtml(
					new String(vo.getContent()));
			extrator.extraiConteudoHead();
		}
	}

	// @Test
	public void testExtraiConteudoPrincipalWikipedia() {
		for (DumpNutchVO vo : listaVos) {
			ExtratorConteudo extrator = new ExtratorConteudo();
			extrator.carregaConteudoHtml(
					new String(vo.getContent()));
			String conteudo =
					extrator.extraiConteudoPrincipalWikipedia();
			logger.info(new String(vo.getUrl()));
			if (conteudo != null) {
				logger.info(conteudo.substring(0, 120));
			} else {
				logger.warn(
						"Esta URL não tem conteúdo principal");
			}
		}
	}

	@Test
	public void testExtraiCategoriasWikipedia() {
		ExtratorConteudo extrator = new ExtratorConteudo();
		Set<String> categorias =
				extrator.extraiCategoriasWikipedia(listaVos);
		for (String categoria : categorias) {
			logger.info(categoria);
		}
	}

}
