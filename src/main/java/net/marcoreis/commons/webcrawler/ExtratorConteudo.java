package net.marcoreis.commons.webcrawler;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExtratorConteudo {
	private static final Logger logger =
			Logger.getLogger(ExtratorConteudo.class);
	Document documento;

	public ExtratorConteudo(String pagina) {
		documento = Jsoup.parse(pagina);
	}

	public void extrairDadosHead() {
		Element head = documento.head();
		Elements elementos = head.children();
		for (Element elemento : elementos) {
			logger.info(elemento.tag());
		}
	}

	public void extrairConteudoPrincipal() {
		Element body = documento.body();
		Element main = body.getElementById("main");
		Elements categorias = body.getElementsByAttribute("rel");
		logger.info(categorias.size());
		for (Element e : categorias) {
			logger.info(e.val());
		}
		logger.info(main.text());
	}
}
