package net.marcoreis.commons.webcrawler;

public class DumpNutchVO {
	private byte[] url;
	private byte[] content;
	private byte[] contentType;
	private byte[] metadata;

	public byte[] getUrl() {
		return url;
	}

	public void setUrl(byte[] url) {
		this.url = url;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public void setContentType(byte[] contentType) {
		this.contentType = contentType;
	}

	public byte[] getContentType() {
		return contentType;
	}

	public void setMetadata(byte[] metadata) {
		this.metadata = metadata;
	}

	public byte[] getMetadata() {
		return metadata;
	}
}
