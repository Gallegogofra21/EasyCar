import 'package:equatable/equatable.dart';
import 'package:flutter_easycar/models/tipo.dart';

abstract class TiposState extends Equatable {
  const TiposState();

  @override
  List<Object> get props => [];
}

class TiposInitial extends TiposState {}

class TiposFetched extends TiposState {
  final List<Content> tipos;

  const TiposFetched(this.tipos);

  @override
  List<Object> get props => [tipos];
}

class TipoFetchError extends TiposState {
  final String mensaje;
  const TipoFetchError(this.mensaje);

  @override
  List<Object> get props => [mensaje];
}
