import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_easycar/bloc/concesionario_bloc/concesionario_bloc.dart';
import 'package:flutter_easycar/bloc/concesionario_bloc/concesionario_event.dart';
import 'package:flutter_easycar/bloc/concesionario_bloc/concesionario_state.dart';
import 'package:flutter_easycar/models/concesionario.dart';
import 'package:flutter_easycar/repository/concesionario_repository/concesionario_repository.dart';
import 'package:flutter_easycar/repository/concesionario_repository/concesionario_repository_impl.dart';
import 'package:flutter_easycar/screens/details_screen.dart';
import 'package:flutter_easycar/screens/vehiculos_concesionario_screen.dart';
import 'package:flutter_easycar/ui/error_page.dart';

class ConcesionariosScreen extends StatefulWidget {
  const ConcesionariosScreen({Key? key}) : super(key: key);

  @override
  _ConcesionariosScreenState createState() => _ConcesionariosScreenState();
}

class _ConcesionariosScreenState extends State<ConcesionariosScreen> {
  late ConcesionarioRepository concesionarioRepository;
  @override
  void initState() {
    super.initState();
    concesionarioRepository = ConcesionarioRepositoryImpl();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider<ConcesionariosBloc>(
      create: (context) {
        return ConcesionariosBloc(concesionarioRepository)
          ..add(const FetchConcesionario());
      },
      child: Scaffold(
          appBar: AppBar(
            title: Padding(
              padding: const EdgeInsets.only(left: 80.0),
              child: Image.asset(
                'assets/images/logo.png',
                width: 150,
                fit: BoxFit.cover,
              ),
            ),
            backgroundColor: Colors.grey.shade900,
          ),
          body: SingleChildScrollView(
            child: Column(
              children: <Widget>[
                _createConcesionario(context),
              ],
            ),
          )),
    );
  }
}

Widget _createConcesionario(BuildContext context) {
  return BlocBuilder<ConcesionariosBloc, ConcesionariosState>(
      builder: (context, state) {
    if (state is ConcesionariosInitial) {
      return const Center(child: CircularProgressIndicator());
    } else if (state is ConcesionarioFetchError) {
      return ErrorPage(
        mensaje: state.mensaje,
        retry: () {
          context.watch<ConcesionariosBloc>().add(const FetchConcesionario());
        },
      );
    } else if (state is ConcesionariosFetched) {
      return _createConcesionarioView(context, state.concesionarios);
    } else {
      return const Text('Not support');
    }
  });
}

Widget _createConcesionarioView(
    BuildContext context, List<ConcesionarioContent> concesionarios) {
  return Column(children: [
    SizedBox(
      height: 700,
      child: ListView.separated(
        itemBuilder: (BuildContext context, int index) {
          return _createConcesionarioViewItem(context, concesionarios[index]);
        },
        separatorBuilder: (context, index) => const VerticalDivider(
          color: Colors.transparent,
          width: 6.0,
        ),
        itemCount: concesionarios.length,
      ),
    ),
  ]);
}

Widget _createConcesionarioViewItem(
    BuildContext context, ConcesionarioContent concesionario) {
  return GridTile(
    child: InkWell(
      onTap: () {
        Navigator.push(
            context,
            MaterialPageRoute(
                builder: (context) => VehiculosConcesionarioScreen(
                      id: concesionario.id,
                    ),
                settings: RouteSettings(arguments: concesionario.id)));
      },
      child: Column(
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.only(top: 8.0),
            child: Text(
              concesionario.nombre,
              style: TextStyle(fontSize: 20),
            ),
          ),
          Text(
            concesionario.direccion,
            style: TextStyle(fontSize: 20),
          ),
          Padding(
            padding: const EdgeInsets.only(top: 8.0, bottom: 30),
            child: Container(
              child: Image.network(
                  concesionario.foto.replaceAll('localhost', '10.0.2.2'),
                  fit: BoxFit.cover,
                  height: 300),
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: const BorderRadius.only(
                    topLeft: Radius.circular(10),
                    topRight: Radius.circular(10),
                    bottomLeft: Radius.circular(10),
                    bottomRight: Radius.circular(10)),
                boxShadow: [
                  BoxShadow(
                    color: Colors.grey.withOpacity(0.6),
                    spreadRadius: 5,
                    blurRadius: 7,
                    offset: const Offset(0, 3), // changes position of shadow
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    ),
  );
}
