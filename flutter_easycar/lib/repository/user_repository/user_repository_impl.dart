import 'dart:convert';

import 'package:flutter_easycar/models/user.dart';
import 'package:flutter_easycar/repository/user_repository/user_repository.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart';

class UserRepositoryImpl extends UserRepository {
  final Client _client = Client();

  @override
  Future<User> fetchUsers(String type) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();

    final response = await _client.get(Uri.parse('http://10.0.2.2:8080/me'),
        headers: {'Authorization': 'Bearer ${prefs.getString('token')}'});
    if (response.statusCode == 200) {
      return User.fromJson(json.decode(response.body));
    } else {
      throw Exception('Fail to load users');
    }
  }
}
