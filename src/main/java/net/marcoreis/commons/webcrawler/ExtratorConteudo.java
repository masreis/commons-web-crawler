package net.marcoreis.commons.webcrawler;

import java.io.IOException;
import java.net.URL;
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

	public void carregaConteudoHtml(String conteudoHtml) {
		documento = Jsoup.parse(conteudoHtml);
	}

	public void carregaConteudoHtml(URL url) throws IOException {
		int timeout_cinco_segundos = 5000;
		documento = Jsoup.parse(url, timeout_cinco_segundos);
	}

	public void extraiConteudoHead() {
		Element head = documento.head();
		Elements elementos = head.children();
		for (Element elemento : elementos) {
			logger.info(elemento.tagName());
			if (elemento.hasText()) {
				logger.info("Text: " + elemento.text());
			} else if (elemento.data().length() > 0) {
				logger.info("Data: " + elemento.data());
			} else {
				logger.info(elemento);
			}
			logger.info("================");
		}
	}

	public String extraiConteudoPrincipalBlog() {
		Element body = documento.body();
		Elements entryContent =
				body.getElementsByClass("entry-content");
		if (entryContent != null && entryContent.hasText()) {
			return entryContent.text();
		} else {
			return null;
		}
	}

	public String extraiConteudoPrincipalWikipedia() {
		Element body = documento.body();
		Element content = body.getElementById("content");
		if (content != null && content.hasText()) {
			return content.text();
		} else {
			return null;
		}
	}

	public Set<String> extraiCategoriasBlog(
			List<DumpNutchVO> listaVos) {
		Set<String> lista = new HashSet<String>();
		for (DumpNutchVO vo : listaVos) {
			carregaConteudoHtml(new String(vo.getContent()));
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

	public Set<String> extraiCategoriasWikipedia(
			List<DumpNutchVO> listaVos) {
		Set<String> lista = new HashSet<String>();
		for (DumpNutchVO vo : listaVos) {
			carregaConteudoHtml(new String(vo.getContent()));
			Element body = documento.body();
			Element content = body.getElementById("content");
			if (content == null) {
				continue;
			}
			Elements categorias =
					content.getElementsByAttributeValueStarting(
							"href", "/wiki/Categoria:");
			for (Element e : categorias) {
				// O símbolo ! indica uma categoria oculta
				if (e.text().contains("!")) {
					continue;
				}
				lista.add(e.text());
			}
		}
		return lista;
	}
}
