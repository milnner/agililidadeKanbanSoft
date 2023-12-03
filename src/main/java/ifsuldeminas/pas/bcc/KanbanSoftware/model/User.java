package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "users")
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String nickname;
    private String name;
    private String surname;
    private LocalDateTime entryDate;
    private LocalDateTime bournDate;
    @OneToMany(mappedBy = "creator")
    private Set<Card> cards;
    @OneToMany(mappedBy = "creator")
    private Set<KanbanList>kanbanLists ;
    @OneToMany(mappedBy = "creator")
    private Set<Board> boards;

    @Column(unique = true)
    private String email;
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToMany(mappedBy = "users")
    private Set<Rolegroup> rolegroupIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getBournDate() {
        return bournDate;
    }

    public void setBournDate(LocalDateTime bournDate) {
        this.bournDate = bournDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Set<Rolegroup> getRolegroupIds() {
        return rolegroupIds;
    }

    public void setRolegroupIds(Set<Rolegroup> rolegroupIds) {
        this.rolegroupIds = rolegroupIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        if (getId() != user.getId()) return false;
        if (getNickname() != null ? !getNickname().equals(user.getNickname()) : user.getNickname() != null)
            return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getSurname() != null ? !getSurname().equals(user.getSurname()) : user.getSurname() != null) return false;
        if (getEntryDate() != null ? !getEntryDate().equals(user.getEntryDate()) : user.getEntryDate() != null)
            return false;
        if (getBournDate() != null ? !getBournDate().equals(user.getBournDate()) : user.getBournDate() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getPasswordHash() != null ? !getPasswordHash().equals(user.getPasswordHash()) : user.getPasswordHash() != null)
            return false;
        if (getSex() != user.getSex()) return false;
        return getRolegroupIds() != null ? getRolegroupIds().equals(user.getRolegroupIds()) : user.getRolegroupIds() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getNickname() != null ? getNickname().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getEntryDate() != null ? getEntryDate().hashCode() : 0);
        result = 31 * result + (getBournDate() != null ? getBournDate().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPasswordHash() != null ? getPasswordHash().hashCode() : 0);
        result = 31 * result + (getSex() != null ? getSex().hashCode() : 0);
        result = 31 * result + (getRolegroupIds() != null ? getRolegroupIds().hashCode() : 0);
        return result;
    }

    public String toJson() {
        StringBuilder jsonBuilder = new StringBuilder("{\n");

        // Adiciona os campos ao JSON
        jsonBuilder.append("\"id\":").append(id).append(",\n");
        jsonBuilder.append("\"nickname\":\"").append(nickname).append("\",\n");
        jsonBuilder.append("\"name\":\"").append(name).append("\",\n");
        jsonBuilder.append("\"surname\":\"").append(surname).append("\",\n");
        jsonBuilder.append("\"entryDate\":\"").append(entryDate).append("\",\n");
        jsonBuilder.append("\"bournDate\":\"").append(bournDate).append("\",\n");
        jsonBuilder.append("\"email\":\"").append(email).append("\",");
        jsonBuilder.append("\"passwordHash\":\"").append(passwordHash).append("\",\n");
        jsonBuilder.append("\"sex\":\"").append(sex).append("\",\n");
        jsonBuilder.append("\"rolegroupIds\":").append(rolegroupIdsToJson()).append("\n}");

        return jsonBuilder.toString();
    }

    // Converte rolegroupIds para JSON
    private String rolegroupIdsToJson() {
        if (rolegroupIds == null || rolegroupIds.isEmpty()) {
            return "[]";
        }

        StringBuilder rolegroupIdsBuilder = new StringBuilder("[");
        boolean first = true;

        for (Rolegroup rolegroup : rolegroupIds) {
            if (!first) {
                rolegroupIdsBuilder.append(",");
            }
            rolegroupIdsBuilder.append("{\"id\":").append(rolegroup.getId()).append("}");
            first = false;
        }

        rolegroupIdsBuilder.append("]");
        return rolegroupIdsBuilder.toString();
    }
}
