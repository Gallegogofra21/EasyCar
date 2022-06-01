class VehiculoResponse {
  VehiculoResponse({
    required this.content,
    required this.pageable,
    required this.last,
    required this.totalPages,
    required this.totalElements,
    required this.size,
    required this.number,
    required this.sort,
    required this.first,
    required this.numberOfElements,
    required this.empty,
  });
  late final List<VehiculoContent> content;
  late final Pageable pageable;
  late final bool last;
  late final int totalPages;
  late final int totalElements;
  late final int size;
  late final int number;
  late final Sort sort;
  late final bool first;
  late final int numberOfElements;
  late final bool empty;

  VehiculoResponse.fromJson(Map<String, dynamic> json) {
    content = List.from(json['content'])
        .map((e) => VehiculoContent.fromJson(e))
        .toList();
    pageable = Pageable.fromJson(json['pageable']);
    last = json['last'];
    totalPages = json['totalPages'];
    totalElements = json['totalElements'];
    size = json['size'];
    number = json['number'];
    sort = Sort.fromJson(json['sort']);
    first = json['first'];
    numberOfElements = json['numberOfElements'];
    empty = json['empty'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['content'] = content.map((e) => e.toJson()).toList();
    _data['pageable'] = pageable.toJson();
    _data['last'] = last;
    _data['totalPages'] = totalPages;
    _data['totalElements'] = totalElements;
    _data['size'] = size;
    _data['number'] = number;
    _data['sort'] = sort.toJson();
    _data['first'] = first;
    _data['numberOfElements'] = numberOfElements;
    _data['empty'] = empty;
    return _data;
  }
}

class VehiculoContent {
  VehiculoContent({
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

  VehiculoContent.fromJson(Map<String, dynamic> json) {
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

class Pageable {
  Pageable({
    required this.sort,
    required this.offset,
    required this.pageSize,
    required this.pageNumber,
    required this.paged,
    required this.unpaged,
  });
  late final Sort sort;
  late final int offset;
  late final int pageSize;
  late final int pageNumber;
  late final bool paged;
  late final bool unpaged;

  Pageable.fromJson(Map<String, dynamic> json) {
    sort = Sort.fromJson(json['sort']);
    offset = json['offset'];
    pageSize = json['pageSize'];
    pageNumber = json['pageNumber'];
    paged = json['paged'];
    unpaged = json['unpaged'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['sort'] = sort.toJson();
    _data['offset'] = offset;
    _data['pageSize'] = pageSize;
    _data['pageNumber'] = pageNumber;
    _data['paged'] = paged;
    _data['unpaged'] = unpaged;
    return _data;
  }
}

class Sort {
  Sort({
    required this.empty,
    required this.sorted,
    required this.unsorted,
  });
  late final bool empty;
  late final bool sorted;
  late final bool unsorted;

  Sort.fromJson(Map<String, dynamic> json) {
    empty = json['empty'];
    sorted = json['sorted'];
    unsorted = json['unsorted'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['empty'] = empty;
    _data['sorted'] = sorted;
    _data['unsorted'] = unsorted;
    return _data;
  }
}
