import 'package:bloc/bloc.dart';
import 'package:flutter_easycar/bloc/tipo_bloc/tipos_event.dart';
import 'package:flutter_easycar/bloc/tipo_bloc/tipos_state.dart';
import 'package:flutter_easycar/repository/tipo_repository/tipo_repository.dart';

class TiposBloc extends Bloc<TiposEvent, TiposState> {
  final TipoRepository tipoRepository;

  TiposBloc(this.tipoRepository) : super(TiposInitial()) {
    on<FetchTipoWithType>(_tiposFetched);
    on<FetchTipoDetails>(_tipoDetailsFetched);
  }

  void _tiposFetched(FetchTipoWithType event, Emitter<TiposState> emit) async {
    try {
      final tipos = await tipoRepository.fetchTipos();
      emit(TiposFetched(tipos));
      return;
    } on Exception catch (e) {
      emit(TipoFetchError(e.toString()));
    }
  }

  void _tipoDetailsFetched(
      FetchTipoDetails event, Emitter<TiposState> emit) async {
    try {
      final tipo = await tipoRepository.fetchTipoDetails(event.id);
      emit(TipoDetailsFetched(tipo, event.id));
      return;
    } on Exception catch (e) {
      emit(TipoFetchError((e.toString())));
    }
  }
}
