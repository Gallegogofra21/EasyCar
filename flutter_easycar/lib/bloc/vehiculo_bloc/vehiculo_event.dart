import 'package:equatable/equatable.dart';

abstract class VehiculosEvent extends Equatable {
  const VehiculosEvent();

  @override
  List<Object> get props => [];
}

class FetchVehiculo extends VehiculosEvent {
  const FetchVehiculo();

  @override
  List<Object> get props => [];
}

class FetchVehiculoDetails extends VehiculosEvent {
  final int id;
  const FetchVehiculoDetails(this.id);

  @override
  List<Object> get props => [];
}
