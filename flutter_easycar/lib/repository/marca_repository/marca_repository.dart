import 'package:flutter_easycar/models/marca.dart';

abstract class MarcaRepository {
  Future<List<MarcaContent>> fetchMarcas();
}
