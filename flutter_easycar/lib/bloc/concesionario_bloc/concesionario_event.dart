import 'package:equatable/equatable.dart';

abstract class ConcesionariosEvent extends Equatable {
  const ConcesionariosEvent();

  @override 
  List<Object> get props => [];
}

class FetchConcesionario extends ConcesionariosEvent {
  const FetchConcesionario();

  @override 
  List<Object> get props => [];
}