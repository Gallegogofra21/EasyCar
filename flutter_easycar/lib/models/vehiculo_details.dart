class VehiculoDetails {
  VehiculoDetails({
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
    required this.foto1,
    required this.llantas,
    required this.distribucion,
    required this.procedencia,
    required this.traccion,
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
  late final String foto1;
  late final String llantas;
  late final String distribucion;
  late final String procedencia;
  late final String traccion;
  late final int concesionario;

  VehiculoDetails.fromJson(Map<String, dynamic> json) {
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
    foto1 = json['foto1'];
    llantas = json['llantas'];
    distribucion = json['distribucion'];
    procedencia = json['procedencia'];
    traccion = json['traccion'];
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
    _data['foto1'] = foto1;
    _data['llantas'] = llantas;
    _data['distribucion'] = distribucion;
    _data['procedencia'] = procedencia;
    _data['traccion'] = traccion;
    _data['concesionario'] = concesionario;
    return _data;
  }
}
