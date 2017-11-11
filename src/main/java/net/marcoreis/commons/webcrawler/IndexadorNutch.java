package net.marcoreis.commons.webcrawler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexadorNutch {
	private static final Logger logger =
			Logger.getLogger(IndexadorNutch.class);
	protected IndexWriter writer;
	private Directory diretorio;
	private String diretorioIndice;
	// private String diretorioSegmento;
	protected long totalUrlsIndexadas;
	private boolean apagarIndice;

	public void inicializar() throws IOException {
		if (apagarIndice) {
			FileUtils.deleteDirectory(new File(diretorioIndice));
		}
		Analyzer analyzer = new StandardAnalyzer();
		diretorio = FSDirectory.open(Paths.get(diretorioIndice));
		IndexWriterConfig conf = new IndexWriterConfig(analyzer);
		writer = new IndexWriter(diretorio, conf);
	}

	public void finalizar() {
		try {
			writer.close();
			diretorio.close();
			//
			logger.info("Total de Urls indexadas: "
					+ totalUrlsIndexadas);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public void indexar(File diretorioSegmento) {
		String nomeArquivoDump = "dump";
		File arquivo =
				new File(diretorioSegmento, nomeArquivoDump);
		indexarArquivo(arquivo);
	}

	public void indexarArquivo(File arquivo) {
		try {
			Date dataModificacao =
					new Date(arquivo.lastModified());
			String dataParaIndexacao = DateTools.dateToString(
					dataModificacao, Resolution.DAY);
			String textoArquivo = "";
			//

		} catch (Exception e) {
			logger.error("Não foi possível processar o arquivo "
					+ arquivo.getAbsolutePath());
			logger.error(e);
		}
	}

	private void criaDocumento(String conteudoPagina)
			throws IOException {
		Document doc = new Document();

		writer.addDocument(doc);
		logger.info("Conteúdo indexado");
		totalUrlsIndexadas++;
	}

	public void setApagarIndice(boolean apagarIndice) {
		this.apagarIndice = apagarIndice;
	}

	public void setDiretorioIndice(String diretorioIndice) {
		this.diretorioIndice = diretorioIndice;
	}

	// public void setDiretorioSegmento(String diretorioSegmento) {
	// this.diretorioSegmento = diretorioSegmento;
	// }

}
