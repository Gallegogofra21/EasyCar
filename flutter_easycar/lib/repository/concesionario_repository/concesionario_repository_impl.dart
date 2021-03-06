import 'package:flutter_easycar/models/concesionario.dart';
import 'package:flutter_easycar/repository/concesionario_repository/concesionario_repository.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart';
import 'dart:convert';

import '../../constants.dart';

class ConcesionarioRepositoryImpl extends ConcesionarioRepository {
  final Client _client = Client();

  @override
  Future<List<ConcesionarioContent>> fetchConcesionarios() async {
    final prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('token');

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ${token}'
    };

    final response = await _client.get(
        Uri.parse('${Constant.ApiBaseUrl}/concesionario/'),
        headers: headers);
    if (response.statusCode == 200) {
      return ConcesionarioResponse.fromJson(json.decode(response.body)).content;
    } else {
      throw Exception('Fail to load concesionarios');
    }
  }
}
