import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_bloc.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_event.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_state.dart';
import 'package:flutter_easycar/models/vehiculo.dart';
import 'package:flutter_easycar/repository/vehiculo_repository/vehiculo_repository.dart';
import 'package:flutter_easycar/repository/vehiculo_repository/vehiculo_repository_impl.dart';
import 'package:flutter_easycar/screens/details_screen.dart';
import 'package:flutter_easycar/ui/error_page.dart';

class SearchScreen extends StatefulWidget {
  const SearchScreen({Key? key}) : super(key: key);

  @override
  _SearchScreenState createState() => _SearchScreenState();
}

class _SearchScreenState extends State<SearchScreen> {
  late VehiculoRepository vehiculoRepository;
  @override
  void initState() {
    super.initState();
    vehiculoRepository = VehiculoRepositoryImpl();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider<VehiculosBloc>(
      create: (context) {
        return VehiculosBloc(vehiculoRepository)..add(const FetchVehiculo());
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
                _createVehiculo(context),
              ],
            ),
          )),
    );
  }
}

Widget _createVehiculo(BuildContext context) {
  return BlocBuilder<VehiculosBloc, VehiculosState>(builder: (context, state) {
    if (state is VehiculosInitial) {
      return const Center(child: CircularProgressIndicator());
    } else if (state is VehiculoFetchError) {
      return ErrorPage(
        mensaje: state.mensaje,
        retry: () {
          context.watch<VehiculosBloc>().add(const FetchVehiculo());
        },
      );
    } else if (state is VehiculosFetched) {
      return _createVehiculoView(context, state.vehiculos);
    } else {
      return const Text('Not support');
    }
  });
}

Widget _createVehiculoView(
    BuildContext context, List<VehiculoContent> vehiculos) {
  return Column(children: [
    SizedBox(
      height: 670,
      child: ListView.separated(
        itemBuilder: (BuildContext context, int index) {
          return _createVehiculoViewItem(context, vehiculos[index]);
        },
        separatorBuilder: (context, index) => const VerticalDivider(
          color: Colors.transparent,
          width: 6.0,
        ),
        itemCount: vehiculos.length,
      ),
    ),
  ]);
}

Widget _createVehiculoViewItem(BuildContext context, VehiculoContent vehiculo) {
  var precio = (vehiculo.precio * 1000).toString();

  return GridTile(
      child: InkWell(
          onTap: () {
            Navigator.push(
                context,
                MaterialPageRoute(
                    builder: (context) => DetailsPage(),
                    settings: RouteSettings(arguments: vehiculo)));
          },
          child: Column(
            children: <Widget>[
              Padding(
                padding: const EdgeInsets.only(top: 8.0),
                child: Container(
                  child: Image.network(
                      vehiculo.foto1.replaceAll('localhost', '10.0.2.2'),
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
                        offset:
                            const Offset(0, 3), // changes position of shadow
                      ),
                    ],
                  ),
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(top: 8.0),
                child: Row(children: [
                  Padding(
                    padding: const EdgeInsets.only(left: 8.0, top: 10),
                    child: Text(
                      vehiculo.nombreMarca,
                      style: const TextStyle(fontSize: 20),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(left: 8.0, top: 10),
                    child: Text(vehiculo.modelo,
                        style: const TextStyle(fontSize: 20)),
                  ),
                ]),
              ),
              Row(
                children: [
                  Padding(
                    padding: const EdgeInsets.only(left: 8.0),
                    child: Text(vehiculo.version,
                        style: const TextStyle(
                            fontSize: 16, fontWeight: FontWeight.w500)),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(left: 110.0),
                    child: Text(
                      precio + "€",
                      style: const TextStyle(fontSize: 18),
                    ),
                  )
                ],
              ),
              Padding(
                padding: const EdgeInsets.only(left: 8.0, top: 5, bottom: 20),
                child: Row(
                  children: [
                    Text(
                      vehiculo.fechaMatriculacion,
                      style: const TextStyle(color: Colors.grey),
                    ),
                    SizedBox(
                        height: 10,
                        child: Row(children: const [
                          VerticalDivider(
                            color: Colors.grey,
                            thickness: 1, //thickness of divier line
                          ),
                        ])),
                    Text(
                      vehiculo.kilometraje,
                      style: const TextStyle(color: Colors.grey),
                    ),
                    SizedBox(
                        height: 10,
                        child: Row(children: const [
                          VerticalDivider(
                            color: Colors.grey,
                            thickness: 1, //thickness of divier line
                          ),
                        ])),
                    Text(
                      vehiculo.potencia,
                      style: const TextStyle(color: Colors.grey),
                    ),
                    SizedBox(
                        height: 10,
                        child: Row(children: const [
                          VerticalDivider(
                            color: Colors.grey,
                            thickness: 1, //thickness of divier line
                          ),
                        ])),
                    Text(
                      vehiculo.marchas,
                      style: const TextStyle(color: Colors.grey),
                    ),
                  ],
                ),
              ),
            ],
          )));
}
