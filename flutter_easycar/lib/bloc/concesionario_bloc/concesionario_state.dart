import 'package:equatable/equatable.dart';
import 'package:flutter_easycar/models/concesionario.dart';

abstract class ConcesionariosState extends Equatable {
  const ConcesionariosState();

  @override 
  List<Object> get props => [];
}

class ConcesionariosInitial extends ConcesionariosState {}

class ConcesionariosFetched extends ConcesionariosState {
  final List<ConcesionarioContent> concesionarios;

  const ConcesionariosFetched(this.concesionarios);

  @override 
  List<Object> get props => [concesionarios];
}

class ConcesionarioFetchError extends ConcesionariosState {
  final String mensaje;
  const ConcesionarioFetchError(this.mensaje);

  @override 
  List<Object> get props => [mensaje];
}