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
		String pathname =
				"/home/marco/software/apache-nutch-1.13/crawlmr/dump-conteudo/dump";
		File arquivoDump = new File(pathname);
		listaVos = parser.parse(arquivoDump);
	}

	@Test
	public void testExtraiConteudoPrincipalBlog() {
		for (DumpNutchVO vo : listaVos) {
			ExtratorConteudo extrator = new ExtratorConteudo();
			extrator.carregaConteudoHtml(
					new String(vo.getContent()));
			String conteudo =
					extrator.extraiConteudoPrincipalBlog();
			logger.info(vo.getUrl());
			if (conteudo != null) {
				logger.info(conteudo);
			} else {
				logger.warn(
						"Esta URL não tem conteúdo principal");
			}
		}
	}

	// @Test
	public void testExtraiCategoriasBlog() {
		ExtratorConteudo extrator = new ExtratorConteudo();
		Set<String> categorias =
				extrator.extraiCategoriasBlog(listaVos);
		for (String categoria : categorias) {
			logger.info(categoria);
		}
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

}
