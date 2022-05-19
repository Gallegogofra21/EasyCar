import 'dart:convert';

import 'package:flutter_easycar/models/tipo.dart';
import 'package:flutter_easycar/models/tipo_dto.dart';
import 'package:flutter_easycar/repository/tipo_repository/tipo_repository.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';

class TipoRepositoryImpl extends TipoRepository {
  final Client _client = Client();

  @override
  Future<List<Content>> fetchTipos() async {
    final prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('token');
    print('Prueba token ${token}');

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ${token}'
    };
    final response = await _client.get(Uri.parse('http://10.0.2.2:8080/tipo/'),
        headers: headers);
    if (response.statusCode == 200) {
      return TipoResponse.fromJson(json.decode(response.body)).content;
    } else {
      throw Exception('Fail to load tipos');
    }
  }
}
