package br.com.bruninhaltorres.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; //NomeDoPackage.NomeDaClasse

@SpringBootApplication //Annotation: Existe uma função por trás dela.
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}	
