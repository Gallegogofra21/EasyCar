import 'package:bloc/bloc.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_event.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_state.dart';
import 'package:flutter_easycar/repository/vehiculo_repository/vehiculo_repository.dart';

class VehiculosBloc extends Bloc<VehiculosEvent, VehiculosState> {
  final VehiculoRepository vehiculoRepository;

  VehiculosBloc(this.vehiculoRepository) : super(VehiculosInitial()) {
    on<FetchVehiculo>(_vehiculosFetched);
    on<FetchVehiculoDetails>(_vehiculoDetailsFetched);
    // on<FetchVehiculosByMarca>(_vehiculosByMarcaFetched);
  }

  void _vehiculosFetched(
      FetchVehiculo event, Emitter<VehiculosState> emit) async {
    try {
      final vehiculos = await vehiculoRepository.fetchVehiculos();
      emit(VehiculosFetched(vehiculos));
      return;
    } on Exception catch (e) {
      emit(VehiculoFetchError(e.toString()));
    }
  }

  void _vehiculoDetailsFetched(
      FetchVehiculoDetails event, Emitter<VehiculosState> emit) async {
    try {
      final vehiculo = await vehiculoRepository.fetchVehiculoDetails(event.id);
      emit(VehiculosDetailsFetched(vehiculo, event.id));
      return;
    } on Exception catch (e) {
      emit(VehiculoFetchError(e.toString()));
    }
  }

  // void _vehiculosByMarcaFetched(
  //     FetchVehiculosByMarca event, Emitter<VehiculosState> emit) async {
  //   try {
  //     final vehiculos = await vehiculoRepository.fetchVehiculosByMarca(id);
  //     emit(VehiculosByMarcaFetched(vehiculos));
  //     return;
  //   } on Exception catch (e) {
  //     emit(VehiculoFetchError(e.toString()));
  //   }
  // }
}
