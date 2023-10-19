package br.com.bruninhaltorres.todolist.user;
//Tudo que for relacionado ao usuário vai ser acessado por aqui, pois é uma classe Controller.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired // injetar automaticamente as dependências 
    private IUserRepository userRepository; //Chama a interface

    @PostMapping("/")
    // Com o ResponseEntity eu posso ter mais de um tipo de retorno na mesma requisição.
    public ResponseEntity create(@RequestBody UserModel userModel){
        var user = this.userRepository.findByUsername(userModel.getUsername());
        // System.out.println(user.getName());

        if(user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe."); //Referente ao Status Code 400.
        }

        //Criptografia da Senha
        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(200).body (userCreated); // Referente ao HttpStatus.CREATED
    }
}
