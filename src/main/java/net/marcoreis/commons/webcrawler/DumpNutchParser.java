package net.marcoreis.commons.webcrawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class DumpNutchParser {
	private static final Logger logger =
			Logger.getLogger(DumpNutchParser.class);

	public List<DumpNutchVO> parse(File arquivoDump)
			throws IOException {
		List<DumpNutchVO> lista = new ArrayList<DumpNutchVO>();
		BufferedReader buf =
				new BufferedReader(new FileReader(arquivoDump));
		String linha = null;
		StringBuilder conteudo = new StringBuilder();
		buf.readLine();
		buf.readLine();
		while ((linha = buf.readLine()) != null) {
			if (linha.startsWith("Recno::")) {
				if (conteudo.toString().contains("Content::")) {
					DumpNutchVO vo = criaVO(conteudo);
					if (vo != null) {
						lista.add(vo);
					}
				}
				conteudo.setLength(0);
			} else {
				conteudo.append(linha).append("\n");
			}
		}
		buf.close();
		return lista;
	}

	private DumpNutchVO criaVO(StringBuilder conteudo) {
		DumpNutchVO vo = new DumpNutchVO();
		// Encontra o início do bloco do conteúdo
		int inicioConteudo = conteudo.indexOf("Content::");
		String conteudoCompleto =
				conteudo.substring(inicioConteudo);
		// Extrai a URL por meio de expressão regular
		Pattern patternUrl = Pattern.compile("(url:\\s)(.*)");
		Matcher matcher = patternUrl.matcher(conteudoCompleto);
		String url = null;
		if (matcher.find()) {
			url = matcher.group(2);
		}
		vo.setUrl(url.getBytes());
		// Extrai o content type
		Pattern patternContentType =
				Pattern.compile("(contentType:\\s)(.*)");
		matcher = patternContentType.matcher(conteudoCompleto);
		String contentType = null;
		if (matcher.find()) {
			contentType = matcher.group(2);
		}
		vo.setContentType(contentType.getBytes());
		// Extrai o metadata
		Pattern patternMetadata =
				Pattern.compile("(metadata:\\s)(.*)");
		matcher = patternMetadata.matcher(conteudoCompleto);
		String metadata = null;
		if (matcher.find()) {
			metadata = matcher.group(2);
		}
		vo.setMetadata(metadata.getBytes());
		// Encontra o início e o fim do código HTML
		String termoContent = "Content:\n";
		int inicioHtml = conteudoCompleto.indexOf(termoContent);
		inicioHtml += termoContent.length();
		String termoFimHtml = "</html>";
		int fimHtml = conteudoCompleto.indexOf(termoFimHtml);
		// Se o código da página não contém a tag de fechamento do HTML
		if (fimHtml < 0) {
			logger.error(
					"O conteúdo da página não é bem formado");
			return null;
		}
		String conteudoHtml =
				conteudoCompleto.substring(inicioHtml, fimHtml);
		vo.setContent(conteudoHtml.getBytes());
		return vo;
	}

}
