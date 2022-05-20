import 'package:flutter_easycar/models/vehiculo.dart';

abstract class VehiculoRepository {
  Future<List<VehiculoContent>> fetchVehiculos();
}