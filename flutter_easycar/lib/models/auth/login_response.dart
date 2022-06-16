class LoginResponse {
  LoginResponse({
    required this.id,
    required this.email,
    required this.username,
    required this.avatar,
    required this.perfil,
    required this.token,
  });
  late final dynamic id;
  late final dynamic email;
  late final dynamic username;
  late final dynamic avatar;
  late final dynamic perfil;
  late final dynamic token;

  LoginResponse.fromJson(Map<dynamic, dynamic> json) {
    id = json['id'];
    email = json['email'];
    username = json['username'];
    avatar = json['avatar'];
    perfil = json['perfil'];
    token = json['token'];
  }

  Map<dynamic, dynamic> toJson() {
    final _data = <dynamic, dynamic>{};
    _data['id'] = id;
    _data['email'] = email;
    _data['username'] = username;
    _data['avatar'] = avatar;
    _data['perfil'] = perfil;
    _data['token'] = token;
    return _data;
  }
}
