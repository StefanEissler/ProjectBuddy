package com.projectbuddy2.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}),
        @UniqueConstraint(columnNames = {"email"})
})
@Getter
@Setter
public class User implements UserDetails{

    public User(){

    }
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    private String email;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
//    @JoinTable(name = "user_budget_list",
//                joinColumns = @JoinColumn(name = "user_id"),
//                inverseJoinColumns = @JoinColumn(name = "budget_id"))
    Set<Budget> budgetList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Budget zu User hinzufuegen
    public void addBudget(Budget budget){
        this.budgetList.add(budget);
        budget.getUsers().add(this);
    }

    // Budget von User loesen
    public void removeBudget(Long budgetId){
        Budget budget = this.budgetList.stream().filter(t -> t.getId() == budgetId).findFirst().orElse(null);
        if(budget != null){
            this.budgetList.remove(budget);
            budget.getUsers().remove(this);
        }
    }
}
