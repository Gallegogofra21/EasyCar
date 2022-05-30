import 'dart:convert';

import 'package:flutter_easycar/models/marca.dart';

import 'package:flutter_easycar/repository/marca_repository/marca_repository.dart';

import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../constants.dart';

class MarcaRepositoryImpl extends MarcaRepository {
  final Client _client = Client();

  @override
  Future<List<MarcaContent>> fetchMarcas() async {
    final prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('token');

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ${token}'
    };
    final response = await _client
        .get(Uri.parse('${Constant.ApiBaseUrl}/marca/'), headers: headers);
    if (response.statusCode == 200) {
      return MarcaResponse.fromJson(json.decode(response.body)).content;
    } else {
      throw Exception('Fail to load marcas');
    }
  }
}
