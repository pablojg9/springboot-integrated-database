package br.com.proieto.springboot.controller;

import br.com.proieto.springboot.model.Usuario;
import br.com.proieto.springboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GreetingController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name){
        return "Spring boot API:" + name + "!";
    }

    @RequestMapping(value = "/nameduo/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String returnName(@PathVariable String name){

        Usuario usuario = new Usuario();
        usuario.setNome(name);

        usuarioRepository.save(usuario);

        return "Olá, Mundo! " + name;
    }

    @GetMapping(value = "/lista") // Primeiro método de API
    @ResponseBody //Retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listUser() {
        List<Usuario> usuarios = usuarioRepository.findAll(); // Busca todos os dados do banco de dados

        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @PostMapping(value = "/salvar") // Mapea a url
    @ResponseBody // descrição da resposta
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { //recebe os dados para salvar
        Usuario user = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<Usuario> delete(@RequestBody Usuario usuario){
        usuarioRepository.delete(usuario); // chamando o método delete() para deletar pelo id;

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK); //Retornando para que foi deletado!
    }


}