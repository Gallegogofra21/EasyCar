import 'dart:convert';
import 'package:flutter_easycar/constants.dart';
import 'package:flutter_easycar/models/auth/login_dto.dart';
import 'package:flutter_easycar/models/auth/login_response.dart';
import 'package:flutter_easycar/models/register_dto.dart';
import 'package:flutter_easycar/models/user.dart';
import 'package:flutter_easycar/repository/auth_repository/auth_repository.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart' as http;

import 'package:http_parser/http_parser.dart';

class AuthRepositoryImpl extends AuthRepository {
  final Client _client = Client();

  @override
  Future<LoginResponse> login(LoginDto loginDto) async {
    Map<String, String> headers = {
      'Content-Type': 'application/json',
    };

    final response = await _client.post(
        Uri.parse('${Constant.ApiBaseUrl}/auth/login'),
        headers: headers,
        body: jsonEncode(loginDto.toJson()));
    if (response.statusCode == 201) {
      return LoginResponse.fromJson(json.decode(response.body));
    } else {
      throw Exception('Fail to login');
    }
  }

  @override
  Future<User> register(RegisterDto registerDto, String image) async {
    var uri = Uri.parse('${Constant.ApiBaseUrl}/auth/register/usuario');
    var request = http.MultipartRequest('POST', uri)
      ..files.add(await http.MultipartFile.fromPath('file', image,
          contentType: MediaType('multipart', 'form-data')))
      ..files.add(await http.MultipartFile.fromString(
          'user', jsonEncode(registerDto.toJson()),
          contentType: MediaType('application', 'json')));

    var response = await request.send();
    final respStr = await response.stream.bytesToString();
    print(respStr);

    if (response.statusCode == 201) {
      return User.fromJson(await jsonDecode(respStr));
    } else {
      print(response.statusCode);
      throw Exception('Fail to register');
    }
  }

  @override
  Future<User> edit(RegisterDto registerDto, String image) async {
    final prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('token');

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ${token}'
    };

    var uri = await Uri.parse('${Constant.ApiBaseUrl}/profile/');
    var request = http.MultipartRequest('PUT', uri)
      ..files.add(await http.MultipartFile.fromPath('file', image,
          contentType: MediaType('multipart', 'form-data')))
      ..files.add(await http.MultipartFile.fromString(
          'user', jsonEncode(registerDto.toJson()),
          contentType: MediaType('application', 'json')))
      ..headers.addAll(headers);

    var response = await request.send();
    final respStr = await response.stream.bytesToString();
    print(respStr);

    if (response.statusCode == 200) {
      return User.fromJson(await jsonDecode(respStr));
    } else {
      print(response.statusCode);
      throw Exception('Fail to edit');
    }
  }
}
