import 'dart:convert';

import 'package:flutter_easycar/models/user.dart';
import 'package:flutter_easycar/repository/user_repository/user_repository.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart';

import '../../constants.dart';

class UserRepositoryImpl extends UserRepository {
  final Client _client = Client();

  @override
  Future<User> fetchUser() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('token');
    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ${token}'
    };

    final response = await _client.get(Uri.parse('${Constant.ApiBaseUrl}/me'),
        headers: headers);
    if (response.statusCode == 200) {
      return User.fromJson(json.decode(response.body));
    } else {
      throw Exception('Fail to load users');
    }
  }
}
