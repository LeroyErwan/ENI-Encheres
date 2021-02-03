package fr.eni.ecole.trocenchere.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class Article implements Serializable {

	private static final long serialVersionUID = -4813349171372997274L;

	private int idArticle;
	private String name;
	private String description;
	private LocalDate bidStartDate;
	private LocalDate bidEndDate;
	private int startingBid;
	private int salePrice;
	private String status;
	private int idCategory;
	private int idUser;

	public int getIdArticle() {
		return idArticle;
	}

	/**
	 * @param idArticle the idArticle to set
	 */
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the bidStartDate
	 */
	public LocalDate getBidStartDate() {
		return bidStartDate;
	}

	/**
	 * @param bidStartDate the bidStartDate to set
	 */
	public void setBidStartDate(LocalDate bidStartDate) {
		this.bidStartDate = bidStartDate;
	}

	/**
	 * @return the bidEndDate
	 */
	public LocalDate getBidEndDate() {
		return bidEndDate;
	}

	/**
	 * @param bidEndDate the bidEndDate to set
	 */
	public void setBidEndDate(LocalDate bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	/**
	 * @return the startingBid
	 */
	public int getStartingBid() {
		return startingBid;
	}

	/**
	 * @param startingBid the startingBid to set
	 */
	public void setStartingBid(int startingBid) {
		this.startingBid = startingBid;
	}

	/**
	 * @return the salePrice
	 */
	public int getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the idCategory
	 */
	public int getIdCategory() {
		return idCategory;
	}

	/**
	 * @param idCategory the idCategory to set
	 */
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	/**
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Article(String name, String description, LocalDate bidStartDate, LocalDate bidEndDate, int startingBid,
			String status, int idCategory, int idUser) {
		this.name = name;
		this.description = description;
		this.bidStartDate = bidStartDate;
		this.bidEndDate = bidEndDate;
		this.startingBid = startingBid;
		this.status = status;
		this.idCategory = idCategory;
		this.idUser = idUser;
	}

	public Article() {
	}

}
