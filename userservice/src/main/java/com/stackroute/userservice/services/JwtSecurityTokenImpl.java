package com.stackroute.userservice.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.userservice.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JwtSecurityTokenImpl  implements SecurityTokenGenerator{

	@Override
	public Map<String, String> generateToken(User user) {
		// TODO Auto-generated method stub
		String jwtToken ="";
		jwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256,"secretkey").compact();
		Map<String,String> map = new HashMap();
		map.put("token", jwtToken);
		map.put("message", "Welcome! Successfully logged in");
		
		return map;
	}

}
