package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Table(name = "rolegroups")
    @Entity(name = "rolegroups")
    public class Rolegroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToMany
    private Set<User> users;

    @Enumerated(EnumType.STRING)
    private KanbanPerms permition;
    @OneToMany(mappedBy = "rolegroup")
    private List<Board> boards;
    @OneToMany(mappedBy = "rolegroup")
    private List<KanbanList> lists;

    @OneToMany(mappedBy = "rolegroup")
    private List<Card> cards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public List<KanbanList> getLists() {
        return lists;
    }

    public void setLists(List<KanbanList> lists) {
        this.lists = lists;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rolegroup rolegroup)) return false;

        if (getId() != rolegroup.getId()) return false;
        if (getName() != null ? !getName().equals(rolegroup.getName()) : rolegroup.getName() != null) return false;
        if (getUsers() != null ? !getUsers().equals(rolegroup.getUsers()) : rolegroup.getUsers() != null) return false;
        if (getBoards() != null ? !getBoards().equals(rolegroup.getBoards()) : rolegroup.getBoards() != null)
            return false;
        if (getLists() != null ? !getLists().equals(rolegroup.getLists()) : rolegroup.getLists() != null) return false;
        return getCards() != null ? getCards().equals(rolegroup.getCards()) : rolegroup.getCards() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getUsers() != null ? getUsers().hashCode() : 0);
        result = 31 * result + (getBoards() != null ? getBoards().hashCode() : 0);
        result = 31 * result + (getLists() != null ? getLists().hashCode() : 0);
        result = 31 * result + (getCards() != null ? getCards().hashCode() : 0);
        return result;
    }
}

