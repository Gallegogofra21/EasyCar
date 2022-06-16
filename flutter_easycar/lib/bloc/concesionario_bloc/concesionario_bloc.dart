import 'package:bloc/bloc.dart';
import 'package:flutter_easycar/bloc/concesionario_bloc/concesionario_event.dart';
import 'package:flutter_easycar/bloc/concesionario_bloc/concesionario_state.dart';
import 'package:flutter_easycar/repository/concesionario_repository/concesionario_repository.dart';

class ConcesionariosBloc extends Bloc<ConcesionariosEvent, ConcesionariosState> {
  final ConcesionarioRepository concesionarioRepository;

  ConcesionariosBloc(this.concesionarioRepository) : super(ConcesionariosInitial()) {
    on<FetchConcesionario>(_concesionariosFetched);
  }

  void _concesionariosFetched(FetchConcesionario event, Emitter<ConcesionariosState> emit) async {
    try {
      final concesionarios = await concesionarioRepository.fetchConcesionarios();
      emit(ConcesionariosFetched(concesionarios));
      return;
    } on Exception catch (e) {
      emit(ConcesionarioFetchError(e.toString()));
    }
  }
}