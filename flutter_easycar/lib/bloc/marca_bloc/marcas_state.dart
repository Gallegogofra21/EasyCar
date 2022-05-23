import 'package:equatable/equatable.dart';
import 'package:flutter_easycar/models/marca.dart';

abstract class MarcasState extends Equatable {
  const MarcasState();

  @override
  List<Object> get props => [];
}

class MarcasInitial extends MarcasState {}

class MarcasFetched extends MarcasState {
  final List<MarcaContent> marcas;

  const MarcasFetched(this.marcas);

  @override
  List<Object> get props => [marcas];
}

class MarcaFetchError extends MarcasState {
  final String mensaje;
  const MarcaFetchError(this.mensaje);

  @override
  List<Object> get props => [mensaje];
}
