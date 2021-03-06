package net.marcoreis.commons.webcrawler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class DumpNutchParserTest {
	@Test
	public void testParser() throws IOException {
		DumpNutchParser parser = new DumpNutchParser();
		File arquivoDump = new File(
				"/home/marco/software/apache-nutch-1.13/crawlmr/dump-conteudo/dump");
		List<DumpNutchVO> lista = parser.parse(arquivoDump);
		for (DumpNutchVO vo : lista) {
			System.out.println(vo.getUrl());
		}
	}
}
