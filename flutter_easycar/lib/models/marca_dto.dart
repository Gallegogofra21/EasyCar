class MarcaDto {
  String? nombre;
  String? foto;

  MarcaDto({this.nombre, this.foto});

  MarcaDto.fromJson(Map<String, dynamic> json) {
    nombre = json['nombre'];
    foto = json['foto'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['nombre'] = nombre;
    data['foto'] = foto;
    return data;
  }
}
