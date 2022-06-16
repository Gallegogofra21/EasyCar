import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_easycar/bloc/user_bloc/user_bloc.dart';
import 'package:flutter_easycar/bloc/user_bloc/user_event.dart';
import 'package:flutter_easycar/bloc/user_bloc/user_state.dart';
import 'package:flutter_easycar/models/user.dart';
import 'package:flutter_easycar/repository/user_repository/user_repository.dart';
import 'package:flutter_easycar/repository/user_repository/user_repository_impl.dart';
import 'package:flutter_easycar/screens/edit_screen.dart';
import 'package:flutter_easycar/ui/error_page.dart';

class ProfileScreen extends StatefulWidget {
  const ProfileScreen({Key? key}) : super(key: key);

  @override
  _ProfileScreenState createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreen> {
  late UserRepository userRepository;
  @override
  void initState() {
    super.initState();
    userRepository = UserRepositoryImpl();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider<UserBloc>(
        create: (context) {
          return UserBloc(userRepository)..add(const FetchUserWithType());
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
            body: _createProfile(context)));
  }
}

Widget _createProfile(BuildContext context) {
  return BlocBuilder<UserBloc, UserState>(builder: (context, state) {
    if (state is UserWithPostInitial) {
      return const Center(child: CircularProgressIndicator());
    } else if (state is UserFetchedError) {
      return ErrorPage(
        mensaje: state.message,
        retry: () {
          context.watch<UserBloc>().add(const FetchUserWithType());
        },
      );
    } else if (state is UsersFetched) {
      return _profile(context, state.users);
    } else {
      return const Text('Not support');
    }
  });
}

Widget _profile(BuildContext context, User user) {
  return SafeArea(
    child: Column(
      children: [
        Column(
          children: [
            Row(
              children: [
                Container(
                  margin: const EdgeInsets.only(
                    top: 20,
                  ),
                  width: 150.0,
                  height: 150.0,
                  decoration: BoxDecoration(
                    shape: BoxShape.circle,
                    image: DecorationImage(
                        fit: BoxFit.cover,
                        image: NetworkImage(
                            user.avatar.replaceFirst('localhost', '10.0.2.2'))),
                  ),
                ),
              ],
              mainAxisAlignment: MainAxisAlignment.spaceAround,
            ),
            Padding(
              padding: const EdgeInsets.only(top: 10.0),
              child: Container(
                  height: 35,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(8),
                    border: Border.all(color: Colors.grey.shade200),
                  ),
                  width: 320,
                  child: TextButton(
                      onPressed: () {
                        Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: ((context) => EditScreen())));
                      },
                      child: const Text(
                        "Edit Profile",
                        style: TextStyle(color: Colors.black),
                      ))),
            )
          ],
        ),
        const Padding(
          padding: EdgeInsets.only(top: 8.0),
          child: Divider(
            height: 10,
          ),
        ),
        Column(
          children: [
            const Padding(
              padding: EdgeInsets.only(right: 300.0, top: 15, bottom: 15),
              child: Text('Correo electrónico',
                  textAlign: TextAlign.left,
                  style: TextStyle(color: Colors.grey, fontSize: 16)),
            ),
            Padding(
              padding: const EdgeInsets.only(right: 285.0),
              child: Text(
                user.email,
                style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
              ),
            ),
            const Padding(
              padding: EdgeInsets.only(right: 300.0, top: 20),
              child: Text('Nombre de usuario',
                  textAlign: TextAlign.left,
                  style: TextStyle(color: Colors.grey, fontSize: 16)),
            ),
            Padding(
              padding: const EdgeInsets.only(right: 285.0),
              child: Text(
                user.username,
                style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
              ),
            ),
            const Padding(
              padding: EdgeInsets.only(right: 370.0, top: 20),
              child: Text('Nombre',
                  textAlign: TextAlign.left,
                  style: TextStyle(color: Colors.grey, fontSize: 16)),
            ),
            Padding(
              padding: const EdgeInsets.only(right: 285.0),
              child: Text(user.nombre,
                  style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16)),
            ),
            const Padding(
              padding: EdgeInsets.only(right: 365.0, top: 20),
              child: Text('Apellidos',
                  textAlign: TextAlign.left,
                  style: TextStyle(color: Colors.grey, fontSize: 16)),
            ),
            Padding(
              padding: const EdgeInsets.only(right: 285.0),
              child: Text(user.apellidos,
                  style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16)),
            ),
            const Padding(
              padding: EdgeInsets.only(right: 370.0, top: 20),
              child: Text('Teléfono',
                  textAlign: TextAlign.left,
                  style: TextStyle(color: Colors.grey, fontSize: 16)),
            ),
            Padding(
              padding: const EdgeInsets.only(right: 285.0),
              child: Text(user.telefono,
                  style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16)),
            ),
          ],
        ),
      ],
    ),
  );
}
