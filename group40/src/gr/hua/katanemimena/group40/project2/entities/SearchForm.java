package gr.hua.katanemimena.group40.project2.entities;

import javax.persistence.Column;

public class SearchForm {

	@Column
	private String sBy;
	
	@Column
	private String sKeyword;

	public SearchForm() {}
	
	public SearchForm(String sBy, String sKeyword) {
		super();
		this.sBy = sBy;
		this.sKeyword = sKeyword;
	}

	public String getsBy() {
		return sBy;
	}

	public void setsBy(String sBy) {
		this.sBy = sBy;
	}

	public String getsKeyword() {
		return sKeyword;
	}

	public void setsKeyword(String sKeyword) {
		this.sKeyword = sKeyword;
	}
	
	
	
	
}
