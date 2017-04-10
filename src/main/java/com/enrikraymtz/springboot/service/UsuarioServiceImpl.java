package com.enrikraymtz.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.enrikraymtz.springboot.model.User;
import com.enrikraymtz.springboot.repository.UsuarioRepository;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;
	
	@Override
	public List<User> findAllUsers() {
		return usuarioRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		return usuarioRepository.findOne(id);
	}

	@Override
	public User findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

	@Override
	public void addUsuario(User user) {
		usuarioRepository.save(user);
	}

	@Override
	public void updateUsuario(User user) {
		addUsuario(user);
	}

	@Override
	public void deleteUsuario(Long id) {
		usuarioRepository.delete(id);
	}

	@Override
	public boolean isUsuarioExist(User user) {
		return findByUsername(user.getUsername()) != null;
	}

}
