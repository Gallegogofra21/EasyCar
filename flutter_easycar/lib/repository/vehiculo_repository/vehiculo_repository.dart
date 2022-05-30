import 'package:flutter_easycar/models/vehiculo.dart';
import 'package:flutter_easycar/models/vehiculo_details.dart';

abstract class VehiculoRepository {
  Future<List<VehiculoContent>> fetchVehiculos();
  Future<VehiculoDetails> fetchVehiculoDetails(int id);
}
