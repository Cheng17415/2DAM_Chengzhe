package com.cheng.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheng.model.Usuario;
import com.cheng.repository.UsuarioRepository;
import com.cheng.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService{
	
	@Autowired
	UsuarioRepository usuariosRepo;
	@Override
	public void guardar(Usuario usuario) {
		usuariosRepo.save(usuario);
	}

	@Override
	public void eliminar(Integer idUsuario) {
		usuariosRepo.deleteById(idUsuario);
	}

	@Override
	public List<Usuario> buscarTodos() {
		return usuariosRepo.findAll();
	}

	@Override
	public List<Usuario> buscarRegistrados() {
		return usuariosRepo.findByFechaRegistroNotNull();
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		return usuariosRepo.findByUsername(username);
	}

	@Override
	public Usuario buscarPorId(Integer idUsuario) {
		Optional<Usuario> optional = usuariosRepo.findById(idUsuario);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public int bloquear(int idUsuario) {
		return usuariosRepo.lock(idUsuario);
	}

	@Override
	public int activar(int idUsuario) {
		return usuariosRepo.unlock(idUsuario);
	}

}
