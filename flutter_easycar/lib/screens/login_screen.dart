import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_easycar/bloc/login_bloc/login_bloc.dart';
import 'package:flutter_easycar/models/auth/login_dto.dart';
import 'package:flutter_easycar/repository/auth_repository/auth_repository.dart';
import 'package:flutter_easycar/repository/auth_repository/auth_repositoryimpl.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'menu_screen.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  _LoginScreenState createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  late Future<SharedPreferences> _prefs;
  late AuthRepository authRepository;
  final _formKey = GlobalKey<FormState>();
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  @override
  void initState() {
    authRepository = AuthRepositoryImpl();
    _prefs = SharedPreferences.getInstance();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
        create: (context) {
          return LoginBloc(authRepository);
        },
        child: _createBody(context));
  }

  _createBody(BuildContext context) {
    return Scaffold(
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
      body: Center(
        child: Container(
            color: Colors.grey.shade400,
            padding: const EdgeInsets.all(20),
            child: BlocConsumer<LoginBloc, LoginState>(
                listenWhen: (context, state) {
              return state is LoginSuccessState || state is LoginErrorState;
            }, listener: (context, state) {
              if (state is LoginSuccessState) {
                _prefs.then((SharedPreferences prefs) {
                  prefs.setString('token', state.loginResponse.token);
                  print(prefs.getString('token'));
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => const MenuScreen()),
                  );
                });
              } else if (state is LoginErrorState) {
                _showSnackbar(context, state.message);
              }
            }, buildWhen: (context, state) {
              return state is LoginInitialState || state is LoginLoadingState;
            }, builder: (ctx, state) {
              if (state is LoginInitialState) {
                return buildForm(ctx);
              } else if (state is LoginLoadingState) {
                return const Center(child: CircularProgressIndicator());
              } else {
                return buildForm(ctx);
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

  Widget buildForm(BuildContext context) {
    return Form(
      key: _formKey,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Padding(
            padding: const EdgeInsets.only(left: 40, right: 40, bottom: 10),
            child: TextFormField(
              controller: emailController,
              decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  suffixIcon: Icon(Icons.email),
                  suffixIconColor: Colors.white,
                  hintText: 'Tel??fono, usuario o correo electr??nico',
                  labelText: 'Email',
                  focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(color: Colors.white))),
              onSaved: (String? value) {
                // This optional block of code can be used to run
                // code when the user saves the form.
              },
              validator: (String? value) {
                return (value == null || !value.contains('@'))
                    ? 'Do not use the @ char.'
                    : null;
              },
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(left: 40, right: 40),
            child: TextFormField(
              controller: passwordController,
              obscureText: true,
              decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  suffixIcon: Icon(Icons.vpn_key),
                  suffixIconColor: Colors.white,
                  hintText: 'Contrase??a',
                  focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(color: Colors.white))),
              onSaved: (String? value) {
                // This optional block of code can be used to run
                // code when the user saves the form.
              },
              validator: (value) {
                return (value == null || value.isEmpty)
                    ? 'Write a password'
                    : null;
              },
            ),
          ),
          Padding(
              padding: const EdgeInsets.all(8.0),
              child: SizedBox(
                width: 280,
                child: ElevatedButton(
                    style: ElevatedButton.styleFrom(primary: Colors.red),
                    onPressed: () {
                      if (_formKey.currentState!.validate()) {
                        final loginDto = LoginDto(
                            email: emailController.text,
                            password: passwordController.text);
                        BlocProvider.of<LoginBloc>(context)
                            .add(DoLoginEvent(loginDto));
                        ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(content: Text('Processing Data')));
                      }
                    },
                    child: Padding(
                      padding: EdgeInsets.only(left: 10),
                      child: SizedBox(
                        width: 300,
                        child: Text(
                          'Iniciar sesi??n'.toUpperCase(),
                          style: const TextStyle(color: Colors.white),
                          textAlign: TextAlign.center,
                        ),
                      ),
                    )),
              )),
          Row(
            children: <Widget>[
              const Padding(
                padding: EdgeInsets.only(left: 50),
                child: Text(
                  '??A??n no est??s registrado?',
                  style: TextStyle(fontSize: 12),
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(left: 0.0),
                child: TextButton(
                  onPressed: () {
                    Navigator.pushNamed(context, '/register');
                  },
                  child: const Text('Registrate ahora!'),
                ),
              )
            ],
          )
        ],
      ),
    );
  }
}
