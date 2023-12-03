package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.util.List;

@Table(name = "kanban_list")
@Entity(name = "kanban_list")
public class KanbanList extends KanbanElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(mappedBy = "list")
    private List<Card> cards;
    @ManyToOne
    private Board board;

    @ManyToOne
    private Rolegroup rolegroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
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
        if (!(o instanceof KanbanList that)) return false;
        if (!super.equals(o)) return false;

        if (getId() != that.getId()) return false;
        if (getCards() != null ? !getCards().equals(that.getCards()) : that.getCards() != null) return false;
        if (getBoard() != null ? !getBoard().equals(that.getBoard()) : that.getBoard() != null) return false;
        return getRolegroup() != null ? getRolegroup().equals(that.getRolegroup()) : that.getRolegroup() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getId();
        result = 31 * result + (getCards() != null ? getCards().hashCode() : 0);
        result = 31 * result + (getBoard() != null ? getBoard().hashCode() : 0);
        result = 31 * result + (getRolegroup() != null ? getRolegroup().hashCode() : 0);
        return result;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
