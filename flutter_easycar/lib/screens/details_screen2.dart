import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_bloc.dart';
import 'package:flutter_easycar/bloc/vehiculo_bloc/vehiculo_event.dart';
import 'package:flutter_easycar/constants.dart';
import 'package:flutter_easycar/models/vehiculo.dart';
import 'package:flutter_easycar/repository/vehiculo_repository/vehiculo_repository.dart';
import 'package:flutter_easycar/repository/vehiculo_repository/vehiculo_repository_impl.dart';
import 'package:flutter_easycar/screens/search_screen.dart';
import 'package:intl/intl.dart';
import 'package:shared_preferences/shared_preferences.dart';

class DetailsPage2 extends StatefulWidget {
  final int id2;

  const DetailsPage2({key, required this.id2}) : super(key: key);

  @override
  _DetailsPageState2 createState() => _DetailsPageState2();
}

class _DetailsPageState2 extends State<DetailsPage2> {
  late Future<VehiculoContent> vehiculo;
  late VehiculoRepository vehiculoRepository;

  @override
  void initState() {
    super.initState();
    vehiculoRepository = VehiculoRepositoryImpl();
    vehiculo = fetchVehiculo(widget.id2);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
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
        body: Center(
            child: FutureBuilder<VehiculoContent>(
          future: vehiculo,
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              return Center(
                  child: GridTile(
                      child: InkWell(
                          onTap: () {},
                          child: Column(
                            children: <Widget>[
                              Padding(
                                padding: const EdgeInsets.only(top: 8.0),
                                child: Container(
                                  child: Image.network(
                                    snapshot.data!.foto1
                                        .replaceAll('localhost', '10.0.2.2'),
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
                                        offset: Offset(
                                            0, 3), // changes position of shadow
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
                                      padding:
                                          const EdgeInsets.only(left: 16.0),
                                      child: Container(
                                        child: Image.network(
                                          snapshot.data!.foto2.replaceAll(
                                              'localhost', '10.0.2.2'),
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
                                              color:
                                                  Colors.grey.withOpacity(0.6),
                                              spreadRadius: 5,
                                              blurRadius: 7,
                                              offset: Offset(0,
                                                  3), // changes position of shadow
                                            ),
                                          ],
                                        ),
                                      ),
                                    ),
                                    Padding(
                                      padding: const EdgeInsets.only(left: 10),
                                      child: Container(
                                        child: Image.network(
                                          snapshot.data!.foto3.replaceAll(
                                              'localhost', '10.0.2.2'),
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
                                              color:
                                                  Colors.grey.withOpacity(0.6),
                                              spreadRadius: 5,
                                              blurRadius: 7,
                                              offset: Offset(0,
                                                  3), // changes position of shadow
                                            ),
                                          ],
                                        ),
                                      ),
                                    ),
                                    Padding(
                                      padding: const EdgeInsets.only(left: 10),
                                      child: Container(
                                        child: Image.network(
                                          snapshot.data!.foto4.replaceAll(
                                              'localhost', '10.0.2.2'),
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
                                              color:
                                                  Colors.grey.withOpacity(0.6),
                                              spreadRadius: 5,
                                              blurRadius: 7,
                                              offset: Offset(0,
                                                  3), // changes position of shadow
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
                                    padding: const EdgeInsets.only(
                                        left: 8.0, top: 10),
                                    child: Text(
                                      snapshot.data!.nombreMarca,
                                      style: const TextStyle(fontSize: 20),
                                    ),
                                  ),
                                  Padding(
                                    padding: const EdgeInsets.only(
                                        left: 8.0, top: 10),
                                    child: Text(snapshot.data!.modelo,
                                        style: const TextStyle(fontSize: 20)),
                                  ),
                                ]),
                              ),
                              Row(
                                children: [
                                  Padding(
                                    padding: const EdgeInsets.only(left: 8.0),
                                    child: Text(snapshot.data!.version,
                                        style: const TextStyle(
                                            fontSize: 16,
                                            fontWeight: FontWeight.w500)),
                                  ),
                                ],
                              ),
                              Padding(
                                padding: const EdgeInsets.only(
                                    left: 8.0, top: 5, bottom: 20),
                                child: Row(
                                  children: [
                                    Text(
                                      snapshot.data!.fechaMatriculacion,
                                      style: TextStyle(color: Colors.grey),
                                    ),
                                    Container(
                                        height: 10,
                                        child: Row(children: const [
                                          VerticalDivider(
                                            color: Colors.grey,
                                            thickness:
                                                1, //thickness of divier line
                                          ),
                                        ])),
                                    Text(
                                      snapshot.data!.kilometraje,
                                      style: TextStyle(color: Colors.grey),
                                    ),
                                    Container(
                                        height: 10,
                                        child: Row(children: const [
                                          VerticalDivider(
                                            color: Colors.grey,
                                            thickness:
                                                1, //thickness of divier line
                                          ),
                                        ])),
                                    Text(
                                      snapshot.data!.potencia,
                                      style: TextStyle(color: Colors.grey),
                                    ),
                                    Container(
                                        height: 10,
                                        child: Row(children: const [
                                          VerticalDivider(
                                            color: Colors.grey,
                                            thickness:
                                                1, //thickness of divier line
                                          ),
                                        ])),
                                    Text(
                                      snapshot.data!.marchas,
                                      style:
                                          const TextStyle(color: Colors.grey),
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
                                      style: TextStyle(
                                          fontSize: 16,
                                          fontWeight: FontWeight.bold),
                                    ),
                                  ),
                                  Padding(
                                    padding: const EdgeInsets.only(
                                        left: 200.0, top: 10),
                                    child: Text(
                                      snapshot.data!.precio.toString() + "€",
                                      style: const TextStyle(
                                          fontSize: 20,
                                          fontWeight: FontWeight.w600),
                                    ),
                                  )
                                ],
                              ),
                              Padding(
                                padding: const EdgeInsets.only(top: 50.0),
                                child: Card(
                                  shape: RoundedRectangleBorder(
                                      borderRadius: BorderRadius.circular(20)),
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
                                                padding: EdgeInsets.only(
                                                    left: 135.0,
                                                    top: 10,
                                                    bottom: 20),
                                                child: Text(
                                                  'A primera vista',
                                                  style: TextStyle(
                                                      fontSize: 20,
                                                      fontWeight:
                                                          FontWeight.w400),
                                                ),
                                              )
                                            ],
                                          ),
                                          Row(
                                            children: [
                                              Column(
                                                children: [
                                                  Padding(
                                                    padding:
                                                        const EdgeInsets.only(
                                                            left: 8.0),
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
                                                    padding: EdgeInsets.only(
                                                        left: 8.0),
                                                    child:
                                                        Text("Matriculación:"),
                                                  ),
                                                  Text(snapshot.data!
                                                      .fechaMatriculacion),
                                                ],
                                              ),
                                              Column(
                                                children: [
                                                  Padding(
                                                    padding:
                                                        const EdgeInsets.only(
                                                            left: 108.0),
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
                                                    padding: EdgeInsets.only(
                                                        left: 8.0),
                                                    child:
                                                        Text("Próx. revisión:"),
                                                  ),
                                                  Text(snapshot.data!
                                                      .fechaMatriculacion),
                                                ],
                                              ),
                                            ],
                                          ),
                                          Padding(
                                            padding: const EdgeInsets.only(
                                                top: 20.0),
                                            child: Row(
                                              children: [
                                                Column(
                                                  children: [
                                                    Padding(
                                                      padding:
                                                          const EdgeInsets.only(
                                                              left: 8.0),
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
                                                      padding: EdgeInsets.only(
                                                          left: 8.0),
                                                      child: Text("Llantas:"),
                                                    ),
                                                    Text(
                                                        snapshot.data!.llantas),
                                                  ],
                                                ),
                                                Column(
                                                  children: [
                                                    Padding(
                                                      padding:
                                                          const EdgeInsets.only(
                                                              left: 148.0),
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
                                                      padding: EdgeInsets.only(
                                                          left: 8.0),
                                                      child:
                                                          Text("Procedencia:"),
                                                    ),
                                                    Text(snapshot
                                                        .data!.procedencia),
                                                  ],
                                                ),
                                              ],
                                            ),
                                          ),
                                          Padding(
                                            padding: const EdgeInsets.only(
                                                top: 20.0),
                                            child: Row(
                                              children: [
                                                Column(
                                                  children: [
                                                    Padding(
                                                      padding:
                                                          const EdgeInsets.only(
                                                              left: 8.0),
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
                                                      padding: EdgeInsets.only(
                                                          left: 8.0),
                                                      child:
                                                          Text("Distribución:"),
                                                    ),
                                                    Text(snapshot
                                                        .data!.distribucion),
                                                  ],
                                                ),
                                                Column(
                                                  children: [
                                                    Padding(
                                                      padding:
                                                          const EdgeInsets.only(
                                                              left: 120.0),
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
                                                      padding: EdgeInsets.only(
                                                          left: 8.0),
                                                      child: Text("Tracción:"),
                                                    ),
                                                    Text(snapshot
                                                        .data!.traccion),
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
                              Padding(
                                padding: const EdgeInsets.only(top: 28.0),
                                child: Card(
                                  shape: RoundedRectangleBorder(
                                      borderRadius: BorderRadius.circular(20)),
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
                                                padding: EdgeInsets.only(
                                                    left: 135.0,
                                                    top: 10,
                                                    bottom: 20),
                                                child: Text(
                                                  'A primera vista',
                                                  style: TextStyle(
                                                      fontSize: 20,
                                                      fontWeight:
                                                          FontWeight.w400),
                                                ),
                                              )
                                            ],
                                          ),
                                          Row(
                                            children: [
                                              Column(
                                                children: [
                                                  Padding(
                                                    padding:
                                                        const EdgeInsets.only(
                                                            left: 8.0),
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
                                                    padding: EdgeInsets.only(
                                                        left: 8.0),
                                                    child:
                                                        Text("Matriculación:"),
                                                  ),
                                                  Text(snapshot.data!
                                                      .fechaMatriculacion),
                                                ],
                                              ),
                                              Column(
                                                children: [
                                                  Padding(
                                                    padding:
                                                        const EdgeInsets.only(
                                                            left: 108.0),
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
                                                    padding: EdgeInsets.only(
                                                        left: 8.0),
                                                    child:
                                                        Text("Próx. revisión:"),
                                                  ),
                                                  Text(snapshot.data!
                                                      .fechaMatriculacion),
                                                ],
                                              ),
                                            ],
                                          ),
                                          Padding(
                                            padding: const EdgeInsets.only(
                                                top: 20.0),
                                            child: Row(
                                              children: [
                                                Column(
                                                  children: [
                                                    Padding(
                                                      padding:
                                                          const EdgeInsets.only(
                                                              left: 8.0),
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
                                                      padding: EdgeInsets.only(
                                                          left: 8.0),
                                                      child: Text("Llantas:"),
                                                    ),
                                                    Text(
                                                        snapshot.data!.llantas),
                                                  ],
                                                ),
                                                Column(
                                                  children: [
                                                    Padding(
                                                      padding:
                                                          const EdgeInsets.only(
                                                              left: 148.0),
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
                                                      padding: EdgeInsets.only(
                                                          left: 8.0),
                                                      child:
                                                          Text("Procedencia:"),
                                                    ),
                                                    Text(snapshot
                                                        .data!.procedencia),
                                                  ],
                                                ),
                                              ],
                                            ),
                                          ),
                                          Padding(
                                            padding: const EdgeInsets.only(
                                                top: 20.0),
                                            child: Row(
                                              children: [
                                                Column(
                                                  children: [
                                                    Padding(
                                                      padding:
                                                          const EdgeInsets.only(
                                                              left: 8.0),
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
                                                      padding: EdgeInsets.only(
                                                          left: 8.0),
                                                      child:
                                                          Text("Distribución:"),
                                                    ),
                                                    Text(snapshot
                                                        .data!.distribucion),
                                                  ],
                                                ),
                                                Column(
                                                  children: [
                                                    Padding(
                                                      padding:
                                                          const EdgeInsets.only(
                                                              left: 120.0),
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
                                                      padding: EdgeInsets.only(
                                                          left: 8.0),
                                                      child: Text("Tracción:"),
                                                    ),
                                                    Text(snapshot
                                                        .data!.traccion),
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
                              )
                            ],
                          ))));
            } else if (snapshot.hasError) {
              return Text('${snapshot.error}');
            }
            return const CircularProgressIndicator();
          },
        )));
  }
}

Future<VehiculoContent> fetchVehiculo(id) async {
  final prefs = await SharedPreferences.getInstance();
  String? token = prefs.getString('token');

  Map<String, String> headers = {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ${token}'
  };

  final response = await http.get(
      Uri.parse('${Constant.ApiBaseUrl}/vehiculo/${id}'),
      headers: headers);

  if (response.statusCode == 200) {
    return VehiculoContent.fromJson(json.decode(response.body));
  } else {
    throw Exception('Failed to load vehiculo');
  }
}
