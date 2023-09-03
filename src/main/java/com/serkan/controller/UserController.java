package com.serkan.controller;

import com.serkan.dto.request.RegisterRequestDto;
import com.serkan.repository.entity.User;
import com.serkan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.serkan.constant.RestApiUrl.*;// static yazmasaydık RestApi.User yazacaktık

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    /*
        create user metodu dısardan name surname email password phone
        alsın daha sonra bunlarla bir user olusturup database e kayıt yapalım
        bu işlem için bir end point ve service sınıfında gerkli metotları yazalım
     */
//    @Autowired // @RequiredArgsConstructor ile final yaptığımız userService'e constructor oluşturduk ondan autowired'a gerek kalmadı
    private final UserService userService;

    //http://localhost:8090/user/save?name=Ahmet&surname=MEhmet&email=abcdaaa@abc.com&phone=12365554987&password=1111
    @GetMapping(SAVE)
    public ResponseEntity<?> createUser(String name, String surname, String email, String password, String phone) {
        //generic içi user'dı ? yaptık hatayı görmek için
        User user = User.builder().name(name).surName(surname).email(email).password(password).phone(phone).build();
        try {
            userService.save(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Kullanıcı oluşturulamadı");
        }

    }
    @GetMapping(REGISTER)
    public ResponseEntity<User> register(RegisterRequestDto dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

    @PostMapping(REGISTER)
    public ResponseEntity<User> register2(@RequestBody RegisterRequestDto dto) {//postman almaz request body yoksa
        return ResponseEntity.ok(userService.register(dto));
    }

    @GetMapping(FINDBYID+"/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {

        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<User> findById2(@RequestParam (required = false)Long id) { //(@RequestParam (required = false,defaultValue="1")Long id)'de yazarsın hep 1. seçim gelir
        if(id==null){
            throw new RuntimeException("id null");
        }
        return ResponseEntity.ok(userService.findById(id));
    }



}
