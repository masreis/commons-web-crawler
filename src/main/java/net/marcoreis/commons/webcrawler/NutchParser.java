package net.marcoreis.commons.webcrawler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class NutchParser {
	private static final Logger logger =
			Logger.getLogger(NutchParser.class);

	public List<DumpNutchVO> parse(File arquivoDump)
			throws IOException {
		List<DumpNutchVO> lista = new ArrayList<DumpNutchVO>();
		InputStream is = new FileInputStream(arquivoDump);
		String textoArquivo = "";
		try {
			textoArquivo = IOUtils.toString(is);
		} catch (Throwable e) {
			logger.error(e);
		} finally {
			is.close();
		}
		String[] paginas = textoArquivo.split("Recno:: ");
		for (String conteudo : paginas) {
			if (conteudo.contains("Content::")) {
				DumpNutchVO vo = criaVO(conteudo);
				if (vo != null) {
					lista.add(vo);
				}
			}
		}
		return lista;
	}

	private DumpNutchVO criaVO(String conteudo) {
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
		vo.setUrl(url);
		// Extrai o content type
		Pattern patternContentType =
				Pattern.compile("(contentType:\\s)(.*)");
		matcher = patternContentType.matcher(conteudoCompleto);
		String contentType = null;
		if (matcher.find()) {
			contentType = matcher.group(2);
		}
		vo.setContentType(contentType);
		// Extrai o metadata
		Pattern patternMetadata =
				Pattern.compile("(metadata:\\s)(.*)");
		matcher = patternMetadata.matcher(conteudoCompleto);
		String metadata = null;
		if (matcher.find()) {
			metadata = matcher.group(2);
		}
		vo.setMetadata(metadata);
		// Encontra o início e o fim do código HTML
		String termoContent = "Content:\n";
		int inicioHtml = conteudoCompleto.indexOf(termoContent);
		inicioHtml += termoContent.length();
		String termoFimHtml = "</html>";
		int fimHtml = conteudoCompleto.indexOf(termoFimHtml);
		// Se o código da página não está correto
		if (fimHtml < 0) {
			return null;
		}
		String conteudoHtml =
				conteudoCompleto.substring(inicioHtml, fimHtml);
		vo.setContent(conteudoHtml);
		return vo;
	}

}
