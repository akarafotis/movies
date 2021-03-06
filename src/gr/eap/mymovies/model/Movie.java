/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.eap.mymovies.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author g_kak
 */
@Entity
@Table(name = "MOVIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m")
    , @NamedQuery(name = "Movie.findById", query = "SELECT m FROM Movie m WHERE m.id = :id")
    , @NamedQuery(name = "Movie.findByTitle", query = "SELECT m FROM Movie m WHERE m.title = :title")
    , @NamedQuery(name = "Movie.findByReleaseDate", query = "SELECT m FROM Movie m WHERE m.releaseDate = :releaseDate")
    , @NamedQuery(name = "Movie.findByRating", query = "SELECT m FROM Movie m WHERE m.rating = :rating")
    , @NamedQuery(name = "Movie.findByOverview", query = "SELECT m FROM Movie m WHERE m.overview = :overview")
    // added namedQueries
    , @NamedQuery(name = "Movie.deleteAll", query = "DELETE FROM Movie")
    , @NamedQuery(name = "Movie.findByYear", query = "SELECT m FROM Movie m JOIN m.genreId g "
            + "WHERE g.name = :genre and FUNC ('YEAR', m.releaseDate) = :releaseDate")
    , @NamedQuery(name = "Movie.findTop10", query = "SELECT m FROM Movie m ORDER BY m.rating DESC")
    , @NamedQuery(name = "Movie.findByFavoriteList", query = "SELECT m FROM Movie m WHERE m.favoriteListId = :favoriteListId ORDER BY m.rating DESC")
})

public class Movie implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "RELEASE_DATE")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RATING")
    private Double rating;
    @Column(name = "OVERVIEW")
    private String overview;
    @JoinColumn(name = "FAVORITE_LIST_ID", referencedColumnName = "ID")
    @ManyToOne
    private FavoriteList favoriteListId;
    @JoinColumn(name = "GENRE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Genre genreId;

    public Movie() {
    }

    public Movie(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String oldTitle = this.title;
        this.title = title;
        changeSupport.firePropertyChange("title", oldTitle, title);
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        Date oldReleaseDate = this.releaseDate;
        this.releaseDate = releaseDate;
        changeSupport.firePropertyChange("releaseDate", oldReleaseDate, releaseDate);
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        Double oldRating = this.rating;
        this.rating = rating;
        changeSupport.firePropertyChange("rating", oldRating, rating);
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        String oldOverview = this.overview;
        this.overview = overview;
        changeSupport.firePropertyChange("overview", oldOverview, overview);
    }

    public FavoriteList getFavoriteListId() {
        return favoriteListId;
    }

    public void setFavoriteListId(FavoriteList favoriteListId) {
        FavoriteList oldFavoriteListId = this.favoriteListId;
        this.favoriteListId = favoriteListId;
        changeSupport.firePropertyChange("favoriteListId", oldFavoriteListId, favoriteListId);
    }

    public Genre getGenreId() {
        return genreId;
    }

    public void setGenreId(Genre genreId) {
        Genre oldGenreId = this.genreId;
        this.genreId = genreId;
        changeSupport.firePropertyChange("genreId", oldGenreId, genreId);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myMoviesPackage.Movie[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
