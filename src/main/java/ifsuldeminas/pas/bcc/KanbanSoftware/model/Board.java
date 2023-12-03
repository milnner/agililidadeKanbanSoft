package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.util.List;

@Table(name = "board")
@Entity(name = "board")
public class Board extends KanbanElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(mappedBy = "board")
    private List<KanbanList> lists;
    @ManyToOne
    private Rolegroup rolegroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<KanbanList> getLists() {
        return lists;
    }

    public void setLists(List<KanbanList> lists) {
        this.lists = lists;
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
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (getId() != board.getId()) return false;
        if (getLists() != null ? !getLists().equals(board.getLists()) : board.getLists() != null) return false;
        return getRolegroup() != null ? getRolegroup().equals(board.getRolegroup()) : board.getRolegroup() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getLists() != null ? getLists().hashCode() : 0);
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
