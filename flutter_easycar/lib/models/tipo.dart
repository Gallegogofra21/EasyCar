class TipoResponse {
  TipoResponse({
    required this.content,
    required this.pageable,
    required this.totalPages,
    required this.totalElements,
    required this.last,
    required this.size,
    required this.number,
    required this.sort,
    required this.numberOfElements,
    required this.first,
    required this.empty,
  });
  late final List<TipoContent> content;
  late final Pageable pageable;
  late final int totalPages;
  late final int totalElements;
  late final bool last;
  late final int size;
  late final int number;
  late final Sort sort;
  late final int numberOfElements;
  late final bool first;
  late final bool empty;

  TipoResponse.fromJson(Map<String, dynamic> json) {
    content =
        List.from(json['content']).map((e) => TipoContent.fromJson(e)).toList();
    pageable = Pageable.fromJson(json['pageable']);
    totalPages = json['totalPages'];
    totalElements = json['totalElements'];
    last = json['last'];
    size = json['size'];
    number = json['number'];
    sort = Sort.fromJson(json['sort']);
    numberOfElements = json['numberOfElements'];
    first = json['first'];
    empty = json['empty'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['content'] = content.map((e) => e.toJson()).toList();
    _data['pageable'] = pageable.toJson();
    _data['totalPages'] = totalPages;
    _data['totalElements'] = totalElements;
    _data['last'] = last;
    _data['size'] = size;
    _data['number'] = number;
    _data['sort'] = sort.toJson();
    _data['numberOfElements'] = numberOfElements;
    _data['first'] = first;
    _data['empty'] = empty;
    return _data;
  }
}

class TipoContent {
  TipoContent({
    required this.id,
    required this.nombre,
    required this.foto,
    required this.vehiculos,
  });
  late final int id;
  late final String nombre;
  late final String foto;
  late final List<TipoVehiculo> vehiculos;

  TipoContent.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    nombre = json['nombre'];
    foto = json['foto'];
    vehiculos = List.from(json['vehiculos'])
        .map((e) => TipoVehiculo.fromJson(e))
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

class TipoVehiculo {
  TipoVehiculo({
    required this.id,
    required this.modelo,
    required this.version,
    required this.fechaMatriculacion,
    required this.kilometraje,
    required this.potencia,
    required this.marchas,
    required this.precio,
    required this.marca,
    this.user,
    required this.foto1,
    required this.foto2,
    required this.foto3,
    required this.foto4,
    required this.llantas,
    required this.distribucion,
    required this.procedencia,
    required this.traccion,
  });
  late final int id;
  late final String modelo;
  late final String version;
  late final String fechaMatriculacion;
  late final String kilometraje;
  late final String potencia;
  late final String marchas;
  late final double precio;
  late final Marca marca;
  late final Null user;
  late final String foto1;
  late final String foto2;
  late final String foto3;
  late final String foto4;
  late final String llantas;
  late final String distribucion;
  late final String procedencia;
  late final String traccion;

  TipoVehiculo.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    modelo = json['modelo'];
    version = json['version'];
    fechaMatriculacion = json['fechaMatriculacion'];
    kilometraje = json['kilometraje'];
    potencia = json['potencia'];
    marchas = json['marchas'];
    precio = json['precio'];
    marca = Marca.fromJson(json['marca']);
    user = null;
    foto1 = json['foto1'];
    foto2 = json['foto2'];
    foto3 = json['foto3'];
    foto4 = json['foto4'];
    llantas = json['llantas'];
    distribucion = json['distribucion'];
    procedencia = json['procedencia'];
    traccion = json['traccion'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['modelo'] = modelo;
    _data['version'] = version;
    _data['fechaMatriculacion'] = fechaMatriculacion;
    _data['kilometraje'] = kilometraje;
    _data['potencia'] = potencia;
    _data['marchas'] = marchas;
    _data['precio'] = precio;
    _data['marca'] = marca.toJson();
    _data['user'] = user;
    _data['foto1'] = foto1;
    _data['foto2'] = foto2;
    _data['foto3'] = foto3;
    _data['foto4'] = foto4;
    _data['llantas'] = llantas;
    _data['distribucion'] = distribucion;
    _data['procedencia'] = procedencia;
    _data['traccion'] = traccion;
    return _data;
  }
}

class Marca {
  Marca({
    required this.id,
    required this.nombre,
    required this.foto,
  });
  late final int id;
  late final String nombre;
  late final String foto;

  Marca.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    nombre = json['nombre'];
    foto = json['foto'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['nombre'] = nombre;
    _data['foto'] = foto;
    return _data;
  }
}

class Pageable {
  Pageable({
    required this.sort,
    required this.offset,
    required this.pageNumber,
    required this.pageSize,
    required this.paged,
    required this.unpaged,
  });
  late final Sort sort;
  late final int offset;
  late final int pageNumber;
  late final int pageSize;
  late final bool paged;
  late final bool unpaged;

  Pageable.fromJson(Map<String, dynamic> json) {
    sort = Sort.fromJson(json['sort']);
    offset = json['offset'];
    pageNumber = json['pageNumber'];
    pageSize = json['pageSize'];
    paged = json['paged'];
    unpaged = json['unpaged'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['sort'] = sort.toJson();
    _data['offset'] = offset;
    _data['pageNumber'] = pageNumber;
    _data['pageSize'] = pageSize;
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
