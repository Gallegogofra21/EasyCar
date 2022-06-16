class User {
  User({
    required this.id,
    required this.username,
    required this.nombre,
    required this.apellidos,
    required this.telefono,
    required this.email,
    required this.avatar,
  });
  late final int id;
  late final String username;
  late final String nombre;
  late final String apellidos;
  late final String telefono;
  late final String email;
  late final String avatar;

  User.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    username = json['username'];
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    telefono = json['telefono'];
    email = json['email'];
    avatar = json['avatar'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['username'] = username;
    _data['nombre'] = nombre;
    _data['apellidos'] = apellidos;
    _data['telefono'] = telefono;
    _data['email'] = email;
    _data['avatar'] = avatar;
    return _data;
  }
}
