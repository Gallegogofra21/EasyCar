import 'package:equatable/equatable.dart';
import 'package:flutter_easycar/models/tipo.dart';
import 'package:flutter_easycar/models/tipo_details.dart';

abstract class TiposState extends Equatable {
  const TiposState();

  @override
  List<Object> get props => [];
}

class TiposInitial extends TiposState {}

class TiposFetched extends TiposState {
  final List<TipoContent> tipos;

  const TiposFetched(this.tipos);

  @override
  List<Object> get props => [tipos];
}

class TipoDetailsFetched extends TiposState {
  final TipoDetails tipo;
  final int id;

  const TipoDetailsFetched(this.tipo, this.id);

  @override
  List<Object> get props => [this.tipo];
}

class TipoFetchError extends TiposState {
  final String mensaje;
  const TipoFetchError(this.mensaje);

  @override
  List<Object> get props => [mensaje];
}
