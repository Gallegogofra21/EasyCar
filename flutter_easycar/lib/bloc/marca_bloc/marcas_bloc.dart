import 'package:bloc/bloc.dart';
import 'package:flutter_easycar/repository/marca_repository/marca_repository.dart';

import 'marcas_event.dart';
import 'marcas_state.dart';

class MarcasBloc extends Bloc<MarcasEvent, MarcasState> {
  final MarcaRepository marcaRepository;

  MarcasBloc(this.marcaRepository) : super(MarcasInitial()) {
    on<FetchMarca>(_marcasFetched);
  }

  void _marcasFetched(FetchMarca event, Emitter<MarcasState> emit) async {
    try {
      final marcas = await marcaRepository.fetchMarcas();
      emit(MarcasFetched(marcas));
      return;
    } on Exception catch (e) {
      emit(MarcaFetchError(e.toString()));
    }
  }
}
