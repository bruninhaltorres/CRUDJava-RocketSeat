package br.com.bruninhaltorres.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

// O sinal <> significa que ele recebe um generator.
// Extends: cria uma classe filha (subclasse) que herda propriedades e comportamentos de uma classe pai (superclasse).
// A JpaRepository oferece métodos pré-definidos para realizar operações de CRUD (Create, Read, Update, Delete) em entidades JPA, sem a necessidade de escrever consultas SQL manualmente.
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
    
}
