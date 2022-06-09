import 'package:flutter_easycar/models/tipo.dart';
import 'package:flutter_easycar/models/tipo_details.dart';

abstract class TipoRepository {
  Future<List<TipoContent>> fetchTipos();
  Future<TipoDetails> fetchTipoDetails(int id);
  Future<TipoVehiculos> fetchTipoVehiculos();
}
