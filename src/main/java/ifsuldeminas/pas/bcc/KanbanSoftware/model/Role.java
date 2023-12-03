package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;


@Table(name = "roles")
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private  Rolegroup rolegroup;
    @Enumerated(EnumType.STRING)
    private KanbanPerms permission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rolegroup getRolegroup() {
        return rolegroup;
    }

    public void setRolegroup(Rolegroup rolegroup) {
        this.rolegroup = rolegroup;
    }

    public KanbanPerms getPermission() {
        return permission;
    }

    public void setPermission(KanbanPerms permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;

        if (getId() != role.getId()) return false;
        if (getRolegroup() != null ? !getRolegroup().equals(role.getRolegroup()) : role.getRolegroup() != null)
            return false;
        return getPermission() == role.getPermission();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getRolegroup() != null ? getRolegroup().hashCode() : 0);
        result = 31 * result + (getPermission() != null ? getPermission().hashCode() : 0);
        return result;
    }


}
