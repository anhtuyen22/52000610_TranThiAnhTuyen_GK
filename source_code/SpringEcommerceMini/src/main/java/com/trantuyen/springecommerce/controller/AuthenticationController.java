package com.trantuyen.springecommerce.controller;

import com.trantuyen.springecommerce.entity.Customer;
import com.trantuyen.springecommerce.models.AuthenticationRequest;
import com.trantuyen.springecommerce.models.CustomerModel;
import com.trantuyen.springecommerce.models.ResponseObject;
import com.trantuyen.springecommerce.service.CustomerService;
import com.trantuyen.springecommerce.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final CustomerService userDetailService;
    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, CustomerService userDetailService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("NOT_FOUND", "Username and password incorrect", ""));
        }
        final Customer customer = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(customer);
        CustomerModel customerModel = CustomerModel.builder()
                .id(customer.getId())
                .admin(false)
                .avatar(customer.getAvatar())
                .username(customer.getUsername())
                .accessToken(jwt).build();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Authenticate success", customerModel));
    }
}
