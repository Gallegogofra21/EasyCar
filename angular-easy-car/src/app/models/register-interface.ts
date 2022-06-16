export interface RegisterAdmin {
  username: string;
  nombre: string;
  apellidos: string;
  telefono: string;
  email: string;
  fechaNacimiento: string;
  password: string;
  password2: string;
  rol: "ADMIN";
}

export interface RegisterGestor {
  username: string;
  nombre: string;
  apellidos: string;
  telefono: string;
  email: string;
  fechaNacimiento: string;
  password: string;
  password2: string;
  rol: "GESTOR";
}

export class RegisterAdminDto {
  username!: string;
  nombre!: string;
  apellidos!: string;
  telefono!: string;
  email!: string;
  fechaNacimiento!: string;
  password!: string;
  password2!: string;
  rol!: "ADMIN";
}

export class RegisterGestorDto {
  username: string;
  nombre: string;
  apellidos: string;
  telefono: string;
  email: string;
  fechaNacimiento: string;
  password: string;
  password2: string;

  constructor() {
    this.username = '';
    this.nombre = '';
    this.apellidos = '';
    this.telefono = '';
    this.email = '';
    this.fechaNacimiento = '';
    this.password = '';
    this.password2 = '';
  }
}
