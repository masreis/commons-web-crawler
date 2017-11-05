package net.marcoreis.commons.webcrawler;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExtratorConteudo {
	private static final Logger logger =
			Logger.getLogger(ExtratorConteudo.class);
	private String pagina;

	public ExtratorConteudo(String pagina) {
		this.pagina = pagina;
	}

	public void extrairDadosHead() {
		Document documento = Jsoup.parse(pagina);
		Element head = documento.head();
		Elements elementos = head.children();
		for (Element elemento : elementos) {
			logger.info(elemento);
		}
	}
}
