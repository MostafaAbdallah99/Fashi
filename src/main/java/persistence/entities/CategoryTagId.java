package persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CategoryTagId implements Serializable {
    private static final long serialVersionUID = 6011144107064738717L;
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "tag_id", nullable = false)
    private Integer tagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CategoryTagId entity = (CategoryTagId) o;
        return Objects.equals(this.tagId, entity.tagId) &&
                Objects.equals(this.categoryId, entity.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, categoryId);
    }

}