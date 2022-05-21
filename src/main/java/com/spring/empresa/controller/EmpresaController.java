package com.spring.empresa.controller;

import java.util.List;

import com.spring.empresa.dao.EmpresaDao;
import com.spring.empresa.dao.UserDao;
import com.spring.empresa.entity.AuthRequest;
import com.spring.empresa.entity.Empresa;
import com.spring.empresa.entity.User;
import com.spring.empresa.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpresaController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmpresaDao empresaDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/createEnterprise")
    public ResponseEntity<Empresa> crearEmpresa(@RequestBody Empresa empresa) {
        try {
            return new ResponseEntity<>(empresaDao.save(empresa), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getLastThreeEnterprises")
    public ResponseEntity<List<Empresa>> obtenerUltimasTresEmpresas() {
        try {
            List<Empresa> listEmpresa = empresaDao.getLastThreeRecords();
            if (listEmpresa.isEmpty() || listEmpresa.size() == 0) {
                return new ResponseEntity<List<Empresa>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Empresa>>(listEmpresa, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Empresa>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/createUserValidate")
    public ResponseEntity<User> crearUsuario(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userDao.save(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
    
}
