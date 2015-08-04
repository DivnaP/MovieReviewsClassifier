package klase;

public class Review {

	
	String id,author,content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String toString() {
		   return "Result [id=" + id + ", author=" + author + ", content="
			+ content + "]";
		}
}
