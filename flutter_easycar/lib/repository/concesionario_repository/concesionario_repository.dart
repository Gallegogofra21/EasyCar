import 'package:flutter_easycar/models/concesionario.dart';

abstract class ConcesionarioRepository {
  Future<List<ConcesionarioContent>> fetchConcesionarios();
}