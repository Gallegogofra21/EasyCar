import 'package:flutter_easycar/models/tipo.dart';

abstract class TipoRepository {
  Future<List<TipoContent>> fetchTipos();
}
