class RegisterDto {
  String? username;
  String? email;
  String? fechaNacimiento;
  String? password;
  String? password2;

  RegisterDto(
      {this.username,
      this.email,
      this.fechaNacimiento,
      this.password,
      this.password2});

  RegisterDto.fromJson(Map<String, dynamic> json) {
    username = json['username'];
    email = json['email'];
    fechaNacimiento = json['fechaNacimiento'];
    password = json['password'];
    password2 = json['password2'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['username'] = username;
    data['email'] = email;
    data['fechaNacimiento'] = fechaNacimiento;
    data['password'] = password;
    data['password2'] = password2;
    return data;
  }
}
