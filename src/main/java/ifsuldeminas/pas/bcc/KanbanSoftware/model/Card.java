package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

@Table(name = "card")
@Entity(name = "card")
public class Card extends KanbanElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private KanbanList list;
    @ManyToOne
    private Rolegroup rolegroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public KanbanList getList() {
        return list;
    }

    public void setList(KanbanList list) {
        this.list = list;
    }

    public Rolegroup getRolegroup() {
        return rolegroup;
    }

    public void setRolegroup(Rolegroup rolegroup) {
        this.rolegroup = rolegroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        if (!super.equals(o)) return false;

        if (getId() != card.getId()) return false;
        if (getList() != null ? !getList().equals(card.getList()) : card.getList() != null) return false;
        return getRolegroup() != null ? getRolegroup().equals(card.getRolegroup()) : card.getRolegroup() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getId();
        result = 31 * result + (getList() != null ? getList().hashCode() : 0);
        result = 31 * result + (getRolegroup() != null ? getRolegroup().hashCode() : 0);
        return result;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // ou lance uma exceção adequada
            return null;
        }
    }
}
