package net.marcoreis.commons.webcrawler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import net.marcoreis.commons.webcrawler.DumpNutchVO;
import net.marcoreis.commons.webcrawler.NutchParser;

public class NutchParserTest {

	@Test
	public void testParser() throws IOException {
		NutchParser parser = new NutchParser();
		File arquivoDump = new File(
				"/home/marco/software/apache-nutch-1.13/crawl/dump-conteudo/dump");
		List<DumpNutchVO> lista = parser.parse(arquivoDump);
		System.out.println(lista.size());
	}

}
