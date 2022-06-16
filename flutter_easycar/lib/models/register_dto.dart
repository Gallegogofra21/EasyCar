class RegisterDto {
  String? username;
  String? nombre;
  String? apellidos;
  String? telefono;
  String? email;
  String? fechaNacimiento;
  String? password;
  String? password2;

  RegisterDto(
      {this.username,
      this.nombre,
      this.apellidos,
      this.telefono,
      this.email,
      this.fechaNacimiento,
      this.password,
      this.password2});

  RegisterDto.fromJson(Map<String, dynamic> json) {
    username = json['username'];
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    telefono = json['telefono'];
    email = json['email'];
    fechaNacimiento = json['fechaNacimiento'];
    password = json['password'];
    password2 = json['password2'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['username'] = username;
    data['nombre'] = nombre;
    data['apellidos'] = apellidos;
    data['telefono'] = telefono;
    data['email'] = email;
    data['fechaNacimiento'] = fechaNacimiento;
    data['password'] = password;
    data['password2'] = password2;
    return data;
  }
}
