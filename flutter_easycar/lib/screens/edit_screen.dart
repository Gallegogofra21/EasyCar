import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_easycar/bloc/edit_bloc/edit_bloc.dart';
import 'package:flutter_easycar/bloc/edit_bloc/edit_event.dart';
import 'package:flutter_easycar/bloc/edit_bloc/edit_state.dart';
import 'package:flutter_easycar/bloc/image_pick_bloc/image_pick_bloc.dart';
import 'package:flutter_easycar/bloc/image_pick_bloc/image_pick_event.dart';
import 'package:flutter_easycar/bloc/image_pick_bloc/image_pick_state.dart';
import 'package:flutter_easycar/models/register_dto.dart';
import 'package:flutter_easycar/models/user.dart';
import 'package:flutter_easycar/repository/auth_repository/auth_repository.dart';
import 'package:flutter_easycar/repository/auth_repository/auth_repositoryimpl.dart';
import 'package:image_picker/image_picker.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:intl/intl.dart';

import 'package:date_field/date_field.dart';

import 'package:file_picker/file_picker.dart';

import 'login_screen.dart';

class EditScreen extends StatefulWidget {
  const EditScreen({Key? key}) : super(key: key);

  @override
  _EditScreenState createState() => _EditScreenState();
}

class _EditScreenState extends State<EditScreen> {
  String imageSelect = "No has seleccionado ninguna imagen";
  FilePickerResult? result;
  PlatformFile? file;

  String date = "";
  DateTime selectedDate = DateTime.now();

  late AuthRepository authRepository;
  final _formKey = GlobalKey<FormState>();
  TextEditingController username = TextEditingController();
  TextEditingController name = TextEditingController();
  TextEditingController apellidos = TextEditingController();
  TextEditingController telefono = TextEditingController();
  TextEditingController email = TextEditingController();
  TextEditingController fechaNacimiento = TextEditingController();
  TextEditingController password2 = TextEditingController();
  TextEditingController password = TextEditingController();
  late Future<SharedPreferences> _prefs;
  final String uploadUrl = 'http://10.0.2.2:8080/auth/register';
  String path = "";
  bool _passwordVisible = false;
  bool _password2Visible = false;
  bool isPublic = true;

  @override
  void initState() {
    authRepository = AuthRepositoryImpl();
    _prefs = SharedPreferences.getInstance();
    _passwordVisible = false;
    _password2Visible = false;
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      backgroundColor: Colors.grey.shade800,
      appBar: AppBar(
        title: Padding(
          padding: const EdgeInsets.only(left: 50.0),
          child: Image.asset(
            'assets/images/logo.png',
            width: 150,
            fit: BoxFit.cover,
          ),
        ),
        backgroundColor: Colors.grey.shade900,
      ),
      body: MultiBlocProvider(
        providers: [
          BlocProvider(
            create: (context) {
              return ImagePickBlocBloc();
            },
          ),
          BlocProvider(
            create: (context) {
              return EditBloc(authRepository);
            },
          ),
        ],
        child: _createBody(context),
      ),
    );
  }

  _createBody(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Container(
            color: Colors.grey.shade400,
            padding: const EdgeInsets.all(20),
            child: BlocBuilder<EditBloc, EditState>(builder: (ctx, state) {
              if (state is EditInitial) {
                return _edit(ctx);
              } else if (state is EditLoading) {
                return const Center(child: CircularProgressIndicator());
              } else {
                return _edit(ctx);
              }
            })),
      ),
    );
  }

  void _showSnackbar(BuildContext context, String message) {
    final snackBar = SnackBar(
      content: Text(message),
    );
    ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }

  _edit(BuildContext context) {
    return SingleChildScrollView(
      child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
        Column(
          crossAxisAlignment: CrossAxisAlignment.start,
        ),
        const SizedBox(
          height: 24,
        ),
        Form(
          key: _formKey,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Padding(
                  padding: const EdgeInsets.only(bottom: 10.0),
                  child: TextFormField(
                    controller: username,
                    decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        hintText: 'Username',
                        labelText: 'Nombre de usuario',
                        focusedBorder: UnderlineInputBorder(
                            borderSide: BorderSide(color: Colors.white))),
                    onSaved: (String? value) {},
                    validator: (String? value) {
                      return (value == null || value.isEmpty)
                          ? 'El campo está vacío.'
                          : null;
                    },
                  )),
              Padding(
                  padding: const EdgeInsets.only(bottom: 10.0),
                  child: TextFormField(
                    controller: name,
                    decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        hintText: 'Name',
                        labelText: 'Nombre',
                        focusedBorder: UnderlineInputBorder(
                            borderSide: BorderSide(color: Colors.white))),
                    onSaved: (String? value) {},
                    validator: (String? value) {
                      return (value == null || value.isEmpty)
                          ? 'El campo está vacío.'
                          : null;
                    },
                  )),
              Padding(
                  padding: const EdgeInsets.only(bottom: 10.0),
                  child: TextFormField(
                    controller: apellidos,
                    decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        hintText: 'Apellidos',
                        labelText: 'Apellidos',
                        focusedBorder: UnderlineInputBorder(
                            borderSide: BorderSide(color: Colors.white))),
                    onSaved: (String? value) {},
                    validator: (String? value) {
                      return (value == null || value.isEmpty)
                          ? 'El campo está vacío.'
                          : null;
                    },
                  )),
              Padding(
                  padding: const EdgeInsets.only(bottom: 10.0),
                  child: TextFormField(
                    controller: telefono,
                    decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        hintText: 'Telefono',
                        labelText: 'Telefono',
                        focusedBorder: UnderlineInputBorder(
                            borderSide: BorderSide(color: Colors.white))),
                    onSaved: (String? value) {},
                    validator: (String? value) {
                      return (value == null || value.isEmpty)
                          ? 'El campo está vacío.'
                          : null;
                    },
                  )),
              Padding(
                padding: const EdgeInsets.only(bottom: 10.0),
                child: DateTimeFormField(
                  initialDate: DateTime(2001, 9, 7),
                  firstDate: DateTime.utc(1900),
                  lastDate: DateTime.now(),
                  decoration: const InputDecoration(
                    hintStyle: TextStyle(color: Colors.black45),
                    errorStyle: TextStyle(color: Colors.redAccent),
                    border: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.white),
                    ),
                    suffixIcon: Icon(Icons.event_note),
                    labelText: 'Select Birth Day',
                  ),
                  mode: DateTimeFieldPickerMode.date,
                  autovalidateMode: AutovalidateMode.always,
                  validator: (e) =>
                      (e?.day ?? 0) == 1 ? 'Please not the first day' : null,
                  onDateSelected: (DateTime value) {
                    selectedDate = value;
                    print(value);
                  },
                ),
              ),
              Padding(
                  padding: const EdgeInsets.only(bottom: 10.0),
                  child: TextFormField(
                    controller: email,
                    decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        suffixIcon: Icon(Icons.email),
                        suffixIconColor: Colors.white,
                        hintText: 'Email',
                        labelText: 'Email',
                        focusedBorder: UnderlineInputBorder(
                            borderSide: BorderSide(color: Colors.white))),
                    onSaved: (String? value) {},
                    validator: (String? value) {
                      return (value == null || !value.contains('@'))
                          ? 'Introduzca un correo válido.'
                          : null;
                    },
                  )),
              Column(
                children: [
                  Padding(
                    padding: const EdgeInsets.only(bottom: 10.0),
                    child: TextFormField(
                      obscureText: !_passwordVisible,
                      controller: password,
                      decoration: InputDecoration(
                        hintText: 'Password',
                        suffixIcon: IconButton(
                          icon: Icon(
                            _passwordVisible
                                ? Icons.visibility
                                : Icons.visibility_off,
                          ),
                          onPressed: () {
                            setState(() {
                              _passwordVisible = !_passwordVisible;
                            });
                          },
                        ),
                        border: const OutlineInputBorder(
                          borderSide: BorderSide(color: Colors.white),
                        ),
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(bottom: 10.0),
                    child: TextFormField(
                      obscureText: !_password2Visible,
                      controller: password2,
                      decoration: InputDecoration(
                          hintText: 'Confirm Password',
                          border: const OutlineInputBorder(
                            borderSide: BorderSide(color: Colors.white),
                          ),
                          suffixIcon: IconButton(
                            icon: Icon(
                              _password2Visible
                                  ? Icons.visibility
                                  : Icons.visibility_off,
                            ),
                            onPressed: () {
                              setState(() {
                                _password2Visible = !_password2Visible;
                              });
                            },
                          )),
                    ),
                  ),
                ],
              ),
              Padding(
                padding: const EdgeInsets.only(bottom: 10.0),
                child: BlocConsumer<ImagePickBlocBloc, ImagePickBlocState>(
                    listenWhen: (context, state) {
                      return state is ImageSelectedSuccessState;
                    },
                    listener: (context, state) {},
                    buildWhen: (context, state) {
                      return state is ImagePickBlocInitial ||
                          state is ImageSelectedSuccessState;
                    },
                    builder: (context, state) {
                      if (state is ImageSelectedSuccessState) {
                        path = state.pickedFile.path;
                        print('PATH ${state.pickedFile.path}');
                        return Column(children: [
                          CircleAvatar(
                            backgroundImage: FileImage(File(path)),
                            radius: 50,
                          ),
                        ]);
                      }
                      return Center(
                          child: ElevatedButton(
                              style: ElevatedButton.styleFrom(
                                  primary: Colors.red.shade300),
                              onPressed: () {
                                BlocProvider.of<ImagePickBlocBloc>(context).add(
                                    const SelectImageEvent(
                                        ImageSource.gallery));
                              },
                              child: const Text('Seleccionar avatar')));
                    }),
              )
            ],
          ),
        ),
        Center(
          child: ElevatedButton(
            style: ElevatedButton.styleFrom(
                fixedSize: const Size(240, 50), primary: Colors.red),
            onPressed: () async {
              SharedPreferences prefs = await SharedPreferences.getInstance();
              if (_formKey.currentState!.validate()) {
                final loginDto = RegisterDto(
                    username: username.text,
                    nombre: name.text,
                    apellidos: apellidos.text,
                    telefono: telefono.text,
                    fechaNacimiento:
                        DateFormat("yyyy-MM-dd").format(selectedDate),
                    email: email.text,
                    password2: password2.text,
                    password: password.text);

                BlocProvider.of<EditBloc>(context)
                    .add(DoEditEvent(loginDto, path));
              }
              prefs.setString('username', username.text);
              prefs.setString('name', name.text);
              prefs.setString('apellidos', apellidos.text);
              prefs.setString('telefono', telefono.text);
              prefs.setString('email', email.text);
              prefs.setString('fechaNacimiento',
                  DateFormat("yyyy-MM-dd").format(selectedDate));
              prefs.setString('password', password.text);
              prefs.setString('password2', password2.text);
              print(path);

              Navigator.pushNamed(context, '/');
            },
            child: const Text('Editar'),
          ),
        ),
      ]),
    );
  }
}
