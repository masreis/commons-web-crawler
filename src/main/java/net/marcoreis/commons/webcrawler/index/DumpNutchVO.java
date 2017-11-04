package net.marcoreis.commons.webcrawler.index;

import java.util.List;

public class DumpNutchVO {
	private String recno;
	private List<String> crawlDatum;
	private String parseData;
	private String parseText;
	private String url;
	private String content;
	private String contentType;
	private String metadata;

	public String getRecno() {
		return recno;
	}

	public void setRecno(String recno) {
		this.recno = recno;
	}

	public List<String> getCrawlDatum() {
		return crawlDatum;
	}

	public void setCrawlDatum(List<String> crawlDatum) {
		this.crawlDatum = crawlDatum;
	}

	public String getParseData() {
		return parseData;
	}

	public void setParseData(String parseData) {
		this.parseData = parseData;
	}

	public String getParseText() {
		return parseText;
	}

	public void setParseText(String parseText) {
		this.parseText = parseText;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public String getMetadata() {
		return metadata;
	}
}
