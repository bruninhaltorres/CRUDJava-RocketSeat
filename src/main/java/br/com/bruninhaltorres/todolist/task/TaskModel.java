package br.com.bruninhaltorres.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    private String description;
    
    @Column(length = 50) //Limitando o numero de caracteres dessa coluna.
    private String title;
    private String priotity;
    
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    
    private UUID idUser; //Vai associar o usuária a tarefa
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
}
