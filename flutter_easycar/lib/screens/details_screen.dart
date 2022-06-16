import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_bloc.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_event.dart';
import 'package:flutter_easycar/models/vehiculo.dart';
import 'package:flutter_easycar/repository/vehiculo_repository/vehiculo_repository.dart';
import 'package:flutter_easycar/repository/vehiculo_repository/vehiculo_repository_impl.dart';
import 'package:flutter_easycar/screens/search_screen.dart';
import 'package:intl/intl.dart';

class DetailsPage extends StatefulWidget {
  const DetailsPage({key}) : super(key: key);

  @override
  _DetailsPageState createState() => _DetailsPageState();
}

class _DetailsPageState extends State<DetailsPage> {
  late VehiculoRepository vehiculoRepository;

  @override
  void initState() {
    super.initState();
    vehiculoRepository = VehiculoRepositoryImpl();
  }

  @override
  Widget build(BuildContext context) {
    final vehiculo =
        ModalRoute.of(context)!.settings.arguments as VehiculoContent;
    return BlocProvider<VehiculosBloc>(
        create: (context) {
          return VehiculosBloc(vehiculoRepository)..add(const FetchVehiculo());
        },
        child: Scaffold(
            appBar: AppBar(
              title: Padding(
                padding: const EdgeInsets.only(left: 100.0),
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
                  _createVehiculoViewItem(context, vehiculo),
                ],
              ),
            )));
  }
}

Widget _createVehiculoViewItem(BuildContext context, VehiculoContent vehiculo) {
  var precio = (vehiculo.precio * 1000).toString();
  return GridTile(
      child: InkWell(
    onTap: () {},
    child: Column(
      children: <Widget>[
        Padding(
          padding: const EdgeInsets.only(top: 8.0),
          child: Container(
            child: Image.network(
              vehiculo.foto1.replaceAll('localhost', '10.0.2.2'),
              fit: BoxFit.cover,
              height: 300,
            ),
            decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.only(
                  topLeft: Radius.circular(10),
                  topRight: Radius.circular(10),
                  bottomLeft: Radius.circular(10),
                  bottomRight: Radius.circular(10)),
              boxShadow: [
                BoxShadow(
                  color: Colors.grey.withOpacity(0.6),
                  spreadRadius: 5,
                  blurRadius: 7,
                  offset: Offset(0, 3), // changes position of shadow
                ),
              ],
            ),
          ),
        ),
        Padding(
          padding: const EdgeInsets.only(top: 8.0),
          child: Row(
            children: [
              Padding(
                padding: const EdgeInsets.only(left: 16.0),
                child: Container(
                  child: Image.network(
                    vehiculo.foto2.replaceAll('localhost', '10.0.2.2'),
                    fit: BoxFit.cover,
                    height: 95,
                  ),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.only(
                        topLeft: Radius.circular(10),
                        topRight: Radius.circular(10),
                        bottomLeft: Radius.circular(10),
                        bottomRight: Radius.circular(10)),
                    boxShadow: [
                      BoxShadow(
                        color: Colors.grey.withOpacity(0.6),
                        spreadRadius: 5,
                        blurRadius: 7,
                        offset: Offset(0, 3), // changes position of shadow
                      ),
                    ],
                  ),
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(left: 10),
                child: Container(
                  child: Image.network(
                    vehiculo.foto3.replaceAll('localhost', '10.0.2.2'),
                    fit: BoxFit.cover,
                    height: 95,
                  ),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.only(
                        topLeft: Radius.circular(10),
                        topRight: Radius.circular(10),
                        bottomLeft: Radius.circular(10),
                        bottomRight: Radius.circular(10)),
                    boxShadow: [
                      BoxShadow(
                        color: Colors.grey.withOpacity(0.6),
                        spreadRadius: 5,
                        blurRadius: 7,
                        offset: Offset(0, 3), // changes position of shadow
                      ),
                    ],
                  ),
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(left: 10),
                child: Container(
                  child: Image.network(
                    vehiculo.foto4.replaceAll('localhost', '10.0.2.2'),
                    fit: BoxFit.cover,
                    height: 95,
                  ),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.only(
                        topLeft: Radius.circular(10),
                        topRight: Radius.circular(10),
                        bottomLeft: Radius.circular(10),
                        bottomRight: Radius.circular(10)),
                    boxShadow: [
                      BoxShadow(
                        color: Colors.grey.withOpacity(0.6),
                        spreadRadius: 5,
                        blurRadius: 7,
                        offset: Offset(0, 3), // changes position of shadow
                      ),
                    ],
                  ),
                ),
              ),
            ],
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
              child:
                  Text(vehiculo.modelo, style: const TextStyle(fontSize: 20)),
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
          ],
        ),
        Padding(
          padding: const EdgeInsets.only(left: 8.0, top: 5, bottom: 20),
          child: Row(
            children: [
              Text(
                vehiculo.fechaMatriculacion,
                style: TextStyle(color: Colors.grey),
              ),
              Container(
                  height: 10,
                  child: Row(children: const [
                    VerticalDivider(
                      color: Colors.grey,
                      thickness: 1, //thickness of divier line
                    ),
                  ])),
              Text(
                vehiculo.kilometraje,
                style: TextStyle(color: Colors.grey),
              ),
              Container(
                  height: 10,
                  child: Row(children: const [
                    VerticalDivider(
                      color: Colors.grey,
                      thickness: 1, //thickness of divier line
                    ),
                  ])),
              Text(
                vehiculo.potencia,
                style: TextStyle(color: Colors.grey),
              ),
              Container(
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
        const Divider(
          color: Colors.grey,
          height: 36,
          thickness: 2,
          indent: 20,
          endIndent: 20,
        ),
        Row(
          children: [
            const Padding(
              padding: EdgeInsets.only(left: 20.0),
              child: Text(
                'Precio al contado*',
                style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(left: 200.0, top: 10),
              child: Text(
                precio + "€",
                style:
                    const TextStyle(fontSize: 20, fontWeight: FontWeight.w600),
              ),
            )
          ],
        ),
        Padding(
          padding: const EdgeInsets.only(top: 50.0),
          child: Card(
            shape:
                RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
            color: Colors.grey[200],
            elevation: 10,
            child: SizedBox(
              width: 400,
              height: 250,
              child: Center(
                child: Column(
                  children: [
                    Row(
                      children: const [
                        Padding(
                          padding:
                              EdgeInsets.only(left: 135.0, top: 10, bottom: 20),
                          child: Text(
                            'A primera vista',
                            style: TextStyle(
                                fontSize: 20, fontWeight: FontWeight.w400),
                          ),
                        )
                      ],
                    ),
                    Row(
                      children: [
                        Column(
                          children: [
                            Padding(
                              padding: const EdgeInsets.only(left: 8.0),
                              child: Image.asset(
                                'assets/images/Icono1.PNG',
                                width: 30,
                                fit: BoxFit.cover,
                              ),
                            )
                          ],
                        ),
                        Column(
                          children: [
                            const Padding(
                              padding: EdgeInsets.only(left: 8.0),
                              child: Text("Matriculación:"),
                            ),
                            Text(vehiculo.fechaMatriculacion),
                          ],
                        ),
                        Column(
                          children: [
                            Padding(
                              padding: const EdgeInsets.only(left: 108.0),
                              child: Image.asset(
                                'assets/images/Icono2.PNG',
                                width: 33,
                                fit: BoxFit.cover,
                              ),
                            )
                          ],
                        ),
                        Column(
                          children: [
                            const Padding(
                              padding: EdgeInsets.only(left: 8.0),
                              child: Text("Próx. revisión:"),
                            ),
                            Text(vehiculo.fechaMatriculacion),
                          ],
                        ),
                      ],
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 20.0),
                      child: Row(
                        children: [
                          Column(
                            children: [
                              Padding(
                                padding: const EdgeInsets.only(left: 8.0),
                                child: Image.asset(
                                  'assets/images/Icono3.PNG',
                                  width: 30,
                                  fit: BoxFit.cover,
                                ),
                              )
                            ],
                          ),
                          Column(
                            children: [
                              const Padding(
                                padding: EdgeInsets.only(left: 8.0),
                                child: Text("Llantas:"),
                              ),
                              Text(vehiculo.llantas),
                            ],
                          ),
                          Column(
                            children: [
                              Padding(
                                padding: const EdgeInsets.only(left: 148.0),
                                child: Image.asset(
                                  'assets/images/Icono4.PNG',
                                  width: 33,
                                  fit: BoxFit.cover,
                                ),
                              )
                            ],
                          ),
                          Column(
                            children: [
                              const Padding(
                                padding: EdgeInsets.only(left: 8.0),
                                child: Text("Procedencia:"),
                              ),
                              Text(vehiculo.procedencia),
                            ],
                          ),
                        ],
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 20.0),
                      child: Row(
                        children: [
                          Column(
                            children: [
                              Padding(
                                padding: const EdgeInsets.only(left: 8.0),
                                child: Image.asset(
                                  'assets/images/Icono5.PNG',
                                  width: 30,
                                  fit: BoxFit.cover,
                                ),
                              )
                            ],
                          ),
                          Column(
                            children: [
                              const Padding(
                                padding: EdgeInsets.only(left: 8.0),
                                child: Text("Distribución:"),
                              ),
                              Text(vehiculo.distribucion),
                            ],
                          ),
                          Column(
                            children: [
                              Padding(
                                padding: const EdgeInsets.only(left: 120.0),
                                child: Image.asset(
                                  'assets/images/Icono6.PNG',
                                  width: 33,
                                  fit: BoxFit.cover,
                                ),
                              )
                            ],
                          ),
                          Column(
                            children: [
                              const Padding(
                                padding: EdgeInsets.only(left: 8.0),
                                child: Text("Tracción:"),
                              ),
                              Text(vehiculo.traccion),
                            ],
                          ),
                        ],
                      ),
                    )
                  ],
                ),
              ),
            ),
          ),
        ),
      ],
    ),
  ));
}

class ScreenArguments {
  final VehiculoContent vehiculo;

  ScreenArguments(this.vehiculo);
}
