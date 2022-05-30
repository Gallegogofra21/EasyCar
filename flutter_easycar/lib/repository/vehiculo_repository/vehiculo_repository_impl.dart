import 'package:flutter_easycar/constants.dart';
import 'package:flutter_easycar/models/vehiculo_details.dart';
import 'package:http/http.dart';
import 'dart:convert';
import 'package:flutter_easycar/models/vehiculo.dart';
import 'package:flutter_easycar/repository/vehiculo_repository/vehiculo_repository.dart';
import 'package:shared_preferences/shared_preferences.dart';

class VehiculoRepositoryImpl extends VehiculoRepository {
  final Client _client = Client();

  @override
  Future<List<VehiculoContent>> fetchVehiculos() async {
    final prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('token');

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ${token}'
    };

    final response = await _client
        .get(Uri.parse('${Constant.ApiBaseUrl}/vehiculo/'), headers: headers);
    if (response.statusCode == 200) {
      return VehiculoResponse.fromJson(json.decode(response.body)).content;
    } else {
      throw Exception('Fail to load vehiculos');
    }
  }

  @override
  Future<VehiculoDetails> fetchVehiculoDetails(int id) async {
    final prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('token');

    Map<String, String> headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ${token}'
    };

    final response = await _client.get(
        Uri.parse('${Constant.ApiBaseUrl}/vehiculo/{id}'),
        headers: headers);
    if (response.statusCode == 200) {
      return VehiculoDetails.fromJson(json.decode(response.body));
    } else {
      throw Exception('Fail to load vehiculos');
    }
  }
}
