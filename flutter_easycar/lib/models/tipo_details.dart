class TipoDetails {
  TipoDetails({
    required this.id,
    required this.nombre,
    required this.foto,
    required this.vehiculos,
  });
  late final int id;
  late final String nombre;
  late final String foto;
  late final List<TipoVehiculos> vehiculos;

  TipoDetails.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    nombre = json['nombre'];
    foto = json['foto'];
    vehiculos = List.from(json['vehiculos'])
        .map((e) => TipoVehiculos.fromJson(e))
        .toList();
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['nombre'] = nombre;
    _data['foto'] = foto;
    _data['vehiculos'] = vehiculos.map((e) => e.toJson()).toList();
    return _data;
  }
}

class TipoVehiculos {
  TipoVehiculos({
    required this.id,
    required this.version,
    required this.modelo,
    required this.fechaMatriculacion,
    required this.kilometraje,
    required this.potencia,
    required this.marchas,
    required this.precio,
    required this.nombreMarca,
    required this.tipo,
    required this.llantas,
    required this.distribucion,
    required this.procedencia,
    required this.traccion,
    required this.foto1,
    required this.foto2,
    required this.foto3,
    required this.foto4,
    required this.concesionario,
  });
  late final int id;
  late final String version;
  late final String modelo;
  late final String fechaMatriculacion;
  late final String kilometraje;
  late final String potencia;
  late final String marchas;
  late final double precio;
  late final String nombreMarca;
  late final int tipo;
  late final String llantas;
  late final String distribucion;
  late final String procedencia;
  late final String traccion;
  late final String foto1;
  late final String foto2;
  late final String foto3;
  late final String foto4;
  late final int concesionario;

  TipoVehiculos.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    version = json['version'];
    modelo = json['modelo'];
    fechaMatriculacion = json['fechaMatriculacion'];
    kilometraje = json['kilometraje'];
    potencia = json['potencia'];
    marchas = json['marchas'];
    precio = json['precio'];
    nombreMarca = json['nombreMarca'];
    tipo = json['tipo'];
    llantas = json['llantas'];
    distribucion = json['distribucion'];
    procedencia = json['procedencia'];
    traccion = json['traccion'];
    foto1 = json['foto1'];
    foto2 = json['foto2'];
    foto3 = json['foto3'];
    foto4 = json['foto4'];
    concesionario = json['concesionario'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['version'] = version;
    _data['modelo'] = modelo;
    _data['fechaMatriculacion'] = fechaMatriculacion;
    _data['kilometraje'] = kilometraje;
    _data['potencia'] = potencia;
    _data['marchas'] = marchas;
    _data['precio'] = precio;
    _data['nombreMarca'] = nombreMarca;
    _data['tipo'] = tipo;
    _data['llantas'] = llantas;
    _data['distribucion'] = distribucion;
    _data['procedencia'] = procedencia;
    _data['traccion'] = traccion;
    _data['foto1'] = foto1;
    _data['foto2'] = foto2;
    _data['foto3'] = foto3;
    _data['foto4'] = foto4;
    _data['concesionario'] = concesionario;
    return _data;
  }
}
