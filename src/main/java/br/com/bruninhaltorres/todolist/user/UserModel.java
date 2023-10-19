package br.com.bruninhaltorres.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data //Faz automaticamente os Getters e Setters
@Entity(name = "tb_users") //Nome que essa classe vai receber no banco de dados.
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID") //Vai gerar automaticamente o valor da chave.
    //Chave primaria UUID é mais seguro que deixar sequencial 
    private UUID id;

    @Column(unique = true) //username vai ter a requição de ser um atributo único, mas além disso, vai ser feito um tratamento no Controller. 
    private String username;

    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt; //Vai pegar a data e a hora que o dado foi adicionado
}
