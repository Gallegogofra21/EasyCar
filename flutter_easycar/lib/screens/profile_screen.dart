import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_easycar/bloc/user_bloc/user_bloc.dart';
import 'package:flutter_easycar/bloc/user_bloc/user_event.dart';
import 'package:flutter_easycar/bloc/user_bloc/user_state.dart';
import 'package:flutter_easycar/models/user.dart';
import 'package:flutter_easycar/repository/user_repository/user_repository.dart';
import 'package:flutter_easycar/repository/user_repository/user_repository_impl.dart';
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
          return UserBloc(userRepository)..add(FetchUserWithType());
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
          context.watch<UserBloc>().add(FetchUserWithType());
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
                  margin: const EdgeInsets.only(top: 12),
                  width: 100.0,
                  height: 100.0,
                  decoration: BoxDecoration(
                    shape: BoxShape.circle,
                    image: DecorationImage(
                        fit: BoxFit.cover,
                        image: NetworkImage(user.avatar
                            .toString()
                            .replaceFirst('localhost', '10.0.2.2'))),
                  ),
                ),
                Column(
                  children: [
                    Row(
                      children: [
                        Column(
                          children: [
                            TextButton(
                              onPressed: null,
                              child: Text(
                                'user.posts.length.toString(),',
                                style: const TextStyle(
                                    fontWeight: FontWeight.bold,
                                    color: Colors.black),
                              ),
                            ),
                            Text("posts"),
                          ],
                        ),
                        const SizedBox(
                          width: 10,
                        ),
                        Column(
                          children: [
                            TextButton(
                              onPressed: () {},
                              child: Text(
                                'user.followers.length.toString()',
                                style: const TextStyle(
                                    fontWeight: FontWeight.bold,
                                    color: Colors.black),
                              ),
                            ),
                            const Text(
                              "followers",
                            ),
                          ],
                        ),
                        const SizedBox(
                          width: 5,
                        ),
                        Column(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            TextButton(
                                onPressed: () {},
                                child: const Text("832",
                                    style: TextStyle(
                                        fontWeight: FontWeight.bold,
                                        color: Colors.black))),
                            const Text("following"),
                          ],
                        ),
                      ],
                    ),
                  ],
                ),
              ],
              mainAxisAlignment: MainAxisAlignment.spaceAround,
            ),
            Row(
              children: [
                Padding(
                  padding: const EdgeInsets.only(left: 8.0),
                  child: Text(user.username.toString()),
                ),
              ],
            ),
            Row(
              children: [
                Padding(
                  padding: const EdgeInsets.only(left: 8.0),
                  child: Text(
                    '${user.username}',
                    style: const TextStyle(color: Colors.grey),
                  ),
                )
              ],
            ),
            Container(
                height: 35,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(8),
                  border: Border.all(color: Colors.grey.shade200),
                ),
                width: 320,
                child: TextButton(
                    onPressed: () {},
                    child: const Text(
                      "Edit Profile",
                      style: TextStyle(color: Colors.black),
                    )))
          ],
        ),
        const Divider(
          height: 10,
        ),

        Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            IconButton(
                onPressed: () {}, icon: const Icon(Icons.table_chart_outlined)),
            IconButton(onPressed: () {}, icon: const Icon(Icons.person_search)),
          ],
        ),
        const SizedBox(
          width: 20,
        ),

        // Flexible(
        //   child: GridView.builder(
        //       gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
        //         crossAxisCount: 3,
        //       ),
        //       itemCount: user.posts.length,
        //       itemBuilder: (BuildContext context, int index) {
        //         return Card(
        //           color: Colors.white,
        //           child: Image(
        //                 image: NetworkImage(user.posts.elementAt(index).contenidoOriginal.toString().replaceFirst('localhost', '10.0.2.2')),
        //                 fit: BoxFit.cover,
        //               ));

        //       }),
        // ),

        const SizedBox(
          width: 20,
        ),
      ],
    ),
  );
}
