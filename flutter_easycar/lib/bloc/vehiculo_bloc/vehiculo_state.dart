import 'package:equatable/equatable.dart';
import 'package:flutter_easycar/models/vehiculo.dart';

abstract class VehiculosState extends Equatable {
  const VehiculosState();

  @override 
  List<Object> get props => [];
}

class VehiculosInitial extends VehiculosState {}

class VehiculosFetched extends VehiculosState {
  final List<VehiculoContent> vehiculos;

  const VehiculosFetched(this.vehiculos);

  @override 
  List<Object> get props => [vehiculos];
}

class VehiculoFetchError extends VehiculosState {
  final String mensaje;
  const VehiculoFetchError(this.mensaje);

  @override 
  List<Object> get props => [mensaje];
}