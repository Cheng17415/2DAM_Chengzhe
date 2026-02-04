from rich.console import Console
from app.service.usuario_service import obtener_usu_email
from app.util.utileria import verificar_contrasena, pedir_contrasena

def iniciar_sesion(email, contrasena):
    usuario = obtener_usu_email(email)
    console = Console()
    
    if not usuario:
        console.print("[red]Usuario no encontrado[/red]")
        return None
    
    if not usuario.activo:  # type: ignore
        console.print("[red]Usuario desactivado. No se puede iniciar sesion[/red]")
        return None
    
    if not verificar_contrasena(contrasena, usuario.password_hash):
        console.print(f"[red]Contrasena incorrecta[/red]")
        return None
    
    console.print(f"[green]Bienvenido de nuevo {usuario.nombre}[/green]")
    return usuario

def iniciar_sesion_usuario():
    email = input("Introduzca su email: ")
    contrasena = pedir_contrasena("Introduzca su contrasena: ")
    return iniciar_sesion(email, contrasena)
    
def iniciar_sesion_invitado():
    return iniciar_sesion("invitado@invitado.com", "invitado")