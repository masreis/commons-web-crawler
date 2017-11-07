package net.marcoreis.commons.webcrawler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExtratorConteudo {
	private static final Logger logger =
			Logger.getLogger(ExtratorConteudo.class);
	private Document documento;

	public void carregarConteudoHtml(String conteudoHtml) {
		documento = Jsoup.parse(conteudoHtml);
	}

	public void extraiConteudoHead() {
		Element head = documento.head();
		Elements elementos = head.children();
		for (Element elemento : elementos) {
			logger.info(elemento.tagName());
			// if (!elemento.tag().isEmpty()) {
			// }
			if (elemento.tag().isData()) {
				logger.info("Data: " + elemento.data());
			} else {
				logger.info(elemento.hasText());
				logger.info("Text: " + elemento.text());
				logger.info("Val: " + elemento.val());
				logger.info("Elemento: " + elemento);
				logger.info(elemento.tag().isData());
				logger.info(elemento.tag().isBlock());
				logger.info(elemento.tag().isEmpty());
				logger.info(elemento.tag().isInline());
			}
			logger.info("================");
			// }
		}
	}

	public void extraiConteudoPrincipal() {
		Element body = documento.body();
		Element main = body.getElementById("main");
		Elements categorias = main.getElementsByAttributeValue(
				"rel", "category tag");
		logger.info(categorias.size());
		for (Element e : categorias) {
			logger.info(e.text());
		}
	}

	public Set<String> extraiCategorias(
			List<DumpNutchVO> listaVos) {
		Set<String> lista = new HashSet<String>();
		for (DumpNutchVO vo : listaVos) {
			carregarConteudoHtml(vo.getContent());
			Element body = documento.body();
			Element main = body.getElementById("main");
			if (main == null) {
				continue;
			}
			Elements categorias =
					main.getElementsByAttributeValue("rel",
							"category tag");
			for (Element e : categorias) {
				lista.add(e.text());
			}
		}
		return lista;
	}
}
