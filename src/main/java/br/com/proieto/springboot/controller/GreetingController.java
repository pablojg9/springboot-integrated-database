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

    @PutMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<?> updateUser(@RequestBody Usuario usuario) {

        if (usuario.getId() == null) {
            return new ResponseEntity<String>("ID NÃO FOI INFORMADO!", HttpStatus.OK);
        }

        Usuario user = usuarioRepository.saveAndFlush(usuario);

        return  new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long id){
        usuarioRepository.deleteById(id);

        return new ResponseEntity<String>("User deletado com sucesso!", HttpStatus.OK); //Retornando para que foi deletado!
    }

    @GetMapping(value = "/buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> search(@RequestParam(name = "iduser") Long iduser) { //Recebe os dados para consultar
        Usuario user = usuarioRepository.findById(iduser).get();

        return new ResponseEntity<Usuario>(user, HttpStatus.OK);

    }

    @GetMapping(value = "/buscarname") // mapeia a url
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarName(@RequestParam(name = "name") String name){
        List<Usuario> user  = usuarioRepository.buscarNome(name);

        return new ResponseEntity<List<Usuario>>(user, HttpStatus.OK);

    }


}