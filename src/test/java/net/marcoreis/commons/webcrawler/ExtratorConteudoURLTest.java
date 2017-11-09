package net.marcoreis.commons.webcrawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class ExtratorConteudoURLTest {
	@Test
	public void testParseFromUrl()
			throws MalformedURLException, IOException {
		ExtratorConteudo extrator = new ExtratorConteudo();
		String spec = "http://pt.wikipedia.org";
		extrator.carregarConteudoHtml(new URL(spec));
		extrator.extraiConteudoHead();
	}
}
