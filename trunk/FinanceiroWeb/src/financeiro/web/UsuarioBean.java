package financeiro.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import financeiro.usuario.Usuario;
import financeiro.usuario.UsuarioRN;

@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private String confirmaSenha;
	private List<Usuario> lista;
	private String destinoSalvar;
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		String senha = this.usuario.getSenha();
		if(!senha.equals(this.confirmaSenha)){
			FacesMessage facesMessage = new FacesMessage("A senha não foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			return null;
		}
		
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		return "usuarioSucesso";
	}
	
	public String novo(){
		this.destinoSalvar = "usuarioSucesso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "usuario";
	}
	
	public String editar(){
		this.confirmaSenha = this.usuario.getSenha();
		return "/publico/usuario";
	}
	
	public String excluir(){
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(usuario);
		this.lista = null;
		return null;
	}
	
	public String ativar(){
		if((this.usuario != null)&&
				(this.usuario.isAtivo())
				){
			this.usuario.setAtivo(false);
		} else{
			this.usuario.setAtivo(true);
		}
		
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(usuario);
		
		return null;
	}
	
	public List<Usuario> getLista(){
		if(this.lista==null){
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return this.lista;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}
}