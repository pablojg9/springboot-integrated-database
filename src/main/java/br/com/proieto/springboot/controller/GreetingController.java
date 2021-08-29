package br.com.proieto.springboot.controller;

import br.com.proieto.springboot.model.Usuario;
import br.com.proieto.springboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
